package cinemas.services;

import cinemas.dtos.SingleLineGraphDto;
import cinemas.enums.ReportTimeSpanEnum;

public interface ReportsService {
    SingleLineGraphDto getSingleLineGraphOfTotalBookingAmount(Integer theaterId, ReportTimeSpanEnum timeSpan);
}
