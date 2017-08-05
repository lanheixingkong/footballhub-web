package org.shenlei.footballhub.service;

import java.util.List;

import org.shenlei.footballhub.model.Article;

public interface ArticleService {

	List<Article> selectNewestList(Article article);
	
	List<Article> selectByIds(String ids);
}
