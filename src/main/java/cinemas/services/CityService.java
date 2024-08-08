package cinemas.services;

import cinemas.models.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();
    City getCityById(int id);
}
