package org.shenlei.footballhub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.shenlei.footballhub.model.Article;

public interface ArticleMapper {

	List<Article> selectNewestList(Article article);

	List<Article> selectByIds(@Param("ids") String ids);
}
