package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.ReviewDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Review;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundInstantFakingException;
import HB_CAPE_MAK.hb_cape_makindu.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService implements DAOServiceInterface {

    private final ReviewRepository reviewRepository;

    @Override
    public List findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review persist(ReviewDTO reviewDTO, Long id) {
        if (id != null && reviewRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Review", "id", id
            );
        }

        Review r = new Review();
        r.setId(id);
        r.setDescription(reviewDTO.getDescription());
        r.setNote(reviewDTO.getNote());
        r.setImage(reviewDTO.getImage());
        r.setUser(reviewDTO.getUser());
        r.setGame(reviewDTO.getGame());
        r.setModerator(reviewDTO.getModerator());



        // Si id = null, le save fera un insert, sinon un update
        return reviewRepository.saveAndFlush(r);
    }
}
