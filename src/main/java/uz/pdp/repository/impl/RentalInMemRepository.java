package uz.pdp.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.config.SessionUser;
import uz.pdp.model.Rental;
import uz.pdp.repository.BookRepository;
import uz.pdp.repository.RentalRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RentalInMemRepository implements RentalRepository {

    private final SessionUser sessionUser;
//    private static final List<Rental> rentals = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RentalInMemRepository(SessionUser sessionUser, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.sessionUser = sessionUser;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(Rental rental) {

        Optional<Rental> byId = findById(rental.getId());
        if (byId.isPresent()) {
            String sql = "update rentals set returned=?,borrow_name=?,updated_at = ?,updated_by = ? where id=?";
            jdbcTemplate.update(sql, rental.isReturned(), rental.getBorrowerName(), rental.getUpdatedAt(), rental.getUpdatedBy(), rental.getId());
        } else {
            String sql = """
                    insert into rentals(id, book_id, borrower_name, created_at, returned, updated_at, created_by, updated_by, deleted) 
                    values (:id, :book_id, :borrower_name, :created_at, :returned, :updated_at, :created_by, :updated_by, :deleted)
                    """;

//            namedParameterJdbcTemplate.update(sql, Map.of(
//                    "id", rental.getId(),
//                    "book_id", rental.getBookId(),
//                    "borrower_name", rental.getBorrowerName(),
//                    "created_at", rental.getCreatedAt(),
//                    "returned", false,
//                    "updated_at", LocalDateTime.now(),
//                    "updated_by", sessionUser.sessionUser().getId(),
//                    "created_by", sessionUser.sessionUser().getId(),
//                    "deleted",false
//            ));
            jdbcTemplate.update(
                    "insert into rentals(id, book_id, borrower_name, created_at, returned, updated_at, created_by, updated_by, deleted) values (?,?,?,?,?,?,?,?,?)",
                    rental.getId(),
                    rental.getBookId(),
                    rental.getBorrowerName(),
                    rental.getCreatedAt(),
                    rental.isReturned(),
                    rental.getUpdatedAt(),
                    rental.getCreatedBy(),
                    rental.getUpdatedBy(),
                    rental.getDeleted()
            );
        }
    }

    public List<Rental> findActive() {
        String sql = "SELECT * FROM rentals WHERE returned = false";
        return jdbcTemplate.query(sql, rowMapper());

    }

    public Optional<Rental> findById(String id) {

        String sql = "select * from rentals r where r.id = ?";
        try {
            Rental rental = jdbcTemplate.queryForObject(sql, rowMapper(), id);
            return Optional.ofNullable(rental);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    private RowMapper<Rental> rowMapper() {
        return (RowMapper<Rental>) (rs, rowNum) -> {
            Rental rental = new Rental();
            rental.setId(rs.getString("id"));
            rental.setBookId(rs.getString("book_id"));
            rental.setBorrowerName(rs.getString("borrower_name"));
            rental.setReturned(rs.getBoolean("returned"));

            rental.setCreatedAt(rs.getDate("created_at").toLocalDate().atStartOfDay());
            rental.setCreatedBy(rs.getString("created_by"));

            rental.setUpdatedAt(rs.getDate("updated_at").toLocalDate().atStartOfDay());
            rental.setUpdatedBy(rs.getString("updated_by"));
            return rental;
        };
    }
}
