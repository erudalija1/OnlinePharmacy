package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Model.Supplier;
import ba.unsa.etf.onlinepharmacy.Requests.addSupplierRequest;
import ba.unsa.etf.onlinepharmacy.Service.PatientService;
import ba.unsa.etf.onlinepharmacy.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping(path = "/suppliers")
    public Iterable<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping(path = "/supplier/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable  int id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping(path = "/supplier")
    public void addSupplier(@RequestBody addSupplierRequest addSupplier) {
        supplierService.addSupplier(addSupplier);
    }

    @DeleteMapping(path = "/supplier/{id}")
    public void deleteSupplier(@PathVariable  int id) {
        supplierService.deleteSupplier(id);
    }
}
