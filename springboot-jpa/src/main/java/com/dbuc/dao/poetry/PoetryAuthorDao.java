package com.dbuc.dao.poetry;

import com.dbuc.dao.BaseDao;
import com.dbuc.entity.poetry.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface PoetryAuthorDao extends BaseDao<Author, String> {
}
