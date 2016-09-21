package com.augmentum.oes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String WELCOME_PAGE = "/WEB-INF/jsp/welcome.jsp";

    public DashboardServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.getRequestDispatcher("/WEB-INF/jsp/question/addquestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
