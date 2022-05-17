package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addUserRequest;
import ba.unsa.etf.onlinepharmacy.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @GetMapping(path = "/patients")
    public Iterable<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(path = "/patient/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @PostMapping(path = "/patient")
    public void addPatient(@RequestBody addUserRequest addUser) {
        patientService.addPatient(addUser);
    }

    @DeleteMapping(path = "/patient/{id}")
    public void deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
    }

}
