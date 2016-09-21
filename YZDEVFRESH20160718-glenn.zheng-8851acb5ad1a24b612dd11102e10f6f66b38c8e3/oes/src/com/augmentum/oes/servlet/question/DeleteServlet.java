package com.augmentum.oes.servlet.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.augmentum.oes.service.QuestionService;
import com.augmentum.oes.service.impl.QuestionServiceImpl;


public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private QuestionService service = new QuestionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] question_ids = ids.split(",");
        //service.deleteQuestionByIds(question_ids);
        request.getRequestDispatcher("/QuestionManagerServlet.action?forwardId=1").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
