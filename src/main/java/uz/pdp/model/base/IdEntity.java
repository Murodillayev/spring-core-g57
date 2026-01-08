package uz.pdp.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class IdEntity {
    private String id = UUID.randomUUID().toString();
}
