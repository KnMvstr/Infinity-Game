package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPostDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPutDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    //Create new User
    @PostMapping
    User create(@Valid @RequestBody UserPostDTO user) {
        return userService.create(user);
    }

    //Get user by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User userFound = userService.findById((id));
        return new ResponseEntity<User>(userFound, HttpStatus.OK);
    }
    //Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }
    //Update user
    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserPutDTO userPutDTO) {
        User userUpdated = userService.update(id, userPutDTO);
        return new ResponseEntity<>(userUpdated, HttpStatus.ACCEPTED);
    }

    //Delete user
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}