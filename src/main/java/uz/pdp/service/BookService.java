package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.model.Book;
import uz.pdp.model.dto.BookCreateDto;
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

    public void add(BookCreateDto dto) {
        validator.validateAdd(dto);

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setTotalCopies(dto.getTotalCopies());

        repository.save(book);
    }

}
