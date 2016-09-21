package com.augmentum.oes.service.impl;

import java.util.List;

import com.augmentum.oes.dao.QuestionDao;
import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.model.Page;
import com.augmentum.oes.model.Question;
import com.augmentum.oes.service.QuestionService;
import com.augmentum.oes.util.StringUtil;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    private ParameterException paramException = new ParameterException();

    @Override
    public List<Question> viewQuestionList(){
        return questionDao.findQuestions();
    }

    @Override
    public Integer addQuestion(Question question)throws ParameterException {

        if (StringUtil.isEmpty(question.getDisplayId())) {
            paramException.addErrorFields("displayId", "displayId can't be null!");
        }

        if (StringUtil.isEmpty(question.getQuestionContent())) {
            paramException.addErrorFields("questionContent", "questionContent can't be null!");
        }

        if (StringUtil.isEmpty(question.getOptionOne())) {
            paramException.addErrorFields("optionOne", "optionOne can't be null!");
        }

        if (StringUtil.isEmpty(question.getOptionTwo())) {
            paramException.addErrorFields("optionTwo", "optionTwo can't be null!");
        }

        if (StringUtil.isEmpty(question.getOptionThree())) {
            paramException.addErrorFields("optionThree", "optionThree can't be null!");
        }

        if (StringUtil.isEmpty(question.getOptionFour())) {
            paramException.addErrorFields("optionFour", "optionFour can't be null!");
        }

        if (StringUtil.isEmpty(question.getCorrectOption())) {
            paramException.addErrorFields("correctOption", "correctOption can't be null!");
        }

        if (question.getOptionOne().length() > 45 || question.getOptionTwo().length() > 45 ||
                question.getOptionThree().length() > 45 || question.getOptionFour().length() > 45) {
            paramException.addErrorFields("tooLong", "The Length is greater than 45 !");
        }

        if (!paramException.isErrorField()) {
            throw paramException;
        }
        Integer id = questionDao.createQuestion(question);
        return id;
    }

    @Override
    public List<Question> viewQuestionListByKeyWords(String keywords) {
        return questionDao.findQuestionsByKeyWord(keywords);
    }

    @Override
    public Page findPageRecords(String pagenum) {
        int num = 1;
        if(!StringUtil.isEmpty(pagenum)){
            num = Integer.parseInt(pagenum);
        }
        int totalrecords = questionDao.getTotalRecords();
        Page page = new Page(num, totalrecords,10);
        List<Question> records = questionDao.findPageRecords(page.getStartIndex(), page.getPagesize());
        page.setRecords(records);
        return page;
    }

    @Override
    public Page findPageRecordsByKeyWords(String pagenum, String keywords) {
        int num = 1;
        if(!StringUtil.isEmpty(pagenum)){
            num = Integer.parseInt(pagenum);
        }
        int totalrecords = questionDao.getTotalRecordsByKeyWord(keywords);
        Page page = new Page(num, totalrecords,10);
        page.setPagesize(10);
        List<Question> records = questionDao.findQuestionsByKeyWordByPage(keywords, page.getStartIndex(), page.getPagesize());
        page.setRecords(records);
        return page;
    }

    @Override
    public Page findPageRecordsByKeyWords(String pagenum, String keywords, String pageSize) {
        int num = 1;
        if(!StringUtil.isEmpty(pagenum)){
            num = Integer.parseInt(pagenum);
        }
        int totalrecords = questionDao.getTotalRecordsByKeyWord(keywords);
        Page page = new Page(num,totalrecords,Integer.parseInt(pageSize));
        List<Question> records = questionDao.findQuestionsByKeyWordByPage(keywords, page.getStartIndex(), page.getPagesize());
        page.setRecords(records);
        return page;
    }

    @Override
    public Question queryQuestionById(String unique_id) {
        return questionDao.getQuestionById(unique_id);
    }

    @Override
    public void updateQuestion(Question question) throws ParameterException {

        if (StringUtil.isEmpty(question.getDisplayId())) {
            paramException.addErrorFields("displayId", "displayId can't be null!");
        }

        if (StringUtil.isEmpty(question.getQuestionContent())) {
            paramException.addErrorFields("questionContent", "questionContent can't be null!");
        }

        if (StringUtil.isEmpty(question.getOptionOne())) {
            paramException.addErrorFields("optionOne", "optionOne can't be null!");
        }

        if (StringUtil.isEmpty(question.getOptionTwo())) {
            paramException.addErrorFields("optionTwo", "optionTwo can't be null!");
        }

        if (StringUtil.isEmpty(question.getOptionThree())) {
            paramException.addErrorFields("optionThree", "optionThree can't be null!");
        }

        if (StringUtil.isEmpty(question.getOptionFour())) {
            paramException.addErrorFields("optionFour", "optionFour can't be null!");
        }

        if (StringUtil.isEmpty(question.getCorrectOption())) {
            paramException.addErrorFields("correctOption", "correctOption can't be null!");
        }

        if (!paramException.isErrorField()) {
            throw paramException;
        }
        questionDao.updateQuestion(question);
    }

    @Override
    public Page findPageRecordsByKeyWordsDESC(String pagenum, String keywords, String pageSize) {
        int num = 1;
        if(!StringUtil.isEmpty(pagenum)){
            num = Integer.parseInt(pagenum);
        }
        int totalrecords = questionDao.getTotalRecordsByKeyWord(keywords);
        Page page = new Page(num,totalrecords,Integer.parseInt(pageSize));
        List<Question> records = questionDao.findQuestionsByKeyWordByPageDESC(keywords, page.getStartIndex(), page.getPagesize());
        page.setRecords(records);
        return page;
    }

    @Override
    public void deleteQuestionByIds(String question_ids) {
        questionDao.deleteByDisplayIds(question_ids);
    }
}
