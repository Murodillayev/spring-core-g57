package uz.pdp.repository;

import uz.pdp.model.AuthUser;

import java.util.Optional;


public interface AuthUserRepository {
    Optional<AuthUser> findByUsername(String username);

    void save(AuthUser authUser);
}
