package HB_CAPE_MAK.hb_cape_makindu.repository;

import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPseudo(String username);

    @Query("SELECT u FROM User u WHERE TYPE(u) != Moderator")
    List<User> findAllExceptModerators();

    @Query("SELECT u FROM User u WHERE TYPE(u) != Gamer")
    List<User> findAllExceptGamers();

    @Query("SELECT u FROM User u WHERE TYPE(u) != Gamer AND TYPE(u) != Moderator")
    List<User> findAllExceptGamersAndModerators();

    @Query("SELECT u FROM User u WHERE LOWER(u.pseudo) LIKE LOWER(CONCAT('%', :pseudo, '%'))")
    List<User> findByUserContainingIgnoreCase(String pseudo);

    boolean existsByEmail(String email);
}

