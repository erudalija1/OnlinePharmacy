package ba.unsa.etf.onlinepharmacy.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class updatePatientRequest {
    private Long id;
    private String gender;
    private String name;
    private String address;
    private String phoneNumber;
    private String healthCard;
    private String password;
    private String email;
    private String username;
}
