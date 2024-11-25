package sample.cafekisok.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sample.cafekisok.spring.interceptor.FirstInterceptor;
import sample.cafekisok.spring.interceptor.SecondInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirstInterceptor())
            .order(1);
        registry.addInterceptor(new SecondInterceptor())
            .order(2);
    }

}
