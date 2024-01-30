package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GameRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImpl implements DAOFindByIdInterface<Game> {
    private GameRepository gameRepository;

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
    public Game findBySlug(String slug) {
        return gameRepository.findBySlug(slug)
                .orElseThrow(EntityNotFoundException::new);
    }
    public List<Game> findTop4ByOrderByCountReview() {
        return gameRepository.findTop4ByOrderByCountReview();
    }

    public Game persist(GameDTO gameDTO, Long id) {
        if (id != null && gameRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
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
