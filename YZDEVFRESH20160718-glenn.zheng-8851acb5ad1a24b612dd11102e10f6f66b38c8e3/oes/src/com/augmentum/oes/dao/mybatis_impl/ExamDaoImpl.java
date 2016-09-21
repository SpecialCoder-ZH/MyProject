package com.augmentum.oes.dao.mybatis_impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.augmentum.oes.dao.ExamDao;
import com.augmentum.oes.exception.DBException;
import com.augmentum.oes.model.Exam;
import com.augmentum.oes.util.ReplaceCharUtil;
import com.augmentum.oes.util.StringUtil;

public class ExamDaoImpl extends SqlSessionDaoSupport implements ExamDao{

    private final Logger logger = Logger.getLogger(ExamDaoImpl.class);
    private static final String CLASS_NAME = Exam.class.getName();
    protected static String SQL_ID_ADD = ".create";
    protected static String SQL_ID_DELETE = ".delete";
    protected static String SQL_ID_GET_COUNT_KEYWORD = ".getTotalRecordsByKeyWord";
    protected static String SQL_ID_GET_COUNT = ".getTotalRecords";
    protected static String SQL_ID_BY_KEYW = ".findExamsByKeyWord";
    protected static String SQL_ID_BY_KEYW_AND_NAME = ".findExamsByName";
    protected static String SQL_ID_BY_KEYW_DESC = ".findExamsOrderByIdDESC";
    protected static String SQL_ID_FIND_PAGE_RECORDS = ".findPageRecords";
    protected static String SQL_ID_FIND_PAGE_RECORDS_BY_NAME = ".findPageRecordsByName";
    protected static String SQL_ID_FIND_PAGE_RECORDS_BY_NAME_DESC = ".findPageRecordsByNameDESC";
    protected static String SQL_ID_FIND_PAGE_RECORDS_DESC = ".findPageRecordsDESC";
    protected static String SQL_ID_FIND_ALL = ".findExams";
    protected static String SQL_ID_QUERY_BYID = ".queryById";
    protected static String SQL_ID_GET_QUESTION_COUNT = ".getQuestionCount";
    protected static String SQL_ID_BY_KEYW_AND_NAME_DESC = ".findExamsOrderByNameDESC";
    protected static String SQL_ID_FIND_PAGE_RECORDS_BY_TIME = ".findPageRecordsByTime";
    protected static String SQL_ID_BY_KEYW_AND_TIME = ".findExamsOrderByTime";
    protected static String SQL_ID_FIND_PAGE_RECORDS_BY_TIME_DESC = ".findPageRecordsByTimeDESC";
    protected static String SQL_ID_BY_KEYW_AND_TIME_DESC = ".findExamsOrderByTimeDESC";
    protected static String SQL_ID_GET_COUNT_KEYWORD_DATE = ".getTotalRecordsByKeyWordAndDate";
    protected static String SQL_ID_GET_COUNT_BY_DATE = ".getTotalRecordsByDate";
    protected static String SQL_ID_UPDATE = ".update";

    @Override
    public int create(Exam exam) {
        try{
            getSqlSession().insert(CLASS_NAME + SQL_ID_ADD, exam);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException(e);
        }
        return exam.getId();
    }

