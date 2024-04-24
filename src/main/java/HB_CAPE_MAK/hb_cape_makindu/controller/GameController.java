package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.GameServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<List<Game>>findAllGamesWithDetails(){
    List<Game> games= gameService.findAllGamesWithDetails();
        return ResponseEntity.ok(games);
    }

// Use in Angular nav search bar
    @GetMapping("/search")
    public ResponseEntity<List<Game>> searchGames(@RequestParam String query) {
        List<Game> games = gameService.search(query);
        return ResponseEntity.ok(games);
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
    public Game persist(@Validated @RequestBody GameDTO gameDTO) {
        return gameService.persist(gameDTO, null);
    }

    // Endpoint to get the count of reviews
    @GetMapping("/count")
    public ResponseEntity<Long> getGamesCount() {
        Long count = gameService.getGamesCount();
        return ResponseEntity.ok(count);
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
