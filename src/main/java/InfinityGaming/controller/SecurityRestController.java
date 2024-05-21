package InfinityGaming.controller;

import InfinityGaming.service.UserServiceImpl;
import InfinityGaming.DTO.UserLoginDTO;
import InfinityGaming.DTO.UserPostDTO;
import InfinityGaming.custom_response.JwtTokenResponse;
import InfinityGaming.entity.User;
import InfinityGaming.service.security.JwtAuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class SecurityRestController {

    private final UserServiceImpl userService;
    private final JwtAuthenticationService jwtAuthenticationService;

    // Explicit constructor for clarity
    public SecurityRestController(UserServiceImpl userService, JwtAuthenticationService jwtAuthenticationService) {
        this.userService = userService;
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return jwtAuthenticationService.authenticate(userLoginDTO);
    }

    @PostMapping("/register")
    public User register(@RequestBody @Valid UserPostDTO userDTO) {
        return userService.create(userDTO);
    }
}
