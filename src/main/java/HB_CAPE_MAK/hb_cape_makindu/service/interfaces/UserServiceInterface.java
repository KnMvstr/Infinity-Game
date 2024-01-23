package HB_CAPE_MAK.hb_cape_makindu.service.interfaces;

import HB_CAPE_MAK.hb_cape_makindu.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

}
