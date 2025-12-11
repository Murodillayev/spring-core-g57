package uz.pdp;

import org.springframework.stereotype.Component;

@Component
public class Service2 {

    private final Service3 service3;
    public Service2(Service3 service3) {
        this.service3 = service3;
    }


    public void m1(){
        System.out.println("Service2.m1");
        service3.m1();
    }
}
