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

import com.augmentum.oes.AppContext;
import com.augmentum.oes.Constants;
import com.augmentum.oes.model.User;


public class AppContextFilter implements Filter {


    public AppContextFilter() {
    }


    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        String uri = request.getRequestURI();
        String requestUri = uri.substring(request.getContextPath().length()+1);

        AppContext.setContextPath(request.getContextPath());

        if (requestUri.endsWith(".css") || requestUri.endsWith(".js") || requestUri.endsWith(".png") || requestUri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }
        AppContext appContext = AppContext.getAppContext();
        appContext.addObject(Constants.APP_CONTEXT_REQUEST, request);
        appContext.addObject(Constants.APP_CONTEXT_RESPONSE, response);
        HttpSession session = request.getSession();
        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);
        User user = (User) session.getAttribute(Constants.USER);
        appContext.addObject(Constants.USER, user);
        try {
            chain.doFilter(request, response);
        } catch (IOException ioException) {
            throw ioException;
        } catch (ServletException servletException) {
            throw servletException;
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        } finally {
            appContext.clear();
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
