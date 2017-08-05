package org.shenlei.footballhub.service;

import java.util.List;

import org.shenlei.footballhub.model.SimilarArticle;

public interface SimilarArticleService {

	public List<SimilarArticle> selectByMaxIds(String maxIds);
	
	public SimilarArticle selectByMaxId(Long maxIds);
}
