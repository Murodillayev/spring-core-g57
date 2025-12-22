package uz.pdp.repository;

import uz.pdp.model.Permission;

import java.util.List;

public interface PermissionRepository {
    List<Permission> findAllByRoleId(String roleId);
}
