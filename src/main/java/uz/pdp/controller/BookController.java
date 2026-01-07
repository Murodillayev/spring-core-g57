package uz.pdp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.config.SessionUser;
import uz.pdp.model.dto.BookCreateDto;
import uz.pdp.service.BookService;


@Controller
@RequestMapping("/books")
public class BookController {
    private final SessionUser sessionUser;

    private final BookService service;

    public BookController(SessionUser sessionUser, BookService service) {
        this.sessionUser = sessionUser;
        this.service = service;
    }

    @GetMapping
    public String list(Model model, @RequestParam(value = "success", required = false) String success) {
        model.addAttribute("books", service.getAll());
        model.addAttribute("success", success);
        return "books";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("book", new BookCreateDto());
        return "book-form";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("book") BookCreateDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "book-form";
        }

        service.add(dto);

        return "redirect:/books?success=Successfully";
    }

    //    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {

        return "admin_page";
    }

    //    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/user")
//    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public String user() {
        return "user_page";
    }


}
