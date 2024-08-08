package cinemas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketBookingsController {
    @GetMapping("/ticketBooking")
    public String ticketBooking() {
        return "user/bookings/index";
    }
}
