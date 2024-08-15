package cinemas.repositories;

import cinemas.dtos.Pageable;
import cinemas.enums.MovieStatus;
import cinemas.models.Movie;

import java.util.List;

public interface MoviesRepository extends BaseRepository<Movie, Integer> {
    List<Movie> getMoviesByTitleAndStatus(String keyword, Pageable pageable, MovieStatus status);
    Integer countMoviesByTitleAndStatus(String keyword, MovieStatus status);
    List<Movie> getMoviesByStatus(MovieStatus status);
}
