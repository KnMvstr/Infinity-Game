package InfinityGaming.service;

import InfinityGaming.DTO.UserDTO;
import InfinityGaming.entity.Gamer;
import InfinityGaming.entity.Moderator;
import InfinityGaming.entity.User;
import InfinityGaming.repository.UserRepository;
import InfinityGaming.service.interfaces.DAOFindByEmailInterface;
import InfinityGaming.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements DAOFindByIdInterface<User>, DAOFindByEmailInterface, UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepository.findByPseudo(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getPseudo(),
                user.getPassword(),
                userGrantedAuthority(user)
        );
    }


    private List<GrantedAuthority> userGrantedAuthority(User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_GAMER"));
    }


    public User create(UserDTO userDTO) {
        User user = new Gamer();
        user.setPseudo(userDTO.getPseudo());
        user.setEmail(userDTO.getUsername().toLowerCase());
        user.setPwd(passwordEncoder.encode(userDTO.getPwd()));

        return userRepository.saveAndFlush(user);
    }
}

//    public User edit(Long id, UserPutDTO userPutDTO) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser.isEmpty()) { // OBLIGATOIRE CAR ON A UN OPTIONAL
//            throw new NotFoundInstantFakingException("User", "id", id); //Mettre en template
//        }
//        User user = optionalUser.get();
//        user.setEmail(userPutDTO.getEmail());
//        user.setPwd(userPutDTO.getPwd());
//        // Pas besoin de save dans le cas d'un objet modifié
//        // C'est-à-dire un objet qui a DEJA été persisté en BDD
//        userRepository.flush();
//        return user;
//    }

