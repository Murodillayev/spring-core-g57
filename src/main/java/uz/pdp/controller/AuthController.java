package uz.pdp.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.dto.AuthUserCreateDto;
import uz.pdp.dto.IdNameDto;
import uz.pdp.model.AuthUser;
import uz.pdp.service.AuthRoleService;
import uz.pdp.service.AuthUserService;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthUserService service;
    private final AuthRoleService roleService;

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
        List<IdNameDto> roles = roleService.roles();

        model.addAttribute("roles", roles);

        return "register";
    }

    @PostMapping("/register")
    public String registerPage(
            @ModelAttribute AuthUserCreateDto dto,
            @RequestParam("img") MultipartFile img) {

        service.create(dto, img);
        return "redirect:/login";
    }


//    @GetMapping("/test")
//    @ResponseBody
//    public String test(
//            @RequestParam(value = "l", required = false) String lastName,
//            @RequestParam(value = "f", required = false) String firstName
//            ) {
//        return "Hello " + firstName + " " + lastName;
//    }

//    @GetMapping("/test/{path}")
//    @ResponseBody
//    public String test(
//            @PathVariable(value = "path") String path) {
//        return path;
//    }

}
