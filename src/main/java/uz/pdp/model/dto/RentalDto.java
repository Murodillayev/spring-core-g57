package uz.pdp.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class RentalDto {
    private String id;
    private String borrowerName;
    private BookDto book;
    private LocalDateTime createdAt;

}
