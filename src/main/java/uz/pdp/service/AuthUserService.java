package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.model.dto.AuthUserCreateDto;
import uz.pdp.model.dto.AuthUserDto;
import uz.pdp.mapper.AuthUserMapper;
import uz.pdp.model.AuthUser;
import uz.pdp.repository.AuthUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserService {
    private final FileService fileService;
    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;

    public void create(AuthUserCreateDto dto) {
        AuthUser authUser = mapper.fromDto(dto);
        authUser.setImgUrl(fileService.uploadUserImg(dto.getImg()));

        repository.save(authUser);
    }

    public List<AuthUserDto> getAll() {
        List<AuthUser> users = repository.findAll();
        return mapper.toDto(users);
    }
}