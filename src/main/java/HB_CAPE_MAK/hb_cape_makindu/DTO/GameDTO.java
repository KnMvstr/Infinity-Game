package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.entity.Classification;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.entity.Publisher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 100, message = "Classification description should have at least 2 characters")
    private String description;
    @NotBlank
    private String image;
    @NotBlank
    @DateTimeFormat
    private LocalDate releaseDate;
    @NotBlank
    private Genre genre;
    @NotBlank
    private Publisher publisher;
    @NotBlank
    private BusinessModel businessModel;
    @NotBlank
    private Classification classification;
}
