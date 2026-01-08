package uz.pdp.repository;

import uz.pdp.model.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalRepository {

    void save(Rental rental);

    List<Rental> findActive();

    Optional<Rental> findById(String id);
}
