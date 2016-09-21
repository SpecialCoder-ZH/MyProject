package com.augmentum.oes.dao;

import java.util.List;

import com.augmentum.oes.model.Exam;

public interface ExamDao {

    int create(Exam exam);

    void delete(String id);

    int getTotalRecordsByKeyWord(String keywords);

    int getTotalRecords();

    List<Exam> findExamsByKeyWord(String keywords, String beginDate, String endDtae, int startIndex, int pagesize);

    List<Exam> findPageRecords(String beginDate, String endDate, int startIndex, int pagesize);

    List<Exam> findExams();

    Exam queryById(Integer newId);

    Integer getQuestionCount();

    List<Exam> findExamsOrderByIdDESC(String keywords, String beginDate, String endDate, int startIndex, int pagesize);

    List<Exam> findExamsByName(String keywords, String beginDate, String endDate, int startIndex, int pagesize);

    List<Exam> findExamsOrderByNameDESC(String keywords, String beginDate, String endDate, int startIndex, int pagesize);

    List<Exam> findExamsOrderByTime(String keywords, String beginDate, String endDate, int startIndex, int pagesize);

    List<Exam> findExamsOrderByTimeDESC(String keywords, String beginDate, String endDate, int startIndex, int pagesize);

    int getTotalRecordsByKeyWordAndDate(String keywords, String beginDate, String endDate);

    int getTotalRecordsByDate(String beginDate, String endDate);

    void update(Exam exam);
}
