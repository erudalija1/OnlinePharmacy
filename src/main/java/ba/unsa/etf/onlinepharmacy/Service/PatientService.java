package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
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
        if(patientRepository.findById(id).isPresent())
         return patientRepository.findById(id).get();
        else return null;
    }

    public void addPatient() {
        Patient patient = new Patient();
        patientRepository.save(patient);
    }

    public void deletePatient(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent())
            patientRepository.delete(patient.get());
    }
}
