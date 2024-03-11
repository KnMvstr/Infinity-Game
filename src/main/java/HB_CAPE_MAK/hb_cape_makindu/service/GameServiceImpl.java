package HB_CAPE_MAK.hb_cape_makindu.service;


import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.entity.Moderator;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GameRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImpl implements DAOFindByIdInterface<Game> {
    private GameRepository gameRepository;
    private UserServiceImpl userService;


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



}
