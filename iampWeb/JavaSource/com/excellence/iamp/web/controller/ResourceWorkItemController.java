package com.excellence.iamp.web.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excellence.iacommon.common.util.CategoryAndKeyWordsUtil;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.T;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iacommon.common.util.XMLUtil;
import com.excellence.iamp.resource.service.SearchResourceService;
import com.excellence.iamp.service.ResourceWorkItemService;
import com.excellence.iamp.vo.ResourceWorkItem;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/resworkitem")
public class ResourceWorkItemController {
	
	@Autowired
	private ResourceWorkItemService resourceWorkItemService;
	
	@Autowired
	private SearchResourceService searchResourceService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list.do")
	public String getResWorkItems(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String resTitle = MapUtils.getString(map, "resTitle");
		String belongDomain = MapUtils.getString(map, "belongDomain");
		String orderBy = MapUtils.getString(map, "orderBy","createDate");
		String orderDirection = MapUtils.getString(map, "orderDirection","desc");
		int workStatus = MapUtils.getIntValue(map, "workStatus",1);
		String dbCode = MapUtils.getString(map, "dbCode");
		Map paramMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(resTitle)) {
			paramMap.put("resTitle", resTitle);
			request.setAttribute("resTitle", resTitle);
		}
		if(StringUtils.isNotEmpty(belongDomain)) {
			paramMap.put("belongDomain", belongDomain);
			request.setAttribute("belongDomain", belongDomain);
		}
		if(StringUtils.isNotEmpty(dbCode)) {
			paramMap.put("dbCode", dbCode);
			request.setAttribute("dbCode", dbCode);
		}
		if(workStatus > 0) {
			paramMap.put("workStatus", workStatus);
			request.setAttribute("workStatus", workStatus);
		}
		paramMap.put("orderBy", orderBy);
		paramMap.put("orderDirection", orderDirection);
		PageInfo<ResourceWorkItem> page = resourceWorkItemService.page(paramMap, pageNo, pageSize);
		request.setAttribute("page", page);
		return "/knowledgeLib/resworkitem/list";
	}
	
	 @RequestMapping(value="/importwcm.do")
	 public String importFromWcm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("method", "create");
        return "/knowledgeLib/resworkitem/list";
    }
	 
	 @RequestMapping(value="/importexcel.do")
	 public String importFromExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("method", "create");
        return "/knowledgeLib/resworkitem/list";
    }
	 
	 @RequestMapping(value="/importfolder.do")
	 public String importFromFolder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("method", "create");
        return "/knowledgeLib/resworkitem/list";
    }
	 
	 /**
	  * 预处理
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="/prehandle.do", method=RequestMethod.GET)
	 public String preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String workItemIds = request.getParameter("workItemIds");
        if(StringUtils.isNotEmpty(workItemIds)) {
        	List<ResourceWorkItem> list = new ArrayList<ResourceWorkItem>();
        	String[] itemIds = workItemIds.split(";");
        	List<String> idList = new ArrayList<String>();
        	Collections.addAll(idList, itemIds);
        	String handleResult = CategoryAndKeyWordsUtil.getCategoryAndKeyWords(Constant.DB_HOST, Constant.DB_ACCOUNT, Constant.DB_PASSWORD, Constant.DB_NAME, "ia_res_workitem", idList, Constant.MODEL_NAME, Constant.MODEL_DIR);
        	if(StringUtils.isNotEmpty(handleResult)) {
        		JSONArray arr = new JSONArray(handleResult);
            	if(arr != null && arr.length() > 0) {
            		for(int i=0;i<arr.length();i++) {
                		JSONObject obj = arr.getJSONObject(i);
                		String id = obj.getString("id");
                		String title = obj.getString("title");
                		JSONArray category = obj.getJSONArray("category");
                		JSONArray keyWords = obj.getJSONArray("keyWords");
                		ResourceWorkItem item = new ResourceWorkItem();
                		item.setWorkItemId(id);
                		item.setResTitle(title);
                		item.setKeyWord(keyWords.join(";"));
                		item.setBelongDomain(category.join(";"));
                		list.add(item);
                	}
            	}
            	request.setAttribute("list", list);
        	}
        }
        request.setAttribute("method", "update");
        return "/knowledgeLib/resworkitem/detail";
     }
	 
	 /**
	  * 更新
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="/update.do", method=RequestMethod.POST)
	 public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
	    boolean result = true;
		String msg = "处理成功";
		json.put("result", result);
		json.put("msg", msg);
		String datas = request.getParameter("datas");
        if(StringUtils.isNotEmpty(datas)) {
        	JSONArray arr = new JSONArray(datas);
        	if(arr != null && arr.length() > 0) {
        		for(int i =0;i<arr.length();i++) {
        			JSONObject jsObj = arr.getJSONObject(i);
        			String wordItemId = jsObj.getString("workItemId");
        			String belongDomain = jsObj.getString("belongDomain");
        			String keyword = jsObj.getString("keyword");
        			ResourceWorkItem item = resourceWorkItemService.findById(wordItemId);
        			if(item != null) {
        				item.setBelongDomain(belongDomain);
        				item.setKeyWord(keyword);
        				resourceWorkItemService.update(item);
        				JSONObject condition = new JSONObject();
        				condition.put("keywords", item.getKeyWord());
        				condition.put("category", item.getBelongDomain());
        				searchResourceService.updateWCMDoc(item.getChannelId(), item.getResId(), condition.toString());
        			}
        		}
        	}
        }
        //导入数据到wcm
        WebUtil.outputHtml(response, json.toString());
        return null;
     }
	 
	 @RequestMapping(value="/delete.do")
	 public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String workItemId = request.getParameter("workItemId");
        ResourceWorkItem resourceWorkItem = resourceWorkItemService.findById(workItemId);
        if(resourceWorkItem != null) {
        	resourceWorkItemService.delete(resourceWorkItem);
        }
        JSONObject json = new JSONObject();
        boolean result = true;
		String msg = "删除成功";
		json.put("result", result);
		json.put("msg", msg);
		WebUtil.outputHtml(response, json.toString());
        return null;
     }
	 
	 @RequestMapping(value="/searchChildren.do")
	 public String searchChildren(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 Map map  = WebUtil.requestToMap(request);
			 String id = MapUtils.getString(map, "id");
			 String text = MapUtils.getString(map, "text");
			 String type = MapUtils.getString(map, "type");
			 String parentPath = MapUtils.getString(map, "parentPath");
			 String pageNo = MapUtils.getString(map, "pageNo");
			 String pageSize = MapUtils.getString(map, "pageSize");
			 WebUtil.outputHtml(response, searchResourceService.searchChildren(id, text, type, parentPath, pageNo, pageSize));
		 } catch(Exception e) {
			 throw new RuntimeException(e);
		 }
		 return null;
	 }
	 
	 @RequestMapping(value="/getsources.do")
	 public String getResourceList(HttpServletRequest request, HttpServletResponse response) {
		 Map map  = WebUtil.requestToMap(request);
		 String url = MapUtils.getString(map, "url");
		 try {
			 WebUtil.outputHtml(response, searchResourceService.getCatalogTree(url));
		 } catch(Exception e) {
			 throw new RuntimeException(e);
		 }
		 return null;
	 }

	 
	 @RequestMapping(value="/saveDocs.do", method=RequestMethod.POST)
	 public String saveDocs(HttpServletRequest request, HttpServletResponse response) {
		 Map map  = WebUtil.requestToMap(request);
		 JSONObject jsonResult = new JSONObject();
		 String msg = "导入成功!";
		 boolean result = true;
		 try {
			 String docs = URLDecoder.decode(MapUtils.getString(map, "docs", null), "utf-8");
			 // 将前台数据转换成vo
			 JSONArray jsons = new JSONArray(docs);
			 JSONObject json;
			 String format = "yyyy-MM-dd HH:mm";
			 int count = 0;
			 Map<String, Object> resource;
			 String tableName = searchResourceService.getTableName(jsons.getJSONObject(0).getString("channelId"));
			 for (int i=0; i<jsons.length(); i++) {
				 json = jsons.getJSONObject(i);
				 ResourceWorkItem resourceWorkItem = new ResourceWorkItem();
				 resourceWorkItem.setResId(json.getString("id"));
				 resourceWorkItem.setChannelId(json.getString("channelId"));
				 resourceWorkItem.setResTitle(json.getString("title"));
				 resourceWorkItem.setCreateDate(T.dateValue(json.getString("createTime"), format, T.getNow()));
				 resourceWorkItem.setWorkStatus(1);// 待处理
				 resourceWorkItem.setDbCode(tableName);
				 resource = XMLUtil.parseXmlStr(searchResourceService.getResource(json.getString("url")));
				 resourceWorkItem.setBelongDomain((String) resource.get("category"));
				 resourceWorkItem.setKeyWord((String) resource.get("keywords"));
				 resourceWorkItem.setResContent((String) resource.get("htmlContent"));
				 Map<String, String> paramMap = new HashMap<String, String>();
				 paramMap.put("resId", json.getString("id"));
				 List<ResourceWorkItem> list = resourceWorkItemService.getByCondition(paramMap);
				 if(list != null && list.size() > 0) {
					 //已存在不导入
					 continue;
				 }
				 count++;
				 resourceWorkItemService.create(resourceWorkItem);
			 }
			 msg = count > 0 ? msg+"成功导入"+count+"条数据!":"";
			 // 保存
			 // 返回
		 } catch(Exception e) {
			 msg = e.getMessage();
			 result = false;
		 }
		 jsonResult.put("result", result);
		 jsonResult.put("msg", msg);
		 WebUtil.outputHtml(response, jsonResult.toString());
		 return null;
	 }
	 
}
