package cinemas.services.impl;

import cinemas.models.Movie;
import cinemas.repositories.MoviesRepository;
import cinemas.services.MoviesService;

import java.util.List;

public class MoviesServiceImpl implements MoviesService {
    private MoviesRepository moviesRepository;

    public MoviesServiceImpl(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }
}
