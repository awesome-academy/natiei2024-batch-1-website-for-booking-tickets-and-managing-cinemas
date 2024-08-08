package cinemas.controllers.user;

import cinemas.models.Movie;
import cinemas.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MoviesController {
    @Autowired
    private MoviesService moviesService;

    @GetMapping("/movies/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Movie movie = moviesService.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        model.addAttribute("movie", movie);
        return "user/movies/show";
    }
}
