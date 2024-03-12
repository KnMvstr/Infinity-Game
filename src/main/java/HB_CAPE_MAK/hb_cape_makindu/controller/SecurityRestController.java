package HB_CAPE_MAK.hb_cape_makindu.controller;
import HB_CAPE_MAK.hb_cape_makindu.service.UserServiceImpl;
import HB_CAPE_MAK.hb_cape_makindu.DTO.UserLoginDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.UserPostDTO;
import HB_CAPE_MAK.hb_cape_makindu.custom_response.JwtTokenResponse;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.service.security.JwtAuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SecurityRestController {

    private UserServiceImpl userService;
    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping("/register")
    public User create(@RequestBody @Valid UserPostDTO userDTO) {
        return userService.create(userDTO);
    }

    @PostMapping("/login")
    ResponseEntity<JwtTokenResponse> create(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return jwtAuthenticationService.authenticate(userLoginDTO);
    }
}
