package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GenreRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements DAOEntityInterface {
    @Autowired
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List <Genre> findAllSorted() {
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

     public Object findByName(String name) throws Throwable {
        return genreRepository.findByName( name)
                .orElseThrow(EntityNotFoundException::new);
    }
}
