package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPostDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPutDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundInstantFakingException;
import HB_CAPE_MAK.hb_cape_makindu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements DAOServiceInterface<User> {

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User create(UserPostDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setName(userDTO.getName());
        user.setPseudo(userDTO.getPseudo());
        user.setEmail(userDTO.getEmail());
        user.setBirthAt(userDTO.getBirthAt());
        user.setPwd(userDTO.getPwd());
        user.setCellPhoneNumber(userDTO.getCellPhoneNumber());

        return userRepository.saveAndFlush(user);
    }

    public User edit(Long id, UserPutDTO userPutDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { // OBLIGATOIRE CAR ON A UN OPTIONAL
            throw new NotFoundInstantFakingException("User", "id", id); //Mettre en template
        }
        User user = optionalUser.get();
        user.setEmail(userPutDTO.getEmail());
        user.setBirthAt(userPutDTO.getBirthAt());
        user.setPwd(userPutDTO.getPwd());
        // Pas besoin de save dans le cas d'un objet modifié
        // C'est-à-dire un objet qui a DEJA été persisté en BDD
        userRepository.flush();
        return user;
    }
}
