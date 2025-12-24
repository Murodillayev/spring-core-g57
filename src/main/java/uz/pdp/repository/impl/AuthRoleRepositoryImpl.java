package uz.pdp.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.model.AuthRole;
import uz.pdp.repository.AuthRoleRepository;

import java.util.List;

@Repository
public class AuthRoleRepositoryImpl implements AuthRoleRepository {
    private final JdbcTemplate jdbcTemplate;

    public AuthRoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AuthRole> findAll() {

        String sql = "select * from auth_role";
        RowMapper<AuthRole> rowMapper = BeanPropertyRowMapper.newInstance(AuthRole.class);
        List<AuthRole> roles = jdbcTemplate.query(sql, rowMapper);
        return roles;
    }
}
