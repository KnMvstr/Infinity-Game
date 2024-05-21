package InfinityGaming.service;

import InfinityGaming.DTO.PlatformDTO;
import InfinityGaming.entity.Platform;
import InfinityGaming.exception.NotFoundCapEntException;
import InfinityGaming.repository.PlatformRepository;
import InfinityGaming.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformServiceImpl implements DAOEntityInterface<Platform> {

    @Autowired
    private final PlatformRepository platformRepository;

    @Override
    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    @Override
    public List<Platform> findAllSorted() {
        return platformRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Platform findById(Long id) {
        return platformRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Platform> findAllByFieldAndDirection(String field, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
        return platformRepository.findAll(sort);
    }

    //Method to add record in Database
    public Platform persist(PlatformDTO platformDTO, Long id) {
        Platform platform;
        if (id != null) {
            // Attempt to find the existing platform by ID
            platform = platformRepository.findById(id)
                    .orElseThrow(() -> new NotFoundCapEntException("Platform", "id", id, "No game found with ID: " + id));

            // Update the existing platform's fields
            platform.setName(platformDTO.getName());
            // Add any other fields from the DTO you wish to update
        } else {
            // If no ID is provided, create a new platform
            platform = new Platform();
            platform.setName(platformDTO.getName());
            // Initialize any other fields as necessary
        }

        // Save the updated platform or the new platform
        return platformRepository.saveAndFlush(platform);
    }
    //Methode to delete a record
    public void delete(Long id) {
        platformRepository.deleteById(id);
    }

}
