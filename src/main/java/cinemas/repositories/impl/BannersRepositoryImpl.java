package cinemas.repositories.impl;

import cinemas.models.Banner;
import cinemas.repositories.BannersRepository;
import org.springframework.stereotype.Repository;

@Repository("bannersRepository")
public class BannersRepositoryImpl extends BaseRepositoryImpl<Banner, Integer> implements BannersRepository {
    public BannersRepositoryImpl() {
        super(Banner.class);
    }
}
