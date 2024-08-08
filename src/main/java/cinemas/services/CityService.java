package cinemas.services;

import cinemas.dtos.CityDto;
import cinemas.models.City;

import java.util.List;

public interface CityService {
    List<CityDto> getAllCityDtos();
    City getCityById(int id);
    CityDto getCityDtoWithTheatersById(int id);
}
