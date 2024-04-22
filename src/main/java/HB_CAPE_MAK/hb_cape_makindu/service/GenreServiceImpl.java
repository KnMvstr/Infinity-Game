package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GenreRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements DAOEntityInterface<Genre> {
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
    public List<Genre> findAllByFieldAndDirection(String field, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
        return genreRepository.findAll(sort);
    }


    //Method to add record in Database
    public Genre persist(GenreDTO genreDTO, Long id) {
        Genre genre;
        if (id != null) {
            // Attempt to find the existing platform by ID
            genre = genreRepository.findById(id)
                    .orElseThrow(() -> new NotFoundCapEntException("Genre", "id", id, "No game found with ID: " + id));

            // Update the existing platform's fields
            genre.setName(genreDTO.getName());
            // Add any other fields from the DTO you wish to update
        } else {
            // If no ID is provided, create a new platform
            genre = new Genre();
            genre.setName(genreDTO.getName());
            // Initialize any other fields as necessary
        }
        // Save the updated platform or the new platform
        return genreRepository.saveAndFlush(genre);
    }

    //Method to delete a record
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

}
