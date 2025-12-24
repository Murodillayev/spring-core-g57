package uz.pdp.repository;

import uz.pdp.model.AuthRole;

import java.util.List;

public interface AuthRoleRepository {
    List<AuthRole> findAll();
}
