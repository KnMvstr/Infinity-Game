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
    protected String firstName;

    @NotBlank
    protected String name;

    @NotBlank
    protected String pseudo;

    @Email
    @NotBlank
    protected String email;

    @Past
    private LocalDate birthAt;

    @NotBlank
    protected String pwd;

//    @Pattern(regexp = "((\\+33|0)[0-9]{10})", message = "Invalid phone number format")
    @NotBlank
    private String cellPhoneNumber;



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
