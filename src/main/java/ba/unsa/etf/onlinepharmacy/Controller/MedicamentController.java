package ba.unsa.etf.onlinepharmacy.Controller;


import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Service.MedicamentService;
import ba.unsa.etf.onlinepharmacy.Service.PatientService;
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
    public Optional<Medicament> getMedicamenttById(int id) {
        return medicamentService.getMedicamentById(id);
    }

    @PostMapping(path = "/medicament")
    public void addMedicament() {
        medicamentService.addMedicament();
    }

    @DeleteMapping(path = "/medicament/{id}")
    public void deleteMedicament(int id) {
        medicamentService.deleteMedicament(id);
    }
}
