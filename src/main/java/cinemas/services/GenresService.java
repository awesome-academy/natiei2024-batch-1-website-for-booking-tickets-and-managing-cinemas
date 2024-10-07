package cinemas.services;

import cinemas.models.Genre;

import java.util.List;

public interface GenresService {
    List<Genre> findAll();
    Genre insert(Genre genre);
}
