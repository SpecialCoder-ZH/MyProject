package com.augmentum.oes.dao;

import java.util.List;

import com.augmentum.oes.model.Question;

public interface QuestionDao {

    Integer createQuestion(Question Question);
    void deleteQuestionById(String questionId);
    void updateQuestion(Question Question);
    Question getQuestionById(String questionId);
    List<Question> findQuestions();
    List<Question> findQuestionsByKeyWord(String keywords);
    int getTotalRecords();
    List<Question> findPageRecords(int startIndex, int pagesize);
    List<Question> findPageRecordsDESC(int startIndex, int pagesize);
    List<Question> findQuestionsByKeyWordByPage(String keywords, int startIndex, int pagesize);
    int getTotalRecordsByKeyWord(String keyword);
    List<Question> findQuestionsByKeyWordByPageDESC(String keywords, int startIndex, int pagesize);
    void deleteByDisplayIds(String question_ids);
}
