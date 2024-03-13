package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.BusinessModelDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.PlatformDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.PlatformRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOEntityInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //Method to add record in Database
    public Platform persist(PlatformDTO platformDTO, Long id) {
        if (id != null && platformRepository.findById(id).isEmpty()) {
            throw new NotFoundCapEntException(
                    "Platform", "id", id
            );
        }
        Platform platform = new Platform();
        platform.setName(platformDTO.getName());

        return platformRepository.saveAndFlush(platform);
    }

    //Methode to delete a record
    public void delete(Long id) {
        platformRepository.deleteById(id);
    }


}
