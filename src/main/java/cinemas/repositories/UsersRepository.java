package cinemas.repositories;

import cinemas.models.User;

public interface UsersRepository extends BaseRepository<User, Integer> {
    User findByEmail(String email);
}
