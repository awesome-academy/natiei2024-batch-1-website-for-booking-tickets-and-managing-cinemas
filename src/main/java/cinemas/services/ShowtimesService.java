package cinemas.services;

import cinemas.models.Screen;
import cinemas.models.Showtime;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ShowtimesService {
    Showtime save(Showtime showtime);
    List<Showtime> getShowtimeByDateAndCity(int movieId, int cityId, LocalDate date);
    List<Map<String, String>> generateFormattedDates(LocalDate startDate, int daysRange);
    Map<Screen, List<Showtime>> getGroupedShowtimes(int movieId, int cityId, LocalDate startDate);
}
