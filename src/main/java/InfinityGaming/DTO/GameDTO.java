package InfinityGaming.DTO;

import InfinityGaming.entity.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDTO {
    @NotBlank(message = "Specify the name of the game")
    private String name;

    @NotBlank(message = "Describe this new name")
    private String description;

    @NotBlank(message = "Specify the release date")
    private LocalDate releaseDate;

    @NotBlank(message = "Illustrate the game")
    private String image;

    @NotBlank(message = "Embellish our website")
    private String backgroundImage;

    @NotBlank(message = "For promotional purpose give use a trailer link")
    private String trailer;

    private Genre genre;

    private Publisher publisher;

    private BusinessModel businessModel;

    private List<Platform> platforms;

    private Classification classification;

}


