package com.excellence.iamp.web.controller;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.mongodb.service.UserOpLogService;
import com.excellence.iamp.mongodb.vo.UserOpLog;
import com.excellence.iamp.service.AppService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.App;

@Controller
@RequestMapping("/userOpLog")
public class UserOpLogController {
	
	@Autowired
	private UserOpLogService userOpLogService;
	
	@Autowired
	private AppService appService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/queryList.do")
	public String queryList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String appCode =  MapUtils.getString(map, "appCode","");
		String userName =  MapUtils.getString(map, "userName","");
		String opCode =  MapUtils.getString(map, "opCode","");
		JSONObject obj = new JSONObject();
		if(StringUtils.isNotBlank(appCode) && !StringUtils.equals(appCode, "all")){
			obj.put("appCode", appCode);
		}
		if(StringUtils.isNotBlank(userName)){
			obj.put("userName", userName);
		}
		if(StringUtils.isNotBlank(opCode) && !StringUtils.equals(opCode, "all")){
			obj.put("opCode", opCode);
		}
		Map paramMap = new HashMap();
		paramMap.put("status", 1);
		List<App> appList = appService.getByCondition(paramMap);
		Page<UserOpLog> page = userOpLogService.findByParamsAndCollectionName(pageNo, pageSize, obj.toString());
		request.setAttribute("page", page);
		request.setAttribute("appList", appList);
		request.setAttribute("appCode", appCode);
		request.setAttribute("opCode", opCode);
		request.setAttribute("userName", userName);
		return "/baseManage/userOpLog/userOpLogList";
	}
	
}
