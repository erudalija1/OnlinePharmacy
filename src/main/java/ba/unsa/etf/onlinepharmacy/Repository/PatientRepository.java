package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findById(Integer id);

    Patient findByEmail(String email);

    Optional<Patient> findByUsername(String username);

    List<Patient> findByOrderByTimesOrderedDesc();

    List<Patient> findByName(String name);

}