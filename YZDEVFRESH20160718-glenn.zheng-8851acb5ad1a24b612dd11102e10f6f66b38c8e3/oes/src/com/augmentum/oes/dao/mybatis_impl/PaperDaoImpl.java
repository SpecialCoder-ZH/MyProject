package com.augmentum.oes.dao.mybatis_impl;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.augmentum.oes.dao.PaperDao;
import com.augmentum.oes.exception.DBException;
import com.augmentum.oes.model.Paper;

public class PaperDaoImpl extends SqlSessionDaoSupport implements PaperDao{

    private final Logger logger = Logger.getLogger(PaperDaoImpl.class);
    private static final String CLASS_NAME = Paper.class.getName();
    protected static String SQL_ID_ADD = ".create";
    protected static String SQL_ID_DELETE = ".delete";

    @Override
    public int create(Paper paper) {
        try {
            getSqlSession().insert(CLASS_NAME + SQL_ID_ADD, paper);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
        return paper.getId();
    }

    @Override
    public void delete(String id) {
        try {
            getSqlSession().delete(CLASS_NAME + SQL_ID_DELETE, id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

}
