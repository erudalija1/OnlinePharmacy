package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addUserRequest;
import ba.unsa.etf.onlinepharmacy.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/patients")
    public Iterable<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/patient/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/patient")
    public void addPatient(@RequestBody addUserRequest addUser) {
        patientService.addPatient(addUser);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(path = "/patient/{id}")
    public void deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
    }

}
