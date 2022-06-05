package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.MedicamentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicamentOrderRepository extends JpaRepository<MedicamentOrder,Long> {
    Optional<List<MedicamentOrder>> findByUserOrder_Id(Integer id);

}
