package ba.unsa.etf.onlinepharmacy.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addUserRequest {
    private String gender;
    private String name;
    private String address;
    private String phoneNumber;
    private String healthCard;
}