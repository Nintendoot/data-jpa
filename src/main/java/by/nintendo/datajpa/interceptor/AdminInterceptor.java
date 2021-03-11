package by.nintendo.datajpa.interceptor;

import by.nintendo.datajpa.model.Role;
import by.nintendo.datajpa.service.KeyService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    private final KeyService keyService;

    public AdminInterceptor(KeyService keyService) {
        this.keyService = keyService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String d = request.getHeader("Key");
        Role status = keyService.validKey(d);
        if (status.equals(Role.ADMIN)) {
            return true;
        } else {
            return false;
        }
    }
}
