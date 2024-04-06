package com.works.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.IOException;
import java.util.Map;

@Configuration
public class FilterConfigs implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Uygulsms ayağa kalktığı zaman çalışır.
        System.out.println("Server UP");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        AccountLogic accountLogic = new AccountLogic();
        Map<String, String> clientInfo = accountLogic.getClientInfo(req);
        System.out.println(clientInfo);

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        long start = System.currentTimeMillis();
        String agent = req.getHeader("user-agent");
        String session = req.getSession().getId();
        String url = req.getRequestURI();
        String token = req.getHeader("token");
        System.out.println( start + " - " + url + " - " + token + " - " + session + "  - " + agent + " - " + ipAddress);
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("Server DOWN");
    }
}
