package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.PlatformRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


}
