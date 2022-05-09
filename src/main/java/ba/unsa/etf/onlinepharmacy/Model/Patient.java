package ba.unsa.etf.onlinepharmacy.Model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String gender;
    private String name;
    private String address;
    private String phoneNumber;
    private String healthCard;


    @OneToMany(mappedBy = "patient")
    private List<UserOrder> userOrders;


    public Patient(String gender, String name, String address, String phoneNumber, String healthCard) {
        this.gender = gender;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.healthCard = healthCard;
    }


    public Patient() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }
}

