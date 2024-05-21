package InfinityGaming.controller;

import InfinityGaming.DTO.ClassificationDTO;
import InfinityGaming.entity.Classification;
import InfinityGaming.json_views.JsonViews;
import InfinityGaming.service.ClassificationServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/classification")
public class ClassificationController {
    @Autowired
    private ClassificationServiceImpl classificationService;

    @GetMapping
    @JsonView(JsonViews.ClassificationFullView.class)
    List<Classification> getAllClassification() {
        return classificationService.findAll();
    }

    @GetMapping(path = "/sorted")
    @JsonView(JsonViews.ClassificationFullView.class)
    public List<Classification> getAllByFieldAndDirection(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return classificationService.findAllByFieldAndDirection(field, direction);
    }
    @GetMapping(path = "/asc")
    @JsonView(JsonViews.ClassificationPrivateView.class)
    List<Classification> getAllClassificationSortedAsc() {
        return classificationService.findAllSorted();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.ClassificationPrivateView.class)
    public Classification getClassificationById(@PathVariable Long id) {
        return classificationService.findById(id);
    }

    @PostMapping(path = "/create")
    @JsonView(JsonViews.ClassificationPrivateView.class)
    public Classification persist(@Valid @RequestBody ClassificationDTO classificationDTO) {
        return classificationService.persist(classificationDTO, null);
    }

    @PutMapping(path = "/edit/{id}")
    @JsonView(JsonViews.ClassificationPrivateView.class)
    public ResponseEntity<Classification> updateClassification(@PathVariable Long id, @RequestBody ClassificationDTO classificationDTO) {
        Classification classificationUpdated = classificationService.persist(classificationDTO, id);
        return new ResponseEntity<>(classificationUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        classificationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}