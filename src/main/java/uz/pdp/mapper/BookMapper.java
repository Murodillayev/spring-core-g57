package uz.pdp.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.model.Book;
import uz.pdp.model.dto.BookDto;

@Component
public class BookMapper {

    public BookDto toDto(Book book) {


        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .id(book.getId())
                .totalCopies(book.getTotalCopies())
                .rentedCopies(book.getRentedCopies())
                .build();
    }
}
