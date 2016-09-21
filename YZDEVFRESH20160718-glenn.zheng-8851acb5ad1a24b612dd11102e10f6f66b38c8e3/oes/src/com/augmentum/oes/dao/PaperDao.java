package com.augmentum.oes.dao;

import com.augmentum.oes.model.Paper;

public interface PaperDao {

    int create(Paper paper);

    void delete(String id);
}
