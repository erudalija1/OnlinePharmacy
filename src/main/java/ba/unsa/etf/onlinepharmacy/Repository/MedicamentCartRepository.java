package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.MedicamentCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MedicamentCartRepository extends JpaRepository<MedicamentCart,Long> {
    List<MedicamentCart> findByDateOfShoppingAndPatient_Id(int id, LocalDate localDate);

    List<MedicamentCart> findByPatient_Id(int id);
}
