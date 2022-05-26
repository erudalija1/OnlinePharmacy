package ba.unsa.etf.onlinepharmacy.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class EmployeeWrapper {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String username;
    private ArrayList<String> roles;
}
