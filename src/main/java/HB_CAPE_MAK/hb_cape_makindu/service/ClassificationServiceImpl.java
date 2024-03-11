package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.entity.Classification;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.ClassificationRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ClassificationServiceImpl implements DAOEntityInterface<Classification> {
   private ClassificationRepository classificationRepository;

    @Override
    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    @Override
    public List<Classification> findAllSorted() {
        return classificationRepository.findAllByOrderByNameAsc();
    }


    @Override
    public Classification findById(Long id) {
        return classificationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


}
