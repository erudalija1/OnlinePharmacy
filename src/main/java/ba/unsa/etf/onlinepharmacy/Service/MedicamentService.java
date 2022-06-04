package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Model.Supplier;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentRepository;
import ba.unsa.etf.onlinepharmacy.Repository.SupplierRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addMedicamentPicturePath;
import ba.unsa.etf.onlinepharmacy.Requests.addMedicamentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentService {
    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private SupplierRepository supplierRepository;

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
        medicament.setDescription(addMedicament.getDescription());
        medicament.setPrice(addMedicament.getPrice());
        medicament.setRating(0.0);
        medicament.setPhoto("");
        medicament.setCategory(addMedicament.getCategory());
        Supplier supplier=supplierRepository.findByName(addMedicament.getSupplier());
        medicament.setSupplierId(supplier);
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

    public void addMedicamentPicturePath(int id, addMedicamentPicturePath picturePath){
        Medicament medicament=medicamentRepository.findById(id).orElse(null);
        System.out.println(picturePath.getPutanja());
        medicament.setPhoto(picturePath.getPutanja());
        medicamentRepository.save(medicament);
    }

    public List<Medicament> getAllMedicamentsByName(String name){
        return medicamentRepository.findByName(name);
    }

}
