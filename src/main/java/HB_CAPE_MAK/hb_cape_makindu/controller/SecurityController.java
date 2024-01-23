package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.UserDTO;
import HB_CAPE_MAK.hb_cape_makindu.mapping.UrlRoute;
import HB_CAPE_MAK.hb_cape_makindu.service.UserServiceImpl;
import ch.qos.logback.core.joran.util.beans.BeanDescriptionFactory;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView login(ModelAndView mav, String error) {
        if (error != null) {
            mav.addObject("error", "Your username or password is invalid.");
        }
        mav.setViewName("security/login");
        return mav;
    }


}
