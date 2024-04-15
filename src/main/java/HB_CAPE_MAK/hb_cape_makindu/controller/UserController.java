package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPostDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPutDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import HB_CAPE_MAK.hb_cape_makindu.service.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    //Create new User
    @PostMapping(path = "/create")
    public User create(@Valid @RequestBody UserPostDTO userDTO) {
        return userService.create(userDTO);
    }

    //Get user by id
    @GetMapping(path = "/by_id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User userFound = userService.findById((id));
        return new ResponseEntity<User>(userFound, HttpStatus.OK);
    }

    //Get user by pseudo
    @GetMapping(path = "/by_pseudo/{pseudo}")
    @JsonView(JsonViews.UserPrivateView.class)
    public ResponseEntity<User> findByPseudo(@PathVariable String pseudo) {
        User userPseudo = userService.findByPseudo((pseudo));
        return new ResponseEntity<User>(userPseudo, HttpStatus.OK);
    }

    //Get all Users
    @GetMapping
    @JsonView(JsonViews.UserPrivateView.class)
    public ResponseEntity<List<User>> getAll() {
        List<User> usersList = userService.getAll();
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }

    //Get all Gamers
    @GetMapping (path = "/{gamers}")
    @JsonView(JsonViews.GamerPublicView.class)
    public ResponseEntity<List<User>> getAllGamers() {
        List<User> usersList = userService.getAllGamers();
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }

    //Get all Moderators
    @GetMapping (path = "/{moderators}")
    @JsonView(JsonViews.ModeratorPrivateView.class)
    public ResponseEntity<List<User>> getAllModerators() {
        List<User> usersList = userService.getAllModerators();
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }

    //Update user
    @PutMapping(path = "/edit/{id}")
    @JsonView(JsonViews.UserPrivateView.class)
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserPutDTO userPutDTO) {
        User userUpdated = userService.update(id, userPutDTO);
        return new ResponseEntity<>(userUpdated, HttpStatus.ACCEPTED);
    }

    //Delete user
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}