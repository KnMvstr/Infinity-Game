package HB_CAPE_MAK.hb_cape_makindu.restcontroller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.BusinessModelDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.BusinessModelService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/businessmodel")
public class BusinessModelRestController {

        private BusinessModelService businessModelService;

    @GetMapping
    List list() {
        return businessModelService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.BusinessModelPrivateView.class)
    Optional<BusinessModel> show(@PathVariable Long id) {
        return businessModelService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.BusinessModelPublicView.class)
    public BusinessModel create(@Valid @RequestBody BusinessModelDTO businessModelDTO) {
        return businessModelService.persist(businessModelDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.BusinessModelPrivateView.class)
    public BusinessModel update(@Valid @RequestBody BusinessModelDTO businessModelDTO, @PathVariable Long id) {
        return businessModelService.persist(businessModelDTO, id);
    }
}
