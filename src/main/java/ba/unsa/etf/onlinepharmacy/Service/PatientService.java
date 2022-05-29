package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void changeName(int id,String ime){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setName(ime);
            patientRepository.save(patient);
        }
    }

    public void changeEmail(int id,String email){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setEmail(email);
            patientRepository.save(patient);
        }
    }

    public void changeAddress(int id,String adresa){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setAddress(adresa);
            patientRepository.save(patient);
        }
    }

    public void changePhoneNumber(int id,String brojTelefona){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setPhoneNumber(brojTelefona);
            patientRepository.save(patient);
        }
    }

    public void changeHealthCard(int id,String kartica){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setHealthCard(kartica);
            patientRepository.save(patient);
        }
    }

    public void changePassword(int id,String password){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setPassword(password);
            patientRepository.save(patient);
        }
    }

    public void changeUsername(int id,String username){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setUsername(username);
            patientRepository.save(patient);
        }
    }






}
