package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.model.base.BaseEntity;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Book extends BaseEntity {
    private String title;
    private String author;
    private String isbn;
    private Integer totalCopies;
    private Integer rentedCopies = 0;
    private String libraryId;
}
