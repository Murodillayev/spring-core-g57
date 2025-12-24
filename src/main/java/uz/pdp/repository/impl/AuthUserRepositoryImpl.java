package uz.pdp.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import uz.pdp.model.AuthUser;
import uz.pdp.repository.AuthUserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthUserRepositoryImpl implements AuthUserRepository {

    private final JdbcTemplate jdbcTemplate;


    public AuthUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<AuthUser> findByUsername(String username) {

        String sql = "SELECT * FROM auth_users WHERE username = ?";

        try {
            AuthUser user = jdbcTemplate.queryForObject(sql, rowMapper(), username);

            return Optional.ofNullable(user);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(AuthUser authUser) {
        String sql = "insert into auth_users(id,username,password,full_name,img_url,role_id) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, authUser.getId(), authUser.getUsername(), authUser.getPassword(), authUser.getFullName(), authUser.getImgUrl(), authUser.getRoleId());
    }

    @Override
    public List<AuthUser> findAll() {
        String sql = "SELECT * FROM auth_users";
        return jdbcTemplate.query(sql, rowMapper());
    }

    private RowMapper<AuthUser> rowMapper() {
        return (rs, rowNum) -> {
            AuthUser authUser = new AuthUser();
            authUser.setUsername(rs.getString("username"));
            authUser.setPassword(rs.getString("password"));
            authUser.setFullName(rs.getString("full_name"));
            authUser.setRoleId(rs.getString("role_id"));
            authUser.setImgUrl(rs.getString("img_url"));
            authUser.setId(rs.getString("id"));
            return authUser;
        };
    }
}
