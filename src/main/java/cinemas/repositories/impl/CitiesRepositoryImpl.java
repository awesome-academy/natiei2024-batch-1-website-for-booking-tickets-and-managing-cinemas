package cinemas.repositories.impl;

import cinemas.models.City;
import cinemas.repositories.CitiesRepository;
import org.springframework.stereotype.Repository;

@Repository("citiesRepository")
public class CitiesRepositoryImpl extends BaseRepositoryImpl<City, Integer> implements CitiesRepository {
    public CitiesRepositoryImpl() {
        super(City.class);
    }
}
