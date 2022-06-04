package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        patient.setDiscount(false);
        patient.setRegistradionDate(LocalDate.now());
        patientRepository.save(patient);
    }

    public void deletePatient(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent())
            patientRepository.delete(patient.get());
    }

    public void changeName(int id,updatePatientName name){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setName(name.getUsername());
            patientRepository.save(patient);
        }
    }

    public void changeEmail(int id, updatePatientEmail email){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setEmail(email.getEmail());
            patientRepository.save(patient);
        }
    }

    public void changeAddress(int id, updatePatientAddress updateAddress){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setAddress(updateAddress.getAddress());
            patientRepository.save(patient);
        }
    }

    public void changePhoneNumber(int id, updatePatientPhoneNumber updatePhoneNumber){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setPhoneNumber(updatePhoneNumber.getPhoneNumber());
            patientRepository.save(patient);
        }
    }

    public void changeHealthCard(int id,updatePatientHealthCard updateCard){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setHealthCard(updateCard.getHealthCard());
            patientRepository.save(patient);
        }
    }

    public void changePassword(int id,updatePatientPassword updatePassword){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setPassword(updatePassword.getPassword());
            patientRepository.save(patient);
        }
    }

    public void changeUsername(int id, updatePatientName name){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient!=null){
            patient.setUsername(name.getUsername());
            patientRepository.save(patient);
        }
    }






}
