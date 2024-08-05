package cinemas.services.impl;

import cinemas.enums.RoleEnum;
import cinemas.models.User;
import cinemas.repositories.UserRepository;
import cinemas.services.CustomUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
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
