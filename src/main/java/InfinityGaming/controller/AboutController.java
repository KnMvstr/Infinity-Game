package InfinityGaming.controller;

import InfinityGaming.mapping.UrlRoute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppAbout")
@AllArgsConstructor
public class AboutController {
    @GetMapping(path = UrlRoute.URL_ABOUT)
    public ModelAndView about(

            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        {
            mav.setViewName("about");
            return mav;
        }
    }
}
