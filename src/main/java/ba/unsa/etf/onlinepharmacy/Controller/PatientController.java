package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Requests.*;
import ba.unsa.etf.onlinepharmacy.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void changeName(@PathVariable int id,@RequestBody updatePatientName updateName){patientService.changeName(id,updateName);}

    @PutMapping(path="/patient/{id}/changeUsername")
    public void changeUsername(@PathVariable int id,@RequestBody updatePatientName updateName){patientService.changeUsername(id,updateName);}

    @PutMapping(path="/patient/{id}/changeAddress")
    public void changeAddress(@PathVariable int id,@RequestBody updatePatientAddress updateAddress){patientService.changeAddress(id,updateAddress);}

    @PutMapping(path="/patient/{id}/changePhoneNumber")
    public void changePhoneNumber(@PathVariable int id,@RequestBody updatePatientPhoneNumber updatePhoneNumber){patientService.changePhoneNumber(id,updatePhoneNumber);}

    @PutMapping(path="/patient/{id}/changeEmail")
    public void changeEmail(@PathVariable int id,@RequestBody updatePatientEmail email){patientService.changeEmail(id,email);}

    @PutMapping(path="/patient/{id}/changeHealthCard")
    public void changeHealthCard(@PathVariable int id,@RequestBody updatePatientHealthCard updateCard){patientService.changeHealthCard(id,updateCard);}

    @PutMapping(path="/patient/{id}/changePassword")
    public void changePassword(@PathVariable int id,@RequestBody updatePatientPassword updatePassword){patientService.changePassword(id,updatePassword);}

    @GetMapping(path="/patient/popular")
    public List<Patient> popularPatients(){
        return patientRepository.findByOrderByTimesOrderedDesc();
    }
}
