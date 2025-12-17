package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Book {

    private String id = UUID.randomUUID().toString();
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int rentedCopies = 0;
}
