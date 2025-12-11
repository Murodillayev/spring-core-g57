package uz.pdp;

import org.springframework.stereotype.Component;

@Component
public class Service1 {

    private final Service2 service2;

    public Service1(Service2 service2) {
        this.service2 = service2;
    }


    public void m1() {
        System.out.println("m1 method works");
        service2.m1();

    }



}


// lazy, eager

//  -> fetch type lazy (soraganda yaratiladi)
//  -> fetch type eager (ioc ishga tushishi bilan yaratiladi)

//default
// singleton -> eager
// protype -> lazy