package uz.pdp.config;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Calculator {

    public Double add(Double a, Double b) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return a + b;
    }

    public Double subtract(Double a, Double b) {
        System.out.println("Subtract method working...");
        return a - b;
    }

    public Double multiply(Double a, Double b) {

        return a * b;

    }

    public double divide(double a, double b) {

        if (new Random().nextBoolean()){
            throw new RuntimeException("Error on divide");
        }
        return a / b;
    }
}
