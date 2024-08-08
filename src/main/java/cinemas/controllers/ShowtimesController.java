package cinemas.controllers;

import cinemas.models.City;
import cinemas.models.Screen;
import cinemas.models.Showtime;
import cinemas.services.CitiesService;
import cinemas.services.ShowtimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class ShowtimesController {
    @Autowired
    private ShowtimesService showtimesService;
    @Autowired
    private CitiesService cityService;

    @GetMapping("/tickets/modal")
    public String getModalShowtime(
            @RequestParam("movieId") int movieId,
            Model model) {
        LocalDate startDate = LocalDate.now();
        List<City> cityList = cityService.getAllCity();
        List<Map<String, String>> formattedDates = showtimesService.generateFormattedDates(startDate, 20);
        Map<Screen, List<Showtime>> groupedShowtimes = showtimesService.getGroupedShowtimes(movieId, 1, startDate);
        model.addAttribute("dates", formattedDates);
        model.addAttribute("cities", cityList);
        model.addAttribute("groupedShowtimes", groupedShowtimes);
        return "components/dialog-ticket :: modal-content";
    }

    @GetMapping("/tickets/modal/showtime")
    public String getShowtime(
            @RequestParam("movieId") int movieId,
            @RequestParam("date") String dateStr,
            @RequestParam("cityId") int cityId,
            Model model) {
        LocalDate selectedDate = LocalDate.parse(dateStr);
        Map<Screen, List<Showtime>> groupedShowtimes = showtimesService.getGroupedShowtimes(movieId, cityId, selectedDate);
        model.addAttribute("groupedShowtimes", groupedShowtimes);
        return "components/dialog-ticket :: showtime-content";
    }
}
