package ba.unsa.etf.onlinepharmacy.Model;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;



}
