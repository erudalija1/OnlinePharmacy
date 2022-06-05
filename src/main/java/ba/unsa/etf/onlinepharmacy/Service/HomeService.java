package ba.unsa.etf.onlinepharmacy.Service;


import ba.unsa.etf.onlinepharmacy.Model.*;
import ba.unsa.etf.onlinepharmacy.Repository.EmployeeRepository;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Repository.RoleRepository;
import ba.unsa.etf.onlinepharmacy.Requests.LoginRequest;
import ba.unsa.etf.onlinepharmacy.Requests.RegisterAdminRequest;
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

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class HomeService {

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final EmployeeRepository employeeRepository;

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
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getUsername());
        e.setTimesOrdered(0);
        e.setDiscount(false);
        e.setRegistradionDate(LocalDate.now());
        e.setRole(2);
       /* if (registerRequest.getRoleName().equals(RoleName.ROLE_ADMIN)) {
            List<Role> roles = Collections.singletonList(roleRepository.findByRolename(RoleName.ROLE_ADMIN));
            e.setRoles(new HashSet<>(roles));

        } else {
            List<Role> roles = Collections.singletonList(roleRepository.findByRolename(RoleName.ROLE_PATIENT));
            e.setRoles(new HashSet<>(roles));
        }**/

        List<Role> roles = Collections.singletonList(roleRepository.findByRolename(RoleName.ROLE_PATIENT));
        e.setRoles(new HashSet<>(roles));
        patientRepository.save(e);
        return "Registration is completed.";
    }

    public String registerAdmin(RegisterAdminRequest registerAdminRequest) {
        Optional<Patient> patient = patientRepository.findByUsername(registerAdminRequest.getUsername());
        if (patient.isPresent())
            return "User already exists with this username.";

        Optional<Patient> patient1 = Optional.ofNullable(patientRepository.findByEmail(registerAdminRequest.getEmail()));
        if (patient1.isPresent())
            return "User already exists with this email.";

        Patient admin=new Patient();
        admin.setRegistradionDate(LocalDate.now());
        admin.setUsername(registerAdminRequest.getUsername());
        admin.setAddress(null);
        admin.setPassword(passwordEncoder.encode(registerAdminRequest.getPassword()));
        admin.setDiscount(false);
        admin.setHealthCard(null);
        admin.setEmail(registerAdminRequest.getEmail());
        admin.setName(registerAdminRequest.getName());
        admin.setPhoneNumber(registerAdminRequest.getPhoneNumber());
        admin.setGender(null);
        admin.setTimesOrdered(0);
        admin.setRole(1);


            List<Role> roles = Collections.singletonList(roleRepository.findByRolename(RoleName.ROLE_ADMIN));
            admin.setRoles(new HashSet<>(roles));
            patientRepository.save(admin);
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

        return new LoginResponse(jwtTokenProvider.generateToken(authentication, new PatientWrapper(emp.getId().toString(),emp.getEmail(), emp.getUsername(),roles)));
    }

    public LoginResponse authenticationAdmin(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
            Patient emp=getAdminByUsername(loginRequest.getUsername());
            ArrayList<String> roles = new ArrayList<>();
            for (Role role : emp.getRoles()) roles.add(role.getRolename().name());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new LoginResponse(jwtTokenProvider.generateToken(authentication, new PatientWrapper(emp.getId().toString(),emp.getEmail(), emp.getUsername(),roles)));

    }

    public Patient getPatientByUsername(String username){
        String errorMessage = "User with username " + username + " does not exist.";
        return patientRepository
                .findByUsername(username)
                .orElseThrow(null);
    }

    public Patient getAdminByUsername(String username){
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

    public List<Patient> getAllAdmins(){
        List<Patient> patient=patientRepository.findAll();
        List<Patient> admins=new ArrayList<>();
        for (Patient p:patient){
          if(p.getRole()==1){
              admins.add(p);
          }
            }

        return admins;
    }


}
