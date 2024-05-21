package InfinityGaming.service;

import InfinityGaming.DTO.ClassificationDTO;
import InfinityGaming.entity.Classification;
import InfinityGaming.exception.NotFoundCapEntException;
import InfinityGaming.repository.ClassificationRepository;
import InfinityGaming.service.interfaces.DAOEntityInterface;
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
