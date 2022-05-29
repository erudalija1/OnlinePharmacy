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

    @PutMapping(path="/patient/{id}/changeName")
    public void changeName(@PathVariable int id,@RequestBody String ime){patientService.changeName(id,ime);}

    @PutMapping(path="/patient/{id}/changeUsername")
    public void changeUsername(@PathVariable int id,@RequestBody String username){patientService.changeUsername(id,username);}

    @PutMapping(path="/patient/{id}/changeAddress")
    public void changeAddress(@PathVariable int id,@RequestBody String adresa){patientService.changeAddress(id,adresa);}

    @PutMapping(path="/patient/{id}/changePhoneNumber")
    public void changePhoneNumber(@PathVariable int id,@RequestBody String phoneNumber){patientService.changePhoneNumber(id,phoneNumber);}

    @PutMapping(path="/patient/{id}/changeEmail")
    public void changeEmail(@PathVariable int id,@RequestBody String email){patientService.changeEmail(id,email);}

    @PutMapping(path="/patient/{id}/changeHealthCard")
    public void changeHealthCard(@PathVariable int id,@RequestBody String healthCard){patientService.changeName(id,healthCard);}

    @PutMapping(path="/patient/{id}/changePassword")
    public void changePassword(@PathVariable int id,@RequestBody String password){patientService.changePassword(id,password);}

}
