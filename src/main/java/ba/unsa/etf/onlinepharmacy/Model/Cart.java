package ba.unsa.etf.onlinepharmacy.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate dateOfShopping;

    private Double initialPrice;

    public Cart(LocalDate dateOfShopping, Double initialPrice) {
        this.dateOfShopping = dateOfShopping;
        this.initialPrice = initialPrice;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Cart(LocalDate dateOfShopping) {
        this.dateOfShopping = dateOfShopping;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateOfShopping() {
        return dateOfShopping;
    }

    public void setDateOfShopping(LocalDate dateOfShopping) {
        this.dateOfShopping = dateOfShopping;
    }
}
