package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Gamer;
import HB_CAPE_MAK.hb_cape_makindu.entity.Moderator;
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
