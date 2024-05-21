package InfinityGaming.controller;

import InfinityGaming.DTO.GameDTO;
import InfinityGaming.DTO.GenreDTO;
import InfinityGaming.mapping.UrlRoute;
import InfinityGaming.service.*;
import InfinityGaming.utils.FlashMessageBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppGenre")
@AllArgsConstructor
public class GenreController {
    GenreServiceImpl genreService;
    GameServiceImpl gameService;
    ClassificationServiceImpl classificationService;
    PublisherServiceImpl publisherService;
    BusinessModelServiceImpl businessModelService;
    PlatformServiceImpl platformService;
    private FlashMessageBuilder flashMessageBuilder;

    @GetMapping(path = UrlRoute.URL_GENRE)
    public ModelAndView genre(
            ModelAndView mav,
            Principal principal
    ) throws Throwable {
        if (principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        {
            mav.setViewName("genre");
            mav.addObject("genreDto", new GenreDTO());
            mav.addObject("gameDto", new GameDTO());
            mav.addObject("AllGenre", genreService.findAll());
            mav.addObject("genres", genreService.findAllSorted());
            mav.addObject("classifications", classificationService.findAllSorted());
            mav.addObject("businessModels", businessModelService.findAllSorted());
            mav.addObject("publishers", publisherService.findAllSorted());
            mav.addObject("platforms", platformService.findAllSorted());

            return mav;
        }
    }


    @PostMapping(UrlRoute.URL_GENRE)
    public ModelAndView create(
            ModelAndView mav,
            @ModelAttribute("gameDto") GameDTO gameDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("genre");
            return mav;
        }

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                flashMessageBuilder.createSuccessFlashMessage("Jeu créé avec succès !")
        );
        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + gameService.create(gameDTO, principal.getName()).getSlug());
        return mav;
    }


}





