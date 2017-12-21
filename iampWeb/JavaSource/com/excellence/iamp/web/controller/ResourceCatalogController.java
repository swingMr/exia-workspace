package com.excellence.iamp.web.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
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

import com.excellence.common.UserInfo;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.mongodb.service.ResourceCatalogService;
import com.excellence.iamp.mongodb.vo.ResourceCatalog;
import com.excellence.iamp.resource.service.ExkeOntologService;
import com.excellence.iamp.vo.enums.EStatus;

/**
 * 目录管理
 * @author huangjinyuan
 *
 */
@Controller
@RequestMapping("/resourceCatalog")
public class ResourceCatalogController {
	
	@Autowired
	private ResourceCatalogService resourceCatalogService;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/tree.do")
	public String ajaxGetList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map  = WebUtil.requestToMap(request);
		String libNum = MapUtils.getString(map, "libNum","");
		String catelogId = MapUtils.getString(map, "id","root");
		JSONArray array = new JSONArray();
		List<ResourceCatalog> list = resourceCatalogService.getCatalogsByParentIdAndLibNum(catelogId, libNum);
		if(list != null && list.size() > 0) {
			for(ResourceCatalog catalog : list) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", catalog.getId());
				jsonObj.put("name", catalog.getCatelogName());
				jsonObj.put("catelogNum", catalog.getCatelogNum());
				jsonObj.put("displayOrder", catalog.getDisplayOrder());
				jsonObj.put("displayMode", catalog.getDisplayMode());
				jsonObj.put("isParent", true);
				array.put(jsonObj);
			}
		}
        WebUtil.outputHtml(response, array.toString());
        return null;
    }
	
	@RequestMapping(value="/create.do", method=RequestMethod.GET)
	public String showCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 Map map  = WebUtil.requestToMap(request);
		String displayMode = MapUtils.getString(map, "displayMode","");
		request.setAttribute("method", "create");
		request.setAttribute("displayMode", displayMode);
       return "/knowledgeLib/resourceCatalog/detail";
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/create.do", method=RequestMethod.POST)
	public String create(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Map map  = WebUtil.requestToMap(request);
	   UserInfo user = UserUtil.getCurrentUser(request);
	   String catelogId = MapUtils.getString(map, "catelogId","");
	   String parentCateLogNum = MapUtils.getString(map, "parentCateLogNum","root");
	   String parentCateLogId = MapUtils.getString(map, "parentCateLogId","root");
	   String parentCateLogName = MapUtils.getString(map, "parentCateLogName","");
	   String resourceLibNum = MapUtils.getString(map, "resourceLibNum","");
	   String resourceLibName = MapUtils.getString(map, "resourceLibName","");
	   String cateLogNum = MapUtils.getString(map, "cateLogNum","");
	   String cateLogName = MapUtils.getString(map, "cateLogName","");
	   String displayOrder = MapUtils.getString(map, "displayOrder","");
	   String displayMode = MapUtils.getString(map, "sex","");
	   ResourceCatalog resourceCatalog = null;
	   ResourceCatalog result = null;
	   String msg = "保存成功";
	   if(StringUtils.isNotBlank(catelogId)){
		   resourceCatalog = resourceCatalogService.findById(catelogId);
		   resourceCatalog.setUpdateDate(new Date());
	   }else{
		   resourceCatalog = new ResourceCatalog();
		   resourceCatalog.setUpdateDate(new Date());
	   }
	   resourceCatalog.setCatelogName(cateLogName);
	   resourceCatalog.setCatelogNum(cateLogNum);
	   resourceCatalog.setCreateDate(new Date());
	   resourceCatalog.setCreatorId(user.getId());
	   resourceCatalog.setCreatorName(user.getUsername());
	   resourceCatalog.setParentId(parentCateLogId);
	   resourceCatalog.setParentNum(parentCateLogNum);
	   resourceCatalog.setLibNum(resourceLibNum);
	   resourceCatalog.setDisplayMode(Integer.valueOf(displayMode));
	   resourceCatalog.setStatus(EStatus.normal.getIndex());
	   resourceCatalog.setDisplayOrder(Integer.valueOf(displayOrder));
	   if(StringUtils.isNotBlank(catelogId)){
		   resourceCatalogService.update(resourceCatalog);
		   msg = "修改成功";
	   }else{
		   result = resourceCatalogService.create(resourceCatalog);
		   if(result == null){
			   msg = "保存失败";
		   }
	   }
       WebUtil.outputHtml(response, msg);
       return null;
    }
	
	@RequestMapping(value="/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   String id = request.getParameter("id");
	   String libNum = request.getParameter("libNum");
	   JSONObject json = new JSONObject();
	   ResourceCatalog resourceCatalog = resourceCatalogService.findById(id);
	   List<ResourceCatalog> list = resourceCatalogService.getCatalogsByParentIdAndLibNum(id, libNum);
	   String msg = "删除成功";
	   if(resourceCatalog != null && list.size() <= 0){
		   resourceCatalogService.delete(resourceCatalog);
	   }else{
		   msg = "删除失败,该目录下还存在子目录，不能删除！";
	   }
	   WebUtil.outputHtml(response, msg);
	   return null;
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
    public String showUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
       String id = request.getParameter("id");
       request.setAttribute("method", "update");
       return "/knowledgeLib/resourceCatalog/detail";
    }
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
       String id = request.getParameter("id");
       int num = 0;
       if(StringUtils.contains(id, "[")){
    	   JSONArray ids = new JSONArray(id);
    	   for(int i=0;i<ids.length();i++){
    		   String catelogId = (String)ids.get(i);
    		   ResourceCatalog resourceCatalog = resourceCatalogService.findById(catelogId);
    		   if(resourceCatalog != null){
    			   resourceCatalog.setDisplayOrder(i);
    			   resourceCatalogService.update(resourceCatalog);
    			   num++;
    		   }
    	   }
       }else{
    	   ResourceCatalog resourceCatalog = resourceCatalogService.findById(id);
    	   if(resourceCatalog != null){
			   resourceCatalogService.update(resourceCatalog);
			   num++;
		   }
       }
       WebUtil.outputHtml(response, String.valueOf(num));
       return null;
    }
	
	@RequestMapping(value="/conceptTree.do", method=RequestMethod.POST)
    public String conceptTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
       String conceptName = request.getParameter("rootName");
       conceptName = URLDecoder.decode(conceptName, "UTF-8");
       String conceptId = request.getParameter("id");
       JSONArray domainArr = new JSONArray();
       String result = "";
       JSONArray dataArr = null;
       if(StringUtils.isBlank(conceptId)){
           String[] conceptNames = new String[]{conceptName};
           String jsonResult = ExkeOntologService.getConceptsByKeywords(conceptNames);
           JSONObject obj = new JSONObject(jsonResult);
           Object arr = obj.get("data");
           if(arr != null){
        	   dataArr = (JSONArray)obj.get("data");
           }
           if(dataArr != null){
        	   JSONObject obj1 = (JSONObject)dataArr.get(0);
        	   conceptId = obj1.getString("id");
           }
       }
       if(StringUtils.isNotBlank(conceptId)){
    	   JSONObject json = new JSONObject();
			json.put("elementType","relation");
			json.put("direction","outbound");
			json.put("content", "外延");
			JSONArray conditions = new JSONArray();
			conditions.put(json);
			result = ExkeOntologService.getChildConceptsById(conceptId, conditions.toString(), 100);
       }
       if(StringUtils.isNotBlank(result)){
    	   JSONArray resultArr = new JSONArray(result);
    	   for(int i=0;i<resultArr.length();i++){
    		   JSONObject resultObj = (JSONObject)resultArr.get(i);
    		   JSONArray conceptsArr = (JSONArray)resultObj.get("concepts");
    		   JSONObject resultObj1 = (JSONObject)conceptsArr.get(1);
    		   JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", resultObj1.getString("id"));
				jsonObj.put("name", resultObj1.getString("content"));
				jsonObj.put("domainId", resultObj1.getString("id"));
				jsonObj.put("isParent", true);
				domainArr.put(jsonObj);
    	   }
       }
       WebUtil.outputHtml(response, domainArr.toString());
       return null;
    }
}
