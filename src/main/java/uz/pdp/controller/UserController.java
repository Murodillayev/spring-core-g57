package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.dto.AuthUserDto;
import uz.pdp.model.AuthUser;
import uz.pdp.service.AuthUserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final AuthUserService service;

    public UserController(AuthUserService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView users() {
        List<AuthUserDto> users = service.getAll();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
