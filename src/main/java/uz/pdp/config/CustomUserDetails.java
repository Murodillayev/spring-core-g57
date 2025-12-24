package uz.pdp.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomUserDetails implements UserDetails {
    private String id;
    private String imgUrl;
    private String fullName;
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities;




}
