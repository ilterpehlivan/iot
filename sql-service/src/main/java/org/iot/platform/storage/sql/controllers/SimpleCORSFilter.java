package org.iot.platform.storage.sql.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCORSFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(SimpleCORSFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        logger.debug("Origin: {}", request.getHeader("Origin"));

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Cache-Control, X-Requested-With, User-Id, Session-Id");
        response.setHeader("Access-Control-Expose-Headers", "Content-Type, Accept, Cache-Control, X-Requested-With, User-Id, Session-Id");

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        //doing nothing
    }

    @Override
    public void destroy() {
        //doing nothing
    }

}
