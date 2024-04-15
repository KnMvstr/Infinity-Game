package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.BusinessModelDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.ReviewDTO;
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

    public List<Review> findAllByGame(Game game) {
        return (List<Review>) reviewRepository.findAllByGame(game);
    }

    public boolean findModeratedReview(String pseudo, Long id, Long moderate) {
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
//    public List<Review> findAllByField(String field) {
//        return reviewRepository.findAll(Sort.by(field));}

public List<Review> findAllByFieldAndDirection(String field, String direction) {
    Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
    return reviewRepository.findAll(sort);
}

    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundCapEntException("Review", "id", id));
    }

    public Review persist(ReviewDTO reviewDTO, Long id) {
        if (id != null && reviewRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Review", "id", id
            );
        }
        Review review = new Review();
        review.setDescription(reviewDTO.getDescription());

        return reviewRepository.saveAndFlush(review);
    }

    //Methode to delete a record
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

}

