package HB_CAPE_MAK.hb_cape_makindu.controller;


import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
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
    @JsonView(JsonViews.GenrePrivateView.class)
    List<Genre> getAllGenre() {
        return genreService.findAll();
    }

    @GetMapping(path = "/sorted")
    @JsonView(JsonViews.GenrePrivateView.class)
    public List<Genre> getAllByFieldAndDirection(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return genreService.findAllByFieldAndDirection(field, direction);
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

    @PostMapping(path = "/create")
    @JsonView(JsonViews.GenrePublicView.class)
    public Genre persist(@Valid @RequestBody GenreDTO genreDTO) {
        return genreService.persist(genreDTO, null);
    }

    @PutMapping(path = "/edit/{id}")
    @JsonView(JsonViews.GenrePrivateView.class)
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        Genre genreUpdated = genreService.persist(genreDTO, id);
        return new ResponseEntity<>(genreUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        genreService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
