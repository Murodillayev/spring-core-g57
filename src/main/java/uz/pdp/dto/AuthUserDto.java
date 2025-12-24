package uz.pdp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthUserDto {
    private String id;
    private String fullName;
    private String username;
    private String role;
}
