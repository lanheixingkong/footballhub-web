package org.shenlei.footballhub.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private Date posttime;
	private String sourceUrl;
	private String sourceName;
	private String content;
	private String contentText;
	private String link;
	private String linkmd5id;
	private Date createtime;
	private String crawlSite;
	private String contentWords;
	
	private String timeBetween;
	private List<Article> similarArticle;
}
