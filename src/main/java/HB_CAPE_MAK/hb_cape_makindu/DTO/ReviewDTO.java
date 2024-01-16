package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    @NotBlank
    private String description;
    @NotBlank
    private float note;
    @NotBlank
    private String image;
    @NotBlank
    private User user;
    @NotBlank
    private Game game;
    @NotBlank
    private User moderator;
}
