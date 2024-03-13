package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.BusinessModelDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.GenreServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/genre")
public class GenreController {
    @Autowired
    private GenreServiceImpl genreService;

    @GetMapping
    @JsonView(JsonViews.GenrePublicView.class)
    List<Genre> getAllGenre() {
        return genreService.findAll();
    }

    @GetMapping(path = "/asc")
    @JsonView(JsonViews.GenrePrivateView.class)
    List<Genre> getAllGenreSortedAsc() {
        return genreService.findAllSorted();
    }


    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.GenrePrivateView.class)
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.GenreMinimalView.class)
    public Genre persist(@Valid @RequestBody GenreDTO genreDTO) {
        return genreService.persist(genreDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.GenrePrivateView.class)
    public Genre persist (@Valid @RequestBody GenreDTO genreDTO, @PathVariable Long id){
        return genreService.persist(genreDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        genreService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
