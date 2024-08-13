package cinemas.repositories.impl;

import cinemas.models.Movie;
import cinemas.repositories.MoviesRepository;
import org.springframework.stereotype.Repository;

@Repository("moviesRepository")
public class MoviesRepositoryImpl extends BaseRepositoryImpl<Movie, Integer> implements MoviesRepository {
    public MoviesRepositoryImpl() {
        super(Movie.class);
    }
}
