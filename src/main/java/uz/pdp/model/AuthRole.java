package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.model.base.IdEntity;

@Getter
@Setter
@ToString
public class AuthRole extends IdEntity {

    private String name;
    private String code;
}
