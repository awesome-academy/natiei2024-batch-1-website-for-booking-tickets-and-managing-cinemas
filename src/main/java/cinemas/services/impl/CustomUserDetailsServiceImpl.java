package cinemas.services.impl;

import cinemas.enums.RoleEnum;
import cinemas.models.User;
import cinemas.repositories.UsersRepository;
import cinemas.services.CustomUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private UsersRepository usersRepository;

    public CustomUserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = usersRepository.findByEmail(email);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(RoleEnum role) {
        if (role == RoleEnum.CUSTOMER) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        } else if (role == RoleEnum.MANAGER) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }
        return Collections.emptyList();
    }
}
