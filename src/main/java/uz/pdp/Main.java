package uz.pdp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // xml based
        // java based

        // create IOC
//        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:ioc-config.xml");
        BeanFactory factory = new AnnotationConfigApplicationContext(IocConfig.class);

//        ProductService productService = factory.getBean(ProductService.class);
//        ProductService productService1 = factory.getBean(ProductService.class);
//        ProductService productService2 = factory.getBean(ProductService.class);
//        ProductService productService3 = factory.getBean(ProductService.class);
//
//        System.out.println(productService);
//        System.out.println(productService1);
//        System.out.println(productService2);
//        System.out.println(productService3);


    }
}