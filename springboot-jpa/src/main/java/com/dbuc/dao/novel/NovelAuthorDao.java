package com.dbuc.dao.novel;

import com.dbuc.entity.novel.AuthorNovel;
import com.dbuc.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelAuthorDao extends BaseDao<AuthorNovel, String> {
}
