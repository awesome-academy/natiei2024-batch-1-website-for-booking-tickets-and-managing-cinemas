package cinemas.services.impl;

import cinemas.dtos.CityDto;
import cinemas.dtos.TheaterDto;
import cinemas.models.City;
import cinemas.repositories.CitiesRepository;
import cinemas.services.CitiesService;
import java.util.ArrayList;
import java.util.List;

public class CitiesServiceImpl implements CitiesService {
    private CitiesRepository citiesRepository;
    public CitiesServiceImpl(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    @Override
    public List<CityDto> getAllCityDtos() {
        var cities = citiesRepository.findAll();
        var cityDtos = new ArrayList<CityDto>();
        cities.forEach(city -> {
            cityDtos.add(new CityDto(city.getId(), city.getName(), null));
        });
        return cityDtos;
    }

    @Override
    public City getCityById(int id) {
        return citiesRepository.findById(id).orElse(null);
    }

    @Override
    public CityDto getCityDtoWithTheatersById(int id) {
        var city = citiesRepository.findById(id);
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
    @Override
    public List<City> getAllCity() {
        return citiesRepository.findAll();
    }

}
