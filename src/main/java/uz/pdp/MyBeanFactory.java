package uz.pdp;

import org.springframework.context.ApplicationEventPublisher;

import java.util.HashMap;
import java.util.Map;

public class MyBeanFactory {



    public MyBeanFactory(String basePackage) {

    }

    private final Map<Class<?>, Object> beans = new HashMap<>();

    public <T> T getBean(Class<T> clazz) {

        return (T) beans.get(clazz);
    }



}
