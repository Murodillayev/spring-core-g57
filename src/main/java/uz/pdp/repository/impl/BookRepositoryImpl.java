package uz.pdp.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Book;
import uz.pdp.repository.BookRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {

        String sql = "SELECT * FROM books";

        return jdbcTemplate.query(sql, bookRowMapper());
    }

    @Override
    public Optional<Book> findById(String id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        Book book = jdbcTemplate.queryForObject(sql, bookRowMapper(), id);
        return Optional.ofNullable(book);
    }

    @Override
    public void save(Book book) {
        Optional<Book> byId = findById(book.getId());
        String sql = (byId.isEmpty()) ?
                "INSERT INTO books (title, author, isbn, total_copies,rented_copies, id) VALUES (?, ?, ?, ?, ?, ?)"
                :
                "UPDATE books SET title = ?, author = ?, isbn = ?, total_copies = ?,rented_copies = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getTotalCopies(),
                book.getRentedCopies(),
                book.getId()
        );
    }

    private RowMapper<Book> bookRowMapper() {
        return (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getString("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            book.setTotalCopies(rs.getInt("totalCopies"));
            book.setRentedCopies(rs.getInt("rentedCopies"));
            return book;
        };
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}


// query -> find many
// queryForObject -> find one
// update  -> create, update, delete
