package HB_CAPE_MAK.hb_cape_makindu.service.interfaces;

import java.util.List;

public interface DAOEntityInterface<T> extends
            DAOFindByIdInterface<T>
{

    List<T> findAll();


}
