package com.excellence.iaserver.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.iaserver.common.util.Jwt;

public class TokenInterceptor implements HandlerInterceptor  {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------------after completion------------------");
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------------post Handle------------------");
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------------pre Handle------------------");
		/*String extoken = request.getParameter("extoken");
		try {
			Map<String, Object> resultMap = Jwt.validToken(extoken);
			Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
			if(!isSuccess){
				response.getWriter().write("extoken unValidate!");
				return false;
			}
		} catch (Exception e) {
			response.getWriter().write("extoken unValidate!");
			e.printStackTrace();
			return false;
		}*/
		return true;
	}

}
