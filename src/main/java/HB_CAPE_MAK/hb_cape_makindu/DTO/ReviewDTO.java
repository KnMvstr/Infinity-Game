package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Gamer;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
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
