package com.augmentum.oes.service;

import java.util.List;

import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.model.Page;
import com.augmentum.oes.model.Question;

public interface QuestionService {

    List<Question> viewQuestionList();
    Integer addQuestion(Question question) throws ParameterException;
    List<Question> viewQuestionListByKeyWords(String keywords);
    Page findPageRecords(String pagenum);
    Page findPageRecordsByKeyWords(String pagenum, String keywords);
    Page findPageRecordsByKeyWords(String pagenum, String keywords, String pageSize);
    Question queryQuestionById(String unique_id);
    void updateQuestion(Question question) throws ParameterException;
    Page findPageRecordsByKeyWordsDESC(String pagenum, String keywords, String pageSize);
    void deleteQuestionByIds(String ids);
}
