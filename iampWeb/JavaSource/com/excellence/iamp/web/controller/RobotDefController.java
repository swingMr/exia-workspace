package com.excellence.iamp.web.controller;

import java.util.Date;
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
import com.excellence.iamp.robot.vo.RobotDef;
import com.excellence.iamp.service.AppService;
import com.excellence.iamp.service.RobotChatMngService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.App;
import com.excellence.iamp.vo.enums.EStatus;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/robotDef")
public class RobotDefController {
	@Autowired
	private RobotChatMngService robotChatMngService;
	@Autowired
	private AppService appService;
	/**
	 * 分页列表
	 * @author Liuzh
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list.do")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		Map appMap = new HashMap();
		appMap.put("status", EStatus.normal.getIndex());
		List<App> appList  = appService.getByCondition(appMap);
		Map paramMap = new HashMap();
		if(StringUtils.isNotBlank(type) && !type.equals("all")){
			paramMap.put("appCode", type);
		}
		String pNo  = request.getParameter("pageNo");
		int pageNo = 1;
		String pSize = request.getParameter("pageSize");
		int pageSize = 10;
		if(StringUtils.isNotBlank(pNo)){
			pageNo = Integer.parseInt(pNo);
		}
		if(StringUtils.isNotBlank(pSize)){
			pageSize = Integer.parseInt(pSize);
		}
		PageInfo<RobotDef> list = robotChatMngService.pageRobotDef(paramMap, pageNo, pageSize);
		ModelAndView mv = new ModelAndView("/robot/list");
		mv.addObject("list", list.getList());
		mv.addObject("pages", list.getPages());
		mv.addObject("currentPage", pageNo);
		mv.addObject("appList", appList);
		mv.addObject("type", type);
		return mv;
	}
	
	@RequestMapping("/showDetail.do")
	public ModelAndView showDetail(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		RobotDef robot =  new RobotDef();
		if(StringUtils.isNotBlank(id)){
			robot = robotChatMngService.findRobotDefById(id);
		}
		ModelAndView mv = new ModelAndView("/robot/detail");
		Map paramMap = new HashMap();
		paramMap.put("status", EStatus.normal.getIndex());
		List<App> appList  = appService.getByCondition(paramMap);
		mv.addObject("appList", appList);
		mv.addObject("obj", robot);
		return mv;
	}
	
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request, HttpServletResponse response) {
		String ids =request.getParameter("ids");
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(ids);
		if(arr != null && arr.length()>0){
			for(int i=0; arr.length()>i; i++){
				robotChatMngService.deleteExRobot(arr.get(i).toString());
			}
			json.put("result", "success");
		}else{
			json.put("result", "fail");
		}
		WebUtil.outputHtml(response, json.toString());
	}
	
	@RequestMapping("/save.do")
	public ModelAndView save(RobotDef robotDef , HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int result =0;
		String robotId = "";
		if(robotDef.getRobotId() != null){
			robotId = robotDef.getRobotId();
		};
		String startTime =request.getParameter("startTime");
		String endTime =request.getParameter("endTime");
		String serviceGreeting = "";
		serviceGreeting=startTime+":00 ";
		serviceGreeting += "- "+ endTime+":00";
		robotDef.setServiceTime(serviceGreeting);
		if(StringUtils.isNotBlank(robotId)){
			//更新
			robotChatMngService.updateRobotDef(robotDef);
			result = 1;
		}else{
			//新增
			robotChatMngService.createExRobot(robotDef);
			result = 1;
		}
		if(result == 1){
			json.put("result", "success");
		}else{
			json.put("result", "false");
		}
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
}
