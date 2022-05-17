package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id){
        Patient p=patientRepository.findById(id).orElse(null);
        if (p==null){
            return null;
        }
         return p;

    }

    public void addPatient(addUserRequest user) {
        Patient patient = new Patient();
        patient.setGender(user.getGender());
        patient.setAddress(user.getAddress());
        patient.setHealthCard(user.getHealthCard());
        patient.setPhoneNumber(user.getPhoneNumber());
        patient.setName(user.getName());
        patient.setEmail(user.getEmail());
        patient.setPassword(user.getPassword());
        patient.setUsername(user.getUsername());
        patientRepository.save(patient);
    }

    public void deletePatient(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent())
            patientRepository.delete(patient.get());
    }


}
