package cinemas.controllers.user;

import cinemas.dtos.CityDto;
import cinemas.services.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/theaters")
public class TheatersController {
    @Autowired
    private CitiesService citiesService;

    @GetMapping(value = "")
    public String getCities(Model model) {
        List<CityDto> cities = citiesService.getAllCityDtos();
        model.addAttribute("cities", cities);
        return "user/theaters/index";
    }
}
