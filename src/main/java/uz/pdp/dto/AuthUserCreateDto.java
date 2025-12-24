package uz.pdp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AuthUserCreateDto {
    private String fullName;
    private String username;
    private String roleId;
    private String password;
    private MultipartFile[] imgs;
    private MultipartFile img;
}

