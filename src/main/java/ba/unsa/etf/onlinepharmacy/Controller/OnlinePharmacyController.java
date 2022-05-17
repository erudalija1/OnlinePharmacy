package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.RoleRepository;
import ba.unsa.etf.onlinepharmacy.Requests.LoginRequest;
import ba.unsa.etf.onlinepharmacy.Requests.RegisterRequest;
import ba.unsa.etf.onlinepharmacy.Requests.updatePatientRequest;
import ba.unsa.etf.onlinepharmacy.Response.LoginResponse;
import ba.unsa.etf.onlinepharmacy.Response.Response;
import ba.unsa.etf.onlinepharmacy.Security.CurrentUser;
import ba.unsa.etf.onlinepharmacy.Security.UserPrincipal;
import ba.unsa.etf.onlinepharmacy.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OnlinePharmacyController {

    @Autowired
    private RoleRepository roleReposritory;

    private HomeService homeService;

    @GetMapping("/home")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Pokrenuto");
    }

    @PostMapping("/registration")
    public ResponseEntity<Response> register(@Valid @RequestBody RegisterRequest registerRequest){
        String msg = homeService.register(registerRequest);
        return ResponseEntity.ok(new Response(msg));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authentication(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(homeService.authentication(loginRequest));
    }

    @GetMapping("/profil")
    public ResponseEntity<Patient> getProfil(@CurrentUser UserPrincipal userPrincipal) {
        Patient patient = homeService.getPatientByUsername(userPrincipal.getUsername());
        return ResponseEntity.ok(patient);
    }

    @Secured({ "ROLE_EMPLOYEE", "ROLE_ADMIN" })
    @PutMapping("/profil")
    public ResponseEntity<String> updatePatient(@RequestBody updatePatientRequest patientUpdate){
        return  ResponseEntity.ok(homeService.updatePatient(patientUpdate));
    }





}
