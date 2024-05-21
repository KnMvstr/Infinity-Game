package InfinityGaming.DTO;

import InfinityGaming.entity.Game;
import InfinityGaming.entity.Gamer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    @NotBlank
    private String description;

    @NotBlank
    @Range(min=0, max=5)
    private float note;

    @NotBlank
    private String image;

    @NotBlank
    private Gamer gamer;

    @NotBlank
    private Game game;

    @NotBlank
    @Range(min = 0, max = 5)
    private float rating;
}
