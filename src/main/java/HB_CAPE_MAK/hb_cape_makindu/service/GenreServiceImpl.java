package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GenreRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements DAOEntityInterface {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List findAllSorted() {
        return genreRepository.findAllByOrderByNameAsc();
    }


    public List<Genre> findAllByNameOrderByName(String name) {
        return genreRepository.findAllByNameOrderByName(name);
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    public Genre persist(GenreDTO genreDTO, Long id) {
        if (id != null && genreRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Genre", "id", id
            );
        }

        Genre g = new Genre();
        g.setId(id);
        g.setName(genreDTO.getName());


        // Si id = null, le save fera un insert, sinon un update
        return genreRepository.saveAndFlush(g);
    }

    public Object findByName(String name) throws Throwable {
        return genreRepository.findByName( name)
                .orElseThrow(EntityNotFoundException::new);
    }
}
