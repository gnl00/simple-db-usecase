package com.dbuc.dao.novel;

import com.dbuc.entity.novel.Novel;
import com.dbuc.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelDao extends BaseDao<Novel, String> {
}
