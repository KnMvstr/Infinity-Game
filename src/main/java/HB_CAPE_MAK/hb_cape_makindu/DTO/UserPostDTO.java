package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPostDTO {

    @Email(message = "Please, give a valid email")
    @NotBlank(message = "Please, give an email")
    @Column(unique=true)
    private String email;


    @NotBlank(message = "Please, give a pseudo")
    @Size(message = "The account name must have at least 5 characters", min = 5)
    private String pseudo;

    @NotBlank(message = "Please, the password must have a value")
    @Size(message = "The password must have at least 8 characters", min = 5)
    private String pwd;

    @NotBlank(message = "please, define your role MODERATOR/GAMER")
    private String userType;

}