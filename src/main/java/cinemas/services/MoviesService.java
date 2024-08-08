package cinemas.services;

import cinemas.models.Movie;
import cinemas.validators.MovieValidator;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MoviesService {
    List<Movie> getAllMovies();
    Optional<Movie> findById(int id);
    Movie save(Movie movie);
    Movie save(MovieValidator movieValidator);
    Movie uploadPhoto(Movie movie, MultipartFile photo);
}
