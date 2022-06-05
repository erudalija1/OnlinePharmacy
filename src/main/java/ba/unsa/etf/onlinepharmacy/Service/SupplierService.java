package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Supplier;
import ba.unsa.etf.onlinepharmacy.Repository.SupplierRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addSupplierPicRequest;
import ba.unsa.etf.onlinepharmacy.Requests.addSupplierRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Iterable<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

    public void addSupplier(addSupplierRequest addsupplier) {
        Supplier supplier = new Supplier();
        supplier.setName(addsupplier.getName());
        supplier.setAddress(addsupplier.getAddress());
        supplier.setEmail(addsupplier.getEmail());
        supplier.setPeriodInDays(addsupplier.getPeriod());
        supplier.setDelayedTimeOfResponsibility(0);
        supplier.setFulfilledResponsibility(false);
        supplier.setDateOfLastSupplierResponsibility(LocalDate.now());
        supplier.setDelayedTimeOfResponsibility(0);
        supplier.setDateOfLastPharmacyResponsibility(LocalDate.now());
        supplier.setTimesOrdered(0);
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent())
            supplierRepository.delete(supplier.get());
    }


    public void setSupplierResponsibility(int id){
        Supplier supplier=supplierRepository.getById(id);
        Integer dani=SetSupplierDelayedTime(id);
        if(dani>=0 && dani<=supplier.getPeriodInDays()){
            supplier.setFulfilledResponsibility(true);

        }
        else if (dani>supplier.getPeriodInDays()){
            supplier.setFulfilledResponsibility(false);
        }

    }

    public Integer SetSupplierDelayedTime(int id){
        Supplier supplier=supplierRepository.getById(id);
        Period period=Period.between(supplier.getDateOfLastSupplierResponsibility(), LocalDate.now());
        if(period.getDays()>supplier.getPeriodInDays())
        supplier.setDelayedTimeOfResponsibility(period.getDays());
        else{
            supplier.setDelayedTimeOfResponsibility(supplier.getPeriodInDays());
        }
        return period.getDays();
    }

    public List<Supplier> getSuppliersByName(String name) {
        return (List<Supplier>) supplierRepository.findByName(name);
    }

    public void addSupplierPic(int id, addSupplierPicRequest addSupplierPicRequest){
        Supplier supplier=supplierRepository.findById(id).orElse(null);
        supplier.setPhotoFolder(addSupplierPicRequest.getPictureFolder());
        supplierRepository.save(supplier);
    }
}