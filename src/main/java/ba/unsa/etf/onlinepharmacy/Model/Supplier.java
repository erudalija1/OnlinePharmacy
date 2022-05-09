package ba.unsa.etf.onlinepharmacy.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "supplier")
    private List<SuppliedMedicaments> suppliedMedicaments;

    public Supplier(String name) {
        this.name = name;
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

    public List<SuppliedMedicaments> getSuppliedMedicaments() {
        return suppliedMedicaments;
    }

    public void setSuppliedMedicaments(List<SuppliedMedicaments> suppliedMedicaments) {
        this.suppliedMedicaments = suppliedMedicaments;
    }
}
