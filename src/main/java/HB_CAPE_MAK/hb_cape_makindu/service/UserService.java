package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.entity.Moderator;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.repository.UserRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements DAOServiceInterface<User>, UserDetailsService {

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepository.findByPseudo(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
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
}

//    public User create(UserPostDTO userDTO) {
//        User user = new User();
//        user.setPseudo(userDTO.getPseudo());
//        user.setEmail(userDTO.getEmail());
//        user.setBirthAt(userDTO.getBirthAt());
//        user.setPwd(userDTO.getPwd());
//        user.setCellPhoneNumber(userDTO.getCellPhoneNumber());
//
//        return userRepository.saveAndFlush(user);
//    }

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

