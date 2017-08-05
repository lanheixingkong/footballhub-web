package org.shenlei.footballhub.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SimilarArticle implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long maxId;
	private String simIds;
	private Date createtime;
}
