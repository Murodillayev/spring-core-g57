package uz.pdp;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Service3 {

    private final Service1 service1;

    public Service3(@Lazy Service1 service1) {
        this.service1 = service1;
    }

    public void m1() {
        System.out.println("Service3.m1");
    }

    public void m2() {
        service1.m1();
        System.out.println("Service1.m1");
    }
}
