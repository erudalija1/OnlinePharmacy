package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Model.Supplier;
import ba.unsa.etf.onlinepharmacy.Repository.SupplierRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addSupplierRequest;
import ba.unsa.etf.onlinepharmacy.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @Autowired
    private SupplierRepository supplierRepository;



    @GetMapping(path = "/suppliers")
    public Iterable<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping(path = "/supplier/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

    //@Secured("ROLE_ADMIN")
    @PostMapping(path = "/supplier")
    public void addSupplier(@RequestBody addSupplierRequest addSupplier) {
        supplierService.addSupplier(addSupplier);
    }

    // @Secured("ROLE_ADMIN")
    @DeleteMapping(path = "/supplier/{id}")
    public void deleteSupplier(@PathVariable int id) {
        supplierService.deleteSupplier(id);
    }

    @PutMapping(path = "/supplier/{id}/responsibility")
    public void setSupplierResponsibility(@PathVariable int id) {
        supplierService.setSupplierResponsibility(id);
    }

    @GetMapping(path="/supplier/popular")
    public List<Supplier> popularSuppliers(){
        return supplierRepository.findByOrderByTimesOrderedDesc();
    }

    @RequestMapping(value = "/suppliers/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Supplier> getSupplierByName(@PathVariable("name") String name) {
        return supplierService.getSuppliersByName(name);
    }
}
