package ba.unsa.etf.onlinepharmacy.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "medicament")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private boolean prescriptionNeeded;
    private Integer inStock;
    private Integer timesBought;

    public Integer getTimesBought() {
        return timesBought;
    }

    public void setTimesBought(Integer timesBought) {
        this.timesBought = timesBought;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    @OneToMany(mappedBy = "medicament")
    private List<SuppliedMedicament> suppliedMedicaments;

    public Medicament() {
    }

    public Medicament(String name, boolean perscriptionNeeded) {
        this.name = name;
        this.prescriptionNeeded = perscriptionNeeded;
        this.timesBought=0;
    }
    public Medicament(String name, boolean perscriptionNeeded,Integer inStock) {
        this.name = name;
        this.prescriptionNeeded = perscriptionNeeded;
        this.timesBought=0;
        this.inStock=inStock;
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

    public boolean isPrescriptionNeeded() {
        return prescriptionNeeded;
    }

    public void setPrescriptionNeeded(boolean prescriptionNeeded) {
        this.prescriptionNeeded = prescriptionNeeded;
    }

    public List<SuppliedMedicament> getSuppliedMedicaments() {
        return suppliedMedicaments;
    }

    public void setSuppliedMedicaments(List<SuppliedMedicament> suppliedMedicaments) {
        this.suppliedMedicaments = suppliedMedicaments;
    }
}
