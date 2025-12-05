package uz.pdp;

public class OrderService {

    private ProductValidator productValidator;


    // DI
//    public OrderService(ProductValidator productValidator) {
//        this.productValidator = productValidator;
//    }

    public void setProductValidator(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }

    public void m1() {
        productValidator.validate();
        System.out.println("Order service m1 called : validator -> " + productValidator);
    }
}

// DI

// -> SI
// -> FI
// -> CI