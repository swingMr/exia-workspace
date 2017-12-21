package com.excellence.iaserver.web.controller;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.exke.connector.ConnectorException;
import com.excellence.iaserver.common.util.ElasticSearchUtil;
import com.excellence.iaserver.common.util.WebUtil;
import com.excellence.iaserver.service.SearchKnowledgeService;


/**
 * 获取本体引文
 * @author chegnhq
 *
 */
@Controller
@RequestMapping(value = "/services/ontology/getinfo")
public class GetinfoOntologyController {
	
	@Autowired
	private SearchKnowledgeService searchKnowledgeService;
	/**
	 * 通过概念查找关联信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author liuzehang
	 * @throws ConnectorException 
	 * @created 2017-1-4
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getInformationsByIdsAndCondition")
	public String getInformationsByIdsAndCondition(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String ids = MapUtils.getString(map, "ids");
		String infoCondition = MapUtils.getString(map, "infoCondition");
		JSONArray infoArr = new JSONArray();
		int status = 1;
		if(StringUtils.isNotEmpty(ids)) {
			try{
				infoArr =  searchKnowledgeService.getInformationsByIdsAndCondition(ids, infoCondition);
			}catch (Exception e) {
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", infoArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * 通过关键词查找关联文档
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author liuzehang
	 * @throws ConnectorException 
	 * @created 2017-1-4
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getInformationsByKeywordsAndCondition")
	public String getInformationsByKeywordsAndCondition(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String words = MapUtils.getString(map, "words");
		String infoCondition = MapUtils.getString(map, "infoCondition");
		int status = 1;
		JSONArray infoArr = new JSONArray();
		if(StringUtils.isNotEmpty(words)) {
			try{
				infoArr =  searchKnowledgeService.getInformationsByKeywordsAndCondition(words,infoCondition);
			}catch (Exception e) {
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", infoArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * 查找关联文档
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author liuzehang
	 * @created 2017-1-4
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getInformations")
	public String getInformations(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String conceptId = MapUtils.getString(map, "conceptId");
		String conditions = MapUtils.getString(map, "conditions");
		String infoCondition = MapUtils.getString(map, "infoCondition");
		JSONArray infoArr = new JSONArray();
		int status = 1;
		if(StringUtils.isNotEmpty(conceptId)) {
			try{
				infoArr =  searchKnowledgeService.getInformations(conceptId, conditions, infoCondition);
			}catch (Exception e) {
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", infoArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * 获取主题词流数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author liuzehang
	 * @created 2017-1-4
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getInformationsByUrlAndAttribute")
	public String getInformationsByUrlAndAttribute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "text/html;charset=GBK"); 
			Map map = WebUtil.requestToMap(request);
			String attribute = MapUtils.getString(map, "attribute");
			String url = URLDecoder.decode(MapUtils.getString(map, "url"), "utf-8");
//			String url = "http://<host>/exck#id=59ba15798170040e246f9558&libNum=wcmmetatablespecialpolicy";
//			System.out.println("---2222222222222222222----------"+url+"------------");
			if(StringUtils.isNotEmpty(url)) {
				ByteArrayOutputStream out = (ByteArrayOutputStream) searchKnowledgeService.getInformationsByUrlAndAttribute(url, attribute);
				if(out!=null) {
//					System.out.println(out.toString());
					response.getOutputStream().write(out.toByteArray());
				} else {
					response.getOutputStream().write(new ByteArrayOutputStream().toByteArray());
				}
				 
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取主题词流数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author liuzehang
	 * @created 2017-1-4
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getInformationsByUrl")
	public String getInformationsByUrl(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Content-type", "text/html;charset=GBK");
			Map map = WebUtil.requestToMap(request);
			String url = URLDecoder.decode(MapUtils.getString(map, "url"), "utf-8");
			if(StringUtils.isNotEmpty(url)) {
				String ql = "select * from 213trswcmv7 where url='"+url+"' limit 0,1";
				JSONObject obj = ElasticSearchUtil.searchBySql(ql, 1, 1);
				if(obj!=null && obj.has("num") && obj.getInt("num")>0) {
					JSONObject data = obj.getJSONArray("informations").getJSONObject(0);
					if(data!=null) {
						StringBuilder resource = new StringBuilder();
						resource.append("<root>");
						// 正文
						resource.append("<content>").append("<![CDATA[").append(data.getJSONObject("attributes").getString("content")).append("]]>").append("</content>");
						// 标题
						resource.append("<title>").append("<![CDATA[").append(data.getString("title")).append("]]>").append("</title>");
						// 文章id
						resource.append("<id>").append("<![CDATA[").append(data.getString("id")).append("]]>").append("</id>");
						// 属性
						resource.append("</root>");
						response.getOutputStream().write(resource.toString().getBytes());
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
