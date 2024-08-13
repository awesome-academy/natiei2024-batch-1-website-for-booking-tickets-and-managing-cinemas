package cinemas.repositories.impl;

import cinemas.models.User;
import cinemas.repositories.UsersRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;


@Repository("usersRepository")
public class UsersRepositoryImpl extends BaseRepositoryImpl<User, Integer> implements UsersRepository {
    public UsersRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "FROM User u WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("email", email);

        return query.getSingleResult();
    }
}
