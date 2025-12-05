package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    @Autowired
    private ProductValidator productValidator;

//    public ProductService(ProductValidator productValidator) {
//        this.productValidator = productValidator;
//    }

    public void m1() {
        productValidator.validate();
        System.out.println("m1 method works: validator -> " + productValidator);
    }

    public void m2() {
        productValidator.validate();
        System.out.println("m2 method works: validator -> " + productValidator);
    }
}
