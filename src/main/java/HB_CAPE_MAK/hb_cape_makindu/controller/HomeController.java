package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.UserDTO;
import HB_CAPE_MAK.hb_cape_makindu.mapping.UrlRoute;
import HB_CAPE_MAK.hb_cape_makindu.service.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(path = "/", name = "AppHome")
@AllArgsConstructor
public class HomeController {

    @GetMapping("/")
    public ModelAndView index(
            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("index");
        return mav;
    }

}
