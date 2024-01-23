package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.ClassificationDTO;
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
    public Classification findById(Long id) {
        return classificationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Classification persist(ClassificationDTO classificationDTO, Long id) {
        if (id != null && classificationRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Business", "id", id
            );
        }

        Classification p = new Classification();
        p.setId(id);
        p.setName(classificationDTO.getName());
        p.setDescription(classificationDTO.getDescription());
        p.setImage(classificationDTO.getImage());

        // Si id = null, le save fera un insert, sinon un update
        return classificationRepository.saveAndFlush(p);
    }
}
