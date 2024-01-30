package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.mapping.UrlRoute;
import HB_CAPE_MAK.hb_cape_makindu.service.GameServiceImpl;
import HB_CAPE_MAK.hb_cape_makindu.service.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppReview")
@AllArgsConstructor
public class ReviewController {
    ReviewServiceImpl reviewService;
    @GetMapping(path = UrlRoute.URL_REVIEW + "/{id}", name = "show")
    public ModelAndView review(
            @PathVariable Long id,
            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        {
            mav.setViewName("review");
            mav.addObject("review", reviewService.findById(id));

            return mav;
        }
    }
}
