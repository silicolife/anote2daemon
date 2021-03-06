package com.silicolife.anote2daemon.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

@Deprecated
/**
 * 
 * COnfiguration is in web.xml on src/main/webapp/WEB-INF/web.xml
 * 
 * @author Hugo Costa
 *
 */
public class CorsFilterAnoteWeb extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;       
//        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4200"/*"http://localhost:8080, http://127.0.0.1:8080, http://localhost:4200, http://127.0.0.1:4200", http://127.0.0.1:8081*/); 
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8081");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-XSRF-TOKEN, content-type, Origin");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }           
    }
}
// Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, 
//, X-SECURYTY ,  XSRF-TOKEN, x-xsrf-token,