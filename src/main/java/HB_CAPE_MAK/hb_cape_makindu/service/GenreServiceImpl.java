package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GenreRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Genre> findAllSorted() {
        return genreRepository.findAllByOrderByNameAsc();
    }




    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    //Method to add record in Database
    public Genre persist(GenreDTO genreDTO, Long id) {
        if (id != null && genreRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Genre", "id", id
            );
        }
        Genre genre = new Genre();
        genre.setName(genreDTO.getName());

        return genreRepository.saveAndFlush(genre);
    }

    //Methode to delete a record
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

}
