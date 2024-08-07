package cinemas.repositories.impl;

import cinemas.models.City;
import cinemas.repositories.CityRepository;
import org.springframework.stereotype.Repository;

@Repository("cityRepository")
public class CityRepositoryImpl extends BaseRepositoryImpl<City, Integer> implements CityRepository {
    public CityRepositoryImpl() {
        super(City.class);
    }
}
