package HB_CAPE_MAK.hb_cape_makindu.repository;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
//    List<Review> findAllByCreatedAOrderByCreatedAtCreatedAtAsc;
List <Review> findAllByCreatedAtOrderByCreatedAtAsc(LocalDateTime createdAt);
List <Review> findAllByCreatedAtOrderByCreatedAtDesc(LocalDateTime createdAt);

}
