package HB_CAPE_MAK.hb_cape_makindu.repository;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //    List<Review> findAllByCreatedAOrderByCreatedAtCreatedAtAsc;
    List<Review> findAllByCreatedAtOrderByCreatedAtAsc(LocalDateTime createdAt);

    List<Review> findAllByCreatedAtOrderByCreatedAtDesc(LocalDateTime createdAt);

    @Query("SELECT r FROM Review r WHERE r.game = :game")
    List<Review> findAllByGame(@Param("game") Game game);

    Page<Review> findByModeratorIsNotNullOrGamerPseudo(String pseudo, Pageable pageable);

    Page<Review> findByModeratorIsNull(Pageable pageable);
    Page<Review> findByModeratorIsNotNull(Pageable pageable);

    List<Review> findByGameId(Long gameId);

}
