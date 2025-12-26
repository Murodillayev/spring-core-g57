package uz.pdp.config;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class CalculatorController {
    private final Calculator calculator;

    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam("a") Double a, @RequestParam("b") Double b) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("result", calculator.add(a, b));
        return mv;
    }

    @GetMapping("/subtract")
    public ModelAndView subtract(@RequestParam("a") Double a, @RequestParam("b") Double b) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("result", calculator.subtract(a, b));
        return mv;
    }

    @GetMapping("/divide")
    public ModelAndView divide(@RequestParam("a") Double a, @RequestParam("b") Double b) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("result", calculator.subtract(a, b));
        return mv;
    }


}
