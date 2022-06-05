package ba.unsa.etf.onlinepharmacy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Nullable
    private String gender;
    private String name;
    @Nullable
    private String address;
    private String phoneNumber;
    @Nullable
    private String healthCard;
    private String password;
    @Column(unique = true)
    private String email;
    private String username;
    private Integer timesOrdered;
    private LocalDate registradionDate;
    private boolean discount;
    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Patient(@Nullable String gender, String name, @Nullable String address, String phoneNumber, @Nullable String healthCard, String password, String email, String username, Integer timesOrdered, LocalDate registradionDate, boolean discount, Integer role, List<UserOrder> userOrders, Set<Role> roles) {
        this.gender = gender;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.healthCard = healthCard;
        this.password = password;
        this.email = email;
        this.username = username;
        this.timesOrdered = timesOrdered;
        this.registradionDate = registradionDate;
        this.discount = discount;
        this.role = role;
        this.userOrders = userOrders;
        this.roles = roles;
    }

    @OneToMany(mappedBy = "patient")
    private List<UserOrder> userOrders;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public Integer getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(Integer timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    public LocalDate getRegistradionDate() {
        return registradionDate;
    }

    public void setRegistradionDate(LocalDate registradionDate) {
        this.registradionDate = registradionDate;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public Patient(String gender, String name, String address, String phoneNumber, String healthCard, String email, String password, String username) {
        this.gender = gender;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.healthCard = healthCard;
        this.password=password;
        this.email=email;
        this.username=username;
        this.timesOrdered=0;
        this.discount=false;
        this.registradionDate= LocalDate.from(LocalDateTime.now());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

