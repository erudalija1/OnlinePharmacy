package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository  extends JpaRepository<Medicament, Integer> {
}
