package uz.pdp.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.config.CustomUserDetails;
import uz.pdp.config.CustomUserDetailsService;
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

//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        CustomUserDetails sessionUser =(CustomUserDetails) authentication.getPrincipal();


        List<IdNameDto> roles = roleService.roles();

        model.addAttribute("roles", roles);

        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@ModelAttribute AuthUserCreateDto dto) {
        service.create(dto);
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
