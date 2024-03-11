package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
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

    @Override
    public List<BusinessModel> findAll() {
        return businessModelRepository.findAll();
    }

    @Override
    public List<BusinessModel> findAllSorted() {
        return businessModelRepository.findAllByOrderByNameAsc();
    }

    @Override
    public BusinessModel findById(Long id) {
        return businessModelRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
