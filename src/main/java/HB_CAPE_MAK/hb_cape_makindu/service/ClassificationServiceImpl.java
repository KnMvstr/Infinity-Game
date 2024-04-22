package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.ClassificationDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Classification;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.ClassificationRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ClassificationServiceImpl implements DAOEntityInterface<Classification> {
    @Autowired
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

    public List<Classification> findAllByFieldAndDirection(String field, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
        return classificationRepository.findAll(sort);
    }

    //Method to add record in Database
    public Classification persist(ClassificationDTO classificationDTO, Long id) {
        Classification classification;
        if (id != null) {
            // Attempt to find the existing platform by ID
            classification = classificationRepository.findById(id)
                    .orElseThrow(() -> new NotFoundCapEntException("Classification", "id", id, "No game found with ID: " + id));
            // Update the existing platform's fields
            classification.setName(classificationDTO.getName());
            classification.setDescription(classificationDTO.getDescription());
            // Add any other fields from the DTO you wish to update
        } else {
            // If no ID is provided, create a new platform
            classification = new Classification();
            classification.setName(classificationDTO.getName());
            classification.setDescription(classificationDTO.getDescription());
            // Initialize any other fields as necessary
        }

        // Save the updated platform or the new platform
        return classificationRepository.saveAndFlush(classification);
    }

    //Methode to delete a record
    public void delete(Long id) {
        classificationRepository.deleteById(id);
    }


}
