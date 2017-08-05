package org.shenlei.footballhub.controller.base;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import trueman.common.mybatis.page.PageContext;
import trueman.common.utils.datetime.DateUtils;

public class BaseController {
	protected static final int DEFAULT_PAGE_SIZE = 10;
	protected static final int DEFAULT_PAGE_INDEX = 0;

	protected static final String PAGE_SIZE = "pageSize";
	protected static final String PAGE_INDEX = "pageIndex";
	
	/**
	 * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				try{
					setValue(Long.parseLong(text));
					return;
				}catch(Exception e){
				}
				try{
					setValue(DateUtils.parse(text, DateUtils.YYYY_MM_DD_HH_MM_SS));
					return;
				}catch(Exception e){
				}
				setValue(DateUtils.parseDate(text));
			}
			// @Override
			// public String getAsText() {
			// Object value = getValue();
			// return value != null ? DateUtils.formatDateTime((Date)value) :
			// "";
			// }
		});
		// Date 类型转换
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(Timestamp.valueOf(text));
			}
		});
	}

	protected String getReqStr(HttpServletRequest request, String name, String defvalue) {
		String val = request.getParameter(name);
		return StringUtils.isNotEmpty(val) ? val : defvalue;
	}

	protected int getReqInt(HttpServletRequest request, String name, int defvalue) {
		String val = request.getParameter(name);
		return StringUtils.isNotEmpty(val) ? Integer.parseInt(val) : defvalue;
	}

	protected double getReqDouble(HttpServletRequest request, String name, double defvalue) {
		String val = request.getParameter(name);
		return StringUtils.isNotEmpty(val) ? Double.parseDouble(val) : defvalue;
	}
	
	protected String getStringFeild(Map<String, Object> map, String key){
		Object obj = map.get(key);
		return obj == null ? null : obj.toString();
	}

	protected PageContext initPageContext(HttpServletRequest request) {
		PageContext page = new PageContext();
		page.setPageSize(getReqInt(request, PAGE_SIZE, DEFAULT_PAGE_SIZE));
		page.setPageIndex(getReqInt(request, PAGE_INDEX, DEFAULT_PAGE_INDEX));
		page.setPagination(true);
		PageContext.setContext(page);

		return page;
	}
	
}
