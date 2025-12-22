package uz.pdp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.pdp.repository.AuthUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthUserRepository authUserRepository;

    public SecurityConfig(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security.authorizeHttpRequests(
                auth ->
                        auth.requestMatchers(
                                        "/login", "/register")
                                .permitAll()
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
        return new CustomUserDetailsService(authUserRepository);
    }

}
