package cinemas.services.impl;

import cinemas.models.Theater;
import cinemas.repositories.TheaterRepository;
import cinemas.services.TheaterService;

public class TheaterServiceImpl implements TheaterService {
    private TheaterRepository theaterRepository;

    public TheaterServiceImpl(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public Theater getTheaterById(int id) {
        return theaterRepository.findById(id).orElse(null);
    }
}
