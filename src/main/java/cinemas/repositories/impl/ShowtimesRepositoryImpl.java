package cinemas.repositories.impl;

import cinemas.models.Showtime;
import cinemas.repositories.ShowtimesRepository;
import org.springframework.stereotype.Repository;

@Repository("showtimesRepository")
public class ShowtimesRepositoryImpl extends BaseRepositoryImpl<Showtime, Integer> implements ShowtimesRepository {
    public ShowtimesRepositoryImpl() {
        super(Showtime.class);
    }
}
