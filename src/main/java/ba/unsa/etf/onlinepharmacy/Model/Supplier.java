package ba.unsa.etf.onlinepharmacy.Model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private Integer periodInDays;
    private Boolean fulfilledResponsibility;
    private Integer timesOrdered;
    private String photoFolder;

    public Integer getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(Integer timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    @Nullable
    private LocalDate dateOfLastSupplierResponsibility;
    @Nullable
    private LocalDate dateOfLastPharmacyResponsibility;

    private Integer delayedTimeOfResponsibility;

    @OneToMany(mappedBy = "supplier")
    private List<SuppliedMedicament> suppliedMedicaments;

    public Supplier(String name) {
        this.name = name;
    }

    public Integer getDelayedTimeOfResponsibility() {
        return delayedTimeOfResponsibility;
    }

    public void setDelayedTimeOfResponsibility(Integer delayedTimeOfResponsibility) {
        this.delayedTimeOfResponsibility = delayedTimeOfResponsibility;
    }

    public Supplier(String name, String address, String email, String phoneNumber, Integer periodInDays) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.periodInDays = periodInDays;
        this.fulfilledResponsibility=false;
        this.delayedTimeOfResponsibility=0;
        this.timesOrdered=0;
        this.photoFolder="";
    }

    public Supplier() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SuppliedMedicament> getSuppliedMedicaments() {
        return suppliedMedicaments;
    }

    public void setSuppliedMedicaments(List<SuppliedMedicament> suppliedMedicaments) {
        this.suppliedMedicaments = suppliedMedicaments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPeriodInDays() {
        return periodInDays;
    }

    public void setPeriodInDays(Integer periodInDays) {
        this.periodInDays = periodInDays;
    }

    @Nullable
    public LocalDate getDateOfLastSupplierResponsibility() {
        return dateOfLastSupplierResponsibility;
    }

    public void setDateOfLastSupplierResponsibility(@Nullable LocalDate dateOfLastSupplierResponsibility) {
        this.dateOfLastSupplierResponsibility = dateOfLastSupplierResponsibility;
    }

    @Nullable
    public LocalDate getDateOfLastPharmacyResponsibility() {
        return dateOfLastPharmacyResponsibility;
    }

    public void setDateOfLastPharmacyResponsibility(@Nullable LocalDate dateOfLastPharmacyResponsibility) {
        this.dateOfLastPharmacyResponsibility = dateOfLastPharmacyResponsibility;
    }

    public Boolean getFulfilledResponsibility() {
        return fulfilledResponsibility;
    }

    public void setFulfilledResponsibility(Boolean fulfilledResponsibility) {
        this.fulfilledResponsibility = fulfilledResponsibility;
    }

    public String getPhotoFolder() {
        return photoFolder;
    }

    public void setPhotoFolder(String photoFolder) {
        this.photoFolder = photoFolder;
    }
}
