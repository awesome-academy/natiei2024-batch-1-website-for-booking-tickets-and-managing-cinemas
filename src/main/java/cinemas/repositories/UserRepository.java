package cinemas.repositories;

import cinemas.models.User;

public interface UserRepository extends BaseRepository<User, Integer> {
    User findByEmail(String email);
}
