package uz.pdp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class SecurityFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

        String token = req.getHeader("Authorization"); // {"username":mansur,"role":"admin"} -> sadljkasdjkladsjkhasljkhadjlkhsaljhsdaljdsahljsadhsadljkhsadulhu32u432w7edwsaudsa

        if (token != null) {
            chain.doFilter(req, res);
        }else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }

//        String requestURI = req.getRequestURI();
//        String method = req.getMethod();
//        String sessionId = req.getSession().getId();
//
//        Map<String, Object> headers = new HashMap<>();
//        Enumeration<String> headerNames = req.getHeaderNames();
//
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = req.getHeader(headerName);
//            headers.put(headerName, headerValue);
//        }
//
//        System.out.println("Request URI: " + requestURI);
//        System.out.println("Method: " + method);
//        System.out.println("SessionId: " + sessionId);
//        System.out.println("Headers: " + headers);

    }
}
// http req (-> f1 -> f2 -> f3 -> SecurityFilter -> | servlet) -> (interceptor -> controller  -> service ->  ...)-> db
// -> REQ (BODY, HEADER(token)) -> Filter(Auht) -> |servlet -> controller  -> service ->  ...-> db
// {"Authorization":"secter_token"}
