package ba.unsa.etf.onlinepharmacy.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicament_cart")
public class MedicamentCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate dateOfShopping;

    public LocalDate getDateOfShopping() {
        return dateOfShopping;
    }

    public void setDateOfShopping(LocalDate dateOfShopping) {
        this.dateOfShopping = dateOfShopping;
    }

    public MedicamentCart(LocalDate dateOfShopping, Medicament medicament, Cart cart) {
        this.dateOfShopping = dateOfShopping;
        this.medicament = medicament;
        this.cart = cart;
    }

    @ManyToOne
    @JoinColumn(name="medicament_id")
    private Medicament medicament;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    public MedicamentCart(Medicament medicament, Cart cart) {
        this.medicament = medicament;
        this.cart = cart;
    }

    public Integer getId() {
        return id;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
