package InfinityGaming.service;

import InfinityGaming.DTO.BusinessModelDTO;
import InfinityGaming.entity.BusinessModel;
import InfinityGaming.exception.NotFoundCapEntException;
import InfinityGaming.repository.BusinessModelRepository;
import InfinityGaming.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<BusinessModel> findAllByFieldAndDirection(String field, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
        return businessModelRepository.findAll(sort);
    }

    //Method to add record in Database
    public BusinessModel persist(BusinessModelDTO businessModelDTO, Long id) {
        BusinessModel businessModel;
        if (id != null) {
            // Attempt to find the existing platform by ID
            businessModel = businessModelRepository.findById(id)
                    .orElseThrow(() -> new NotFoundCapEntException("BusinessModel", "id", id, "No game found with ID: " + id));

            // Update the existing platform's fields
            businessModel.setName(businessModelDTO.getName());
            // Add any other fields from the DTO you wish to update
        } else {
            // If no ID is provided, create a new platform
            businessModel = new BusinessModel();
            businessModel.setName(businessModelDTO.getName());
            // Initialize any other fields as necessary
        }
        // Save the updated platform or the new platform
        return businessModelRepository.saveAndFlush(businessModel);
    }

    //Methode to delete a record
    public void delete(Long id) {
        businessModelRepository.deleteById(id);
    }

}
