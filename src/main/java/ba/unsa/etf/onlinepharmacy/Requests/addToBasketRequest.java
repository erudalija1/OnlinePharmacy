package ba.unsa.etf.onlinepharmacy.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addToBasketRequest {
    private Integer patientId;
    private Integer Status;
    private List<Integer> medicamentOrderList;

}
