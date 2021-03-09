package by.nintendo.datajpa.interceptor;

import by.nintendo.datajpa.model.UserStatus;
import by.nintendo.datajpa.service.KeyService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorithationInterceptor implements HandlerInterceptor {
    private final KeyService keyService;


    public AuthorithationInterceptor(KeyService keyService) {
        this.keyService = keyService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String d = request.getHeader("Key");
        UserStatus status = keyService.validKey(d);
        if (status.equals(UserStatus.USER)) {
            return true;
        } else {
            return false;
        }
    }
}