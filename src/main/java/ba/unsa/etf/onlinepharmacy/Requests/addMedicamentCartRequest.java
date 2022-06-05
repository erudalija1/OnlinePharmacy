package ba.unsa.etf.onlinepharmacy.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addMedicamentCartRequest {
    private Integer medicamentId;
}
