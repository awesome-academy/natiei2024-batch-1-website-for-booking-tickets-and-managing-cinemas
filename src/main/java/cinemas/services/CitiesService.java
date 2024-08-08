package cinemas.services;

import cinemas.dtos.CityDto;
import cinemas.models.City;
import java.util.List;

public interface CitiesService {
    List<CityDto> getAllCityDtos();
    City getCityById(int id);
    CityDto getCityDtoWithTheatersById(int id);
    List<City> getAllCity();
}
