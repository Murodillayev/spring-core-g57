package uz.pdp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.pdp.repository.AuthUserRepository;
import uz.pdp.repository.PermissionRepository;
import uz.pdp.repository.RoleRepository;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true
//)
public class SecurityConfig {

    private String[] WHITE_LIST = {
            "/login",
            "/register",
            "/test/**",
    };
    private final AuthUserRepository authUserRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public SecurityConfig(AuthUserRepository authUserRepository, PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.authUserRepository = authUserRepository;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security.authorizeHttpRequests(
                auth ->
                        auth.requestMatchers(WHITE_LIST)
                                .permitAll()
                                .requestMatchers("/books/admin")
                                .hasAnyAuthority("create:book", "create:rental")
                                .requestMatchers("/books/user")
                                .hasAnyRole("USER", "ADMIN")
                                .anyRequest()
                                .authenticated()
        );


        // form based auth config
        security.formLogin(
                loginForm ->
                        loginForm.loginPage("/login")
                                .usernameParameter("usrn")
                                .passwordParameter("pass")
                                .defaultSuccessUrl("/", true)
        );

        security.logout(
                logout ->
                        logout.logoutUrl("/logout_post")
                                .deleteCookies("JSESSIONID")
                                .clearAuthentication(true)
                                .logoutSuccessUrl("/")

        );

        security.userDetailsService(customUserDetailsService());
        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService(authUserRepository, permissionRepository, roleRepository);
    }


}
