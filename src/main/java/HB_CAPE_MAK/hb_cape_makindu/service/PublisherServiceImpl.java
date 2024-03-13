package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.entity.Publisher;
import HB_CAPE_MAK.hb_cape_makindu.repository.PublisherRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements DAOEntityInterface<Publisher> {
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
    
}

