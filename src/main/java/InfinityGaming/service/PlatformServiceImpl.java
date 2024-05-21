package InfinityGaming.service;

import InfinityGaming.DTO.PlatformDTO;
import InfinityGaming.entity.Platform;
import InfinityGaming.exception.NotFoundCapEntException;
import InfinityGaming.repository.PlatformRepository;
import InfinityGaming.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformServiceImpl implements DAOEntityInterface<Platform> {

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


    public Platform persist(PlatformDTO platformDTO, Long id) {
        if (id != null && platformRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Platform", "id", id
            );
        }

        Platform p = new Platform();
        p.setId(id);
        p.setName(platformDTO.getName());


        // Si id = null, le save fera un insert, sinon un update
        return platformRepository.saveAndFlush(p);
    }
}
