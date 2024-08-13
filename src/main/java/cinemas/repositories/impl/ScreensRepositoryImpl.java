package cinemas.repositories.impl;

import cinemas.models.Screen;
import cinemas.repositories.ScreensRepository;
import org.springframework.stereotype.Repository;

@Repository("screensRepository")
public class ScreensRepositoryImpl extends BaseRepositoryImpl<Screen, Integer> implements ScreensRepository {
    public ScreensRepositoryImpl() {
        super(Screen.class);
    }
}
