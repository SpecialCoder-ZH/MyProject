package com.augmentum.oes.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.augmentum.oes.Constants;
import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.exception.ServiceException;
import com.augmentum.oes.model.User;
import com.augmentum.oes.service.UserService;
import com.augmentum.oes.service.impl.UserServiceImpl;
import com.augmentum.oes.util.StringUtil;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    private ServletConfig config ;

    private static final long serialVersionUID = 1L;

    private final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    public LoginServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.getRequestDispatcher("/QuestionManagerServlet.action").forward(request, response);
        } else {
            String go = request.getParameter("go");
            if (StringUtil.isEmpty(go)) {
                go = "";
            }
            request.setAttribute("go", go);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String go = request.getParameter("go");
        String querystring = request.getParameter("querystring");
        System.out.println(querystring);

        try {
            User user = null;
            user = userService.login(userName, password);
            user.setUserName(userName);
            user.setPassword(password);
            HttpSession session = request.getSession();
            user.setPassword(null);
            session.setAttribute("user", user);

            if (!StringUtil.isEmpty(querystring)) {
                if (querystring.startsWith("#")) {
                    querystring = querystring.substring(1);
                }

                go = go + "?" + querystring;
            }

            if (StringUtil.isEmpty(go)) {
                request.getRequestDispatcher("/QuestionManagerServlet.action").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath()+"/"+go);
            }
        } catch (ParameterException e) {
            Map<String,String> errorMessage = e.getErrorFields();
            request.setAttribute(Constants.ERROR_MESSAGE, errorMessage);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }catch (ServiceException e) {
            request.setAttribute(Constants.MESSAGE, e.getMessage());
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }
    }
}
