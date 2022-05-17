package ba.unsa.etf.onlinepharmacy.Service;


import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Model.PatientWrapper;
import ba.unsa.etf.onlinepharmacy.Model.Role;
import ba.unsa.etf.onlinepharmacy.Model.RoleName;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Repository.RoleRepository;
import ba.unsa.etf.onlinepharmacy.Requests.LoginRequest;
import ba.unsa.etf.onlinepharmacy.Requests.RegisterRequest;
import ba.unsa.etf.onlinepharmacy.Requests.updatePatientRequest;
import ba.unsa.etf.onlinepharmacy.Response.LoginResponse;
import ba.unsa.etf.onlinepharmacy.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class HomeService {

    @Autowired
    private final PatientRepository patientRepository;

    private final RoleRepository roleRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest registerRequest) {
        Optional<Patient> patient = patientRepository.findByUsername(registerRequest.getUsername());
        if (patient.isPresent())
            return "User already exists with this username.";

        Optional<Patient> patient1 = Optional.ofNullable(patientRepository.findByEmail(registerRequest.getEmail()));
        if (patient1.isPresent())
            return "User already exists with this email.";

        Patient e = new Patient(
                registerRequest.getGender(),
                registerRequest.getName(),
                registerRequest.getAddress(),
                registerRequest.getPhoneNumber(),
                registerRequest.getHealthCard(),
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                passwordEncoder.encode(registerRequest.getPassword()));

        if (registerRequest.getRoleName().equals(RoleName.ROLE_ADMIN)) {
            List<Role> roles = Collections.singletonList(roleRepository.findByRolename(RoleName.ROLE_ADMIN));
            e.setRoles(new HashSet<>(roles));

        } else {
            List<Role> roles = Collections.singletonList(roleRepository.findByRolename(RoleName.ROLE_PATIENT));
            e.setRoles(new HashSet<>(roles));
        }
        patientRepository.save(e);
        return "Registration is completed.";
    }

    public LoginResponse authentication(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        Patient emp = getPatientByUsername(loginRequest.getUsername());
        ArrayList<String> roles = new ArrayList<>();
        for (Role role : emp.getRoles()) roles.add(role.getRolename().name());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginResponse(jwtTokenProvider.generateToken(authentication, new PatientWrapper(emp.getId().toString(), emp.getName(), emp.getGender(),
                emp.getHealthCard(), emp.getAddress(), emp.getEmail(), emp.getPhoneNumber(), emp.getUsername(),roles)));
    }

    public Patient getPatientByUsername(String username){
        String errorMessage = "User with username " + username + " does not exist.";
        return patientRepository
                .findByUsername(username)
                .orElseThrow(null);
    }

    public String updatePatient(updatePatientRequest patientUpdate) {
        Patient patient = patientRepository.findById(Math.toIntExact(patientUpdate.getId())).orElseThrow(null);
        patient.setUsername(patientUpdate.getUsername());
        patient.setPassword(patientUpdate.getPassword());
        patient.setEmail(patientUpdate.getEmail());
        patient.setName(patientUpdate.getName());
        patient.setAddress(patientUpdate.getAddress());
        patient.setHealthCard(patientUpdate.getHealthCard());
        patient.setPhoneNumber(patientUpdate.getPhoneNumber());
        patient.setGender(patientUpdate.getGender());
        patientRepository.save(patient);
        return "Successfully updated profile!";
    }


}
