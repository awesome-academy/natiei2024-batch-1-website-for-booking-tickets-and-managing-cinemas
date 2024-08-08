package cinemas.services.impl;

import cinemas.models.Movie;
import cinemas.repositories.MoviesRepository;
import cinemas.services.CloudinaryService;
import cinemas.services.MoviesService;
import cinemas.validators.MovieValidator;
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
    public Movie save(MovieValidator movieValidator) {
        Movie movie = save(movieValidator.toMovie());
        if (!movieValidator.getPhoto().isEmpty()) {
            return uploadPhoto(movie, movieValidator.getPhoto());
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
}
