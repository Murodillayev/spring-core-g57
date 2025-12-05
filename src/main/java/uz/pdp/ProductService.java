package uz.pdp;

public class ProductService {


    private final ProductValidator productValidator;

    // DI, wiring
    public ProductService(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }


    public void m1() {
        productValidator.validate();
        System.out.println("m1 method works: validator -> " + productValidator);
    }

    public void m2() {
        productValidator.validate();
        System.out.println("m2 method works: validator -> " + productValidator);
    }
}
