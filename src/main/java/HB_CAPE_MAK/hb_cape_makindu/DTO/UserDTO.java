package HB_CAPE_MAK.hb_cape_makindu.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


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

//
//    @Past
//    private LocalDate birthAt;



//    @Pattern(regexp = "((\\+33|0)[0-9]{10})", message = "Invalid phone number format")
//    @NotBlank
//    private String cellPhoneNumber;



    //    @Column(nullable = false, unique = true)
//    @Email
//    @JsonView(JsonViews.PlayerView.class)
//    protected String email;
//
//    @Column(nullable = false)
//    @JsonView(JsonViews.UserView.class)
//    @Size(message = "Your password must contains at least of 9 characters", min = 9)
//    protected String pwd;


}
