package HB_CAPE_MAK.hb_cape_makindu.service.interfaces;

import java.util.List;

public interface DAOFindAllInterface<T> {

    List<T> findAll();
    List<T> findAllSorted();
}
