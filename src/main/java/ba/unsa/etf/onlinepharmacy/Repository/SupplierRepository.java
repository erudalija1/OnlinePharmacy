package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository  extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByOrderByTimesOrderedDesc();

    List<Supplier> findByName(String name);
}
