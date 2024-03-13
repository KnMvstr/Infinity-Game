package HB_CAPE_MAK.hb_cape_makindu.controller;


import HB_CAPE_MAK.hb_cape_makindu.DTO.PlatformDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.PublisherDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.entity.Publisher;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.PublisherServiceImpl;
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

    @GetMapping(path = "/asc")
    @JsonView(JsonViews.PublisherPrivateView.class)
    List<Publisher> getAllPublisherSortedAsc() {
        return publisherService.findAllSorted();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.PublisherPrivateView.class)
    public Publisher getPublisherById(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.PublisherMinimalView.class)
    public Publisher persist(@Valid @RequestBody PublisherDTO publisherDTO) {
        return publisherService.persist(publisherDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.PublisherPrivateView.class)
    public Publisher persist (@Valid @RequestBody PublisherDTO publisherDTO, @PathVariable Long id){
        return publisherService.persist(publisherDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
