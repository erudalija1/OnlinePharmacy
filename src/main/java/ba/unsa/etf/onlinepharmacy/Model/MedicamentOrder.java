package ba.unsa.etf.onlinepharmacy.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medicament_order")
public class MedicamentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="medicament_id")
    private Medicament medicament;

    @ManyToOne
    @JoinColumn(name="order_id")
    private UserOrder userOrder;

    public Integer getId() {
        return id;
    }

    public MedicamentOrder(Medicament medicament, UserOrder userOrder) {
        this.medicament = medicament;
        this.userOrder = userOrder;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
}
