package com.excellence.iaserver.web.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.iamp.service.ResourceTaskService;
import com.excellence.iamp.vo.ResourceTask;
import com.excellence.iaserver.common.util.WebUtil;

@Controller
@RequestMapping(value = "/resource/task")
public class ResourceTaskController {
	@Autowired
	private ResourceTaskService resourceTaskService;
	
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request,HttpServletResponse response) throws Exception{
		long startTime = System.currentTimeMillis();
		String libNum = request.getParameter("libNum");
		String resTitle = request.getParameter("resTitle");
		String resId = request.getParameter("resId");
		String expireDate = request.getParameter("expireDate");
		String type = request.getParameter("type");
		JSONObject json = new JSONObject();
		ResourceTask task = new ResourceTask();
		task.setLibNum(libNum);
		task.setResTitle(resTitle);
		task.setResId(resId);
		task.setCreateDate(new Date());
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = sdf.parse(expireDate);
		task.setExpireDate(date);
		if(type.equals("update")){
			task.setActCode("update_index");
		}else{
			task.setActCode("update_tag_index");	
		}
		//TODO ID and Name
		task.setCreatorId("123456");
		task.setCreatorName("管理员");
		resourceTaskService.create(task);
		json.put("status", "success");
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	@RequestMapping(value = "/del")
	public String del(HttpServletRequest request,HttpServletResponse response) throws Exception{
		long startTime = System.currentTimeMillis();
		String libNum = request.getParameter("libNum");
		String resTitle = request.getParameter("resTitle");
		String resId = request.getParameter("resId");
		String expireDate = request.getParameter("expireDate");
//		System.out.println(expireDate);
		JSONObject json = new JSONObject();
		ResourceTask task = new ResourceTask();
		task.setLibNum(libNum);
		task.setResTitle(resTitle);
		task.setResId(resId);
		task.setCreateDate(new Date());
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = sdf.parse(expireDate);
		task.setExpireDate(date);
		task.setActCode("del_index");
		//TODO ID and Name
		task.setCreatorId("123456");
		task.setCreatorName("管理员");
		resourceTaskService.create(task);
		json.put("status", "success");
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
}
