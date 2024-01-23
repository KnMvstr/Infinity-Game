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
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameServiceImpl implements DAOFindByIdInterface<Game> {
    private GameRepository gameRepository;

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
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
