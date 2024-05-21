package InfinityGaming.service.interfaces;

import java.util.Optional;

public interface DAOFindByEmailInterface <T>{
    Optional<T> findByEmail(String email);
}
