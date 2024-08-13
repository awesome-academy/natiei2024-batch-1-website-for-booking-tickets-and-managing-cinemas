package cinemas.repositories.impl;

import cinemas.models.Theater;
import cinemas.repositories.TheatersRepository;
import org.springframework.stereotype.Repository;

@Repository("theatersRepository")
public class TheatersRepositoryImpl extends BaseRepositoryImpl<Theater, Integer> implements TheatersRepository {
    public TheatersRepositoryImpl() {
        super(Theater.class);
    }
}
