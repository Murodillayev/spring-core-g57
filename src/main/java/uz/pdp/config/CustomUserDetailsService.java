package uz.pdp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.pdp.model.AuthRole;
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
        AuthRole role = roleRepository.findById(authUser.getRoleId()).orElse(null);

        if (role != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getCode());
            authorities.add(authority);
        }
        List<Permission> permissions = permissionRepository.findAllByRoleId(authUser.getRoleId());


        permissions
                .forEach(
                        permission ->
                                authorities.add(new SimpleGrantedAuthority(permission.getCode())));


        // ROLE_ADMIN, create:book, create:rental, ...
        return CustomUserDetails.builder()
                .id(authUser.getId())
                .username(authUser.getUsername())
                .password(authUser.getPassword())
                .authorities(authorities)
                .fullName(authUser.getFullName())
                .imgUrl("https://images.ctfassets.net/xjcz23wx147q/iegram9XLv7h3GemB5vUR/0345811de2da23fafc79bd00b8e5f1c6/Max_Rehkopf_200x200.jpeg")
                .build();
    }
}
