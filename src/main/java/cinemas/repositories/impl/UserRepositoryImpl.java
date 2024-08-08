package cinemas.repositories.impl;

import cinemas.models.User;
import cinemas.repositories.UserRepository;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Integer> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "FROM User u WHERE u.email = :email";
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        query.setMaxResults(1);
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
}
