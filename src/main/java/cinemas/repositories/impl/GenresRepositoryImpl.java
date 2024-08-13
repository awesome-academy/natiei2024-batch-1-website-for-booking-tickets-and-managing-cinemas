package cinemas.repositories.impl;

import cinemas.models.Genre;
import cinemas.repositories.GenresRepository;
import org.springframework.stereotype.Repository;

@Repository("genresRepository")
public class GenresRepositoryImpl extends BaseRepositoryImpl<Genre, Integer> implements GenresRepository {
    public GenresRepositoryImpl() {
        super(Genre.class);
    }
}
