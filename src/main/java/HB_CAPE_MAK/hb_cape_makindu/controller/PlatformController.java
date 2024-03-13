package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.BusinessModelDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.PlatformDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.PlatformServiceImpl;
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

    @GetMapping(path = "/asc")
    @JsonView(JsonViews.PlatformPrivateView.class)
    List<Platform> getAllPlatformSortedAsc() {
        return platformService.findAllSorted();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.PlatformPrivateView.class)
    public Platform getPlatformById(@PathVariable Long id) {
        return platformService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.PlatformMinimalView.class)
    public Platform persist(@Valid @RequestBody PlatformDTO platformDTO) {
        return platformService.persist(platformDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.PlatformPrivateView.class)
    public Platform persist (@Valid @RequestBody PlatformDTO platformDTO, @PathVariable Long id){
        return platformService.persist(platformDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        platformService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
