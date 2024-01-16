package HB_CAPE_MAK.hb_cape_makindu.restcontroller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.PublisherDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Publisher;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.PublisherService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/publisher")
public class PublisherRestController {
    private PublisherService publisherService;

    @GetMapping
    List list() {
        return publisherService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.PublisherPrivateView.class)
    Optional<Publisher> show(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.PublisherPublicView.class)
    public Publisher create(@Valid @RequestBody PublisherDTO publisherDTO) {
        return publisherService.persist(publisherDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.PublisherPrivateView.class)
    public Publisher update(@Valid @RequestBody PublisherDTO publisherDTO, @PathVariable Long id) {
        return publisherService.persist(publisherDTO, id);
    }
}
