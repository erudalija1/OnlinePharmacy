package ba.unsa.etf.onlinepharmacy.Model;

import javax.persistence.*;

@Entity
@Table(name = "order_payment")
public class OrderPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double price;

    @OneToOne
    @JoinColumn(name="patient_id",  nullable = false)
    private Patient patient;

    public OrderPayment(Integer id, Double price, Patient patient) {
        this.id = id;
        this.price = price;
        this.patient = patient;
    }

    @OneToOne
    @JoinColumn(name="order_id",  nullable = false)
    private UserOrder userOrder;


    public OrderPayment() {
    }

    public OrderPayment(Double price, Patient patient, UserOrder userOrder) {
        this.price = price;
        this.patient = patient;
        this.userOrder = userOrder;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
