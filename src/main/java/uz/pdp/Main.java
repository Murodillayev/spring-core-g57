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

        ProductValidator productValidatorBean = factory.getBean(ProductValidator.class);        ProductValidator productValidatorBean2 = factory.getBean(ProductValidator.class);

        ProductService productService = factory.getBean(ProductService.class);


        System.out.println(productValidatorBean);
        System.out.println(productService);







    }
}