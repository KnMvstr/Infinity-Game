package InfinityGaming.service;

import InfinityGaming.DTO.ReviewDTO;
import InfinityGaming.entity.*;
import InfinityGaming.exception.NotFoundCapEntException;
import InfinityGaming.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl {

    private final ReviewRepository reviewRepository;
    private UserServiceImpl userService;

    public Review persist(ReviewDTO reviewDTO, Long id) {
        if (id != null && reviewRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Review", "id", id
            );
        }

        Review r = new Review();
        r.setId(id);
        r.setDescription(reviewDTO.getDescription());
        r.setRating(reviewDTO.getRating());
        r.setImage(reviewDTO.getImage());
        r.setGamer(reviewDTO.getGamer());
        r.setGame(reviewDTO.getGame());
        r.setCreatedAt(LocalDateTime.now());

        // Si id = null, le save fera un insert, sinon un update
        return reviewRepository.saveAndFlush(r);
    }
    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public Page<Review> findAllByGame(Game game, Pageable pageable) {
        return reviewRepository.findAllByGame(game, pageable);
    }

    public boolean moderateReview(String pseudo, Long id, Long moderate) {
        Review review = findById(id);
        boolean isModerate = true;
        if (moderate == 1L) {
            review.setModerator((Moderator) userService.findByPseudo(pseudo));
            review.setModeratedAt(LocalDateTime.now());
        } else {
            reviewRepository.delete(review);
            isModerate = false;
        }
        reviewRepository.flush();
        return isModerate;
    }



    public List<Review> findAll() {
        return reviewRepository.findAll();
    }



    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundCapEntException("Review", "id", id));
    }

    public Review createReview(ReviewDTO reviewDTO, Game game, String name) {
        Review review = new Review();
        review.setGame(game);
        review.setGamer((Gamer) userService.findByPseudo(name));
        review.setDescription(reviewDTO.getDescription());
        review.setRating(reviewDTO.getRating());
        return reviewRepository.saveAndFlush(review);
    }

    public Page<Review> findByUserPseudo(String pseudo, Pageable pageable) {
        return reviewRepository.findByModeratorIsNotNullOrGamerPseudo(pseudo, pageable);
    }

    public Page<Review> getPageReviewByPseudo(String pseudo, Pageable pageable) {
        User user = userService.findByPseudo(pseudo);
        Page<Review> pageReviews = findByUserPseudo(pseudo, pageable);
        if (user.isModerator()) {
            Sort.Order order = pageable.getSort().getOrderFor("moderator");
            if (order != null) {
                if (order.isAscending()) {
                    pageReviews = reviewRepository.findByModeratorIsNull(pageable);
                } else {
                    pageReviews = reviewRepository.findByModeratorIsNotNull(pageable);
                }
            } else {
                pageReviews = reviewRepository.findAll(pageable);
            }
        }
        return pageReviews;
    }
}
