package cinemas.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticket-bookings")
public class TicketBookingsControllers {
    @GetMapping("/showtimes/{id}") // view seats of a showtime
    public String show(@PathVariable("id") int id, Model model) {
        return "user/showtimes/show";
    }

    @PostMapping("/showtimes/{id}") // view food of a showtime ofter selecting seats
    public String showFoods(@PathVariable("id") int id, Model model) {
        return "user/foods/index";
    }
}
