package uz.pdp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.Post;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

@Controller
public class MyController {

    @GetMapping(value = "/post")
    public String post(Model model) {

        model.addAttribute("post",
                Post.builder()
                        .id(UUID.randomUUID().toString())
                        .title("Spring Security")
                        .content("Spring Security is authentification and authorzation")
                        .author("Muhammadkomil")
                        .build());
        return "post";
    }


    @GetMapping("/index")
    public ModelAndView test2(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("name", "Muhammadkomil");
        modelAndView.addObject("post",
                Post.builder()
                        .id(UUID.randomUUID().toString())
                        .title("Spring Security")
                        .content("Spring Security is authentification and authorzation")
                        .author("Muhammadkomil")
                        .build()
        );

        modelAndView.addObject("posts",
                List.of(Post.builder()
                                .id(UUID.randomUUID().toString())
                                .title("Spring Security")
                                .content("Spring Security is authentification and authorzation")
                                .author("Muhammadkomil")
                                .build(),
                        Post.builder()
                                .id(UUID.randomUUID().toString())
                                .title("Spring Data")
                                .content("Spring Data is good")
                                .author("Aziz")
                                .build())
        );

        model.addAttribute("opr", "hello()'");
        model.addAttribute("buttonStyleType", "btn-success");
//        model.addAttribute("buttonStyleType", "btn-primary");
        model.addAttribute("isChecked", new Random().nextBoolean());
        return modelAndView;

    }

    @PostMapping(value = "/index/{id}")
    public String test1(@PathVariable(name = "id") String id) {
        System.out.println(id);
        return "redirect:/test/2";
    }
}


// replace
// include

// insert (deprecated)
