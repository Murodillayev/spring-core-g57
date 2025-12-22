package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.model.AuthUser;
import uz.pdp.repository.AuthUserRepository;

@Service
@RequiredArgsConstructor
public class AuthUserService {
    private final AuthUserRepository repository;

    public void create(AuthUser authUser) {
        repository.save(authUser);
    }
}