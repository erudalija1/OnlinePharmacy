package ba.unsa.etf.onlinepharmacy.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addMedicamentRequest {
    private String name;
    private boolean prescriptionNeeded;
    private Integer inStock;
    private Double price;
    private String description;
    private String pictureFolder;
    private String supplier;
    private String category;
}
