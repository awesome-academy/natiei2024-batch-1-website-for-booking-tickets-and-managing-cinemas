package cinemas.services.impl;

import cinemas.dtos.ScreenCreateFormDto;
import cinemas.enums.SeatTypeEnum;
import cinemas.exceptions.TheaterNotFoundException;
import cinemas.models.Screen;
import cinemas.models.Seat;
import cinemas.repositories.ScreensRepository;
import cinemas.repositories.TheatersRepository;
import cinemas.services.ScreensService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ScreensServiceImpl implements ScreensService {
    private ScreensRepository screensRepository;
    private TheatersRepository theatersRepository;

    public ScreensServiceImpl(ScreensRepository screensRepository, TheatersRepository theatersRepository) {
        this.screensRepository = screensRepository;
        this.theatersRepository = theatersRepository;
    }

    @Override
    public void create(ScreenCreateFormDto screenCreateFormDto) throws TheaterNotFoundException {
        var theater = theatersRepository.findById(screenCreateFormDto.getTheaterId());
        if (theater.isEmpty()) {
            throw new TheaterNotFoundException();
        }
        Screen screen = new Screen();
        screen.setName(screenCreateFormDto.getName());
        screen.setVerticalSize(screenCreateFormDto.getVerticalSize());
        screen.setHorizontalSize(screenCreateFormDto.getHorizontalSize());
        screen.setTheater(theater.get());
        // default seats created by size
        // will be removed in the future
        Set<Seat> seats = new HashSet<>();
        for (int i = 0; i < screen.getVerticalSize(); i++) {
            for (int j = 0; j < screen.getHorizontalSize(); j++) {
                Seat seat = new Seat();
                seat.setScreen(screen);
                seat.setName((char) ('A' + i) + "" + (j + 1));
                seat.setVerticalIndex(i);
                seat.setHorizontalIndex(j);
                seat.setType(SeatTypeEnum.STANDARD);
                seats.add(seat);
            }
        }
        screen.setSeats(seats);
        screensRepository.save(screen);
    }

    public Screen save(Screen screen) {
        return screensRepository.save(screen);
    }

    @Override
    public Optional<Screen> findById(int id) {
        return screensRepository.findById(id);
    }
}
