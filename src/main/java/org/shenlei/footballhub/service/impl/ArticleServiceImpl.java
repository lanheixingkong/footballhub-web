package org.shenlei.footballhub.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.shenlei.footballhub.dao.ArticleMapper;
import org.shenlei.footballhub.model.Article;
import org.shenlei.footballhub.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleMapper articleMapper;

	@Override
	public List<Article> selectNewestList(Article article) {
		return articleMapper.selectNewestList(article);
	}

	@Override
	public List<Article> selectByIds(String ids) {
		return articleMapper.selectByIds(ids);
	}

}
