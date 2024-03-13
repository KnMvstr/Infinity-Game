package HB_CAPE_MAK.hb_cape_makindu.service;


import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GameRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImpl implements DAOEntityInterface<Game> {
    @Autowired
    private GameRepository gameRepository;

    //Method to get all
    @Override
    public List<Game> findAll() { return gameRepository.findAll(); }


    //Method to get all sorted in case we add many more
    @Override
    public List<Game> findAllSorted() { return gameRepository.findAllByOrderByNameAsc();}

    //Method to find by Id
    public Game findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    //Method to find by Slug
    public Game findBySlug(String slug) {
        return gameRepository.findBySlug(slug)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Game> findTop4ByOrderByCountReview() {
        return gameRepository.findTop4ByOrderByCountReview();
    }

    //Method to add record in Database
    public Game persist(GameDTO gameDTO, Long id) {
        if (id != null && gameRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Game", "id", id
            );
        }
        Game game = new Game();
        game.setName(gameDTO.getName());

        return gameRepository.saveAndFlush(game);
    }

    //Methode to delete a record
    public void delete(Long id) {
        gameRepository.deleteById(id);
    }

}
