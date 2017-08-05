package org.shenlei.footballhub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.shenlei.footballhub.model.SimilarArticle;

public interface SimilarArticleMapper {

	public List<SimilarArticle> selectByMaxIds(@Param("maxIds")String maxIds);
	
	public SimilarArticle selectByMaxId(@Param("maxId") Long maxId);
}
