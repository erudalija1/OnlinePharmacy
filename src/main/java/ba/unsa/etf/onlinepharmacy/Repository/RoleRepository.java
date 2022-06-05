package ba.unsa.etf.onlinepharmacy.Repository;

import ba.unsa.etf.onlinepharmacy.Model.Role;
import ba.unsa.etf.onlinepharmacy.Model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRolename(RoleName roleName);

}
