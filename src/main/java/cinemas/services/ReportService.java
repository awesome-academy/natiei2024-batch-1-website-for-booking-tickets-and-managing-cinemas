package cinemas.services;

import cinemas.dtos.BookingAmountSummary;
import cinemas.enums.ReportTimeSpanEnum;

import java.util.List;

public interface ReportService {
    List<BookingAmountSummary> getTotalBookingAmountByDayForTimeSpan(Integer theaterId, ReportTimeSpanEnum timeSpan);
}
