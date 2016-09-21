package com.augmentum.oes.service;

import java.util.List;

import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.model.Exam;
import com.augmentum.oes.model.Page;
import com.augmentum.oes.model.PageInfo;

public interface ExamService {

    Page findPageRecords(PageInfo pageInfo);

    Integer addExam(Exam exam) throws ParameterException;

    List<Exam> viewExamList();

    void deleteExamByIds(String ids);

    Exam queryExamById(String id);

    Integer getQuestionCount();

    void updateExam(Exam exam);
}
