package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.ClassificationDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Classification;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.ClassificationRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassificationService implements DAOServiceInterface {
   private ClassificationRepository classificationRepository;
    @Override
    public List findAll() {
        return classificationRepository.findAll();
    }

    @Override
    public Optional<Classification> findById(Long id) {
        return classificationRepository.findById(id);
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
