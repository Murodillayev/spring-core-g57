package uz.pdp.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Book;
import uz.pdp.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {

        String sql = "SELECT * FROM books order by title desc";

        return jdbcTemplate.query(sql, bookRowMapper());
    }

    @Override
    public Optional<Book> findById(String id) {
        String sql = "SELECT * FROM books WHERE id = :id";
        try {
            Book book = jdbcTemplate.queryForObject(sql, Map.of("id", id), bookRowMapper());
            return Optional.ofNullable(book);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    @Override
    public void save(Book book) {
        Optional<Book> byId = findById(book.getId());
        String sql = (byId.isEmpty()) ?
                "INSERT INTO books (id,title, author, isbn, total_copies,rented_copies) VALUES (:id,:title, :author, :isbn, :total_copies,:rented_copies)"
                :
                "UPDATE books SET title = :title, author = :author, isbn = :isbn, total_copies = :total_copies, rented_copies = :rented_copies WHERE id = :id";

        jdbcTemplate.update(sql, Map.of(
                "title", book.getTitle(),
                "rented_copies", book.getRentedCopies(),
                "author", book.getAuthor(),
                "isbn", book.getIsbn(),
                "total_copies", book.getTotalCopies(),
                "id", book.getId()
        ));
    }

    private RowMapper<Book> bookRowMapper() {
        return (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getString("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            book.setTotalCopies(rs.getInt("total_copies"));
            book.setRentedCopies(rs.getInt("rented_copies"));
            return book;
        };
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM books WHERE id = :id";
        jdbcTemplate.update(sql, Map.of("id", id));
    }
}


// query -> find many
// queryForObject -> find one
// update  -> create, update, delete

// omborchi (spring) 95 %
// dorixonachi (spring) 50 %
// online taxi (telegram bot + spring) 20 %   (*)
// online restaurant delivery (telegram bot + spring) 20 %  (*)
// dokonchi (spring) 20 %  (*)
// db-control (spring) 0.3 %        (*)
// online document (spring) 0.1 %   (*)
// online shop (spring)  90 %


// java(90% bank-davlat,), python, node js(payme,click), go, php(ba


// web, mobile, desctop
