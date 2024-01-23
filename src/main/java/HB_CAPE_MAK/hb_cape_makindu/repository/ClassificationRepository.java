package HB_CAPE_MAK.hb_cape_makindu.repository;

import HB_CAPE_MAK.hb_cape_makindu.entity.Classification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    Optional findByName(String name);
}

