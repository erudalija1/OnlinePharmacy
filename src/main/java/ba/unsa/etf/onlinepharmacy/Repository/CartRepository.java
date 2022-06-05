package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByDateOfShopping(LocalDate localDate);
}
