package uz.pdp.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.model.AuthRole;
import uz.pdp.repository.RoleRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<AuthRole> findById(String id) {
        String sql = "SELECT r.* FROM auth_role r WHERE r.id = ?";

        try {
            AuthRole authRole = jdbcTemplate.queryForObject(sql, rowMapper(), id);

            return Optional.ofNullable(authRole);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private RowMapper<AuthRole> rowMapper() {
        return (rs, rowNum) -> {
            AuthRole authRole = new AuthRole();
            authRole.setId(rs.getString("id"));
            authRole.setName(rs.getString("name"));
            authRole.setCode(rs.getString("code"));
            return authRole;
        };
    }
}
