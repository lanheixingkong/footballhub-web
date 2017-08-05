package org.shenlei.footballhub.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HttpContext工具类 <br>
 * 描述:<br>
 * 获取request、session、response对象<br>
 * 需要配置{@link org.springframework.web.context.request.RequestContextListener}<br>
 * 历史：<br>
 * 1.2015年9月7日 created by Phicos Hu<br>
 */
public class RequestHolder {

	/**
	 * 获取当前会话的request对象
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return req;
	}

	/**
	 * 获取当前会话的response对象
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse resp = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
		return resp;
	}

	/**
	 * 获取当前会话，没有则创建一个会话
	 */
	public static HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true);
	}

	/**
	 * 获取当前会话
	 * 
	 * @param create
	 *            是否创建
	 */
	public static HttpSession getSession(boolean create) {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(create);
	}
}
