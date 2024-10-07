package cinemas.controllers.admin;

import cinemas.dtos.GenreDto;
import cinemas.services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/genres")
public class GenresController {
    @Autowired
    GenresService genreService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "admin/genres/index";
    }

    @GetMapping("/new")
    public String insertForm(Model model) {
        if (!model.containsAttribute("genreDto")) {
            model.addAttribute("genreDto", new GenreDto());
        }

        return "admin/genres/new";
    }

    @PostMapping("/new")
    public String insert(@ModelAttribute("movieDto") GenreDto genreDto) {
        genreService.insert(genreDto.toGenre());
        return "redirect:/admin/genres/";
    }
}
