package uz.pdp;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.exception.BadRequestException;

@ControllerAdvice("uz.pdp")
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ModelAndView handleBadRequestBookTitleHandler(BadRequestException e) {
        ModelAndView modelAndView = new ModelAndView("error/400");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

}
