package uz.pdp.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.dto.AuthUserCreateDto;
import uz.pdp.dto.AuthUserDto;
import uz.pdp.model.AuthRole;
import uz.pdp.model.AuthUser;
import uz.pdp.repository.RoleRepository;

import java.util.List;

@Component
public class AuthUserMapper {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthUserMapper(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AuthUserDto> toDto(List<AuthUser> users) {
        return users.stream()
                .map(this::toDto)
                .toList();
    }

    public AuthUserDto toDto(AuthUser user) {
        AuthUserDto authUserDto = AuthUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .build();

        if (user.getRoleId() != null) {
            AuthRole role = roleRepository.findById(user.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            authUserDto.setRole(role.getName());
        }
        return authUserDto;
    }

    public AuthUser fromDto(AuthUserCreateDto dto) {
        AuthUser  authUser = new AuthUser();
        authUser.setUsername(dto.getUsername());
        authUser.setFullName(dto.getFullName());
        authUser.setRoleId(dto.getRoleId());
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        return authUser;
    }
}
