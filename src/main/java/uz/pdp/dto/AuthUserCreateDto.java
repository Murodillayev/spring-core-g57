package uz.pdp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserCreateDto {
    private String fullName;
    private String username;
    private String roleId;
    private String password;
}
