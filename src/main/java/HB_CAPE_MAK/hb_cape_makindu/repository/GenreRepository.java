package HB_CAPE_MAK.hb_cape_makindu.repository;


import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends EntityNomenclatureRepository<Genre>, JpaRepository<Genre, Long> {

    Optional findByName(String name);
}
