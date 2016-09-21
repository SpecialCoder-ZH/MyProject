package com.augmentum.oes.service.impl;

import com.augmentum.oes.dao.PaperDao;
import com.augmentum.oes.service.PaperService;

public class PaperServiceImpl implements PaperService{

    private PaperDao paperDao;

    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }
}
