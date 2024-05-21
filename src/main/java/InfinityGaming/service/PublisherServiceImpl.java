package InfinityGaming.service;

import InfinityGaming.DTO.PublisherDTO;
import InfinityGaming.entity.Publisher;
import InfinityGaming.exception.NotFoundCapEntException;
import InfinityGaming.repository.PublisherRepository;
import InfinityGaming.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements DAOEntityInterface<Publisher> {
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

    public Publisher persist(PublisherDTO publisherDTO, Long id) {
        if (id != null && publisherRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Publisher", "id", id
            );
        }

        Publisher p = new Publisher();
        p.setId(id);
        p.setName(publisherDTO.getName());

        // Si id = null, le save fera un insert, sinon un update
        return publisherRepository.saveAndFlush(p);
    }
}
