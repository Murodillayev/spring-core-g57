package uz.pdp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("%s %s ga so'rov qildi".formatted(request.getSession().getId(), request.getRequestURI()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        response.getWriter().println("<h1>Salom men postHandle man</h1>");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Thread.sleep(10000);
        System.out.println("Request tugadi");

    }
}
// http req (-> f1 -> f2 -> f3 -> SecurityFilter -> | servlet) -> (interceptor -> controller  -> service ->  ...)-> db
// -> REQ (BODY, HEADER(token)) -> Filter(Auht) -> |servlet -> controller  -> service ->  ...-> db
// {"Authorization":"secter_token"}
