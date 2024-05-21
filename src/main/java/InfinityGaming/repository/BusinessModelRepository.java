package InfinityGaming.repository;

import InfinityGaming.entity.BusinessModel;
import InfinityGaming.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessModelRepository extends EntityNomenclatureRepository<BusinessModel>, JpaRepository<BusinessModel, Long> {
}
