package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.ReviewDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Gamer;
import HB_CAPE_MAK.hb_cape_makindu.entity.Review;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.ReviewRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements DAOEntityInterface<Review> {

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

        // Si id = null, le save fera un insert, sinon un update
        return reviewRepository.saveAndFlush(r);
    }
    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public Page<Review> findAllByGame(Game game, Pageable pageable) {
        return reviewRepository.findAllByGame(game, pageable);
    }


    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
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
}
