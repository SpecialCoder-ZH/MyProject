package com.augmentum.oes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.augmentum.oes.model.User;
import com.augmentum.oes.util.StringUtil;

public class SessionFilter implements Filter {

    private String notNeetLoginPages = "";
    private FilterConfig fConfig = null;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.fConfig = fConfig;
        if (fConfig.getInitParameter("notNeetLoginPages") != null) {
            notNeetLoginPages = fConfig.getInitParameter("notNeetLoginPages");
        }
    }

    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletrequest;
        HttpServletResponse response = (HttpServletResponse)servletresponse;
        String uri = request.getRequestURI();
        String requestUri = uri.substring(request.getContextPath().length()+1);

        String[] pages = notNeetLoginPages.split(",");
        boolean isAllow = false;
        for (String page : pages) {
            if (page.equals(requestUri)) {
                isAllow = true;
                break;
            }

            if (requestUri.endsWith(".css") || requestUri.endsWith(".js") || requestUri.endsWith(".png") || requestUri.endsWith(".jpg")) {
                isAllow = true;
                break;
            }
        }

        if (isAllow) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                if (request.getMethod().toLowerCase().equals("get")) {
                    String queryString = request.getQueryString();
                    String go = requestUri;
                    if (!StringUtil.isEmpty(queryString)) {
                        go = go + "#" + request.getQueryString();
                     }
                        System.out.println(requestUri+" <-----requestURI");
                        response.sendRedirect(request.getContextPath()+"/ToLogin?go="+go);
                   } else {
                         response.sendRedirect(request.getContextPath()+"/ToLogin");
                }
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        this.notNeetLoginPages= null;
    }
}
