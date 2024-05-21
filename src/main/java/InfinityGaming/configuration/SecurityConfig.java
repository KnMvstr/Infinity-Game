package InfinityGaming.configuration;

import InfinityGaming.configuration.jwt.JwtTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig  {
    private BCryptPasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .securityMatcher("/api/**")
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                                auth
                                        .requestMatchers("/api/login").permitAll()
                                        .requestMatchers("/api/register").permitAll()
                                        .requestMatchers("/api/user/**").permitAll()
                                        .requestMatchers("/api/business/**").permitAll()
                                        .requestMatchers("/api/genre/**").permitAll()
                                        .requestMatchers("/api/classification/**").permitAll()
                                        .requestMatchers("/api/game/**").permitAll()
                                        .requestMatchers("/api/publisher/**").permitAll()
                                        .requestMatchers("/api/platform/**").permitAll()
                                        .requestMatchers("/api/review/**").permitAll()
                                        .requestMatchers("auth/**").permitAll()

                                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                                        .requestMatchers(HttpMethod.PUT, "/api/**").permitAll()
                                        .requestMatchers(HttpMethod.DELETE, "/api/**").permitAll()

                        //        .requestMatchers(HttpMethod.GET, "/api/user").permitAll()
                        //        .requestMatchers(HttpMethod.GET, "/api/business").permitAll()
                        //        .requestMatchers(HttpMethod.GET, "/api/genre").permitAll()
                        //        .requestMatchers(HttpMethod.GET, "/api/classification").permitAll()
                        //        .requestMatchers(HttpMethod.GET, "/api/game").permitAll()
                        //        .requestMatchers(HttpMethod.GET, "/api/publisher").permitAll()
                        //        .requestMatchers(HttpMethod.GET, "/api/platform").permitAll()
                        //        .requestMatchers(HttpMethod.GET, "/api/review").permitAll()

                        //        .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                        //        .requestMatchers(HttpMethod.POST, "/api/business").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.POST, "/api/genre").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.POST, "/api/classification").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.POST, "/api/game").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.POST, "/api/publisher").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.POST, "/api/platform").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.POST, "/api/review").hasAuthority("ROLE_GAMER")

                        //        .requestMatchers(HttpMethod.PUT, "/api/user").permitAll()
                        //        .requestMatchers(HttpMethod.PUT, "/api/business").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.PUT, "/api/genre").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.PUT, "/api/classification").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.PUT, "/api/game").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.PUT, "/api/publisher").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.PUT, "/api/platform").hasAuthority("ROLE_MODERATOR")
                        ////      .requestMatchers(HttpMethod.PUT, "/api/review").hasAuthority("ROLE_GAMER")

                        //        .requestMatchers(HttpMethod.DELETE, "/api/user").permitAll()
                        //        .requestMatchers(HttpMethod.DELETE, "/api/business").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.DELETE, "/api/genre").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.DELETE, "/api/classification").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.DELETE, "/api/game").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.DELETE, "/api/publisher").hasAuthority("ROLE_MODERATOR")
                        //        .requestMatchers(HttpMethod.DELETE, "/api/platform").hasAuthority("ROLE_SUPER ADMIN")
                        //      .requestMatchers(HttpMethod.DELETE, "/api/review").hasAuthority("ROLE_MODERATOR")
                )
        ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return http
                .authenticationProvider(authProvider)
                .getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
}