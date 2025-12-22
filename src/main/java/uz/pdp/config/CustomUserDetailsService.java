package uz.pdp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.pdp.model.AuthUser;
import uz.pdp.model.Permission;
import uz.pdp.repository.AuthUserRepository;
import uz.pdp.repository.PermissionRepository;
import uz.pdp.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository repository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser authUser = repository.findByUsername(username)
                .orElseThrow(
                        () -> new BadCredentialsException("Bad credentials!!")
                );

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String role = roleRepository.findById(authUser.getId());

        if (role != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            authorities.add(authority);
        }
        List<Permission> permissions = permissionRepository.findAllByRoleId(authUser.getRoleId());


        permissions
                .forEach(
                        permission ->
                                authorities.add(new SimpleGrantedAuthority(permission.getCode())));


        // ROLE_ADMIN, create:book, create:rental, ...
        return new User(authUser.getUsername(), authUser.getPassword(), authorities);
    }
}
