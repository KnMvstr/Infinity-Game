package HB_CAPE_MAK.hb_cape_makindu.controller;
import HB_CAPE_MAK.hb_cape_makindu.service.UserServiceImpl;
import HB_CAPE_MAK.hb_cape_makindu.DTO.UserLoginDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPostDTO;
import HB_CAPE_MAK.hb_cape_makindu.custom_response.JwtTokenResponse;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.service.security.JwtAuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
