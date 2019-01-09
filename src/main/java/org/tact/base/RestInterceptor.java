package org.tact.base;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RestInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger _log = LoggerFactory.getLogger(RestInterceptor.class);

	// before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestString = request.toString();

		if (!requestString.contains("login?")) {
			_log.info("[" + request + "]" + request.getRequestURI() + getParameters(request));
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// _log.info("{postHandle} [postHandle]["+ request + "]");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// _log.info("{afterCompletion} [afterCompletion]["+ request +
		// "][exception: " + ex+"]");
		super.afterCompletion(request, response, handler, ex);
	}

	private String getParameters(HttpServletRequest request) {
		
		StringBuffer posted = new StringBuffer();
		Enumeration<?> e = request.getParameterNames();
		if (e != null){
			posted.append("?");
		}
		
		while (e.hasMoreElements()) {
			if (posted.length() > 1){
				posted.append("&");
			}
			
			String curr = (String) e.nextElement();
			posted.append(curr + "=");
			
			if (curr.contains("password") || curr.contains("key") || curr.contains("answer") || curr.contains("pass")) {
				posted.append("*****");
			} else {
				posted.append(request.getParameter(curr));
			}
		}

		return posted.toString();
	}
}

/**
 * https://www.baeldung.com/spring-http-logging
 */
