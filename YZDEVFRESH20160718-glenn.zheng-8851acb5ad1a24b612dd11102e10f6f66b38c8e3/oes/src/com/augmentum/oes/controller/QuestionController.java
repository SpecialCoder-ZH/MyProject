package com.augmentum.oes.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.augmentum.oes.AppContext;
import com.augmentum.oes.Constants;
import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.model.Page;
import com.augmentum.oes.model.Question;
import com.augmentum.oes.service.QuestionService;

@Controller
public class QuestionController extends BaseController{

    private final String CREATE_QUESTION_JSP = "/question/createQuestion";
    private final String QUESTION_LIST_JSP = "/question/questionList";
    private final String EDIT_QUESTION_JSP = "/question/editQuestion";
    private final String DEFAULT_PAGENUM = "1";
    private final String DEFAULT_PAGESIZE = "10";

    @Autowired
    private QuestionService questionService;

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value="CreateQuestion", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView save(Question question) {
        ModelAndView modelView = new ModelAndView();
        try {
            questionService.addQuestion(question);
            this.addSession("SUCCESS_FLASH_MESSAGE", "SUCCESS CREATE !");
            modelView.addObject("direction", Constants.APP_CONTEXT_DESC);
            Page page = questionService.findPageRecordsByKeyWordsDESC(DEFAULT_PAGENUM, "", DEFAULT_PAGESIZE);
            page.setServleturl("QuestionManager");
            modelView.addObject("page", page);
            modelView.setViewName(QUESTION_LIST_JSP);
        } catch (ParameterException e) {
            e.printStackTrace();
            Map<String,String> questionErrorCreate = e.getErrorFields();
            modelView.addObject("questionError", questionErrorCreate);
            modelView.setViewName(CREATE_QUESTION_JSP);
        }
        return modelView;
    }

    @RequestMapping(value="DeleteQuestion", method=RequestMethod.GET)
    public ModelAndView delete(
                              @RequestParam(value="ids", defaultValue="")String ids,
                              @RequestParam(value="pagenum", defaultValue="")String pagenum
                              ) {
        ModelAndView modelView = new ModelAndView();
        ids = ids.substring(0,ids.length()-2);
        questionService.deleteQuestionByIds(ids);
        this.addSession("SUCCESS_DELETE", "SUCCESS DELETE !");
        modelView.setView(new RedirectView(AppContext.getContextPath()+"/QuestionManager?forwardId=1&pagenum="+pagenum));
        return modelView;
    }

    @RequestMapping(value="EditQuestion", method=RequestMethod.POST)
    public ModelAndView edit(
                             @RequestParam(value="id", defaultValue="")String id,
                             @RequestParam(value="forwardId", defaultValue="")String forwardId,
                             @RequestParam(value="pagesize", defaultValue="")String pagesize,
                             @RequestParam(value="keywords", defaultValue="")String keywords,
                             @RequestParam(value="pagenum", defaultValue="")String pagenum,
                             Question question
                             ) {
        ModelAndView modelView = new ModelAndView();

        if ("questionList".equals(forwardId)) {
            try {
                Question previousQuestion = questionService.queryQuestionById(id);
                Integer uniqueId = questionService.addQuestion(question);
                previousQuestion.setPreviousId(uniqueId.toString());
                questionService.updateQuestion(previousQuestion);
                modelView.addObject("keywords", keywords);
                modelView.addObject("pageSize", pagesize);
                modelView.addObject("pagenum", pagenum);

                if (pagesize != null && !("".equals(pagesize))) {
                    Page page = questionService.findPageRecordsByKeyWords(pagenum, keywords,pagesize);
                    page.setServleturl("QuestionManager");
                    modelView.addObject("page", page);
                    modelView.setViewName(QUESTION_LIST_JSP);
                } else {
                    Page page = questionService.findPageRecordsByKeyWords(pagenum, keywords);
                    page.setServleturl("QuestionManager");
                    modelView.addObject("page", page);
                    modelView.setViewName(QUESTION_LIST_JSP);
                }
            } catch (ParameterException e) {
                Map<String,String> errormessage = e.getErrorFields();
                modelView.addObject("questionError", errormessage);
                modelView.setViewName(EDIT_QUESTION_JSP);
            }
        }
        return modelView;
    }

