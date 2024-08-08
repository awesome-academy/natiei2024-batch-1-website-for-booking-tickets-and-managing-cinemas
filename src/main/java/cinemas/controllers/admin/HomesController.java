package cinemas.controllers.admin;

import cinemas.enums.ReportTimeSpanEnum;
import cinemas.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("adminHomesController")
public class HomesController {
    @Autowired
    private ReportsService reportsService;

    @GetMapping(value = {"/admin", "/admin/"})
    public String getAdminDashboard(Model model) {
        var revenueSingleLineGraphDto = reportsService.getSingleLineGraphOfTotalBookingAmount(
                null,
                ReportTimeSpanEnum.WEEK);

        model.addAttribute("revenueLineGraph", revenueSingleLineGraphDto);
        return "admin/dashboard";
    }
}
