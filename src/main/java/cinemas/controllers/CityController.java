package cinemas.controllers;

import cinemas.dtos.CityDto;
import cinemas.dtos.TheaterDto;
import cinemas.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CityDto>> getCities() {
        var cities = cityService.getAllCityDtos();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
    public String getAllCities(Model model) {
        List<CityDto> cities = cityService.getAllCityDtos();
        model.addAttribute("cities", cities);
        return "user/cities";
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CityDto> getOneCityWithTheaters(@PathVariable("id") int id) {
        var city = cityService.getCityDtoWithTheatersById(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
