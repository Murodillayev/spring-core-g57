package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.Permission;
import uz.pdp.repository.PermissionRepository;

import java.util.List;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {
    @Override
    public List<Permission> findAllByRoleId(String roleId) {
        return List.of();
    }
}
