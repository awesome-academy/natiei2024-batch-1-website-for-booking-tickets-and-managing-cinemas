package cinemas.services.impl;

import cinemas.models.City;
import cinemas.repositories.CityRepository;
import cinemas.services.CityService;

import java.util.List;

public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(int id) {
        return cityRepository.findById(id).orElse(null);
    }
}
