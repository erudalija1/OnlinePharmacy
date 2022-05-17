package ba.unsa.etf.onlinepharmacy.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class PatientWrapper {
    private String id;
    private String name;
    private String gender;
    private String healthCard;
    private String email;
    private String phoneNumber;
    private String username;
    private String address;
    private ArrayList<String> roles;
}
