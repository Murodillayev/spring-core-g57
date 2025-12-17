package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.Rental;
import uz.pdp.repository.BookRepository;
import uz.pdp.repository.RentalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RentalInMemRepository implements RentalRepository {

    private static final List<Rental> rentals = new ArrayList<>();

    public void save(Rental rental) {
        rentals.add(rental);
    }

    public List<Rental> findActive() {
        return rentals.stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

    public Rental findById(String id) {
        return rentals.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
