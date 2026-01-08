package uz.pdp.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.service.BookService;
import uz.pdp.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService service;

    public RentalController(RentalService service) {
        this.service = service;
    }

    // Kitobni ijaraga berish
    @PostMapping("/rent")
    public String rent(@RequestParam("bookId") String bookId,
                       @RequestParam("borrower") String borrower) {
        service.rent(bookId, borrower);
        return "redirect:/books";  // yoki "redirect:/" — o‘zingiz xohlagancha
    }

    // Ijaradagi kitoblar ro‘yxati
    @GetMapping
    public String rentals(Model model) {

        model.addAttribute("rentals", service.activeRentals());
        return "rentals";
    }

    // Kitobni qaytarish
    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable("id") String id) {
        service.returnBook(id);
        return "redirect:/rentals";
    }
}