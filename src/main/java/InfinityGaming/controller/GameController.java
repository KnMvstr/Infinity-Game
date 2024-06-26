package InfinityGaming.controller;

import InfinityGaming.DTO.ReviewDTO;
import InfinityGaming.entity.Game;
import InfinityGaming.mapping.UrlRoute;
import InfinityGaming.service.*;
import InfinityGaming.utils.FlashMessageBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
@RequestMapping(name = "AppGame")
@AllArgsConstructor
public class GameController {
    GameServiceImpl gameService;
    private ReviewServiceImpl reviewService;

    private FlashMessageBuilder flashMessageBuilder;

    @GetMapping(path = UrlRoute.URL_GAME + "/{slug}", name = "show")
    public ModelAndView game(
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal,
            @PageableDefault(
                    size = 9, // nb Element par page
                    sort = {"createdAt"}, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        if (principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        {
            if (principal != null) {
                mav.addObject("reviewDto", new ReviewDTO());
            }
            mav.setViewName("game/game");
            Game game = gameService.findBySlug(slug);
            mav.addObject("pageReviews", reviewService.findAllByGame(game, pageable));
            mav.addObject("game", gameService.findBySlug(slug));

            return mav;
        }
    }

    @PostMapping(UrlRoute.URL_GAME_SLUG)
    public ModelAndView show(
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal,
            @ModelAttribute("reviewDto") ReviewDTO reviewDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("game/game");
            return mav;
        }
        reviewService.createReview(
                reviewDTO,
                gameService.findBySlug(slug),
                principal.getName()
        );
        redirectAttributes.addFlashAttribute(
                "flashMessage",
                flashMessageBuilder.createSuccessFlashMessage("Votre commentaire a bien été enregistré, il est actuellement en attente de modération !")
        );
        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + slug);
        return mav;
    }
//    @PostMapping(UrlRoute.URL_GAME + "/{slug}")
//    public ModelAndView show(
//            @PathVariable String slug,
//            ModelAndView mav,
//            Principal principal,
//            @ModelAttribute("reviewDto") ReviewDTO reviewDTO,
//            BindingResult result
//    ) {
//        if (result.hasErrors()) {
//            mav.setViewName("game");
//            return mav;
//        }
//        reviewService.createReview(
//                reviewDTO,
//                gameService.findBySlug(slug),
//                principal.getName()
//        );
//        mav.addObject("game", gameService.findBySlug(slug));
//        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW_GAME);
//        return mav;
//    }


//    @GetMapping(UrlRoute.URL_GAME_NEW)
//    public ModelAndView create(ModelAndView mav) {
//        mav.setViewName("game/new");
//        mav.addObject("gameDto", new GameDTO());
//        mav.addObject("genres", genreService.findAllSorted());
//        mav.addObject("classifications", classificationService.findAllSorted());
//        mav.addObject("businessModels", businessModelService.findAllSorted());
//        mav.addObject("publishers", publisherService.findAllSorted());
//        mav.addObject("platforms", platformService.findAllSorted());
//        return mav;
//    }}


//    @PostMapping(UrlRoute.URL_GAME_NEW)
//    public ModelAndView create(
//            ModelAndView mav,
//            @ModelAttribute("gameDto") GameDTO gameDTO,
//            BindingResult bindingResult,
//            RedirectAttributes redirectAttributes,
//            Principal principal
//    ) {
//        if (bindingResult.hasErrors()) {
//            mav.setViewName("game/new");
//            return mav;
//        }
//
//        redirectAttributes.addFlashAttribute(
//                "flashMessage",
//                flashMessageBuilder.createSuccessFlashMessage("Jeu créé avec succès !")
//        );
//        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + gameService.create(gameDTO, principal.getName()).getSlug());
//        return mav;
//    }
}







