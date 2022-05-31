package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface MedicamentRepository  extends JpaRepository<Medicament, Integer> {
    List<Medicament> findByOrderByTimesBoughtDesc();

    List<Medicament> findByName(String name);
}
