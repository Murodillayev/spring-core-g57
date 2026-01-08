package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.model.base.BaseEntity;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@ToString
public class Rental extends BaseEntity {
    private String bookId;
    private String borrowerName;
    private boolean returned = false;
}
