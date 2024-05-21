package InfinityGaming.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassificationDTO {

    @NotBlank(message = "Specify the name of the classification")
    private String name;

    @NotBlank(message = "Specify the official description of this classificatioon")
    private String description;
}
