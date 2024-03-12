package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.entity.*;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl {
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private UserServiceImpl userService;

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


    public Page<Review> findByUserPseudo(String pseudo, Pageable pageable) {
        return reviewRepository.findByModeratorIsNotNullOrGamerPseudo(pseudo, pageable);
    }
}
