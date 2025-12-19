package uz.pdp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.util.AntPathMatcher;

import java.awt.desktop.UserSessionEvent;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security.authorizeHttpRequests(
                auth ->
                        auth.requestMatchers("/login")
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
        return security.build();
    }


    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        User user2 = new User("admin", passwordEncoder.encode("123"), List.of());
        User user1 = new User("user", passwordEncoder.encode("111"), List.of());
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
