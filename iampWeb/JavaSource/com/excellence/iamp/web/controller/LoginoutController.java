package com.excellence.iamp.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.common.OAConstant;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.platform.um.exception.CommonAppException;
import com.excellence.platform.um.exception.PasswordNotSetException;

@Controller
@RequestMapping("")
public class LoginoutController {
	@RequestMapping(value="/logout")
	public String login(HttpServletRequest request, HttpServletResponse response) throws CommonAppException, PasswordNotSetException, IOException {
		JSONObject json = new JSONObject();
		String msg = "²Ù×÷³É¹¦";
		boolean result = true;
		request.getSession().removeAttribute(OAConstant.SESSION_USER_KEY);
		json.put("result", result);
		json.put("msg", msg);
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
}
