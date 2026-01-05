package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.model.Book;
import uz.pdp.repository.BookRepository;
import uz.pdp.validator.BookValidator;

import java.util.List;

@Service
public class BookService {

    private final BookValidator validator;
    private final BookRepository repository;

    public BookService(BookValidator validator, BookRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(String id) {
        return validator.existsAndGet(id);
    }

    public void add(Book book) {
        validator.validateAdd(book);
        repository.save(book);
    }

}
