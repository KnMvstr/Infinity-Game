package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.mapping.UrlRoute;
import HB_CAPE_MAK.hb_cape_makindu.service.GameServiceImpl;
import HB_CAPE_MAK.hb_cape_makindu.service.ReviewServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
@RequestMapping(name = "AppGame")
@AllArgsConstructor
public class GameController {
    GameServiceImpl gameService;
    @GetMapping(path = UrlRoute.URL_GAME + "/{slug}", name = "show")
    public ModelAndView game(
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        {
            mav.setViewName("game");
            mav.addObject("game", gameService.findBySlug(slug));

            return mav;
        }
    }
}


//        if (slug == null || slug.isEmpty()) {
//        // Gérer le cas où aucun slug n'est fourni
//        mav.setViewName("errorPage"); // Rediriger vers une page d'erreur ou une page par défaut
//            mav.addObject("errorMessage", "Aucun jeu n'est spécifié");
