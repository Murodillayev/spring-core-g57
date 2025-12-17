package uz.pdp.controller;

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

    private final BookService service;

    public BookController(BookService service) {
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
}
