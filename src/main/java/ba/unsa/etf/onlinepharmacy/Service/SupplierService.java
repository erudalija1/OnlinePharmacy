package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Model.Supplier;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentRepository;
import ba.unsa.etf.onlinepharmacy.Repository.SupplierRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addSupplierRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Iterable<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(int id){
        return supplierRepository.findById(id);
    }

    public void addSupplier(addSupplierRequest addsupplier) {
        Supplier supplier = new Supplier();
        supplier.setName(addsupplier.getName());
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent())
            supplierRepository.delete(supplier.get());
    }
}
