package uz.pdp.repository;

import uz.pdp.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(String id);

    void save(Book book);

    default void deleteById(String id) {

    }
}
