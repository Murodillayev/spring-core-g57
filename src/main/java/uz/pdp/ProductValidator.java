package uz.pdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    @Autowired
    private ProductService productService;


    public void validate() {
        System.out.println("validate method works");
    }
}
