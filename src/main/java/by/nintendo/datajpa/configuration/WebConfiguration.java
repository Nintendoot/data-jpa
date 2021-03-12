package by.nintendo.datajpa.configuration;

import by.nintendo.datajpa.interceptor.AdminInterceptor;
import by.nintendo.datajpa.interceptor.AuthorithationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final AdminInterceptor adminInterceptor;
    private final AuthorithationInterceptor authorithationInterceptor;

    public WebConfiguration(AdminInterceptor adminInterceptor, AuthorithationInterceptor authorithationInterceptor) {
        this.adminInterceptor = adminInterceptor;
        this.authorithationInterceptor = authorithationInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(adminInterceptor).
                addPathPatterns("/user/all").
                addPathPatterns("/user/{name}").
                addPathPatterns("/store/all").
                addPathPatterns("/store/order/{id}").
                addPathPatterns("/store/order/inventory").
                addPathPatterns("/pet").
                //excludePathPatterns("/pet/all").
                excludePathPatterns("/pet/findByStatus").
                addPathPatterns("/pet/{id}").
                excludePathPatterns("/user/auth");

        registry.addInterceptor(authorithationInterceptor).
                addPathPatterns("/pet/all").
                addPathPatterns("/pet/findByStatus").
                addPathPatterns("/store/order");


    }


}
