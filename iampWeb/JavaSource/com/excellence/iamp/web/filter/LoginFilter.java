package com.excellence.iamp.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.excellence.common.OAConstant;
import com.excellence.common.UserInfo;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iamp.util.Jwt;
import com.excellence.platform.um.exception.CommonAppException;

/**
 * 登录拦截器
 * @author huangjinyuan
 *
 */
public class LoginFilter implements Filter{
	
	
	public LoginFilter(){
		super();
	}
	
	public void destroy() {}

	public void doFilter(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse, FilterChain paramFilterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) paramServletRequest;
		HttpServletResponse response = (HttpServletResponse) paramServletResponse;
		if(request.getServletPath().contains("login")||request.getServletPath().startsWith("/css/")||
				request.getServletPath().startsWith("/js/")||
				request.getServletPath().startsWith("/image/")) {
			paramFilterChain.doFilter(request, response);
			return;
		}
		UserInfo user = null;
		try {
			user = UserUtil.getCurrentUser(request);
		} catch (CommonAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isLogin = false;
		//2017年7月28日 获取token刘泽航
		HttpSession session = request.getSession(true);
		String token = (String)session.getAttribute(OAConstant.SESSION_USER_TOKEN_KEY);
		if(StringUtils.isNotBlank(token)) {
			 Map<String, Object> result = Jwt.validToken(token);
			 isLogin = (Boolean) result.get("isSuccess");
			 session.setAttribute(OAConstant.SESSION_USER_TOKEN_KEY, token);
		}
		if(user == null && isLogin == false) {
			response.sendRedirect("/iamp/login.jsp");
			return;
		}
		paramFilterChain.doFilter(request, response);
	}

	public void init(FilterConfig paramFilterConfig) throws ServletException {}
}
