package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.model.dto.IdNameDto;
import uz.pdp.model.AuthRole;
import uz.pdp.repository.AuthRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthRoleService {
    private final AuthRoleRepository repository;

    public AuthRoleService(AuthRoleRepository repository) {
        this.repository = repository;
    }


    public List<IdNameDto> roles() {
        List<AuthRole> roles = repository.findAll();

        return roles.stream().map(
                r -> {
                    return IdNameDto.builder()
                            .id(r.getId())
                            .name(r.getName() + " (" + r.getCode() + ")")
                            .build();
                }
        ).collect(Collectors.toList());

    }
}
