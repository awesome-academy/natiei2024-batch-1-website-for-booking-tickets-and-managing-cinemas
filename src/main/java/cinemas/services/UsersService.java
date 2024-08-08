package cinemas.services;

import cinemas.models.User;

public interface UsersService {
    User save(User user);
    void delete(User user);
    User findByEmail(String email);
}
