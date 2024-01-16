package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.entity.User;

import java.util.List;
import java.util.Optional;

public interface DAOServiceInterface<T> {
    List<T> findAll();

    Optional<T> findById(Long id);
}
