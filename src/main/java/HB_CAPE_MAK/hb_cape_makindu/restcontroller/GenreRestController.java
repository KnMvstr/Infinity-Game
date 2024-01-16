package HB_CAPE_MAK.hb_cape_makindu.restcontroller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.GenreService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/genre")
public class GenreRestController {
    private GenreService genreService;

    @GetMapping
    List list() {
        return genreService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.GenrePrivateView.class)
    Optional<Genre> show(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.GenrePublicView.class)
    public Genre create(@Valid @RequestBody GenreDTO genreDTO) {
        return genreService.persist(genreDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.GenrePrivateView.class)
    public Genre update(@Valid @RequestBody GenreDTO genreDTO, @PathVariable Long id) {
        return genreService.persist(genreDTO, id);
    }
}