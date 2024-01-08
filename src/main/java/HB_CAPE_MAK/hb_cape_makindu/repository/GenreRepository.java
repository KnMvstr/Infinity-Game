package HB_CAPE_MAK.hb_cape_makindu.repository;


import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
