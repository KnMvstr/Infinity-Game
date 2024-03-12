package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Gamer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewPostDTO {

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull(message = "Rating cannot be null")
    private float rating;

    private String image;

    @NotNull(message = "Gamer ID cannot be null")
    private Gamer gamerId;

    @NotNull(message = "Game ID cannot be null")
    private Game gameId;


}
