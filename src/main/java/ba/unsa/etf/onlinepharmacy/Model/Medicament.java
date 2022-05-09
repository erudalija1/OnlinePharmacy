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

    @OneToMany(mappedBy = "medicament")
    private List<SuppliedMedicaments> suppliedMedicaments;

    public Medicament() {
    }

    public Medicament(String name, boolean perscriptionNeeded) {
        this.name = name;
        this.prescriptionNeeded = perscriptionNeeded;
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

    public List<SuppliedMedicaments> getSuppliedMedicaments() {
        return suppliedMedicaments;
    }

    public void setSuppliedMedicaments(List<SuppliedMedicaments> suppliedMedicaments) {
        this.suppliedMedicaments = suppliedMedicaments;
    }
}
