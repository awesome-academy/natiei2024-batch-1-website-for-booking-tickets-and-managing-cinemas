package cinemas.services.impl;

import cinemas.dtos.Pageable;
import cinemas.dtos.PaginationResult;
import cinemas.enums.MovieStatus;
import cinemas.models.Movie;
import cinemas.repositories.MoviesRepository;
import cinemas.services.CloudinaryService;
import cinemas.services.MoviesService;
import cinemas.dtos.MovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public class MoviesServiceImpl implements MoviesService {
    private MoviesRepository moviesRepository;
    private CloudinaryService cloudinaryService;

    public MoviesServiceImpl(MoviesRepository moviesRepository, CloudinaryService cloudinaryService) {
        this.moviesRepository = moviesRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public List<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(int id) {
        return moviesRepository.findById(id);
    }

    @Override
    public Movie save(Movie movie) {
        return moviesRepository.save(movie);
    }

    @Override
    public Movie save(MovieDto movieDto) {
        Movie movie = save(movieDto.toMovie());
        if (!movieDto.getPhoto().isEmpty()) {
            return uploadPhoto(movie, movieDto.getPhoto());
        }
        return movie;
    }

    @Override
    public Movie uploadPhoto(Movie movie, MultipartFile photo) {
        try {
            movie.setPhotoUrl(cloudinaryService.upload(photo));
            return moviesRepository.save(movie);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload photo");
        }
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
