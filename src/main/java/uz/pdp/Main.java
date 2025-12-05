package uz.pdp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // xml based
        // java based

        // create IOC
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:ioc-config.xml");

        ProductValidator productValidatorBean = factory.getBean(ProductValidator.class);
        ProductValidator productValidatorBean2 = factory.getBean(ProductValidator.class);
        ProductService productServiceBean = factory.getBean("productService", ProductService.class);
        ProductService productServiceBean2 = factory.getBean("productService", ProductService.class);
        ProductService productServiceBean3 = factory.getBean("productService2", ProductService.class);
        ProductService productServiceBean4 = factory.getBean("productService2", ProductService.class);


        System.out.println(productValidatorBean);
        System.out.println(productValidatorBean2);


        System.out.println(productServiceBean);
        System.out.println(productServiceBean2);
        System.out.println(productServiceBean3);
        System.out.println(productServiceBean4);

    }
}