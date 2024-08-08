package cinemas.services.impl;

import cinemas.dtos.SingleLineGraphDto;
import cinemas.enums.ReportTimeSpanEnum;
import cinemas.models.Booking;
import cinemas.repositories.BookingsRepository;
import cinemas.services.ReportsService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ReportsServiceImpl implements ReportsService {
    private BookingsRepository bookingsRepository;

    public ReportsServiceImpl(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

    public SingleLineGraphDto<String, Integer> getSingleLineGraphOfTotalBookingAmount(Integer theaterId,
                                                                                      ReportTimeSpanEnum timeSpan) {
        List<Booking> bookings;
        ZonedDateTime currentDate = ZonedDateTime.now();
        int days = 0;
        switch (timeSpan) {
            case WEEK:
                bookings = bookingsRepository.findPrintedBookingsSince(currentDate.minusDays(7), theaterId);
                days = 7;
                break;
            case MONTH:
                bookings = bookingsRepository.findPrintedBookingsSince(currentDate.minusDays(30), theaterId);
                days = 30;
                break;
            case YEAR:
                bookings = bookingsRepository.findPrintedBookingsSince(currentDate.minusDays(365), theaterId);
                days = 365;
                break;
            case ALL_TIME:
                bookings = bookingsRepository.findPrintedBookingsSince(null, theaterId);
                if (!bookings.isEmpty()) {
                    ZonedDateTime firstBookingDate = bookings.get(0).getCreatedAt();
                    days = (int) ChronoUnit.DAYS.between(
                            LocalDate.of(firstBookingDate.getYear(), firstBookingDate.getMonthValue(),
                                    firstBookingDate.getDayOfMonth()),
                            LocalDate.now());
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid time span: " + timeSpan);
        }
        // Initialize a map with all days within the last 30 days, defaulting to zero
        Map<LocalDate, Long> dailyTotals = new LinkedHashMap<>(); // LinkedHashMap to maintain insertion order
        for (int i = days; i >= 0; i--) {
            LocalDate date = currentDate.minusDays(i).toLocalDate();
            dailyTotals.put(date, 0L); // Initialize with zero as Long
        }

        // Update the map with actual booking amounts
        bookings.forEach(booking -> {
            LocalDate bookingDate = booking.getCreatedAt().toLocalDate();
            Long currentTotal = dailyTotals.get(bookingDate);
            Long newTotal = currentTotal + booking.getAmount();
            dailyTotals.put(bookingDate, newTotal);
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY MMM dd");
        var dates = dailyTotals.keySet().stream()
                .map(date -> Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .map(dateFormat::format).toList();
        var amounts = dailyTotals.values().stream().map(Long::intValue).toList();
        // Convert the map to a sorted list of BookingSummary
        return new SingleLineGraphDto<>(dates, amounts);
    }
}
