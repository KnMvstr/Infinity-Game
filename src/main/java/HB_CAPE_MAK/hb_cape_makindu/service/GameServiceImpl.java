package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundInstantFakingException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GameRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.GameServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameServiceInterface {
    private GameRepository gameRepository;
    @Override
    public List findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> findAllByOrderByNameAsc() {
        return gameRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Game> findAllByOrderByNameDesc() {
        return gameRepository.findAllByOrderByNameDesc();
    }

    @Override
    public Optional<Game> findByNameIsLikeIgnoreCase(String name) {
        return gameRepository.findByNameIsLikeIgnoreCase(name);
    }

    public Game persist(GameDTO gameDTO, Long id) {
        if (id != null && gameRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Game", "id", id
            );
        }

        Game p = new Game();
        p.setId(id);
        p.setReleaseDate(gameDTO.getReleaseDate());
        p.setGenre(gameDTO.getGenre());
        p.setPublisher(gameDTO.getPublisher());
        p.setBusinessModel(gameDTO.getBusinessModel());
        p.setClassification(gameDTO.getClassification());
        p.setName(gameDTO.getName());

        // Si id = null, le save fera un insert, sinon un update
        return gameRepository.saveAndFlush(p);
    }
}
