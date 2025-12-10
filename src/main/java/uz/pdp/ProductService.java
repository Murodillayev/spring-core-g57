package uz.pdp;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class ProductService {


    {
        System.out.println(" ProductService bean yaratildi");
    }

    private final ProductValidator validator;

    public ProductService(ProductValidator validator) {
        this.validator = validator;
    }

    public void initMethod() {
        System.out.println("initMethod");
    }

    public void m1() {
        validator.validate();
        System.out.println("m1 method works");
    }

    public void destroyMethod() {
        System.out.println("destroyMethod");
    }


}


// lazy, eager

//  -> fetch type lazy (soraganda yaratiladi)
//  -> fetch type eager (ioc ishga tushishi bilan yaratiladi)

//default
// singleton -> eager
// protype -> lazy