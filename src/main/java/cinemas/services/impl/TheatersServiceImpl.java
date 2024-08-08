package cinemas.services.impl;

import cinemas.models.Theater;
import cinemas.repositories.TheatersRepository;
import cinemas.services.TheatersService;

public class TheatersServiceImpl implements TheatersService {
    private TheatersRepository theatersRepository;

    public TheatersServiceImpl(TheatersRepository theatersRepository) {
        this.theatersRepository = theatersRepository;
    }

    @Override
    public Theater getTheaterById(int id) {
        return theatersRepository.findById(id).orElse(null);
    }
    @Override
    public Theater save(Theater theater) {
        return theatersRepository.save(theater);
    }
}
