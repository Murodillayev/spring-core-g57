package uz.pdp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.pdp.model.AuthUser;
import uz.pdp.repository.AuthUserRepository;

import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser authUser = repository.findByUsername(username)
                .orElseThrow(
                        () -> new BadCredentialsException("Bad credentials!!")
                );

        return new User(authUser.getUsername(), authUser.getPassword(), Collections.emptyList());
    }
}
