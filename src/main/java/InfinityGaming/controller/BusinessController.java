package InfinityGaming.controller;

import InfinityGaming.DTO.BusinessModelDTO;
import InfinityGaming.entity.BusinessModel;
import InfinityGaming.json_views.JsonViews;
import InfinityGaming.service.BusinessModelServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/business")
public class BusinessController {
    @Autowired
    private BusinessModelServiceImpl businessModelService;

    @GetMapping
    @JsonView(JsonViews.BusinessModelPrivateView.class)
    List<BusinessModel> getAllBusinessModel() {
        return businessModelService.findAll();
    }

    @GetMapping(path = "/sorted")
    @JsonView(JsonViews.GenrePrivateView.class)
    public List<BusinessModel> getAllByFieldAndDirection(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return businessModelService.findAllByFieldAndDirection(field, direction);
    }

    @GetMapping(path = "/asc")
    @JsonView(JsonViews.BusinessModelPrivateView.class)
    List<BusinessModel> getAllBusinessModelSortedAsc() {
        return businessModelService.findAllSorted();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.BusinessModelPrivateView.class)
    public BusinessModel getBusinessModelById(@PathVariable Long id) {
        return businessModelService.findById(id);
    }

    @PostMapping(path = "/create")
    @JsonView(JsonViews.BusinessModelPublicView.class)
    public BusinessModel persist(@Valid @RequestBody BusinessModelDTO businessModelDTO) {
        return businessModelService.persist(businessModelDTO, null);
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<BusinessModel> updateGenre(@PathVariable Long id, @RequestBody BusinessModelDTO businessModelDTO) {
        BusinessModel businessModelUpdated = businessModelService.persist(businessModelDTO, id);
        return new ResponseEntity<>(businessModelUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        businessModelService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
