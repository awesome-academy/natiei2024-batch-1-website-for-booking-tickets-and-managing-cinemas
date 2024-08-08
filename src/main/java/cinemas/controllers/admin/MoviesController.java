package cinemas.controllers.admin;

import cinemas.enums.MovieStatus;
import cinemas.models.Genre;
import cinemas.models.Movie;
import cinemas.services.GenresService;
import cinemas.services.MoviesService;
import cinemas.validators.MovieValidator;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RequestMapping("/admin/movies")
@Controller("moviesController")
public class MoviesController {
    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    @Autowired
    private MoviesService moviesService;
    @Autowired
    private GenresService genresService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Register the custom editor for ZonedDateTime
        binder.registerCustomEditor(ZonedDateTime.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"), true) {
            @Override
            public void setAsText(String text) {
                try {
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.forLanguageTag("vi")).parse(text);
                    // Convert to ZonedDateTime with Vietnam time zone
                    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(parsedDate.toInstant(), ZoneId.of("Asia/Ho_Chi_Minh"));
                    setValue(zonedDateTime);
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });
        // Register the custom editor for List<Genre>
        binder.registerCustomEditor(List.class, "genres", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element instanceof String) {
                    Integer genreId = Integer.valueOf((String) element);
                    return genresService.findById(genreId); // Convert String to Genre
                }
                return null;
            }
        });
    }

    @GetMapping(value = {"insert", "insert/"})
    public String getInsertForm(Model model) {
        model.addAttribute("movie", new MovieValidator());
        model.addAttribute("allGenres", genresService.getAllGenres());
        model.addAttribute("allStatuses", MovieStatus.values());
        return "admin/movies/insert.html";
    }

    @PostMapping(value = {"insert", "insert/"})
    public String insertMovie(@Valid @ModelAttribute("movie") MovieValidator movieValidator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Validation error: {}", bindingResult.getAllErrors());
            return "redirect:insert";
        }

        try {
            moviesService.save(movieValidator);
        } catch (Exception e) {
            logger.error("Error while inserting movie: {}", e.getMessage());
            return "redirect:insert";
        }

        return "redirect:/admin/movies";
    }
}
