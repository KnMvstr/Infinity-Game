package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
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
    @JsonView(JsonViews.GamePublicView.class)
    List<Game> getAllGames() {
        return gameService.findAll();
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

    @PostMapping
    public Game persist(@Valid @RequestBody GameDTO gameDTO) {
        return gameService.persist(gameDTO, null);
    }

    @PutMapping("/{id}")
    public Game persist (@Valid @RequestBody GameDTO gameDTO, @PathVariable Long id){
        return gameService.persist(gameDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        gameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
