package HB_CAPE_MAK.hb_cape_makindu.restcontroller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.PlatformDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.PlatformService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/platform")
public class PlatformRestController {
    private PlatformService platformService;
    @GetMapping
    List list() {
        return platformService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.PlatformPrivateView.class)
    Optional<Platform> show(@PathVariable Long id) {
        return platformService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.PlatformPublicView.class)
    public Platform create(@Valid @RequestBody PlatformDTO platformDTO) {
        return platformService.persist(platformDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.PlatformPrivateView.class)
    public Platform update(@Valid @RequestBody PlatformDTO platformDTO, @PathVariable Long id) {
        return platformService.persist(platformDTO, id);
    }
}
