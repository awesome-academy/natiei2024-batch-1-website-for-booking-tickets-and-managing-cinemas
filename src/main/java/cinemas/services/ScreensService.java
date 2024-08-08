package cinemas.services;

import cinemas.exceptions.TheaterNotFoundException;
import cinemas.models.Screen;
import cinemas.dtos.ScreenCreateFormDto;

import java.util.Optional;

public interface ScreensService {
    Screen save(Screen screen);
    Optional<Screen> findById(int id);
    void create(ScreenCreateFormDto screenCreateFormDto) throws TheaterNotFoundException;
}
