package cinemas.services.impl;

import cinemas.dtos.Pageable;
import cinemas.dtos.PaginationResult;
import cinemas.enums.MovieStatus;
import cinemas.models.Movie;
import cinemas.repositories.MoviesRepository;
import cinemas.services.MoviesService;

import java.util.List;
import java.util.Optional;

public class MoviesServiceImpl implements MoviesService {
    private MoviesRepository moviesRepository;

    public MoviesServiceImpl(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }
    @Override
    public Movie save(Movie movie) {
        return moviesRepository.save(movie);
    }

    @Override
    public Optional<Movie> findById(int id) {
        return moviesRepository.findById(id);
    }
    public List<Movie> getMoviesByStatus(MovieStatus status) {
        return moviesRepository.getMoviesByStatus(status);
    }

    @Override
    public PaginationResult<Movie> getPaginationMoviesByTitleAndStatus(String keyword, int page, int size, String status) {
        MovieStatus movieStatus = MovieStatus.fromValue(status);
        var pageable = new Pageable(page, size);
        var movies = moviesRepository.getMoviesByTitleAndStatus(keyword, pageable, movieStatus);
        var totalElements = moviesRepository.countMoviesByTitleAndStatus(keyword, movieStatus);
        return new PaginationResult<>(totalElements, size, movies);
    }
}
