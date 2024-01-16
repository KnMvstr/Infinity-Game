package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.BusinessModelDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundInstantFakingException;
import HB_CAPE_MAK.hb_cape_makindu.repository.BusinessModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusinessModelService implements DAOServiceInterface {

    private BusinessModelRepository businessModelRepository;
    @Override
    public List findAll() {
        return businessModelRepository.findAll();
    }

    @Override
    public Optional<BusinessModel> findById(Long id) {
        return businessModelRepository.findById(id);
    }
    public BusinessModel persist(BusinessModelDTO businessModelDTO, Long id) {
        if (id != null && businessModelRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Business", "id", id
            );
        }

        BusinessModel p = new BusinessModel();
        p.setId(id);
        p.setName(businessModelDTO.getName());

        // Si id = null, le save fera un insert, sinon un update
        return businessModelRepository.saveAndFlush(p);
    }
}
