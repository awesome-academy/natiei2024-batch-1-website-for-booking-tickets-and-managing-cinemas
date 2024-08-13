package cinemas.controllers;

import cinemas.dtos.CityDto;
import cinemas.services.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CitiesController {
    @Autowired
    private CitiesService citiesService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CityDto>> getCities() {
        var cities = citiesService.getAllCityDtos();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
    public String getAllCities(Model model) {
        List<CityDto> cities = citiesService.getAllCityDtos();
        model.addAttribute("cities", cities);
        return "user/cities";
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CityDto> getOneCityWithTheaters(@PathVariable("id") int id) {
        var city = citiesService.getCityDtoWithTheatersById(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
