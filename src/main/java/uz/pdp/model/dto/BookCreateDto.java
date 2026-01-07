package uz.pdp.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookCreateDto {

    @NotBlank(message = "Bo'sh bolmasligi shart")
    @NotNull(message = "Null bolmasligi shart")
    @Size(min = 4, max = 20)
    private String title;

    @NotBlank
    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,}")
    private String author;


    @Size(min = 5, max = 10)
    private String isbn;

    @Min(1)

    private Integer totalCopies;
}
