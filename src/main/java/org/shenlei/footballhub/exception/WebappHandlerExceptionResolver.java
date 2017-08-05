package org.shenlei.footballhub.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.shenlei.footballhub.utils.JsonUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import trueman.common.beans.result.Result;

@Slf4j
public class WebappHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex) {
		log.error("WebappHandlerExceptionResolver", ex);
		// ajax请求
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;

		try {
			out = response.getWriter();
			Result<?> ret = Result.newFailResult("访问错误");
			out.append(JsonUtils.toJsonStr(ret));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return null;
	}

}
