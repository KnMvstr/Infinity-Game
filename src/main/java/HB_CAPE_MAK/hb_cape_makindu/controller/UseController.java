package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UseController {
    @Autowired
    UserServiceImpl userService;

    //Create new User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    //Get user by id
    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> findById(@PathVariable String userId) {
        User userFound = userService.findById(Long.valueOf(userId));
        return new ResponseEntity<User>(userFound, HttpStatus.OK);
    }

    //Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }

    //Update user
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User UserUpdated = userService.updateUser(user);
        return new ResponseEntity<>(UserUpdated, HttpStatus.ACCEPTED);
    }

    //Delete user
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
