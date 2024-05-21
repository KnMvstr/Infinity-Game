package InfinityGaming.controller;

import InfinityGaming.mapping.UrlRoute;
import InfinityGaming.service.ReviewServiceImpl;
import InfinityGaming.utils.FlashMessage;
import InfinityGaming.utils.FlashMessageBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppReview")
@AllArgsConstructor
public class ReviewController {
    ReviewServiceImpl reviewService;
    private FlashMessageBuilder flashMessageBuilder;
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
            mav.setViewName("review/review");
            mav.addObject("review", reviewService.findById(id));

            return mav;
        }
    }

    @GetMapping(UrlRoute.URL_REVIEW_MODERATE_PATH)
    public ModelAndView moderate(
            @PathVariable Long id,
            @PathVariable Long moderate,
            ModelAndView modelAndView,
            RedirectAttributes redirectAttributes,
            Principal principal
    ) {
        boolean isModerate = reviewService.moderateReview(principal.getName(), id, moderate);
        FlashMessage flashMessage = flashMessageBuilder.createSuccessFlashMessage("Le commentaire a bien été modéré !");
        if (!isModerate) {
            flashMessage = flashMessageBuilder.createWarningFlashMessage("Le commentaire a bien été supprimé !");
        }
        redirectAttributes.addFlashAttribute("flashMessage", flashMessage);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
