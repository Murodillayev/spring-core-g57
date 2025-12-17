package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.Book;
import uz.pdp.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class BookInMemRepository implements BookRepository {

    private static final List<Book> books = new ArrayList<>();

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(String id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    public void save(Book book) {
        books.add(book);
    }
}
