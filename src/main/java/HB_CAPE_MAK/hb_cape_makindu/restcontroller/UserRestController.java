package HB_CAPE_MAK.hb_cape_makindu.restcontroller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPostDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPutDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
//Indique que cette classe est un contrôleur
@RestController
//indique le chemin du web service et la méthode ( GET par défaut)
@RequestMapping("/api/user")
public class UserRestController {
    private UserService userService;

    @GetMapping //La méthode GET par défaut
    @JsonView(JsonViews.UserFullView.class)
    List<User> list() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.UserPrivateView.class)
    Optional<User> show(@PathVariable Long id) {
        return userService.findById(id);
    }


    @PostMapping
    @JsonView(JsonViews.UserPublicView.class)
    User create(@Valid @RequestBody UserPostDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping(path = "/{id}")
    @JsonView(JsonViews.UserPrivateView.class)
    User edit(@Valid @RequestBody UserPutDTO userDTO, @PathVariable Long id) {
        return userService.edit(id, userDTO);
    }

}
