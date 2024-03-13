package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.BusinessModelDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.ClassificationDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.entity.Classification;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.ClassificationRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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


    //Method to add record in Database
    public Classification persist(ClassificationDTO classificationDTO, Long id) {
        if (id != null && classificationRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Classification", "id", id
            );
        }
        Classification classification = new Classification();
        classification.setName(classificationDTO.getName());

        return classificationRepository.saveAndFlush(classification);
    }

    //Methode to delete a record
    public void delete(Long id) {
        classificationRepository.deleteById(id);
    }



}
