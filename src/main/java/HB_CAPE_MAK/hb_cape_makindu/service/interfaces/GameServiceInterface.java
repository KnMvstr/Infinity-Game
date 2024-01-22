package HB_CAPE_MAK.hb_cape_makindu.service.interfaces;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameServiceInterface {

    List <Game> findAll();

    List<Game> findAllByOrderByNameAsc();

    Optional<Game> findById(Long id);

    List<Game> findAllByOrderByNameDesc();

    Optional <Game> findByNameIsLikeIgnoreCase(String name);
}
