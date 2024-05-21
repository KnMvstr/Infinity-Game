package InfinityGaming.DTO;

import InfinityGaming.entity.Game;
import InfinityGaming.entity.Gamer;
import InfinityGaming.entity.Moderator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    @NotBlank(message = "Specify the name of the game")
    private Game game;
    @NotBlank(message = "What did you think about this game")
    private String description;
    @NotBlank(message = "Give it a note")
    private float rating;
    private Gamer gamer;
    @NotBlank(message = "Specify the creation date")
    private LocalDateTime createdAt;
    private Moderator moderator;
    @NotBlank(message = "Specify the creation date")
    private LocalDateTime moderatedAt;
}
