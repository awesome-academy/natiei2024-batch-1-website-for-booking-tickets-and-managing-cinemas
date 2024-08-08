package cinemas.services;

import cinemas.models.Theater;

public interface TheatersService {
    Theater getTheaterById(int id);
    Theater save(Theater theater);
}
