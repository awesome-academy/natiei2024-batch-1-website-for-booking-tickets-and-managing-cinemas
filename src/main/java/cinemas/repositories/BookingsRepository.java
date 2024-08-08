package cinemas.repositories;

import cinemas.models.Booking;

import java.time.ZonedDateTime;
import java.util.List;

public interface BookingsRepository extends BaseRepository<Booking, Integer> {
    List<Booking> findPrintedBookingsSince(ZonedDateTime startDate, Integer theaterId);
    List<Booking> findPrintedBookingsSince(ZonedDateTime startDate);
}
