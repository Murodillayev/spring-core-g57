package uz.pdp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // xml based
        // java based

        // create IOC
        ApplicationContext factory = new AnnotationConfigApplicationContext(IocConfig.class);

        ProductService productService = factory.getBean(ProductService.class);
        OrderService orderService = factory.getBean(OrderService.class);

        productService.m1();
        productService.m2();
        orderService.m1();



    }
}