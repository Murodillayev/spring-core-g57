package uz.pdp.validator;

import org.springframework.stereotype.Component;
import uz.pdp.exception.BadRequestException;
import uz.pdp.model.Book;
import uz.pdp.repository.BookRepository;

@Component
public class BookValidator {
    private final BookRepository repository;

    public BookValidator(BookRepository repository) {
        this.repository = repository;
    }

    public void validateAdd(Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new BadRequestException("Book title is empty");
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new BadRequestException("Book author is empty");
        }
        if (book.getTotalCopies() == null || book.getTotalCopies() == 0) {
            throw new BadRequestException("Book totalCopies is empty");
        }
    }

    public Book existsAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Book with id " + id + " not found")
        );
    }
}
