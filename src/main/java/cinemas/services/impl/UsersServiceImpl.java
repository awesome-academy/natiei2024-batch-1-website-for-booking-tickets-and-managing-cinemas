package cinemas.services.impl;

import cinemas.models.User;
import cinemas.repositories.UsersRepository;
import cinemas.services.UsersService;
import jakarta.persistence.NoResultException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        try {
            return usersRepository.findByEmail(email);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User save(User user) {
        if (usersRepository != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return usersRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        usersRepository.delete(user);
    }
}
