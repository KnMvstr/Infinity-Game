package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.PublisherDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.entity.Publisher;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundInstantFakingException;
import HB_CAPE_MAK.hb_cape_makindu.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService implements DAOServiceInterface {
    private final PublisherRepository publisherRepository;
    @Override
    public List findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return publisherRepository.findById(id);
    }

    public Publisher persist(PublisherDTO publisherDTO, Long id) {
        if (id != null && publisherRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
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
