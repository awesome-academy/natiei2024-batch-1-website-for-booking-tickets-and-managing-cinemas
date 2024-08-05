package cinemas.services;

import cinemas.models.User;

public interface UserService {
    User save(User user);

    void delete(User user);
}
