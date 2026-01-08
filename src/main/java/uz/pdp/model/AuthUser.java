package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.model.base.BaseEntity;
import uz.pdp.model.base.IdEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class AuthUser extends BaseEntity {
    private String fullName;
    private String username;
    private String password;
    private String roleId;
    private String imgUrl;
    private String libraryId;

}