    @Override
    public void delete(String ids) {
        String[] arr = ids.split(",");
        try {
            getSqlSession().delete(CLASS_NAME + SQL_ID_DELETE, arr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException(e);
        }
    }

    @Override
    public int getTotalRecordsByKeyWord(String keywords) {
        keywords = ReplaceCharUtil.replace(keywords);
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_COUNT_KEYWORD, keywords);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public int getTotalRecords() {
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_COUNT);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException(e);
        }
    }

    @Override
    public List<Exam> findExamsByKeyWord(String keywords, String beginDate, String endDate, int startIndex, int pagesize) {
        if (StringUtil.isEmpty(keywords)) {
            return findPageRecords(beginDate, endDate, startIndex, pagesize);
        }
        keywords = ReplaceCharUtil.replace(keywords);
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("keywords", keywords);
            params.put("startIndex", String.valueOf(startIndex));
            params.put("pagesize", String.valueOf(pagesize));
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_BY_KEYW, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Exam> findPageRecords(String beginDate, String endDate,int startIndex, int pagesize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startIndex", startIndex);
        params.put("pagesize", pagesize);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_PAGE_RECORDS, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Exam> findExams() {
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ALL);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public Exam queryById(Integer newId) {
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_QUERY_BYID, newId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public Integer getQuestionCount() {
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_QUESTION_COUNT);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Exam> findExamsOrderByIdDESC(String keywords, String beginDate, String endDate, int startIndex, int pagesize) {
        if (StringUtil.isEmpty(keywords)) {
            return findPageRecordsDESC(beginDate, endDate, startIndex, pagesize);
        }
        keywords = ReplaceCharUtil.replace(keywords);
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("keywords", keywords);
            params.put("startIndex", String.valueOf(startIndex));
            params.put("pagesize", String.valueOf(pagesize));
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_BY_KEYW_DESC, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    public List<Exam> findPageRecordsDESC(String beginDate, String endDate, int startIndex, int pagesize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startIndex", startIndex);
        params.put("pagesize", pagesize);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_PAGE_RECORDS_DESC, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Exam> findExamsByName(String keywords, String beginDate, String endDate, int startIndex, int pagesize) {
        if (StringUtil.isEmpty(keywords)) {
            return findPageRecordsByName(beginDate, endDate, startIndex, pagesize);
        }
        keywords = ReplaceCharUtil.replace(keywords);
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("keywords", keywords);
            params.put("startIndex", String.valueOf(startIndex));
            params.put("pagesize", String.valueOf(pagesize));
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_BY_KEYW_AND_NAME, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    public List<Exam> findPageRecordsByName(String beginDate, String endDate, int startIndex, int pagesize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startIndex", startIndex);
        params.put("pagesize", pagesize);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_PAGE_RECORDS_BY_NAME, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Exam> findExamsOrderByNameDESC(String keywords, String beginDate, String endDate, int startIndex, int pagesize) {
        if (StringUtil.isEmpty(keywords)) {
            return findPageRecordsByNameDESC(beginDate, endDate, startIndex, pagesize);
        }
        keywords = ReplaceCharUtil.replace(keywords);
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("keywords", keywords);
            params.put("startIndex", String.valueOf(startIndex));
            params.put("pagesize", String.valueOf(pagesize));
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_BY_KEYW_AND_NAME_DESC, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    public List<Exam> findPageRecordsByNameDESC(String beginDate, String endDate, int startIndex, int pagesize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startIndex", startIndex);
        params.put("pagesize", pagesize);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_PAGE_RECORDS_BY_NAME_DESC , params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Exam> findExamsOrderByTime(String keywords, String beginDate, String endDate, int startIndex, int pagesize) {
        if (StringUtil.isEmpty(keywords)) {
            return findPageRecordsByTime(beginDate, endDate, startIndex, pagesize);
        }
        keywords = ReplaceCharUtil.replace(keywords);
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("keywords", keywords);
            params.put("startIndex", String.valueOf(startIndex));
            params.put("pagesize", String.valueOf(pagesize));
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_BY_KEYW_AND_TIME, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    public List<Exam> findPageRecordsByTime(String beginDate, String endDate, int startIndex, int pagesize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startIndex", startIndex);
        params.put("pagesize", pagesize);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_PAGE_RECORDS_BY_TIME, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Exam> findExamsOrderByTimeDESC(String keywords, String beginDate, String endDate, int startIndex, int pagesize) {
        if (StringUtil.isEmpty(keywords)) {
            return findPageRecordsByTimeDESC(beginDate, endDate, startIndex, pagesize);
        }
        keywords = ReplaceCharUtil.replace(keywords);
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("keywords", keywords);
            params.put("startIndex", String.valueOf(startIndex));
            params.put("pagesize", String.valueOf(pagesize));
            params.put("beginDate", beginDate);
            params.put("endDate", endDate);
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_BY_KEYW_AND_TIME_DESC, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    public List<Exam> findPageRecordsByTimeDESC(String beginDate, String endDate, int startIndex, int pagesize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startIndex", startIndex);
        params.put("pagesize", pagesize);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_PAGE_RECORDS_BY_TIME_DESC , params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public int getTotalRecordsByKeyWordAndDate(String keywords, String beginDate, String endDate) {
        keywords = ReplaceCharUtil.replace(keywords);
        Map<String, String> params = new HashMap<String, String>();
        params.put("keywords", keywords);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_COUNT_KEYWORD_DATE, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public int getTotalRecordsByDate(String beginDate, String endDate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_COUNT_BY_DATE, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException(e);
        }
    }

    @Override
    public void update(Exam exam) {
        try{
            getSqlSession().update(CLASS_NAME + SQL_ID_UPDATE, exam);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException(e);
        }
    }
}
