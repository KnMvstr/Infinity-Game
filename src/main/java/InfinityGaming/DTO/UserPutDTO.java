package InfinityGaming.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPutDTO {
    @NotBlank(message = "Please, the password must have a value")
    @Size(message = "The password must have at least 8 characters", min = 5)
    private String pwd;

    @NotBlank(message = "Please, give a pseudo")
    @Size(message = "The account name must have at least 5 characters", min = 5)
    private String pseudo;

}