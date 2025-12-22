package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@ToString
public class Rental {

    private String id = UUID.randomUUID().toString();
    private Book book;
    private String borrowerName;
    private LocalDate rentDate = LocalDate.now();
    private boolean returned = false;

    public String getId() { return id; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
    public LocalDate getRentDate() { return rentDate; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
}
