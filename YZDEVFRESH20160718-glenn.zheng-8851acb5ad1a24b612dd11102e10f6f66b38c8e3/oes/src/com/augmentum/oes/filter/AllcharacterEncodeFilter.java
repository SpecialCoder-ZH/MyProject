package com.augmentum.oes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class AllcharacterEncodeFilter implements Filter {

    private String encode = "UTF-8";
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        if(fConfig.getInitParameter("encode")!= null) {
            encode = fConfig.getInitParameter("encode");
        }
    }

    public AllcharacterEncodeFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest http_request = (HttpServletRequest)request;
        http_request.setCharacterEncoding(encode);
        chain.doFilter(http_request, response);
    }

    @Override
    public void destroy() {
    }

}
