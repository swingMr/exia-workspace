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
		json.put("msg", "�����ɹ�1122!");
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**
	 * 4.3.1	�����ı��ִ�
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
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		
		Date dt2 = new Date();
		long t = dt2.getTime()-dt1.getTime();
		if(t>100)
		{
			System.out.println("����:/services/calculate/participle  ��ʱ"+t+" ����");
		}
		return null;
	}
	
	/**
	 * 4.3.1	����ʶ��
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
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		
		Date dt2 = new Date();
		long t = dt2.getTime()-dt1.getTime();
		if(t>100)
		{
			System.out.println("����:/services/calculate/participle  ��ʱ"+t+" ����");
		}
		return null;
	}
	
	/**
	 * 4.3.2	�ؼ��ʺ�ժҪ��ȡ
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
				jsonResult.put("msg", "����text����Ϊ��");
			}
		}catch(Exception ex){
			jsonResult.put("msg", ex.getMessage());
		}
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		Date dt2 = new Date();
		long t = dt2.getTime()-dt1.getTime();
		if(t>100)
		{
			System.out.println("����:/services/calculate/abstract  ��ʱ"+t+" ����");
		}
		return null;
	}
	
	@RequestMapping(value="/servicelist")
	public String getList( HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		int status = 0;
		String msg = "ʧ��";
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
		msg = "�����ɹ�";
		json.put("status", status);
		json.put("msg", msg);
		json.put("data",arr);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("����:/services/calculate/servicelist  ��ʱ"+t+" ����");
		}
		return null;
	}
	
	@RequestMapping(value="/info/{name}")
	public String getListByConditions(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("name is :" + name);
		int status = 0;
		String msg = "ʧ��";
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
		msg = "�����ɹ�";
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	@RequestMapping(value="/gentag")
	public String getTagsByText(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Date dt1 = new Date();
		
		Map map = WebUtil.requestToMap(request);
		//����ı���
		String title = MapUtils.getString(map, "title");
		//������ı�
		String text = MapUtils.getString(map, "text");
		//�Ƿ���ݱ���ʶ���������
		boolean recDomain = MapUtils.getBooleanValue(map, "recDomain");
		//�Ƿ�����ı���Դ��λʶ��㼶
		boolean recHierarchy = MapUtils.getBooleanValue(map, "recHierarchy");
		//�ı���Դ��λ
		String sourceUnit = MapUtils.getString(map, "sourceUnit");
		//�ļ���Ϣ��Դ�ķ�������	��ʽ��yyyy-MM-dd
		String publishDate = MapUtils.getString(map, "publishDate");
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("status", 0);
		jsonResult.put("data", JSONObject.NULL);
		
		try{
			if(StringUtils.isNotBlank(title)){
				jsonResult = calculateService.gentag(title, text, recDomain, recHierarchy,sourceUnit,publishDate);
			}else{
				jsonResult.put("msg", "����title����Ϊ��");
			}
		}catch(Exception ex){
			jsonResult.put("msg", ex.getMessage());
		}
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		Date dt2 = new Date();
		long t = dt2.getTime()-dt1.getTime();
		if(t>100)
		{
			System.out.println("����:/services/calculate/gentag  ��ʱ"+t+" ����");
		}
		return null;
	}
}
