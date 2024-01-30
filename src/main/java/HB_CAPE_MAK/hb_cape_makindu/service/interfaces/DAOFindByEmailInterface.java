package HB_CAPE_MAK.hb_cape_makindu.service.interfaces;

import HB_CAPE_MAK.hb_cape_makindu.entity.User;

import java.util.Optional;

public interface DAOFindByEmailInterface <T>{
    Optional<T> findByEmail(String email);
}
