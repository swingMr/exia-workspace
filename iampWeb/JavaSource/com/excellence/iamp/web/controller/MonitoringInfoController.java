package com.excellence.iamp.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.mongodb.service.MonitoringInfoService;
import com.excellence.iamp.mongodb.service.ResourceLibService;
import com.excellence.iamp.mongodb.vo.MonitoringInfo;
import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.enums.EStatus;

@Controller
@RequestMapping("/monitoringInfo")
public class MonitoringInfoController {

	@Autowired
	private MonitoringInfoService monitoringInfoService;
	@Autowired
	private ResourceLibService resourceLibService;
	
	@RequestMapping("/list")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		int pageSize = 10;
		int currentPage = 1;
		String type = request.getParameter("type");
		String dataBase = request.getParameter("dataBase");
		String searchKeyword = request.getParameter("searchKeyword");
		Map<String, Object> map  = new HashMap<String, Object>();
		if(type != null && !type.equals("all")) {
			map.put("type", Integer.parseInt(type));
			request.setAttribute("type", type);
		}else if(type == null){
			//默认未处理
			map.put("type", 0);
			request.setAttribute("type", 0);
		}
		if(dataBase != null && !dataBase.equals("all")) {
			map.put("dataBase", dataBase);
			request.setAttribute("dataBase", dataBase);
		}
		if(dataBase != null && !searchKeyword.equals("")) {
			map.put("searchKeyword", searchKeyword);
			request.setAttribute("searchKeyword", searchKeyword);
		}
		if(StringUtils.isNotBlank(pageNo)){
			currentPage = Integer.parseInt(pageNo);
		}
		ModelAndView mv = new ModelAndView("knowledgeLib/monitorInfo/list");
		Page<MonitoringInfo> list = monitoringInfoService.getList(map,currentPage, pageSize);
		List<ResourceLib> libList = resourceLibService.getAll();
		mv.addObject("pages", list.getTotalPage());
		mv.addObject("currentPage", list.getCurrentPage());
		mv.addObject("list", list.getRows());
		mv.addObject("libList", libList);
		mv.addObject("type", type);
		return mv;
	}
	
	/**
	 * @author LiuZeHang 将资源状态设置为已处理/忽略；
	 * @param request
	 * @param response
	 */
   @RequestMapping(value="/dealWithData")
   public void dealWithData(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String ignore = request.getParameter("ignore");
		JSONArray arr = new JSONArray(ids);
		MonitoringInfo monitoringInfo = new MonitoringInfo();
		for(int i=0; arr.length()>i; i++){
			monitoringInfo  = monitoringInfoService.getOneById(arr.get(i).toString());
			if(StringUtils.isNotBlank(ignore)){
				//设置成忽略
				monitoringInfo.setMonitoredStatus(EStatus.delete.getIndex());
			}else{
				//设置
				monitoringInfo.setMonitoredStatus(EStatus.normal.getIndex());	
			}
			monitoringInfoService.update(monitoringInfo);
		}
		JSONObject json = new JSONObject();
		json.put("result", "success");
		WebUtil.outputHtml(response, json.toString());
   }
	   
}
