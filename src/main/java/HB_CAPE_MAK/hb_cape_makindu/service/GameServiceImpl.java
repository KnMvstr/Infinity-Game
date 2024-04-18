package HB_CAPE_MAK.hb_cape_makindu.service;


import HB_CAPE_MAK.hb_cape_makindu.DTO.GameDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.PublisherDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.entity.Publisher;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GameRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOSearchInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class GameServiceImpl implements DAOEntityInterface<Game>, DAOSearchInterface<Game> {
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
            // Attempt to find the existing platform by ID
            game = gameRepository.findById(id)
                    .orElseThrow(() -> new NotFoundCapEntException("Game", "id", id));

            // Update the existing platform's fields
            game.setName(gameDTO.getName());
            // Add any other fields from the DTO you wish to update
        } else {
            // If no ID is provided, create a new platform
            game = new Game();
            game.setName(gameDTO.getName());
            // Initialize any other fields as necessary
        }
        // Save the updated platform or the new platform
        return gameRepository.saveAndFlush(game);
    }

    //Methode to delete a record
    public void delete(Long id) {
        gameRepository.deleteById(id);
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


}
