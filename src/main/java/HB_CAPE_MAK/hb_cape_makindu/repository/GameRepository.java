package HB_CAPE_MAK.hb_cape_makindu.repository;

import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends EntityNomenclatureRepository<Game>, JpaRepository<Game, Long> {

    List<Game> findAllByOrderByNameAsc();

    List<Game> findAllByOrderByNameDesc();

    Optional <Game> findByNameIsLikeIgnoreCase (String name);

    @Query("select g from Game g join Review r on g.id = r.game.id group by g.id order by count(r.game.id) desc limit 4")
    List<Game> findTop4ByOrderByCountReview();

        Optional<Game> findBySlug(String slug);
}
