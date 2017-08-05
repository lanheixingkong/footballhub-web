package org.shenlei.footballhub.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Years;
import org.shenlei.footballhub.controller.base.BaseController;
import org.shenlei.footballhub.model.Article;
import org.shenlei.footballhub.model.SimilarArticle;
import org.shenlei.footballhub.service.ArticleService;
import org.shenlei.footballhub.service.SimilarArticleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trueman.common.beans.result.Result;
import trueman.common.mybatis.page.PageContext;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("article")
public class ArticleController extends BaseController {
	@Resource
	private ArticleService articleService;

	@Resource
	private SimilarArticleService similarArticleService;

	private static Map<String, String> crawlSiteName = Maps.newHashMap();
	static {
		crawlSiteName.put("sina", "新浪体育");
		crawlSiteName.put("hupu", "虎扑体育");
		crawlSiteName.put("zhibo8", "直播8");
	}

	@RequestMapping("cate")
	public Result<PageContext> selectCatetList(HttpServletRequest request,
			Article param) {
		PageContext page = super.initPageContext(request);
		page.setData(Lists.newArrayList());

		return Result.newSuccResult(page);
	}

	@RequestMapping({ "new", "" })
	public Result<PageContext> selectNewtList(HttpServletRequest request,
			Article param) {

		PageContext page = super.initPageContext(request);
		page.setSortField("ID");
		page.setSortOrder("DESC");
		List<Article> data = articleService.selectNewestList(param);
		page.setPagination(false);

		Map<Long, List<Article>> similarArticles = getSimilarArticles(data);
		DateTime now = DateTime.now();

		page.setData(data
				.stream()
				.map(article -> {
					article.setCrawlSite(getCrawlSiteName(article
							.getCrawlSite()));

					article.setTimeBetween(calPosttime(now, new DateTime(
							article.getPosttime())));

					if (similarArticles.containsKey(article.getId())) {
						article.setSimilarArticle(similarArticles.get(article
								.getId()));
					}

					return article;
				}).collect(Collectors.toList()));

		return Result.newSuccResult(page);
	}

	private String getCrawlSiteName(String crawlSite) {
		if (crawlSiteName.containsKey(crawlSite)) {
			return crawlSiteName.get(crawlSite);
		}
		return crawlSite;
	}

	private Map<Long, List<Article>> getSimilarArticles(List<Article> data) {
		StringBuffer sb = new StringBuffer();
		for (Article a : data) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(a.getId());
		}

		List<SimilarArticle> sims = similarArticleService.selectByMaxIds(sb
				.toString());
		Map<Long, List<Article>> ret = Maps.newHashMap();

		for (SimilarArticle sa : sims) {
			ret.put(sa.getMaxId(),
					articleService
							.selectByIds(sa.getSimIds())
							.stream()
							.map(article -> {
								article.setCrawlSite(getCrawlSiteName(article
										.getCrawlSite()));
								return article;
							}).collect(Collectors.toList()));
		}

		return ret;
	}

	private String calPosttime(DateTime now, DateTime posttime) {
		if (posttime != null) {
			int minutes = Minutes.minutesBetween(posttime, now).getMinutes();
			if (minutes < 60) {
				return minutes + " 分钟之前";
			}

			int hours = Hours.hoursBetween(posttime, now).getHours();
			if (hours < 24) {
				return hours + " 小时之前";
			}

			int days = Days.daysBetween(posttime, now).getDays();
			if (days < 31) {
				return days + " 天之前";
			}
			int months = Months.monthsBetween(posttime, now).getMonths();
			if (months < 12) {
				return months + " 个月之前";
			}

			int years = Years.yearsBetween(posttime, now).getYears();
			return years + " 年之前";
		}
		return "未知";
	}
}
