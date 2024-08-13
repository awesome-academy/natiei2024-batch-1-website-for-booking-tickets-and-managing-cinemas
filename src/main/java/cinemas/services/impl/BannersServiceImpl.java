package cinemas.services.impl;

import cinemas.models.Banner;
import cinemas.repositories.BannersRepository;
import cinemas.services.BannersService;

import java.util.List;

public class BannersServiceImpl implements BannersService {
    private BannersRepository bannersRepository;

    public BannersServiceImpl(BannersRepository bannersRepository) {
        this.bannersRepository = bannersRepository;
    }

    @Override
    public List<Banner> getAllBanners() {
        return bannersRepository.findAll();
    }
}
