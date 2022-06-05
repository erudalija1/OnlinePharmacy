package ba.unsa.etf.onlinepharmacy.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addSupplierRequest {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private Integer period;

}
