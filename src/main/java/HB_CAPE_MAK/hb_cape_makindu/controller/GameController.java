package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.GameServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @GetMapping
    @JsonView(JsonViews.GamePrivateView.class)
    List<Game> getAllGames() {
        return gameService.findAll();
    }

    @GetMapping(path = "/sorted")
    @JsonView(JsonViews.GamePrivateView.class)
    public List<Game> getAllByFieldAndDirection(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return gameService.findAllByFieldAndDirection(field, direction);
    }

    @GetMapping(path = "/asc")
    @JsonView(JsonViews.GamePrivateView.class)
    List<Game> getAllGameSortedAsc() {
        return gameService.findAllSorted();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.GamePrivateView.class)
    public Game getGameById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @PostMapping(path = "/create")
    @JsonView(JsonViews.GamePublicView.class)
    public Game persist(@Valid @RequestBody GameDTO gameDTO) {
        return gameService.persist(gameDTO, null);
    }
    @PutMapping(path = "/edit/{id}")
    @JsonView(JsonViews.GamePrivateView.class)
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        Game gameUpdated = gameService.persist(gameDTO, id);
        return new ResponseEntity<>(gameUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        gameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
