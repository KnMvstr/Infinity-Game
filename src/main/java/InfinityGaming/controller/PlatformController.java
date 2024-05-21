package InfinityGaming.controller;

import InfinityGaming.DTO.PlatformDTO;
import InfinityGaming.entity.Platform;
import InfinityGaming.json_views.JsonViews;
import InfinityGaming.service.PlatformServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/platform")
public class PlatformController {
    @Autowired
    private PlatformServiceImpl platformService;

    @GetMapping
    @JsonView(JsonViews.PlatformPrivateView.class)
    List<Platform> getAllPlatform() {
        return platformService.findAll();
    }

    @GetMapping(path = "/sorted")
    @JsonView(JsonViews.PlatformPrivateView.class)
    public List<Platform> getAllByFieldAndDirection(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return platformService.findAllByFieldAndDirection(field, direction);
    }

    @GetMapping(path = "/asc")
    @JsonView(JsonViews.PlatformPublicView.class)
    List<Platform> getAllPlatformSortedAsc() {
        return platformService.findAllSorted();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.PlatformPrivateView.class)
    public Platform getPlatformById(@PathVariable Long id) {
        return platformService.findById(id);
    }

    @PostMapping(path = "/create")
    @JsonView(JsonViews.PlatformPublicView.class)
    public Platform persist(@Valid @RequestBody PlatformDTO platformDTO) {
        return platformService.persist(platformDTO, null);
    }

    @PutMapping(path = "/edit/{id}")
    @JsonView(JsonViews.PlatformPrivateView.class)
    public ResponseEntity<Platform> updatePlatform(@PathVariable Long id, @RequestBody PlatformDTO platformDTO) {
        Platform platformUpdated = platformService.persist(platformDTO, id);
        return new ResponseEntity<>(platformUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        platformService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
