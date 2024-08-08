package cinemas.controllers;

import cinemas.dtos.CityDto;
import cinemas.dtos.TheaterDto;
import cinemas.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CityDto>> getCities() {
        var cities = cityService.getAllCities();
        List<CityDto> cityDtos = new ArrayList<>();
        cities.forEach(city -> {
            cityDtos.add(new CityDto(city.getId(), city.getName(), Collections.emptyList()));
        });
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/cities/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CityDto> getOneCityWithTheaters(@PathVariable("id") int id) {
        var city = cityService.getCityById(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var theaters = city.getTheaters();
        List<TheaterDto> theaterDtos = new ArrayList<>();
        theaters.forEach(theater ->
                theaterDtos.add(new TheaterDto(theater.getId(), theater.getName()))
        );
        var cityDto = new CityDto(city.getId(), city.getName(), theaterDtos);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }
}
