package InfinityGaming.repository;

import InfinityGaming.entity.Publisher;
import InfinityGaming.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends EntityNomenclatureRepository<Publisher>, JpaRepository<Publisher, Long> {
    Optional findByName(String name);

    @Query("SELECT p FROM Publisher p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%%', :name, '%%'))")
    List<Publisher> findByPublisherContainingIgnoreCase(String name);
}
