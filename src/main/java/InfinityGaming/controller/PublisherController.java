package InfinityGaming.controller;

import InfinityGaming.DTO.PublisherDTO;
import InfinityGaming.entity.Publisher;
import InfinityGaming.json_views.JsonViews;
import InfinityGaming.service.PublisherServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
    @Autowired
    private PublisherServiceImpl publisherService;

    @GetMapping
    @JsonView(JsonViews.PublisherPrivateView.class)
    List<Publisher> getAllPublisher() {
        return publisherService.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Publisher>> searchPublisher(@RequestParam String query) {
        List<Publisher> publishers = publisherService.search(query);
        return ResponseEntity.ok(publishers);
    }
    @GetMapping(path = "/sorted")
    @JsonView(JsonViews.PublisherPrivateView.class)
    public List<Publisher> getAllByFieldAndDirection(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return publisherService.findAllByFieldAndDirection(field, direction);
    }

    @GetMapping(path = "/asc")
    @JsonView(JsonViews.PublisherPublicView.class)
    List<Publisher> getAllPublisherSortedAsc() {
        return publisherService.findAllSorted();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.PublisherPrivateView.class)
    public Publisher getPublisherById(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @PostMapping(path = "/create")
    @JsonView(JsonViews.PublisherPublicView.class)
    public Publisher persist(@Valid @RequestBody PublisherDTO publisherDTO) {
        return publisherService.persist(publisherDTO, null);
    }

    @PutMapping(path = "/edit/{id}")
    @JsonView(JsonViews.PublisherPrivateView.class)
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody PublisherDTO publisherDTO) {
        Publisher publisherUpdated = publisherService.persist(publisherDTO, id);
        return new ResponseEntity<>(publisherUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
