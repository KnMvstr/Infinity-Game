package InfinityGaming.service.interfaces;

import java.util.List;

public interface DAOSearchInterface <T> {

    List<T>search(String query);
}
