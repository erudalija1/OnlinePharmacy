package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addMedicamentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentService {
    @Autowired
    private MedicamentRepository medicamentRepository;

    public Iterable<Medicament> getAllMedicaments() {
        return medicamentRepository.findAll();
    }

    public Optional<Medicament> getMedicamentById(int id){
        return medicamentRepository.findById(id);
    }

    public void addMedicament(addMedicamentRequest addMedicament) {
        Medicament medicament = new Medicament();
        medicament.setName(addMedicament.getName());
        medicament.setPrescriptionNeeded(addMedicament.isPrescriptionNeeded());
        medicament.setInStock(addMedicament.getInStock());
        medicament.setTimesBought(0);
        medicamentRepository.save(medicament);
    }

    public void deleteMedicament(int id) {
        Optional<Medicament> medicament = medicamentRepository.findById(id);
        if (medicament.isPresent())
            medicamentRepository.delete(medicament.get());
    }

    public List<Medicament> popularMedicaments(){
        return medicamentRepository.findByOrderByTimesBoughtDesc();
    }


}
