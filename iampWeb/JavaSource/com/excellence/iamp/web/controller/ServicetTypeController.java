package com.excellence.iamp.web.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.excellence.iamp.service.ServiceTypeService;
import com.excellence.iamp.vo.ServiceType;
import com.excellence.iamp.vo.enums.EStatus;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/serviceType")
public class ServicetTypeController {
	@Autowired
	private ServiceTypeService serviceTypeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/list.do")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Map<String, String> paramMap=new HashMap<String, String>();
		String num = request.getParameter("num");
		String pageSize = request.getParameter("pageSize");
		int targetNum = 1;
		int targetPageSize = 10;
		if(StringUtils.isNotEmpty(num)){
			targetNum = Integer.parseInt(num);
		}
		if(StringUtils.isNotEmpty(pageSize)){
			targetPageSize = Integer.parseInt(pageSize);
		}
		PageInfo<ServiceType> pageInfo= serviceTypeService.getList(targetNum, targetPageSize, paramMap);
		ModelAndView mv = new ModelAndView("serviceConfig/service/serviceTypeList");
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("pages", pageInfo.getPages());
		mv.addObject("currentPage", pageInfo.getPageNum());
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/save.do")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		String typeName  =request.getParameter("typeName");
		String typeId  =request.getParameter("typeId");
		ServiceType serviceType =  new ServiceType();
		JSONObject  obj = new JSONObject();
		if(StringUtils.isNotEmpty(typeId)){
			//保存
			serviceType = serviceTypeService.findById(typeId);
			if(serviceType != null){
				serviceType.setTypeName(typeName);
				serviceType.setUpdateDate(new Date());
				serviceTypeService.update(serviceType);
				result="success";
			}
		}else{
			//新增
			serviceType.setTypeName(typeName);
			serviceType.setStatus(EStatus.normal.getIndex());
			serviceType.setCreateDate(new Date());
			serviceType.setUpdateDate(new Date());
			serviceType.setCreatorName("管理员");
			serviceType.setCreatorId("111");
			int outCome = serviceTypeService.insert(serviceType);
			if(outCome == 1){
				result="success";
			}
		}
		obj.put("result", result);
		WebUtil.outputHtml(response, obj.toString());
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/update.do")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		String typeId  = request.getParameter("typeId");
		ServiceType serviceType =  new ServiceType();
		ModelAndView mv = new ModelAndView("serviceConfig/service/typeDetail");
		if(StringUtils.isNotEmpty(typeId)){
			serviceType = serviceTypeService.findById(typeId);	
		}
		mv.addObject("obj", serviceType);
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/del.do")
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(ids);
		for(int i=0; arr.length()>i; i++){
			String serviceId = (String) arr.get(i);
			serviceTypeService.del(serviceId);
		}
		json.put("result", "success");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
}
