package HB_CAPE_MAK.hb_cape_makindu.service.security;


import HB_CAPE_MAK.hb_cape_makindu.DTO.UserLoginDTO;
import HB_CAPE_MAK.hb_cape_makindu.configuration.jwt.JwtTokenUtil;
import HB_CAPE_MAK.hb_cape_makindu.custom_response.JwtTokenResponse;
import HB_CAPE_MAK.hb_cape_makindu.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserServiceImpl userService;

    public ResponseEntity<JwtTokenResponse> authenticate(UserLoginDTO userLoginDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getUsername(),
                            userLoginDTO.getPassword()
                    )
            );

            UserDetails user = userService.loadUserByUsername(authenticate.getName());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            System.out.println("Login :");
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            user.getAuthorities().forEach(System.out::println);

            String token = jwtTokenUtil.generateAccessToken(user.getUsername(), user.getAuthorities().toString());
            return ResponseEntity.ok(new JwtTokenResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}