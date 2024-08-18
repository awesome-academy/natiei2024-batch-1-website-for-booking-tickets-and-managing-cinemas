package cinemas.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookings")
public class BookingsController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("section", "booking");
        return "user/bookings/index";
    }

    @GetMapping("{id}")
    public String show(Model model) {
        model.addAttribute("section", "booking-detail");
        return "user/bookings/show";
    }
}
