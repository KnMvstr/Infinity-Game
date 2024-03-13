package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.ClassificationDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Classification;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.ClassificationServiceImpl;
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
    @JsonView(JsonViews.ClassificationPublicView.class)
    List<Classification> getAllClassification() {
        return classificationService.findAll();
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

    @PostMapping
    public Classification persist(@Valid @RequestBody ClassificationDTO classificationDTO) {
        return classificationService.persist(classificationDTO, null);
    }

    @PutMapping("/{id}")
    public Classification persist (@Valid @RequestBody ClassificationDTO classificationDTO, @PathVariable Long id){
        return classificationService.persist(classificationDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        classificationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}