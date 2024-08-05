package cinemas.repositories.impl;

import cinemas.models.User;
import cinemas.repositories.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {

    @Override
    protected Class<User> getPersistentClass() {
        return User.class;
    }
}
