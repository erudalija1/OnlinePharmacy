package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {
    List<UserOrder> getAllByStatusEquals(int broj);

    UserOrder getAllByPatient_Id(int id);
}
