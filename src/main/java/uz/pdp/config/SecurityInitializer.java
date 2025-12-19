package uz.pdp.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


// koprikni quradi
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
