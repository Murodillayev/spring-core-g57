package uz.pdp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BeanFactory factory = new AnnotationConfigApplicationContext(IocConfig.class);

        ProductService service = factory.getBean(ProductService.class);

        service.m1();
        service.m2();
    }
}