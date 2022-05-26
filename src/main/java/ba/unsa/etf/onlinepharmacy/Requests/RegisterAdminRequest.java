package ba.unsa.etf.onlinepharmacy.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminRequest {
    @NotBlank(message = "Name can't be blank")
    @Size(min = 2, max = 50, message = "Name must contain between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Phone number can't be blank")
    @Size(min = 2, max = 50, message = "Phone number must contain between 2 and 50 characters")
    private String phoneNumber;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email format must be valid")
    @Size(max = 320, message = "Email can't be longer than 320 characters")
    private String email;

    private String username;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, max = 128, message = "Password must contain between 8 and 128 characters")
    private String password;
}
