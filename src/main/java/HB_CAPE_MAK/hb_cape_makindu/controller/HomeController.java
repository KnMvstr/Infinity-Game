package HB_CAPE_MAK.hb_cape_makindu.controller;


import HB_CAPE_MAK.hb_cape_makindu.mapping.UrlRoute;
import HB_CAPE_MAK.hb_cape_makindu.service.GameServiceImpl;
import HB_CAPE_MAK.hb_cape_makindu.service.ReviewServiceImpl;
import HB_CAPE_MAK.hb_cape_makindu.utils.ExcelReviewService;
import HB_CAPE_MAK.hb_cape_makindu.utils.FlashMessage;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@RequestMapping(path = "/", name = "AppHome")
@AllArgsConstructor
public class HomeController {

    private GameServiceImpl gameService;
    private ReviewServiceImpl reviewService;
    private ExcelReviewService excelService;


    @GetMapping("/")
    public ModelAndView index(
            ModelAndView mav,
            Principal principal,
            @ModelAttribute("flashMessage") FlashMessage flashMessage,
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
        mav.addObject("flashMessage", flashMessage);
        mav.addObject("pageReviews", reviewService.getPageReviewByPseudo(principal.getName(), pageable));
        mav.addObject("pageReviews", reviewService.findAll(pageable));
        mav.addObject("Top4TrendGames", gameService.findTop4ByOrderByCountReview());
        mav.setViewName("index");
        return mav;
    }


    @GetMapping(UrlRoute.URL_EXPORT)
    public void downloadExcel(HttpServletResponse response) {
        try {
            File file = excelService.writeExcel();
            ByteArrayInputStream excelToByte = new ByteArrayInputStream(
                    Files.readAllBytes(Paths.get(file.getAbsolutePath()))
            );
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            IOUtils.copy(excelToByte, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
