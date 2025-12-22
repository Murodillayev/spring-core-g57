package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uz.pdp.model.AuthUser;
import uz.pdp.service.AuthUserService;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthUserService service;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new AuthUser());

        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@ModelAttribute("user") AuthUser authUser) {
        service.create(authUser);
        return "redirect:/login";
    }

}
