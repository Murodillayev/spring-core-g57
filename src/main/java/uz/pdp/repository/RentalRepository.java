package uz.pdp.repository;

import uz.pdp.model.Rental;

import java.util.List;

public interface RentalRepository {

    void save(Rental rental);

    List<Rental> findActive();

    Rental findById(String id);
}
