package uz.pdp.service;

import uz.pdp.model.Book;
import uz.pdp.model.Rental;
import uz.pdp.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final RentalRepository repository;

    public RentalService(RentalRepository repository) {
        this.repository = repository;
    }

    public void rent(Book book, String borrower) {
        if (book.getRentedCopies() < book.getTotalCopies()) {
            book.setRentedCopies(book.getRentedCopies() + 1);
            Rental rental = new Rental();
            rental.setBook(book);
            rental.setBorrowerName(borrower);
            repository.save(rental);
        }
    }

    public List<Rental> activeRentals() {
        return repository.findActive();
    }

    public void returnBook(String rentalId) {
        Rental rental = repository.findById(rentalId);
        if (rental != null && !rental.isReturned()) {
            rental.setReturned(true);
            Book book = rental.getBook();
            book.setRentedCopies(book.getRentedCopies() - 1);
        }
    }
}
