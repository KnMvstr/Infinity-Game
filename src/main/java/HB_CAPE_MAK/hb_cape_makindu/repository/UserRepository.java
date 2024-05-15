package HB_CAPE_MAK.hb_cape_makindu.repository;

import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPseudo(String pseudo);

    @Query("SELECT u FROM User u WHERE TYPE(u) != Moderator")
    List<User> findAllExceptModerators();

    @Query(value ="select u from User u where type(u) != Gamer", nativeQuery = true)
    List<User> findAllExceptGamers();

    @Query(value ="select u from User u where type(u) != Gamer and type(u) != Moderator" , nativeQuery = true)
    List<User> findAllExceptGamersAndModerators();

    @Query(value ="select u from User u where LOWER(u.pseudo) like lower(CONCAT('%%', :pseudo, '%%'))", nativeQuery = true)
    List<User> findByPseudoContainingIgnoreCase(String pseudo);

    boolean existsByEmail(String email);
}

