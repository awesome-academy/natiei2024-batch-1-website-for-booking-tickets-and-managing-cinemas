package cinemas.services.impl;

import cinemas.dtos.CityDto;
import cinemas.dtos.TheaterDto;
import cinemas.models.City;
import cinemas.repositories.CityRepository;
import cinemas.services.CityService;

import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDto> getAllCityDtos() {
        var cities = cityRepository.findAll();
        var cityDtos = new ArrayList<CityDto>();
        cities.forEach(city -> {
            cityDtos.add(new CityDto(city.getId(), city.getName(), null));
        });
        return cityDtos;
    }

    @Override
    public City getCityById(int id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public CityDto getCityDtoWithTheatersById(int id) {
        var city = cityRepository.findById(id);
        if (city.isEmpty()) {
            return null;
        }
        var theaters = city.get().getTheaters();
        List<TheaterDto> theaterDtos = new ArrayList<>();
        theaters.forEach(theater ->
                theaterDtos.add(new TheaterDto(theater.getId(), theater.getName()))
        );
        return new CityDto(city.get().getId(), city.get().getName(), theaterDtos);
    }
}
