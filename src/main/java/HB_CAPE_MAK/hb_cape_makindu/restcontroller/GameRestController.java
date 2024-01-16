package HB_CAPE_MAK.hb_cape_makindu.restcontroller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.GameService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/game")
public class GameRestController {
    private GameService gameService;

    @GetMapping
    List list() {
        return gameService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.GamePrivateView.class)
    Optional<Game> show(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.GamePublicView.class)
    public Game create(@Valid @RequestBody GameDTO gameDTO) {
        return gameService.persist(gameDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.GamePrivateView.class)
    public Game update(@Valid @RequestBody GameDTO gameDTO, @PathVariable Long id) {
        return gameService.persist(gameDTO, id);
    }
}
