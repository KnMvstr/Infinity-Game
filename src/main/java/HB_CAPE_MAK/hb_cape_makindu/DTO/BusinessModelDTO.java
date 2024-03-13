package HB_CAPE_MAK.hb_cape_makindu.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessModelDTO {

    @NotBlank(message = "Specify the name of the brand")
    private String name;

}
