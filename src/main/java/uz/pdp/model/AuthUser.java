package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class AuthUser {
    private String id = UUID.randomUUID().toString();
    private String fullName;
    private String username;
    private String password;
    private String roleId;
    private String imgUrl;


}
