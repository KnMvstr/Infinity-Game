package HB_CAPE_MAK.hb_cape_makindu.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassificationDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 5, message = "Classification description should have at least 2 characters")
    private String description;

    @Nullable
    private String image;
}
