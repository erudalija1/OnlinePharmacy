package ba.unsa.etf.onlinepharmacy.Controller;


import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Requests.addMedicamentRequest;
import ba.unsa.etf.onlinepharmacy.Security.CurrentUser;
import ba.unsa.etf.onlinepharmacy.Security.UserPrincipal;
import ba.unsa.etf.onlinepharmacy.Service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class MedicamentController {

    @Autowired
    MedicamentService medicamentService;

    @GetMapping(path = "/medicaments")
    public Iterable<Medicament> getAllMedicaments() {
        return medicamentService.getAllMedicaments();
    }

    @GetMapping(path = "/medicament/{id}")
    public Optional<Medicament> getMedicamenttById(@PathVariable  int id, @CurrentUser UserPrincipal userPrincipal) {
        return medicamentService.getMedicamentById(id);
    }

   // @Secured("ROLE_ADMIN")
    @PostMapping(path = "/medicament")
    public void addMedicament(@RequestBody addMedicamentRequest addMedicamentRequest) {
        medicamentService.addMedicament(addMedicamentRequest);
    }

  //  @Secured("ROLE_ADMIN")
    @DeleteMapping(path = "/medicament/{id}")
    public void deleteMedicament(@PathVariable  int id) {
        medicamentService.deleteMedicament(id);
    }
}
