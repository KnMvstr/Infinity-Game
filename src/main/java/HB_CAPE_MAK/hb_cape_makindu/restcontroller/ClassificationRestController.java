package HB_CAPE_MAK.hb_cape_makindu.restcontroller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.ClassificationDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Classification;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.ClassificationService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/classification")
public class ClassificationRestController {

    private ClassificationService classificationService;

    @GetMapping
    List list() {
        return classificationService.findAll();
    }
    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.ClassificationPrivateView.class)
    Optional<Classification> show(@PathVariable Long id) {
        return classificationService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.ClassificationPublicView.class)
    public Classification create(@Valid @RequestBody ClassificationDTO classificationDTO) {
        return classificationService.persist(classificationDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.ClassificationPrivateView.class)
    public Classification update(@Valid @RequestBody ClassificationDTO classificationDTO, @PathVariable Long id) {
        return classificationService.persist(classificationDTO, id);
    }

}
