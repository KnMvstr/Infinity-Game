package HB_CAPE_MAK.hb_cape_makindu.controller;


import HB_CAPE_MAK.hb_cape_makindu.service.GameServiceImpl;
import HB_CAPE_MAK.hb_cape_makindu.service.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(path = "/", name = "AppHome")
@AllArgsConstructor
public class HomeController {

    private GameServiceImpl gameService;
    private ReviewServiceImpl reviewService;

    @GetMapping("/")
    public ModelAndView index(
            ModelAndView mav,
            Principal principal,
            @PageableDefault(
                    size = 9, // nb Element par page
                    sort = {"createdAt"}, // order by
                    direction = Sort.Direction.DESC
            )
            Pageable pageable

    ) {
        if (principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("index");
        mav.addObject("pageReviews", reviewService.findAll(pageable));
        mav.addObject("Top4TrendGames", gameService.findTop4ByOrderByCountReview());
        return mav;
    }
}
