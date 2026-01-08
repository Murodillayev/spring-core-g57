package uz.pdp.service;

import uz.pdp.config.SessionUser;
import uz.pdp.mapper.BookMapper;
import uz.pdp.model.Book;
import uz.pdp.model.Rental;
import uz.pdp.model.dto.RentalDto;
import uz.pdp.repository.BookRepository;
import uz.pdp.repository.RentalRepository;
import org.springframework.stereotype.Service;
import uz.pdp.validator.BookValidator;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository repository;
    private final BookRepository bookRepository;
    private final BookValidator bookValidator;
    private final BookMapper bookMapper;
    private final SessionUser sessionUser;

    public RentalService(RentalRepository repository, BookRepository bookRepository, BookValidator bookValidator, BookMapper bookMapper, SessionUser sessionUser) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.bookValidator = bookValidator;
        this.bookMapper = bookMapper;
        this.sessionUser = sessionUser;
    }

    public void rent(String bookId, String borrower) {
        Book book = bookValidator.existsAndGet(bookId);

        if (book.getRentedCopies() < book.getTotalCopies()) {
            book.setRentedCopies(book.getRentedCopies() + 1);
            bookRepository.save(book);
            Rental rental = new Rental();
            rental.setBookId(book.getId());
            rental.setBorrowerName(borrower);
            rental.setCreatedAt(LocalDateTime.now());
            rental.setDeleted(false);
            rental.setReturned(false);
            rental.setUpdatedAt(LocalDateTime.now());
            rental.setCreatedBy(sessionUser.sessionUser().getId());
            rental.setUpdatedBy(sessionUser.sessionUser().getId());
            repository.save(rental);
        }
    }

    public List<RentalDto> activeRentals() {
        List<Rental> rentals = repository.findActive();
        return rentals.stream().map(
                rental -> {
                    Book book = bookValidator.existsAndGet(rental.getBookId());
                    return  RentalDto.builder()
                            .id(rental.getId())
                            .borrowerName(rental.getBorrowerName())
                            .book(bookMapper.toDto(book))
                            .createdAt(rental.getCreatedAt())
                            .build();
                }
        ).toList();
    }

    public void returnBook(String rentalId) {
        Rental rental = repository.findById(rentalId).orElseThrow();

        if (!rental.isReturned()) {
            rental.setReturned(true);
            Book book = bookValidator.existsAndGet(rental.getBookId());
            book.setRentedCopies(book.getRentedCopies() - 1);
        }
    }
}
