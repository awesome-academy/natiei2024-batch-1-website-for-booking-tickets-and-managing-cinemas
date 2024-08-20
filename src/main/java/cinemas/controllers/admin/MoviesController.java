package cinemas.controllers.admin;


import cinemas.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("adminMoviesController")
@RequestMapping("/admin/movies")
public class MoviesController {
    @Autowired
    private MoviesService moviesService;

    @GetMapping
    public String index(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size,
                        @RequestParam(name = "status", defaultValue = "all") String status,
                        Model model) {
        var moviesPagination = moviesService.getPaginationMoviesByTitleAndStatus(keyword, page, size, status);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("status", status);
        model.addAttribute("moviesPagination", moviesPagination);
        return "admin/movies/index";
    }
}