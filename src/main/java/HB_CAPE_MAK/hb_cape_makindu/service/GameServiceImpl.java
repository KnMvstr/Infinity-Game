package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GameRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOSearchInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GameServiceImpl implements DAOEntityInterface<Game>, DAOSearchInterface<Game> {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class); //Upgrade log event display

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GameRepository gameRepository;

    //Method to get all
    @Override
    public List<Game> findAll() { return gameRepository.findAllWithDetails(); }


    //Method to get all sorted in case we add many more
    @Override
    public List<Game> findAllSorted() { return gameRepository.findAllByOrderByNameAsc();}

    @Override
    public List<Game> search(String query) {
        return gameRepository.findByGameContainingIgnoreCase(query);
    }

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

    public List<Game> findAllByFieldAndDirection(String field, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
        return gameRepository.findAll(sort);
    }

    ///Method to add record in Database
    public Game persist(GameDTO gameDTO, Long id) {
        Game game;
        if (id != null) {
            //Attempt to find a specific game by ID
            game = gameRepository.findById(id)
                    .orElseThrow(() -> new NotFoundCapEntException("Game", "id", id.toString(), "No game found with ID: " + id));
            logger.info("Updating existing game with ID: {}", id);

            game.setName(gameDTO.getName());
            game.setDescription(gameDTO.getDescription());
            game.setReleaseDate(gameDTO.getReleaseDate());
            game.setImage(gameDTO.getImage());
            game.setBackgroundImage(gameDTO.getBackgroundImage());
            game.setTrailer(gameDTO.getTrailer());
            game.setGenre(gameDTO.getGenre());
            game.setPublisher(gameDTO.getPublisher());
            game.setBusinessModel(gameDTO.getBusinessModel());
            game.setPlatforms(gameDTO.getPlatforms());
            game.setClassification(gameDTO.getClassification());
        } else {
            // If no ID is provided, create a new game
            game = new Game();
            game.setName(gameDTO.getName());
            game.setName(gameDTO.getName());
            game.setDescription(gameDTO.getDescription());
            game.setReleaseDate(gameDTO.getReleaseDate());
            game.setImage(gameDTO.getImage());
            game.setBackgroundImage(gameDTO.getBackgroundImage());
            game.setTrailer(gameDTO.getTrailer());
            game.setGenre(gameDTO.getGenre());
            game.setPublisher(gameDTO.getPublisher());
            game.setBusinessModel(gameDTO.getBusinessModel());
            game.setPlatforms(gameDTO.getPlatforms());
            game.setClassification(gameDTO.getClassification());
            logger.info("Creating new game");
        }
        // Save the updated game or the new game
        return gameRepository.saveAndFlush(game);
    }

    //Methode to delete a record
    public void delete(Long id) {
        gameRepository.deleteById(id);
        logger.info("Deleted game with ID: {}", id);
    }


    public List<Game> findAllGamesWithDetails() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> query = cb.createQuery(Game.class);
        Root<Game> game = query.from(Game.class);
        game.fetch("publisher", JoinType.LEFT); // Assure-toi que ces entités sont correctement jointes
        game.fetch("genre", JoinType.LEFT);
        game.fetch("platforms", JoinType.LEFT);
        game.fetch("classification", JoinType.LEFT);
        query.select(game).distinct(true);

        return entityManager.createQuery(query).getResultList();
    }

    // Method to get the count of games from JPA Repository which extends GameRepository
    public Long getGamesCount() {
        return gameRepository.count();
    }

}
