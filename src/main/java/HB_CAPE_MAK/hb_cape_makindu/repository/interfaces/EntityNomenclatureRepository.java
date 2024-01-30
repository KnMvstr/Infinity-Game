
package HB_CAPE_MAK.hb_cape_makindu.repository.interfaces;

import java.util.Optional;

public interface EntityNomenclatureRepository<T> {

    Optional<T> findByName(String name);

    Optional<T> findBySlug(String slug);

}
