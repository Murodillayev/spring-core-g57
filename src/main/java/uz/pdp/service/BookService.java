package uz.pdp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import uz.pdp.model.Book;
import uz.pdp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(@Qualifier("bookRepositoryImpl") BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Book with id " + id + " not found")
        );
    }

    public void add(Book book) {
        repository.save(book);
    }
}
