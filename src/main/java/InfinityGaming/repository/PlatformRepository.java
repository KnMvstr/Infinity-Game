package InfinityGaming.repository;

import InfinityGaming.entity.Platform;
import InfinityGaming.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatformRepository extends EntityNomenclatureRepository<Platform>, JpaRepository<Platform, Long> {

    Optional findByName(String name);
}
