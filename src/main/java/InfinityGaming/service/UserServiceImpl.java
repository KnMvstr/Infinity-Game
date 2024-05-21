package InfinityGaming.service;


import InfinityGaming.DTO.UserPostDTO;
import InfinityGaming.DTO.UserPutDTO;
import InfinityGaming.entity.*;
import InfinityGaming.repository.UserRepository;
import InfinityGaming.service.interfaces.DAOFindByEmailInterface;
import InfinityGaming.service.interfaces.DAOFindByIdInterface;
import InfinityGaming.service.interfaces.DAOSearchInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements DAOFindByIdInterface<User>, DAOFindByEmailInterface<Boolean>, UserDetailsService, DAOSearchInterface<User> {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User create(UserPostDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("This email address is already in use");
        }
        User user;
        if ("GAMER".equalsIgnoreCase(userDTO.getUserType())) {
            user = new Gamer();
            // Initialisez spécifiquement un Gamer si nécessaire
        } else if ("SUPERADMIN".equalsIgnoreCase(userDTO.getUserType())) {
            user = new SuperAdmin();
            // Initialisez spécifiquement un Moderator si nécessaire
        } else if ("MODERATOR".equalsIgnoreCase(userDTO.getUserType())) {
            user = new Moderator();
            // Initialisez spécifiquement un Moderator si nécessaire
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
        user.setPseudo(userDTO.getPseudo()); // Assurez-vous que `setName` est censé être `setPseudo`
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setPwd(bCryptPasswordEncoder.encode(userDTO.getPwd()));

        return userRepository.saveAndFlush(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    // function that retrieve all Gamers from database
    public List<User> getAllGamers() {
        return userRepository.findAllExceptModerators();
    }

    // function that retrieve all Moderators from database
    public List<User> getAllModerators() {
        return userRepository.findAllExceptGamers();
    }

    // function that retrieve all Moderators from database
    public List<User> getAllSuperAdmins() {
        return userRepository.findAllExceptGamersAndModerators();
    }

    // function that update an existing user in database
    public User update(Long id, UserPutDTO userPutDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Met à jour les champs de l'utilisateur à partir de UserPutDTO
        if (userPutDTO.getPseudo() != null && !userPutDTO.getPseudo().isEmpty()) {
            user.setPseudo(userPutDTO.getPseudo());
        }
        if (userPutDTO.getPwd() != null && !userPutDTO.getPwd().isEmpty()) {
            user.setPwd(bCryptPasswordEncoder.encode(userPutDTO.getPwd()));
        }

        // Sauvegarde l'utilisateur mis à jour
        return userRepository.save(user);
    }


    // function that remove a user from database by id
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User findByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        User user = (User) userRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getPseudo(),
                user.getPassword(),
                userGrantedAuthority(user)
        );
    }

    @Override
    public List<User> search(String query) {
        return userRepository.findByPseudoContainingIgnoreCase(query);
    }

    private List<GrantedAuthority> userGrantedAuthority(User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        } else if (user instanceof SuperAdmin) {
            return List.of(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_GAMER"));
    }

    public List<User> findAllByFieldAndDirection(String field, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
        return userRepository.findAll(sort);
    }

    @Override
    public Boolean findByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}