package ba.unsa.etf.onlinepharmacy.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private final String tokenType = "Bearer ";
    private String token;


}
