package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.ReviewDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.entity.Review;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.ReviewServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewService;

    @GetMapping
    @JsonView(JsonViews.ReviewPublicView.class)
    List<Review> getAllreview() {
        return reviewService.findAll();
    }

    //    @GetMapping(path = "/sorted")
//    @JsonView(JsonViews.ReviewPublicView.class)
//    public List<Review> getAllByField(@RequestParam String field) {
//        return reviewService.findAllByField(field);
//    }
    @GetMapping(path = "/sorted")
    @JsonView(JsonViews.ReviewPublicView.class)
    public List<Review> getAllByFieldAndDirection(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return reviewService.findAllByFieldAndDirection(field, direction);
    }


    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.ReviewPrivateView.class)
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.findById(id);
    }

    @GetMapping(path = "/moderated")
    @JsonView(JsonViews.ReviewPrivateView.class)
    boolean getAllModeratedReview(@PathVariable String pseudo, Long id, Long moderate) {
        return reviewService.findModeratedReview(pseudo, id, moderate);
    }

    @GetMapping(path = "/by_game")
    @JsonView(JsonViews.ReviewPrivateView.class)
    List<Review> getAllReviewByGame(@PathVariable Game game) {
        return reviewService.findAllByGame(game);
    }

    @PostMapping
    @JsonView(JsonViews.ReviewMinimalView.class)
    public Review persist(@Valid @RequestBody ReviewDTO reviewDTO) {
        return reviewService.persist(reviewDTO, null);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.ReviewPrivateView.class)
    public Review persist(@Valid @RequestBody ReviewDTO reviewDTO, @PathVariable Long id) {
        return reviewService.persist(reviewDTO, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
