package InfinityGaming.repository;


import InfinityGaming.entity.Genre;
import InfinityGaming.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends EntityNomenclatureRepository<Genre>, JpaRepository<Genre, Long> {

   Optional findByName(String name);

   List<Genre> findAllByNameOrderByName(String name);
}
