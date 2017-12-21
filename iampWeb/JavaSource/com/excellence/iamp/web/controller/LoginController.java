package com.excellence.iamp.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.common.OAConstant;
import com.excellence.common.UserInfo;
import com.excellence.common.UserProxy;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.util.Jwt;
import com.excellence.platform.um.dao.UserService;
import com.excellence.platform.um.exception.CommonAppException;
import com.excellence.platform.um.exception.PasswordNotSetException;
import com.excellence.platform.um.vo.Common;
import com.excellence.platform.um.vo.User;

@Controller
@RequestMapping("")
public class LoginController {
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws CommonAppException, PasswordNotSetException {
		JSONObject json = new JSONObject();
		String msg = "";
		boolean result = false;
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String mode = request.getParameter("mode");
		if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password)) {
			UserService userService = UserService.getInstance();
			try {
				//User actualUser = userService.login(userName, password);
				User actualUser = null;
				if(actualUser == null) {
					UserInfo userInfo = new UserProxy();
					//userInfo.setId(""+actualUser.getId());
					//userInfo.setUsername(actualUser.getName());
					//userInfo.setAccount(actualUser.getAccount());
					//userInfo.setActualUser(actualUser);
					//userInfo.setLogintime(new Date());
					//userInfo.setIp(request.getRemoteAddr());
					//userInfo.setMachine(request.getRemoteHost());
					//** 获取当前用户所在的主部门 *//*
					Common org = null;
					Common orgUnit = null;

					//org = userService.getMainOrganizationByUserId(actualUser.getId());
					if (org != null) {
						userInfo.setOrg(org.getId() + "");
						userInfo.setOrgName(org.getName());
					}
					
					//** 获取当前用户所在的单位实体 *//*
					//orgUnit = userService.getUnit(actualUser.getId());
					if (orgUnit != null) {
						userInfo.setUnitId(orgUnit.getId());
						userInfo.setUnitName(orgUnit.getName());
					}
					
					//** 判断该用户是否为系统管理员 *//*
					//userInfo.setSysAdmin(userService.isSystemAdmin(actualUser.getId()));
					request.getSession().setAttribute(OAConstant.SESSION_USER_KEY, userInfo);
					msg = "登录成功";
					result = true;
					
					//2017年9月29日 lzh 添加接口token验证
					if(mode!=null && mode.equals("client")){
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("result", result);
						jsonObject.put("msg", msg);
						
						//创建token；2017年7月28日13:54:28 刘泽航
						Map<String , Object> payload=new HashMap<String, Object>();
				        Date date=new Date();
				        payload.put("userInfo", userInfo);//存session
				        payload.put("iat", date.getTime());//生成时间
				        payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
				        String token=null;
				        token=Jwt.createToken(payload); 
						jsonObject.put("token", token);
						request.getSession().setAttribute(OAConstant.SESSION_USER_TOKEN_KEY, token);
						WebUtil.outputPlain(response, jsonObject.toString());
						return null;
					}
				} else {
					msg="用户账号/密码错误";
					result = true;
				}
			} catch (Exception e) {
				msg="用户账号/密码错误";
				result = false;
			}
			
		}
		json.put("result", result);
		json.put("msg", msg);
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
}
