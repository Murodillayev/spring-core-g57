package uz.pdp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import uz.pdp.config.CustomUserDetails;
import uz.pdp.config.SessionUser;
import uz.pdp.model.Book;
import uz.pdp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




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
    public String list(Model model) {
        model.addAttribute("books", service.getAll());
        return "books";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Book book) {
        service.add(book);
        return "redirect:/books";
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
