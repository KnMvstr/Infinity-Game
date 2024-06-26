package InfinityGaming.controller;

import InfinityGaming.DTO.UserDTO;
import InfinityGaming.mapping.UrlRoute;
import InfinityGaming.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class SecurityController {

    private UserServiceImpl userServiceImpl;

    @GetMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(ModelAndView mav) {
        mav.setViewName("security/register");
        mav.addObject("userForm", new UserDTO());
        return mav;
    }

    @PostMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(
            @Valid @ModelAttribute("userForm") UserDTO userForm,
            BindingResult bindingResult,
            ModelAndView mav
    ) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("security/register");
            return mav;
        }
        userServiceImpl.create(userForm);
        mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_LOGIN)
    public ModelAndView login(ModelAndView mav, Principal principal, String error) {
        if (principal != null)
        {
            mav.setViewName("redirect:" + "/");
            return mav;
        }
        if (error != null) {
            mav.addObject("error", "Your username or password is invalid.");
        }
        mav.setViewName("security/login");
        return mav;
    }
}
