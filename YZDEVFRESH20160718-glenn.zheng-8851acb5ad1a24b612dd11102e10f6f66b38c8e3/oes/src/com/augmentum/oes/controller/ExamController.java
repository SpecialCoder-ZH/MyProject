package com.augmentum.oes.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.augmentum.oes.AppContext;
import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.model.Exam;
import com.augmentum.oes.model.Page;
import com.augmentum.oes.model.PageInfo;
import com.augmentum.oes.service.ExamService;

@Controller
public class ExamController extends BaseController {

    private final String EXAM_LIST_JSP = "/exam/list";
    private final String CREATE_EXAM_JSP = "/exam/create";
    private final String EXAM_LIST_PAGE = "examList";
    private final String EDIT_EXAM_JSP = "/exam/edit";
    Logger logger = Logger.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @RequestMapping(value="examReset", method={RequestMethod.GET})
    public ModelAndView reSet(PageInfo pageInfo) {
        ModelAndView modelView = new ModelAndView();
        Page page = examService.findPageRecords(pageInfo);
        page.setServleturl("examList");
        modelView.addObject("page", page);
        modelView.addObject("keywords", null);
        modelView.addObject("pageSize", null);
        modelView.addObject("direction", "");
        modelView.addObject("nameOrder", "");
        modelView.addObject("timeOrder", "");
        modelView.setViewName(EXAM_LIST_JSP);
        return modelView;
    }

