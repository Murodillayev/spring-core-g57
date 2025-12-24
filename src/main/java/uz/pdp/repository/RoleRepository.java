package uz.pdp.repository;

import uz.pdp.model.AuthRole;
import uz.pdp.model.Permission;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Optional<AuthRole> findById(String id);

}
