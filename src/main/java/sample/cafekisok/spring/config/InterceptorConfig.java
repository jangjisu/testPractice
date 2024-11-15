package sample.cafekisok.spring.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirstInterceptor());
        registry.addInterceptor(new SecondInterceptor());
    }

}
