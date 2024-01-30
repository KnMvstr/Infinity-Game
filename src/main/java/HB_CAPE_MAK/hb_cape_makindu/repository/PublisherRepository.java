package HB_CAPE_MAK.hb_cape_makindu.repository;

import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.entity.Publisher;
import HB_CAPE_MAK.hb_cape_makindu.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends EntityNomenclatureRepository<Publisher>, JpaRepository<Publisher, Long> {
    Optional findByName(String name);
}
