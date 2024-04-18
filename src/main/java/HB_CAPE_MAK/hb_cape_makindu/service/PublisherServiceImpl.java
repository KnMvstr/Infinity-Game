package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.PublisherDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Publisher;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.PublisherRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOSearchInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements DAOEntityInterface<Publisher>, DAOSearchInterface<Publisher> {
    @Autowired
    private final PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public List<Publisher> findAllSorted() {
        return publisherRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Publisher> search(String query) {
        return publisherRepository.findByPublisherContainingIgnoreCase(query);
    }

    public List<Publisher> findAllByFieldAndDirection(String field, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
        return publisherRepository.findAll(sort);
    }

    //Method to add record in Database
    public Publisher persist(PublisherDTO publisherDTO, Long id) {
        Publisher publisher;
        if (id != null) {
            // Attempt to find the existing publisher by ID
            publisher = publisherRepository.findById(id)
                    .orElseThrow(() -> new NotFoundCapEntException("Publisher", "id", id));

            // Update the existing publisher's fields
            publisher.setName(publisherDTO.getName());
            // Add any other fields from the DTO you wish to update
        } else {
            // If no ID is provided, create a new publisher
            publisher = new Publisher();
            publisher.setName(publisherDTO.getName());
            // Initialize any other fields as necessary
        }

        // Save the updated publisher or the new publisher
        return publisherRepository.saveAndFlush(publisher);
    }


    //Methode to delete a record
    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }


}

