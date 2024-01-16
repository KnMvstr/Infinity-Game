package HB_CAPE_MAK.hb_cape_makindu.restcontroller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.PlatformDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.ReviewDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.entity.Review;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.PlatformService;
import HB_CAPE_MAK.hb_cape_makindu.service.ReviewService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewRestController {
    private ReviewService reviewService;

    @GetMapping
    List list() {
        return reviewService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.ReviewPrivateView.class)
    Optional<Review> show(@PathVariable Long id) {
        return reviewService.findById(id);
    }

    @PostMapping
    @JsonView(JsonViews.ReviewPublicView.class)
    public Review create(@Valid @RequestBody ReviewDTO reviewDTO) {
        return reviewService.persist(reviewDTO, null);
    }
    @PutMapping("/{id}")
    @JsonView(JsonViews.ReviewPrivateView.class)
    public Review update(@Valid @RequestBody ReviewDTO reviewDTO, @PathVariable Long id) {
        return reviewService.persist(reviewDTO, id);
    }

}
