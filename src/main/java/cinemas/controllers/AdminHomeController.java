package cinemas.controllers;

import cinemas.dtos.BookingAmountSummary;
import cinemas.enums.ReportTimeSpanEnum;
import cinemas.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminHomeController {
        @Autowired
        private ReportService reportService;

        @GetMapping("/admin")
        public String getAdminDashboard(Model model) {
                List<BookingAmountSummary> bookingAmountSummaries = reportService.getTotalBookingAmountByDayForTimeSpan(
                                null,
                                ReportTimeSpanEnum.WEEK);

                // Extract dates and amounts from booking summaries
                List<Date> rawRevenueDates = bookingAmountSummaries.stream()
                                .map(BookingAmountSummary::getDate)
                                .toList();

                List<Long> revenueAmounts = bookingAmountSummaries.stream()
                                .map(BookingAmountSummary::getTotalAmount)
                                .collect(Collectors.toList());

                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY MMM dd");
                List<String> revenueDates = rawRevenueDates.stream()
                                .map(dateFormat::format)
                                .toList();
                // Pass data to the Thymeleaf template
                model.addAttribute("revenueDates", revenueDates);
                model.addAttribute("revenueAmounts", revenueAmounts);
                return "admin/admin-dashboard";
        }

}
