package uz.pdp.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IdNameDto {
    private String id;
    private String name;
}
