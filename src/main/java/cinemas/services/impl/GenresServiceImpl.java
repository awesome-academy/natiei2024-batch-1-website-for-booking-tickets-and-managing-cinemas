package cinemas.services.impl;

import cinemas.models.Genre;
import cinemas.repositories.GenresRepository;
import cinemas.services.GenresService;

import java.util.List;

public class GenresServiceImpl implements GenresService {
    private GenresRepository genresRepository;
    @Override
    public List<Genre> findAll() {
        return genresRepository.findAll();
    }

    @Override
    public Genre insert(Genre genre) {
        return genresRepository.save(genre);
    }
}
