package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private ProductValidator productValidator;

//    public OrderService(ProductValidator productValidator) {
//        this.productValidator = productValidator;
//    }

    public void m1() {
        productValidator.validate();
        System.out.println("Order service m1 called : validator -> " + productValidator);
    }
}

// DI

// -> SI
// -> FI
// -> CI

