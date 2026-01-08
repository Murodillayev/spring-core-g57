package uz.pdp.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDto {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private Integer totalCopies;
    private Integer rentedCopies = 0;
    private String libraryId;
}
