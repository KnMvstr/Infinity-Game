package InfinityGaming.repository;

import InfinityGaming.entity.Classification;

import InfinityGaming.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepository extends EntityNomenclatureRepository<Classification>, JpaRepository<Classification, Long> {

    Optional <Classification> findByName(String name);
}

