package com.excellence.iaserver.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.iamp.mongodb.service.ResourceLibService;
import com.excellence.iamp.mongodb.service.ResourceService;
import com.excellence.iamp.mongodb.vo.Attribute;
import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iaserver.common.util.WebUtil;

@Controller
@RequestMapping(value = "/services/resdb")
public class ResouceDatabaseController {
	@Autowired
	private ResourceLibService resourceLibService;
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 5.1.1获取正常状态的资源库清单
	 * @author Liuzehang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String getDatabaseList(HttpServletRequest request,HttpServletResponse response){
		long startTime = System.currentTimeMillis();
		List<ResourceLib> libList =  resourceLibService.getAll();
		JSONArray arr = new JSONArray();
		if(libList != null && libList.size() > 0) {
			for(int i=0; libList.size()>i; i++ ){
				JSONObject obj = new JSONObject();
				obj.put("id", libList.get(i).getId());
				obj.put("libNum", libList.get(i).getLibNum());
				obj.put("libName", libList.get(i).getLibName());
				obj.put("type", libList.get(i).getType());
				obj.put("resType", libList.get(i).getResType());
				obj.put("displayOrder", libList.get(i).getDisplayOrder());
				obj.put("remark", libList.get(i).getRemark());
				JSONArray arr2 = new JSONArray();
				List<Attribute> exntendAttrs = libList.get(i).getExtendAttrs();
				if(exntendAttrs != null && exntendAttrs.size() > 0) {
					for(int j=0; exntendAttrs.size()>j; j++){
						JSONObject attrObj = new JSONObject();
						attrObj.put("attrName",exntendAttrs.get(j).getAttrName());
						attrObj.put("attrCName",exntendAttrs.get(j).getAttrCName());
						attrObj.put("require",exntendAttrs.get(j).getRequire());
						attrObj.put("type",exntendAttrs.get(j).getType());
						attrObj.put("value",exntendAttrs.get(j).getValue());
						arr2.put(attrObj);
					}
				}
				obj.put("extendAttrs", arr2);
				arr.put(obj);
			}
		}
		JSONObject result = new JSONObject();
		result.put("status", 1);
		result.put("data", arr);
		result.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
        WebUtil.outputHtml(response, result.toString());
		return null;
	}
	
	/**
	 * 5.1.2获取指定资源库的详细情况
	 * @author liuzehang
	 * @param libNum
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dbinfo/{libNum}")
	public String geteDbinfo(@PathVariable("libNum") String libNum,HttpServletRequest request,HttpServletResponse response){
		long startTime = System.currentTimeMillis();
		int status = 0;
		ResourceLib lib = resourceLibService.getByLibNum(libNum);
		JSONObject result = new JSONObject();
		if(lib != null){
			result.put("id", lib.getId());
			result.put("libNum", lib.getLibNum());
			result.put("libName", lib.getLibName());
			result.put("type", lib.getType());
			result.put("resType", lib.getResType());
			result.put("displayOrder", lib.getDisplayOrder());
			result.put("remark", lib.getRemark());
			JSONArray arr = new JSONArray();
			List<Attribute> exntendAttrs = lib.getExtendAttrs();
			if(exntendAttrs != null && exntendAttrs.size() > 0) {
				for(int i=0; exntendAttrs.size()>i; i++){
					JSONObject attrObj = new JSONObject();
					attrObj.put("attrName",exntendAttrs.get(i).getAttrName());
					attrObj.put("attrCName",exntendAttrs.get(i).getAttrCName());
					attrObj.put("require",exntendAttrs.get(i).getRequire());
					attrObj.put("type",exntendAttrs.get(i).getType());
					attrObj.put("value",exntendAttrs.get(i).getValue());
					arr.put(attrObj);
				}
			}
			result.put("extendAttrs", arr);
			long count = resourceService.getCount(lib.getCollectionName());
			result.put("resCount", count);
		}
		status =1;
		String msg="success";
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		obj.put("msg", msg);
		obj.put("data", result);
		result.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
        WebUtil.outputHtml(response, obj.toString());
		return null;
	}
}
