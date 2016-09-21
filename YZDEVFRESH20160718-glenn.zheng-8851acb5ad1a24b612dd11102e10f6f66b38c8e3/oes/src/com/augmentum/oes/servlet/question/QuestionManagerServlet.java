package com.augmentum.oes.servlet.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.augmentum.oes.model.Page;
import com.augmentum.oes.service.QuestionService;
import com.augmentum.oes.service.impl.QuestionServiceImpl;


public class QuestionManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private QuestionService service = new QuestionServiceImpl();

    public QuestionManagerServlet() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forwardId = request.getParameter("forwardId");
        if (forwardId.equals("2")) {
            request.getRequestDispatcher("/WEB-INF/jsp/question/createQuestion.jsp").forward(request, response);
        }

        if (forwardId.equals("1")) {
            String keywords = request.getParameter("keywords");
            request.setAttribute("keywords", keywords);
            String pagenum = request.getParameter("pagenum");

            String pageSize = request.getParameter("pagesize");
            request.setAttribute("pageSize", pageSize);

            if (pageSize!=null && !("".equals(pageSize))) {
                Page page = service.findPageRecordsByKeyWords(pagenum, keywords,pageSize);
                page.setServleturl("QuestionManagerServlet.action");
                request.setAttribute("page", page);
                request.getRequestDispatcher("/WEB-INF/jsp/question/questionList.jsp").forward(request, response);
            } else {
                Page page = service.findPageRecordsByKeyWords(pagenum, keywords);
                page.setServleturl("QuestionManagerServlet.action");
                request.setAttribute("page", page);
                request.getRequestDispatcher("/WEB-INF/jsp/question/questionList.jsp").forward(request, response);
            }
        }

        if ("searchByKeyWord".equals(forwardId)) {
            String keywords = request.getParameter("keywords");
            request.setAttribute("keywords", keywords);
            String pagenum = request.getParameter("pagenum");
            Page page = service.findPageRecordsByKeyWords(pagenum, keywords);
            page.setServleturl("QuestionManagerServlet.action");
            request.setAttribute("page", page);
            request.getRequestDispatcher("/WEB-INF/jsp/question/questionList.jsp").forward(request, response);
        }

        if ("reset".equals(forwardId)) {
            String pagenum = request.getParameter("pagenum");
            Page page = service.findPageRecords(pagenum);
            page.setServleturl("QuestionManagerServlet.action");
            request.setAttribute("page", page);
            request.getRequestDispatcher("/WEB-INF/jsp/question/questionList.jsp").forward(request, response);
        }

        if ("decrese".equals(forwardId)) {
            decrese(request,response);
        }
    }


    private void decrese(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagenum = request.getParameter("pagenum");
        String keywords = request.getParameter("keywords");
        request.setAttribute("keywords", keywords);
        String pageSize = request.getParameter("pagesize");
        request.setAttribute("pageSize", pageSize);
        Page page = service.findPageRecordsByKeyWordsDESC(pagenum, keywords, pageSize);
        page.setServleturl("QuestionManagerServlet.action");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/WEB-INF/jsp/question/questionList.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagenum = request.getParameter("pagenum");
        Page page = service.findPageRecords(pagenum);
        page.setServleturl("QuestionManagerServlet.action");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/WEB-INF/jsp/question/questionList.jsp").forward(request, response);
    }
}
