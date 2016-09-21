package com.augmentum.oes.service.impl;

import java.util.List;

import com.augmentum.oes.dao.ExamDao;
import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.model.Exam;
import com.augmentum.oes.model.Page;
import com.augmentum.oes.model.PageInfo;
import com.augmentum.oes.service.ExamService;
import com.augmentum.oes.util.ClassUtil;
import com.augmentum.oes.util.StringUtil;

public class ExamServiceImpl implements ExamService{

    private ExamDao examDao;
    private ParameterException paramException = new ParameterException();

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Override
    public Page findPageRecords(PageInfo pageInfo) {
        String pagenum = pageInfo.getPagenum();
        String pageSize = pageInfo.getPagesize();
        String keywords = pageInfo.getKeywords();
        String direction = pageInfo.getDirection();
        String timeOrder = pageInfo.getTimeOrder();
        String nameOrder = pageInfo.getNameOrder();
        String beginDate = pageInfo.getBeginDate();
        String endDate = pageInfo.getEndDate();

        int totalrecords = 0;
        int num = 1;
        if(!StringUtil.isEmpty(pagenum)){
            num = Integer.parseInt(pagenum);
        }

        if (!StringUtil.isEmpty(keywords) && StringUtil.isEmpty(endDate)) {
            totalrecords = examDao.getTotalRecordsByKeyWord(keywords);
        }

        if (!StringUtil.isEmpty(keywords) && !StringUtil.isEmpty(endDate)) {
            totalrecords = examDao.getTotalRecordsByKeyWordAndDate(keywords,beginDate,endDate);
       }

        if (StringUtil.isEmpty(keywords) && !StringUtil.isEmpty(endDate)) {
            totalrecords = examDao.getTotalRecordsByDate(beginDate,endDate);
       }

        if(StringUtil.isEmpty(keywords) && StringUtil.isEmpty(endDate)) {
            totalrecords = examDao.getTotalRecords();
        }

        if (StringUtil.isEmpty(pageSize)) {
            pageSize = "10";
        }

        Page page = new Page(num,totalrecords,Integer.parseInt(pageSize));
        List<Exam> records = null;

        if (StringUtil.isEmpty(endDate)) {
            beginDate = "0000-00-00";
            endDate = "9999-12-30";
        }

        if (!StringUtil.isEmpty(direction)) {
            if (direction.equals("ASC")) {
                records = examDao.findExamsByKeyWord(keywords, beginDate, endDate, page.getStartIndex(), page.getPagesize());
            } else {
                records = examDao.findExamsOrderByIdDESC(keywords, beginDate, endDate, page.getStartIndex(), page.getPagesize());
            }
        } else {
            records = examDao.findExamsByKeyWord(keywords, beginDate, endDate,page.getStartIndex(), page.getPagesize());
        }

        if (!StringUtil.isEmpty(timeOrder)) {
            if (timeOrder.equals("ASC")) {
                records = examDao.findExamsOrderByTime(keywords, beginDate, endDate, page.getStartIndex(), page.getPagesize());
            } else {
                records = examDao.findExamsOrderByTimeDESC(keywords, beginDate, endDate, page.getStartIndex(), page.getPagesize());
            }
        }

        if (!StringUtil.isEmpty(nameOrder)) {
            if (nameOrder.equals("ASC")) {
                records = examDao.findExamsByName(keywords, beginDate, endDate, page.getStartIndex(), page.getPagesize());
            } else {
                records = examDao.findExamsOrderByNameDESC(keywords, beginDate, endDate, page.getStartIndex(), page.getPagesize());
            }
        }

        page.setRecords(records);
        return page;
    }

    @Override
    public Integer addExam(Exam exam) throws ParameterException {

        String emptyFileds = ClassUtil.isEmpty(exam);
        String[] fileds = emptyFileds.split(",");

        if (!StringUtil.isEmpty(emptyFileds)) {
            for (String string : fileds) {
                paramException.addErrorFields(string, string + " can't be null!");
            }
        }

        if (!paramException.isErrorField()) {
            throw paramException;
        }

        Integer id = examDao.create(exam);
        return id;

    }

    @Override
    public List<Exam> viewExamList() {
        return examDao.findExams();
    }

    @Override
    public void deleteExamByIds(String ids) {
        examDao.delete(ids);
    }

    @Override
    public Exam queryExamById(String id) {
        Integer newId = Integer.parseInt(id);
        return examDao.queryById(newId);
    }

    @Override
    public Integer getQuestionCount() {
        return examDao.getQuestionCount();
    }

    @Override
    public void updateExam(Exam exam) {
        examDao.update(exam);
    }
}
