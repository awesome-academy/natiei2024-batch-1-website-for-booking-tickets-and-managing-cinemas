package cinemas.services.impl;

import cinemas.models.Genre;
import cinemas.repositories.GenresRepository;
import cinemas.services.GenresService;

import java.util.List;

public class GenresServiceImpl implements GenresService {
    private GenresRepository genresRepository;

    public GenresServiceImpl(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genresRepository.findAll();
    }

    @Override
    public Genre findById(Integer genreId) {
        return genresRepository.findById(genreId).orElse(null);
    }
}
