package ba.unsa.etf.onlinepharmacy.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class PatientWrapper {
    private String id;
    private String email;
    private String username;
    private ArrayList<String> roles;
}
