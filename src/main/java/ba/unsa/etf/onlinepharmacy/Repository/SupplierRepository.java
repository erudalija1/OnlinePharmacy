package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository  extends JpaRepository<Supplier, Integer> {
}
