package cinemas.services.impl;

import cinemas.models.Screen;
import cinemas.models.Showtime;
import cinemas.repositories.ShowtimesRepository;
import cinemas.services.ShowtimesService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShowtimesServiceImpl implements ShowtimesService {
    private ShowtimesRepository showtimesRepository;
    public ShowtimesServiceImpl(ShowtimesRepository showtimeRepository){this.showtimesRepository = showtimeRepository;}
    @Override
    public Showtime save(Showtime showtime) {
        return showtimesRepository.save(showtime);
    }

    @Override
    public List<Showtime> getShowtimeByDateAndCity(int movieId, int cityId, LocalDate date) {
        return showtimesRepository.getShowtimeByDateAndCity(movieId, cityId, date);
    }
    @Override
    public List<Map<String, String>> generateFormattedDates(LocalDate startDate, int daysRange) {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEE");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

        return IntStream.range(0, daysRange)
                .mapToObj(startDate::plusDays)
                .map(date -> {
                    Map<String, String> dateMap = new HashMap<>();
                    dateMap.put("month", date.format(monthFormatter));
                    dateMap.put("dayOfWeek", date.format(dayOfWeekFormatter));
                    dateMap.put("day", date.format(dayFormatter));
                    return dateMap;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<Screen, List<Showtime>> getGroupedShowtimes(int movieId, int cityId, LocalDate startDate) {
        List<Showtime> showtimes = getShowtimeByDateAndCity(movieId, cityId, startDate);
        return showtimes.stream()
                .collect(Collectors.groupingBy(Showtime::getScreen));
    }

}
