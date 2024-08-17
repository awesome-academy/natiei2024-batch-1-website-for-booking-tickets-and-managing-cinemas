package cinemas.services;

import cinemas.enums.MovieStatus;
import cinemas.dtos.PaginationResult;
import cinemas.models.Movie;
import cinemas.dtos.MovieCreateFormDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MoviesService {
    List<Movie> getAllMovies();
    Movie save(Movie movie);
    Optional<Movie> findById(int id);
    Movie save(MovieCreateFormDto movieCreateFormDto);
    Movie uploadPhoto(Movie movie, MultipartFile photo);
    List<Movie> getMoviesByStatus(MovieStatus movieStatus);
    PaginationResult<Movie> getPaginationMoviesByTitleAndStatus(String keyword, int page, int size, String status);
}
