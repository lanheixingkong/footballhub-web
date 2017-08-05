package org.shenlei.footballhub.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.shenlei.footballhub.dao.SimilarArticleMapper;
import org.shenlei.footballhub.model.SimilarArticle;
import org.shenlei.footballhub.service.SimilarArticleService;
import org.springframework.stereotype.Service;

@Service
public class SimilarArticleServiceImpl implements SimilarArticleService {
	@Resource
	private SimilarArticleMapper similarArticleMapper;

	@Override
	public List<SimilarArticle> selectByMaxIds(String maxIds) {
		return similarArticleMapper.selectByMaxIds(maxIds);
	}

	@Override
	public SimilarArticle selectByMaxId(Long maxIds) {
		return similarArticleMapper.selectByMaxId(maxIds);
	}

}