    @RequestMapping(value="examList", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView examList(PageInfo pageInfo) {
        ModelAndView modelView = new ModelAndView();
        Page page = examService.findPageRecords(pageInfo);
        page.setServleturl("examList");
        modelView.addObject("page", page);
        modelView.addObject("keywords", pageInfo.getKeywords());
        modelView.addObject("pageSize", pageInfo.getPagesize());
        modelView.addObject("direction", pageInfo.getDirection());
        modelView.addObject("nameOrder", pageInfo.getNameOrder());
        modelView.addObject("timeOrder", pageInfo.getTimeOrder());
        modelView.addObject("endDate", pageInfo.getEndDate());
        modelView.setViewName(EXAM_LIST_JSP);
        return modelView;
    }

    @RequestMapping(value="CreateExam", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView save(
                             @RequestParam(value="effTime", defaultValue="0000-00-00")String effTime,
                             @RequestParam(value="effHour", defaultValue="00")String effHour,
                             @RequestParam(value="effMin", defaultValue="00")String effMin,
                             Exam exam
                             ) {
        ModelAndView modelView = new ModelAndView();
        try {
            String userName = this.getUserName();
            exam.setCreator(userName);
            effTime = effTime + " " + effHour + ":" + effMin;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date effectiveTime = sdf.parse(effTime);
            exam.setEffectiveTime(effectiveTime);
            examService.addExam(exam);
            this.addSession("SUCCESS_FLASH_MESSAGE", "SUCCESS CREATE !");
            modelView.setView(this.getRedirectView(EXAM_LIST_PAGE));
            return modelView;
        } catch (ParameterException e) {
            Map<String,String> examErrorCreate = e.getErrorFields();
            modelView.addObject("examError", examErrorCreate);
            modelView.setViewName(CREATE_EXAM_JSP);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            this.addSession("SUCCESS_FLASH_MESSAGE", "FAILED CREATE !");
            modelView.setView(this.getRedirectView(EXAM_LIST_PAGE));
        }
        return modelView;
    }

    @RequestMapping(value="EditExam", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView edit(
                             @RequestParam(value="effTime", defaultValue="0000-00-00")String effTime,
                             @RequestParam(value="effHour", defaultValue="00")String effHour,
                             @RequestParam(value="effMin", defaultValue="00")String effMin,
                             Exam exam,
                             PageInfo pageInfo
                             ) {
        ModelAndView modelView = new ModelAndView();
        try {
            if (exam.getIsUsed().equals("false")) {
                String userName = this.getUserName();
                exam.setCreator(userName);
                effTime = effTime + " " + effHour + ":" + effMin;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date effectiveTime = sdf.parse(effTime);
                exam.setEffectiveTime(effectiveTime);
                examService.updateExam(exam);
            } else {
                String userName = this.getUserName();
                exam.setCreator(userName);
                effTime = effTime + " " + effHour + ":" + effMin;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date effectiveTime = sdf.parse(effTime);
                exam.setEffectiveTime(effectiveTime);
                examService.addExam(exam);
            }
            this.addSession("SUCCESS_FLASH_MESSAGE", "SUCCESS EDIT !");
            modelView.addObject("pagenum", pageInfo.getPagenum());
            modelView.addObject("keywords", pageInfo.getKeywords());
            modelView.addObject("pagesize", pageInfo.getPagesize());
            modelView.addObject("direction", pageInfo.getDirection());
            modelView.addObject("nameOrder", pageInfo.getNameOrder());
            modelView.addObject("timeOrder", pageInfo.getTimeOrder());
            modelView.addObject("endDate", pageInfo.getEndDate());
            modelView.setView(this.getRedirectView(EXAM_LIST_PAGE));
            return modelView;
        } catch (ParameterException e) {
            Map<String,String> examErrorCreate = e.getErrorFields();
            modelView.addObject("examError", examErrorCreate);
            modelView.setViewName(CREATE_EXAM_JSP);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            this.addSession("SUCCESS_FLASH_MESSAGE", "FAILED CREATE !");
            modelView.setView(this.getRedirectView(EXAM_LIST_PAGE));
        }
        return modelView;
    }

    @RequestMapping(value="ToCreateExam", method={RequestMethod.GET})
    public ModelAndView toCreateExam() {
        ModelAndView modelView = new ModelAndView();
        modelView.addObject("SUCCESS_FLASH_MESSAGE", null);
        modelView.setViewName(CREATE_EXAM_JSP);
        return modelView;
    }

    @RequestMapping(value="deleteExam", method=RequestMethod.GET)
    public ModelAndView delete(
                               @RequestParam(value="ids", defaultValue="")String ids,
                               @RequestParam(value="pagesize", defaultValue="")String pagesize,
                               @RequestParam(value="pagenum", defaultValue="")String pagenum,
                               @RequestParam(value="keywords", defaultValue="")String keywords,
                               @RequestParam(value="nameOrder", defaultValue="")String nameOrder,
                               @RequestParam(value="timeOrder", defaultValue="")String timeOrder,
                               @RequestParam(value="direction", defaultValue="")String direction
                               ) {
        ModelAndView modelView = new ModelAndView();
        ids = ids.substring(0,ids.length()-2);
        examService.deleteExamByIds(ids);
        this.addSession("SUCCESS_DELETE", "SUCCESS DELETE !");
        modelView.setView(new RedirectView(AppContext.getContextPath()
                            + "/examList?keywords=" + keywords + "&pageSize="
                            + pagesize+"&pagenum="+ pagenum + "&direction="+direction
                            + "&nameOrder="+nameOrder + "&timeOrder="+timeOrder));
        return modelView;
    }

    @RequestMapping(value="ToEditExam", method=RequestMethod.GET)
    public ModelAndView toEdit(
                              @RequestParam(value="id", defaultValue="")String id,
                              PageInfo pageInfo
                              ) {
        ModelAndView modelView = new ModelAndView();
        Exam exam = examService.queryExamById(id);
        modelView.addObject("exam", exam);
        modelView.addObject("id", id);
        modelView.addObject("pagenum", pageInfo.getPagenum());
        modelView.addObject("keywords", pageInfo.getKeywords());
        modelView.addObject("pagesize", pageInfo.getPagesize());
        modelView.addObject("direction", pageInfo.getDirection());
        modelView.addObject("nameOrder", pageInfo.getNameOrder());
        modelView.addObject("timeOrder", pageInfo.getTimeOrder());
        modelView.addObject("endDate", pageInfo.getEndDate());
        modelView.setViewName(EDIT_EXAM_JSP);
        return modelView;
    }

    @RequestMapping(value="getQuestionCount", method=RequestMethod.POST)
    @ResponseBody
    public String getQuestionCount(@RequestBody String questionQuantity){
        String[] questionQuantitys = questionQuantity.split(":");
        questionQuantity = questionQuantitys[1].substring(1,questionQuantitys[1].length()-2);
        Integer questionCount = examService.getQuestionCount();
        return questionCount.toString();
    }
}
