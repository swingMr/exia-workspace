package com.excellence.iaserver.web.controller;

import java.util.HashMap;
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

import com.excellence.iamp.mongodb.service.UserOpLogService;
import com.excellence.iaserver.common.util.ElasticSearchUtil;
import com.excellence.iaserver.common.util.Jwt;
import com.excellence.iaserver.common.util.UserOpLogUtil;
import com.excellence.iaserver.common.util.WebUtil;
import com.excellence.iaserver.service.ResouceSearchService;
import com.excellence.iaserver.service.SearchKnowledgeService;
import com.excellence.iaserver.service.TRSProtalService;


/**
 * 检索信息资源
 * @author chegnhq
 *
 */
@Controller
@RequestMapping(value = "/services/ontology/textsearch")
public class TextsearchOntologyController {
	@Autowired
	private ResouceSearchService resouceSearchService;
	@Autowired
	private SearchKnowledgeService searchKnowledgeService;
	
	@Autowired
	private UserOpLogService userOpLogService;
	
	@Autowired
	private UserOpLogUtil userOpLogUtil;
	/**
	 * 根据条件进行信息资源检索
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bycondition")
	public String searchByCondition(HttpServletRequest request,HttpServletResponse response) 
	{
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken","");
		//基于“两系统五要素”描述的知识需求。参考：基于“两系统五要素”的知识需求标识的JSON格式。
		String context = MapUtils.getString(params, "context");
		String resOfReqJson = MapUtils.getString(params, "resOfReqJson");
		String infoSourceStr = MapUtils.getString(params, "infoSources");
		String[] infoSources =new String[]{};
		if(StringUtils.isNotEmpty(infoSourceStr)){
			infoSources= infoSourceStr.split(",");
		}
		String orderName = MapUtils.getString(params, "orderName");
		String orderType = MapUtils.getString(params, "orderType");
		int page = MapUtils.getIntValue(params, "page", 1);
		int pageSize = MapUtils.getIntValue(params, "pageSize", 50);		
		JSONObject result = new JSONObject();
		try 
		{
			JSONObject data = null;
			if(StringUtils.isNotEmpty(resOfReqJson)){
				data = resouceSearchService.searchByCondition(resOfReqJson, infoSources, orderName, orderType, page, pageSize);
				result.put("status", 1);
				result.put("msg", "成功");
			}else{
				result.put("status", 0);
				result.put("msg", "参数resOfReqJson不能为空！");
			}
			
			result.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", -99);
			result.put("msg", "出现异常:"+e.getMessage());		
		}	
		result.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputPlain(response, result.toString());
		return null;
	}
	
	/**
	 * 根据采用基于“两系统五要素”描述的知识需求进行信息资源检索
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/byontology")
	public String searchByOntologies(HttpServletRequest request,HttpServletResponse response) 
	{
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken","");
		//基于“两系统五要素”描述的知识需求。参考：基于“两系统五要素”的知识需求标识的JSON格式。
		String ontoOfReqJson = MapUtils.getString(params, "ontoOfReqJson");
		String infoYearsJson = MapUtils.getString(params, "infoYears");
		int page = MapUtils.getIntValue(params, "page", 1);
		int pageSize = MapUtils.getIntValue(params, "pageSize", 10);		
		String context = MapUtils.getString(params, "context");
		String[] infoSources = request.getParameterValues("infoSources");
		
		JSONObject textObj = new JSONObject();
		textObj.put("ontoOfReqJson", ontoOfReqJson);
		textObj.put("infoYearsJson", infoYearsJson);
		textObj.put("infoSources", infoSources);
		
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess){
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			String appCode = resultObj.getString("appCode");
			String appName = resultObj.getString("appName");
			String appPwd = resultObj.getString("appPwd");
			if(StringUtils.isNotBlank(context)){
				//记录行为日志
				userOpLogUtil.recordOpLog(context,appCode,appName,"XW013","资源检索",textObj.toString(),"");
			}
			
		}	
		
		JSONObject result = new JSONObject();
		try 
		{
			JSONObject ontoOfReq = null;
			if(StringUtils.isNotBlank(ontoOfReqJson))
			{
				ontoOfReq = new JSONObject(ontoOfReqJson);
			}
			JSONArray infoYears = null;
			if(StringUtils.isNotBlank(infoYearsJson))
			{
				infoYears = new JSONArray(infoYearsJson);
			}
			
			JSONObject data = null;
			data = resouceSearchService.searchByOntologies(null, ontoOfReq, infoSources, infoYears, page, pageSize);
			result.put("status", 1);
			result.put("msg", "成功");
			result.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", -99);
			result.put("msg", "出现异常:"+e.getMessage());		
		}	
		result.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputPlain(response, result.toString());
		return null;
	}
	
	/**
	 * 根据采用基于“两系统五要素”描述的知识需求进行信息资源检索
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sql")
	public String searchBySql(HttpServletRequest request,HttpServletResponse response) 
	{
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String sql = MapUtils.getString(params, "sql");
		int page = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "pageSize");		
		String[] infoSources = request.getParameterValues("infoSources");
		
		JSONObject result = new JSONObject();
		try 
		{
		
			JSONObject data = null;
			data = resouceSearchService.searchBySql(sql, infoSources, page, pageSize);
			result.put("status", 1);
			result.put("msg", "成功");
			result.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", -99);
			result.put("msg", "出现异常:"+e.getMessage());		
		}	
		result.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputPlain(response, result.toString());
		return null;
	}	
	/**
	 * 全文检索
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchText")
	public String searchText(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		TRSProtalService trsProtalService = new TRSProtalService();
		Map params = WebUtil.requestToMap(request);
		String conditionStr = MapUtils.getString(params, "conditions");
		JSONObject conObj = new JSONObject(conditionStr);
		
		String text = "";
		int page = 1;
		int pageSize = 10;
		int isReturnContent = 0;
		Map<String, Object> conditions = new HashMap<String, Object>();
		if (conObj!=null) {
			if (conObj.has("text") && conObj.get("text") instanceof String) {
				text = conObj.getString("text");
			}
			conditions.put("text", text);
			String ignoreText = "";
			if (conObj.has("ignoreText") && conObj.get("ignoreText") instanceof String) {
				ignoreText = conObj.getString("ignoreText");
				conditions.put("ignoreText", ignoreText);
			}
			String metadataId = "";
			if (conObj.has("metadataId") && conObj.get("metadataId") instanceof String) {
				metadataId = conObj.getString("metadataId");
				conditions.put("metadataId", metadataId);
			}
			
			if (conObj.has("page") && conObj.get("page") instanceof Integer) {
				page = conObj.getInt("page");
			}
			conditions.put("page", page);
			if (conObj.has("pageSize") && conObj.get("pageSize") instanceof Integer) {
				pageSize = conObj.getInt("pageSize");
			}
			conditions.put("pageSize", pageSize);
			if (conObj.has("isReturnContent") && conObj.get("isReturnContent") instanceof Integer) {
				isReturnContent = conObj.getInt("isReturnContent");
			}
			conditions.put("isReturnContent", isReturnContent);
			int sortType = 0;
			if (conObj.has("sortType") && conObj.get("sortType") instanceof Integer) {
				sortType = conObj.getInt("sortType");
			}
			conditions.put("sortType", sortType);
			String sortField = "";
			if (conObj.has("sortField") && conObj.get("sortField") instanceof String) {
				sortField = conObj.getString("sortField");
				if (StringUtils.isNotEmpty(sortField)) {
					conditions.put("sortField", sortField);
				}
			}
			String infoSources = "";
			if (conObj.has("infoSources") && conObj.get("infoSources") instanceof String) {
				infoSources = conObj.getString("infoSources");
				if (StringUtils.isNotEmpty(infoSources)) {
					conditions.put("infoSources", infoSources);
				}
			}
			String categorys = "";
			if (conObj.has("categorys") && conObj.get("categorys") instanceof String) {
				categorys = conObj.getString("categorys");
				if (StringUtils.isNotEmpty(categorys)) {
					conditions.put("categorys", categorys);
				}
			}
			String genres = "";
			if (conObj.has("genres") && conObj.get("genres") instanceof String) {
				genres = conObj.getString("genres");
				if (StringUtils.isNotEmpty(genres)) {
					conditions.put("genres", genres);
				}
			}
			String forms = "";
			if (conObj.has("forms") && conObj.get("forms") instanceof String) {
				forms = conObj.getString("forms");
				if (StringUtils.isNotEmpty(forms)) {
					conditions.put("forms", forms);
				}
			}
			String symbols = "";
			if (conObj.has("symbols") && conObj.get("symbols") instanceof String) {
				symbols = conObj.getString("symbols");
				if (StringUtils.isNotEmpty(symbols)) {
					conditions.put("symbols", symbols);
				}
			}
			String zdjg = "";
			if (conObj.has("zdjg") && conObj.get("zdjg") instanceof String) {
				zdjg = conObj.getString("zdjg");
				if (StringUtils.isNotEmpty(zdjg)) {
					conditions.put("zdjg", zdjg);
				}
			}
			String bbwh = "";
			if (conObj.has("bbwh") && conObj.get("bbwh") instanceof String) {
				bbwh = conObj.getString("bbwh");
				if (StringUtils.isNotEmpty(bbwh)) {
					conditions.put("bbwh", bbwh);
				}
			}
			String sxx = "";
			if (conObj.has("sxx") && conObj.get("sxx") instanceof String) {
				sxx = conObj.getString("sxx");
				if (StringUtils.isNotEmpty(sxx)) {
					conditions.put("sxx", sxx);
				}
			}
		}
//		String result = TRSServiceUtil.search(conditions);
		String result = ElasticSearchUtil.elSearch(conditions);
		if(StringUtils.isEmpty(result)) {
			WebUtil.outputPlain(response, "null");
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", new JSONObject(result));
			jsonObject.put("status", status);
			jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
			WebUtil.outputPlain(response, jsonObject.toString());
		}
		return null;
	}
	
	@RequestMapping("/getComplexUrl")
	public String getComplexUrl(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		SearchKnowledgeService searchKnowledgeService = new SearchKnowledgeService();
		Map params = WebUtil.requestToMap(request);
		String simpleUrl = MapUtils.getString(params, "simpleUrl");
		
		JSONObject jsonResult = null;
		
		if(StringUtils.isNotBlank(simpleUrl)) {
			jsonResult = searchKnowledgeService.getComplexUrl(simpleUrl);
		}
		
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		
		return null;
	}
	
	@RequestMapping("/getSimpleUrl")
	public String getSimpleUrl(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String complexUrl = MapUtils.getString(params, "complexUrl");
		
		JSONObject jsonResult = null;
		
		if(StringUtils.isNotBlank(complexUrl)) {
			jsonResult = searchKnowledgeService.getSimpleUrl(complexUrl);
		}
		
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
			WebUtil.outputPlain(response, jsonResult.toString());
		}
		
		return null;
	}
	
	/**
	 * 提取文本摘要和主题词
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/absText")
	public String absText(HttpServletRequest request,HttpServletResponse response) {
		try {
			long startTime = System.currentTimeMillis();
			int status = 1;
			Map params = WebUtil.requestToMap(request);
			String text = MapUtils.getString(params, "text", null);
			Integer numOfSub = MapUtils.getInteger(params, "numOfSub", null);
			Integer percent = MapUtils.getInteger(params, "percent", null);
			String dictName = MapUtils.getString(params, "dictName", null);
			
			TRSProtalService trsProtalService = new TRSProtalService();
			JSONObject result = trsProtalService.absText(text, numOfSub, percent, dictName);
			
			if(result==null) {
				WebUtil.outputPlain(response, "null");
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("data", result);
				jsonObject.put("status", status);
				jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
				WebUtil.outputPlain(response, jsonObject.toString());
			}
			return null;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
