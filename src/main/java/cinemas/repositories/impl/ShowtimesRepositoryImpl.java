package cinemas.repositories.impl;

import cinemas.models.Showtime;
import cinemas.repositories.ShowtimesRepository;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository("showtimesRepository")
public class ShowtimesRepositoryImpl extends BaseRepositoryImpl<Showtime, Integer> implements ShowtimesRepository {
    public ShowtimesRepositoryImpl() {
        super(Showtime.class);
    }

    @Override
    public List<Showtime> getShowtimeByDateAndCity(int movieId, int cityId, LocalDate selectedDate) {
        LocalDateTime startOfDay = selectedDate.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);

        String startDay = String.valueOf(startOfDay);
        String endDay = String.valueOf(endOfDay);

        String sql = "SELECT * FROM showtimes WHERE movie_id = :movieId " +
                "AND city_id = :cityId " +
                "AND start_time BETWEEN :startOfDay AND :endOfDay";

        Query query = entityManager.createNativeQuery(sql, Showtime.class);
        query.setParameter("movieId", movieId);
        query.setParameter("cityId", cityId);
        query.setParameter("startOfDay", startDay);
        query.setParameter("endOfDay", endDay);

        return query.getResultList();
    }
}
