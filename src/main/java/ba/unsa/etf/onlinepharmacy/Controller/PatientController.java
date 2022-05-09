package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping(path = "/patients")
    public Iterable<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(path = "/patient/{id}")
    public Optional<Patient> getPatientById(int id) {
        return patientService.getPatientById(id);
    }

    @PostMapping(path = "/patient")
    public void addPatient() {
        patientService.addPatient();
    }

    @DeleteMapping(path = "/patient/{id}")
    public void deletePatient(int id) {
        patientService.deletePatient(id);
    }
}
