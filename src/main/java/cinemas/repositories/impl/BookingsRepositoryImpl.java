package cinemas.repositories.impl;

import cinemas.enums.BookingStatusEnum;
import cinemas.models.Booking;
import cinemas.models.Screen;
import cinemas.models.Showtime;
import cinemas.models.Theater;
import cinemas.repositories.BookingsRepository;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository("bookingsRepository")
public class BookingsRepositoryImpl extends BaseRepositoryImpl<Booking, Integer> implements BookingsRepository {
    public BookingsRepositoryImpl() {
        super(Booking.class);
    }

    @Override
    public List<Booking> findPrintedBookingsSince(ZonedDateTime startDate) {
        return findPrintedBookingsSince(startDate, null);
    }

    @Override
    public List<Booking> findPrintedBookingsSince(ZonedDateTime startDate, Integer theaterId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Booking> cq = cb.createQuery(Booking.class);
        Root<Booking> booking = cq.from(Booking.class);
        Join<Booking, Showtime> showtime = booking.join("showtime");
        Join<Showtime, Screen> screen = showtime.join("screen");
        Join<Screen, Theater> theater = screen.join("theater");

        // Base predicates
        Predicate statusPredicate = cb.equal(booking.get("status"), BookingStatusEnum.PRINTED); // Assuming status 1 means printed

        // Conditional date predicate
        Predicate datePredicate = cb.conjunction(); // Default to true
        if (startDate != null) {
            ZonedDateTime startOfStartDate = startDate.withHour(0).withMinute(0).withSecond(0).withNano(0);
            datePredicate = cb.greaterThanOrEqualTo(booking.get("createdAt"), startOfStartDate);
        }

        // Conditional theater predicate
        Predicate theaterPredicate = cb.conjunction(); // Default to true
        if (theaterId != null) {
            theaterPredicate = cb.equal(theater.get("id"), theaterId);
        }

        // Combine predicates
        cq.where(cb.and(datePredicate, statusPredicate, theaterPredicate));

        TypedQuery<Booking> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
