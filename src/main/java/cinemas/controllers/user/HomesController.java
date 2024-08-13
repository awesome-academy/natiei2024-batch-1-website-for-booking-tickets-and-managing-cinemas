package cinemas.controllers.user;

import cinemas.models.Banner;
import cinemas.models.Movie;
import cinemas.services.BannersService;
import cinemas.services.MoviesService;
import cinemas.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("userHomesController")
public class HomesController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private BannersService bannersService;

    @Autowired
    private MoviesService moviesService;

    @GetMapping("/")
    public String home(Model model) {
        List<Banner> banners = bannersService.getAllBanners();
        List<Movie> movies = moviesService.getAllMovies();
        model.addAttribute("banners", banners);
        model.addAttribute("movies", movies);
        return "user/home-page";
    }

}
