package cinemas.controllers;

import cinemas.dtos.BookingAmountSummary;
import cinemas.enums.ReportTimeSpanEnum;
import cinemas.models.City;
import cinemas.models.Theater;
import cinemas.services.ReportService;
import cinemas.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/reports")
public class AdminReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private TheaterService theaterService;

    @GetMapping("/revenue")
    public String getAdminReport(@RequestParam(value = "theaterId", required = false) String theaterId,
            @RequestParam(value = "time", required = false) String time,
            Model model) {
        // Fetch booking summaries
        Integer id = parseIntOrNull(theaterId);
        ReportTimeSpanEnum timeSpan = ReportTimeSpanEnum.fromOrdinal(parseIntOrNull(time));
        List<BookingAmountSummary> bookingAmountSummaries = reportService.getTotalBookingAmountByDayForTimeSpan(id,
                timeSpan);

        // Extract dates and amounts from booking summaries
        List<Date> dates = bookingAmountSummaries.stream()
                .map(BookingAmountSummary::getDate)
                .toList();

        List<Long> amounts = bookingAmountSummaries.stream()
                .map(BookingAmountSummary::getTotalAmount)
                .collect(Collectors.toList());

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY MMM dd");
        List<String> formattedDates = dates.stream()
                .map(dateFormat::format)
                .toList();

        Theater theater = null;
        City city = null;
        if (id != null) {
            theater = theaterService.getTheaterById(id);
        }
        if (theater != null) {
            city = theater.getCity();
        }
        // Pass data to the Thymeleaf template
        model.addAttribute("city", city);
        model.addAttribute("theater", theater);
        model.addAttribute("timeSpan", timeSpan.ordinal());
        model.addAttribute("revenueDates", formattedDates);
        model.addAttribute("revenueAmounts", amounts);
        return "admin/revenue-chart-detail";
    }

    private Integer parseIntOrNull(String theaterId) {
        try {
            return Integer.parseInt(theaterId);
        } catch (NumberFormatException e) {
            return null; // Or handle this case as needed
        }
    }

}
