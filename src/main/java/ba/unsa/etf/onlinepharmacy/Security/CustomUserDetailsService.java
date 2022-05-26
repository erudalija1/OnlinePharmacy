package ba.unsa.etf.onlinepharmacy.Security;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PatientRepository korisnikRepository;


    public CustomUserDetailsService(final PatientRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient user = korisnikRepository.findByUsername(username)
               .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username : " + username)
                );

        return UserPrincipal.create(user);
    }



    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        Patient user = korisnikRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}