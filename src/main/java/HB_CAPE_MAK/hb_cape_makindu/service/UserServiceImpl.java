package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.UserDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Gamer;
import HB_CAPE_MAK.hb_cape_makindu.entity.Moderator;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundCapEntException;
import HB_CAPE_MAK.hb_cape_makindu.repository.UserRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.UserServiceInterface;
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
public class UserServiceImpl implements UserServiceInterface, UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
//                .orElseThrow(EntityNotFoundException::getValue);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
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
            return List.of(new SimpleGrantedAuthority("MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("GAMER"));
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

