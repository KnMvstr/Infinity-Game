package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GameDTO {
    private String name;


    private String description;


    private LocalDate releaseDate;


    private String image;


    private String backgroundImage;


    private String trailer;


    private Genre genre;


    private Publisher publisher;


    private BusinessModelDTO businessModel;


    private Moderator moderator;


    private List<Platform> platforms;


    private Classification classification;

}
