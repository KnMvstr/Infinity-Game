package InfinityGaming.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    @NotBlank
    protected String pseudo;

    @Email(message = "Veuillez renseigner une adresse email valide")
    @NotBlank(message = "Veuillez renseigner une adresse email")
    protected String username;

    @NotBlank(message = "Veuillez renseigner un mot de passe")
    @Size(message = "Le mot de passe doit comporter au moins 8 caractères", min = 5)
    protected String pwd;

}
