package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.DTO.ReviewDTO;
import HB_CAPE_MAK.hb_cape_makindu.mapping.UrlRoute;
import HB_CAPE_MAK.hb_cape_makindu.service.GameServiceImpl;
import HB_CAPE_MAK.hb_cape_makindu.service.GenreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppGenre")
@AllArgsConstructor
public class GenreController {
    GenreServiceImpl genreService;
    GameServiceImpl gameService;

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
            if (principal != null) {
                mav.addObject("genreDto", new GenreDTO());
            }
            mav.setViewName("genre");
            mav.addObject("AllGenre", genreService.findAll());
            return mav;
        }
    }}



