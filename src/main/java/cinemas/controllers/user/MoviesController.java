package cinemas.controllers.user;

import cinemas.models.Movie;
import cinemas.enums.MovieStatus;
import cinemas.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    private MoviesService moviesService;
    @GetMapping("/now-showing")
    public String getShowingMovie(Model model) {
        List<Movie> movies = moviesService.getMoviesByStatus(MovieStatus.NOW_SHOWING);
        model.addAttribute("movies", movies);
        return "user/movies/now-showing";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Movie movie = moviesService.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        model.addAttribute("movie", movie);
        return "user/movies/show";
    }

}
