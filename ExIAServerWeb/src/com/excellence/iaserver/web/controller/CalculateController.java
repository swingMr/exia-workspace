package com.excellence.iaserver.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.iamp.service.ServiceDefinitionService;
import com.excellence.iamp.vo.ServiceDefinition;
import com.excellence.iaserver.common.util.HanLPUtil;
import com.excellence.iaserver.common.util.WebUtil;
import com.excellence.iaserver.service.CalculateService;

@Controller
@RequestMapping("/services/calculate")
public class CalculateController {
	
	@Autowired
	private ServiceDefinitionService serviceDefinitionService;
	
	@Autowired
	private CalculateService calculateService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/test/{id}"/*,method=RequestMethod.POST*/)
	public String test(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("id is :" + id);
		Map paramMap = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		json.put("result", true);
		json.put("msg", "操作成功1122!");
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**
	 * 4.3.1	中文文本分词
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/participle")
	public String participle(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Date dt1 = new Date();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken", null);
		String text = MapUtils.getString(params, "text", null);
		Boolean tagPart = MapUtils.getBoolean(params, "tagPart", false);
		int patten = MapUtils.getIntValue(params, "patten", 1);
		JSONObject jsonResult;
		try{
			jsonResult = calculateService.participle(text, tagPart, patten);
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
			jsonResult.put("data", JSONObject.NULL);
		}
		
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		
		Date dt2 = new Date();
		long t = dt2.getTime()-dt1.getTime();
		if(t>100)
		{
			System.out.println("警告:/services/calculate/participle  耗时"+t+" 毫秒");
		}
		return null;
	}
	
	/**
	 * 4.3.1	人名识别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/renming")
	public String renming(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Date dt1 = new Date();
		Map params = WebUtil.requestToMap(request);
		String text = MapUtils.getString(params, "text", null);
		JSONObject jsonResult = new JSONObject();
		try{
			List result = HanLPUtil.recPersonName(text);
			JSONArray data = new JSONArray(result);
			jsonResult.put("data", data);
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
			jsonResult.put("data", JSONObject.NULL);
		}
		
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		
		Date dt2 = new Date();
		long t = dt2.getTime()-dt1.getTime();
		if(t>100)
		{
			System.out.println("警告:/services/calculate/participle  耗时"+t+" 毫秒");
		}
		return null;
	}
	
	/**
	 * 4.3.2	关键词和摘要提取
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/abstract")
	public String abs(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Date dt1 = new Date();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken", null);
		String title = MapUtils.getString(params, "title", null);
		String text = MapUtils.getString(params, "text", null);
		int keyWordLimit = MapUtils.getIntValue(params, "keyWordLimit", 50);
		int abstractLength = MapUtils.getIntValue(params, "abstractLength", 3);
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("status", 0);
		jsonResult.put("data", JSONObject.NULL);
		
		try{
			if(StringUtils.isNotBlank(text)){
				jsonResult = calculateService.abs(title, text, keyWordLimit, abstractLength);
			}else{
				jsonResult.put("msg", "参数text不能为空");
			}
		}catch(Exception ex){
			jsonResult.put("msg", ex.getMessage());
		}
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		Date dt2 = new Date();
		long t = dt2.getTime()-dt1.getTime();
		if(t>100)
		{
			System.out.println("警告:/services/calculate/abstract  耗时"+t+" 毫秒");
		}
		return null;
	}
	
	@RequestMapping(value="/servicelist")
	public String getList( HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		int status = 0;
		String msg = "失败";
		List<ServiceDefinition> list = serviceDefinitionService.getList();
		JSONArray arr = new JSONArray();
		for(int i=0; list.size()>i; i++){
			JSONObject obj = new JSONObject();
			obj.put("serviceId",list.get(i).getServiceId());
			obj.put("serviceName",list.get(i).getServiceName());
			obj.put("serviceCnName",list.get(i).getServiceCname());
			obj.put("typeName",list.get(i).getTypeName());
			obj.put("createDate",list.get(i).getCreateDate());
			arr.put(obj);
		}
		JSONObject json = new JSONObject();
		status = 1;
		msg = "操作成功";
		json.put("status", status);
		json.put("msg", msg);
		json.put("data",arr);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("警告:/services/calculate/servicelist  耗时"+t+" 毫秒");
		}
		return null;
	}
	
	@RequestMapping(value="/info/{name}")
	public String getListByConditions(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("name is :" + name);
		int status = 0;
		String msg = "失败";
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("serviceName", name);
		List<ServiceDefinition> list = serviceDefinitionService.getListByConditions(paramMap);
		JSONArray arr = new JSONArray();
		for(int i=0; list.size()>i; i++){
			JSONObject obj = new JSONObject();
			obj.put("serviceId",list.get(i).getServiceId());
			obj.put("serviceName",list.get(i).getServiceName());
			obj.put("serviceCnName",list.get(i).getServiceCname());
			obj.put("typeName",list.get(i).getTypeName());
			obj.put("createDate",list.get(i).getCreateDate());
			arr.put(obj);
		}
		JSONObject json = new JSONObject();
		status = 1;
		msg = "操作成功";
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	@RequestMapping(value="/gentag")
	public String getTagsByText(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Date dt1 = new Date();
		
		Map map = WebUtil.requestToMap(request);
		//输入的标题
		String title = MapUtils.getString(map, "title");
		//输入的文本
		String text = MapUtils.getString(map, "text");
		//是否根据标题识别归属领域
		boolean recDomain = MapUtils.getBooleanValue(map, "recDomain");
		//是否根据文本来源单位识别层级
		boolean recHierarchy = MapUtils.getBooleanValue(map, "recHierarchy");
		//文本来源单位
		String sourceUnit = MapUtils.getString(map, "sourceUnit");
		//文件信息资源的发布日期	格式：yyyy-MM-dd
		String publishDate = MapUtils.getString(map, "publishDate");
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("status", 0);
		jsonResult.put("data", JSONObject.NULL);
		
		try{
			if(StringUtils.isNotBlank(title)){
				jsonResult = calculateService.gentag(title, text, recDomain, recHierarchy,sourceUnit,publishDate);
			}else{
				jsonResult.put("msg", "参数title不能为空");
			}
		}catch(Exception ex){
			jsonResult.put("msg", ex.getMessage());
		}
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		Date dt2 = new Date();
		long t = dt2.getTime()-dt1.getTime();
		if(t>100)
		{
			System.out.println("警告:/services/calculate/gentag  耗时"+t+" 毫秒");
		}
		return null;
	}
}