    @RequestMapping(value="ToEditQuestion", method=RequestMethod.GET)
    public ModelAndView toEdit(
                              @RequestParam(value="id", defaultValue="")String id,
                              @RequestParam(value="pagenum", defaultValue="")String pagenum
                              ) {
        ModelAndView modelView = new ModelAndView();
        Question question = questionService.queryQuestionById(id);
        modelView.addObject("question", question);
        modelView.addObject("pagenum", pagenum);

        modelView.setViewName(EDIT_QUESTION_JSP);
        return modelView;
    }

    @RequestMapping(value="QuestionManager", method=RequestMethod.GET)
    public ModelAndView list(
                             @RequestParam(value="forwardId", defaultValue="")String forwardId,
                             @RequestParam(value="pagenum", defaultValue="")String pagenum,
                             @RequestParam(value="keywords", defaultValue="")String keywords,
                             @RequestParam(value="pagesize", defaultValue= "10")String pageSize,
                             @RequestParam(value="direction", defaultValue="")String direction
                             ) {
        ModelAndView modelView = new ModelAndView();
        if (forwardId.equals("2")) {
            modelView.addObject("SUCCESS_FLASH_MESSAGE", null);
            modelView.setViewName(CREATE_QUESTION_JSP);
        }

        if (forwardId.equals("1")) {
            modelView.addObject("direction", Constants.APP_CONTEXT_ASC);
            modelView.addObject("keywords", keywords);

            modelView.addObject("pageSize", pageSize);

            if (pageSize != null && !("".equals(pageSize))) {
                Page page = questionService.findPageRecordsByKeyWords(pagenum, keywords,pageSize);
                page.setServleturl("QuestionManager");
                modelView.addObject("page", page);
                modelView.setViewName(QUESTION_LIST_JSP);
            } else {
                Page page = questionService.findPageRecordsByKeyWords(pagenum, keywords);
                page.setServleturl("QuestionManager");
                modelView.addObject("page", page);
                modelView.setViewName(QUESTION_LIST_JSP);
            }
        }

        if ("searchByKeyWord".equals(forwardId)) {
            modelView.addObject("keywords", keywords);
            Page page = questionService.findPageRecordsByKeyWords(pagenum, keywords,pageSize);
            page.setServleturl("QuestionManager");
            modelView.addObject("page", page);
            modelView.addObject("pageSize", pageSize);
            modelView.setViewName(QUESTION_LIST_JSP);
        }

        if ("reset".equals(forwardId)) {
            Page page = questionService.findPageRecords(pagenum);
            page.setServleturl("QuestionManager");
            modelView.addObject("page", page);
            modelView.addObject("keywords", null);
            modelView.addObject("pageSize", null);
            modelView.addObject("direction", Constants.APP_CONTEXT_ASC);
            modelView.setViewName(QUESTION_LIST_JSP);
        }

        if ("decrese".equals(forwardId)) {
            modelView.addObject("keywords", keywords);
            modelView.addObject("pageSize", pageSize);
            modelView.addObject("direction", Constants.APP_CONTEXT_DESC);
            Page page = questionService.findPageRecordsByKeyWordsDESC(pagenum, keywords, pageSize);
            page.setServleturl("QuestionManager");
            modelView.addObject("page", page);
            modelView.setViewName(QUESTION_LIST_JSP);
        }
        return modelView;
    }

    @RequestMapping(value="ToQuestionManager", method=RequestMethod.GET)
    public ModelAndView toList(@RequestParam(value="pagenum", defaultValue="")String pagenum) {
        ModelAndView modelView = new ModelAndView();
        Page page = questionService.findPageRecords(pagenum);
        page.setServleturl("QuestionManager");
        modelView.addObject("direction", Constants.APP_CONTEXT_ASC);
        modelView.addObject("page", page);
        modelView.setViewName(QUESTION_LIST_JSP);
        return modelView;
    }
}
