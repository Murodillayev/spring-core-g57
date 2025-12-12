package uz.pdp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class MyController {

    @GetMapping("/1")
    public String test1() {
        return "index";
    }

    @GetMapping("/2")
    public ModelAndView test2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("name", "Muhammadkomil");

        return modelAndView;
    }
}
