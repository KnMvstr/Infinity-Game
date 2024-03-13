package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.BusinessModelDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.BusinessModelRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BusinessModelServiceImpl implements DAOEntityInterface<BusinessModel> {
    @Autowired
    private BusinessModelRepository businessModelRepository;

    //Method to get all
    @Override
    public List<BusinessModel> findAll() {
        return businessModelRepository.findAll();
    }

    //Method to get all sorted in case we add many more
    @Override
    public List<BusinessModel> findAllSorted() {
        return businessModelRepository.findAllByOrderByNameAsc();
    }

    //Method to find by Id
    @Override
    public BusinessModel findById(Long id) {
        return businessModelRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    //Method to add record in Database
    public BusinessModel persist(BusinessModelDTO businessModelDTO, Long id) {
        if (id != null && businessModelRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "BusinessModel", "id", id
            );
        }
        BusinessModel brand = new BusinessModel();
        brand.setName(businessModelDTO.getName());

        return businessModelRepository.saveAndFlush(brand);
    }

    //Methode to delete a record
    public void delete(Long id) {
        businessModelRepository.deleteById(id);
    }

}
