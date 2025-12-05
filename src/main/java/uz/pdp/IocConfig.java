package uz.pdp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IocConfig {

    @Bean
    public ProductService productService() {
        return new ProductService(productValidator());
    }

    @Bean
    public ProductValidator productValidator() {
        return new ProductValidator();
    }

    @Bean
    public OrderService orderService() {
        OrderService orderService = new OrderService();
        orderService.setProductValidator(productValidator());
        return orderService;
    }
}

// BeanFactory -> deprecated
// ApplicationContext  -> actual
