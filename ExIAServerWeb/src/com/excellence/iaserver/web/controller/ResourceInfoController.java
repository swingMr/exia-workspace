package com.excellence.iaserver.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.excellence.iamp.mongodb.service.ResourceCatalogService;
import com.excellence.iamp.mongodb.service.ResourceLibService;
import com.excellence.iamp.mongodb.service.ResourceService;
import com.excellence.iamp.mongodb.vo.Attribute;
import com.excellence.iamp.mongodb.vo.FileInfo;
import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.mongodb.vo.Resource.TagInfo;
import com.excellence.iamp.mongodb.vo.ResourceCatalog;
import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iamp.mongodb.vo.ResourceSection;
import com.excellence.iamp.service.ResourceSectionService;
import com.excellence.iamp.service.TagRecordService;
import com.excellence.iamp.service.UserDomainService;
import com.excellence.iamp.util.AsyncInfo;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.TagRecord;
import com.excellence.iamp.vo.UserDomain;
import com.excellence.iaserver.common.util.ElasticSearchUtil;
import com.excellence.iaserver.common.util.FileHandlerUtil;
import com.excellence.iaserver.common.util.WebUtil;

@Controller
@RequestMapping(value = "/services/res")
public class ResourceInfoController {
   @Autowired
   private ResourceLibService resourceLibService;
   @Autowired
   private ResourceService resourceService;
   @Autowired
   private ResourceCatalogService resourceCatalogService;
   @Autowired
   private TagRecordService tagRecordService;
   @Autowired
   private ResourceSectionService resourceSectionService;
   @Autowired
   private UserDomainService userDomainService;
   @Autowired
   private AsyncInfo asyncInfo;
   	
   /**
    * 
    * ��Դ���б�
    * @param request
    * @param response
    */
	@RequestMapping(value="/libList")
	public void resourceLibList(HttpServletRequest request, HttpServletResponse response)  {
		JSONArray arr = new JSONArray();
		JSONObject json = new JSONObject();
		int status = -1;
		String msg = "���ؽ���쳣��";
		List<ResourceLib> list  = resourceLibService.getAll();
		if(list != null && list.size()>0){
			msg = "�з��ؽ����";
			status = 1;
			for(int i=0; list.size()>i ; i++){
				JSONObject obj = new JSONObject();
				obj.put("libNum", list.get(i).getLibNum());
				obj.put("name", list.get(i).getLibName());
				arr.put(obj);
			}
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("data", arr);
		WebUtil.outputHtml(response, json.toString());
	}
   
	@RequestMapping(value="/getCatalogs")
	public void getCatalogs(HttpServletRequest request, HttpServletResponse response) {
		Map map  = WebUtil.requestToMap(request);
		String libNum = MapUtils.getString(map, "libNum","");
		String catelogId = MapUtils.getString(map, "id","root");
		JSONObject json = new JSONObject();
		int status = -1;
		String msg = "���ؽ���쳣��";
		JSONArray array = new JSONArray();
		List<ResourceCatalog> list = resourceCatalogService.getCatalogsByParentIdAndLibNum(catelogId, libNum);
		if(list != null && list.size() > 0) {
			msg = "�з��ؽ����";
			status = 1;
			for(ResourceCatalog catalog : list) {
				List<ResourceCatalog> ctList = resourceCatalogService.getCatalogsByParentIdAndLibNum(catalog.getId(), libNum);
				boolean hasChildren = false;
				if(ctList != null && ctList.size()>0){
					hasChildren = true;
				}
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("hasChildren", hasChildren);
				jsonObj.put("libNum", catalog.getLibNum());
				jsonObj.put("id", catalog.getId());
				jsonObj.put("name", catalog.getCatelogName());
				array.put(jsonObj);
			}
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("data", array);
        WebUtil.outputHtml(response, json.toString());
	}
	
	
	
   /**
    * 5.2.5ͨ����Դurl��ȡ��Դ�б�
    * @param request
    * @param response
    */
	@RequestMapping(value="/resources")
	public void resourcesByUrl(HttpServletRequest request, HttpServletResponse response)  {
		JSONObject result = new JSONObject();
		int status = 0;
		String msg ="fail";
		long startTime = System.currentTimeMillis();
		JSONArray arr =  new JSONArray();
		String url = request.getParameter("url");
		//eg :http://<host>/wcm/���ɷ����#pageNo=1&pageSize=20;
		if(StringUtils.isNotBlank(url)){
			int t = url.indexOf("wcm/");
			int t2 = url.indexOf("#");
			int t3 = url.indexOf("pageNo=");
			int t4 = url.indexOf("pageSize=");
			int t5 = url.indexOf("&");
			String libName = url.substring(t+4,t2);
			String pageN = url.substring(t3+7,t5);
			String pageS = url.substring(t4+9,url.length());
			int pageNo = Integer.parseInt(pageN);
			int pageSize = Integer.parseInt(pageS);
			String param = "";
			Page<Resource> pageList = resourceService.findByParamsAndCollectionName(pageNo, pageSize, libName, param);
			if(pageList != null){
				List resourceList = pageList.getRows();
				if(resourceList != null){
					for(int i=0; resourceList.size()>i; i++){
						JSONObject obj = new JSONObject();
						Resource resource = (Resource) resourceList.get(i);
						obj.put("id", resource.getId());
						obj.put("text", resource.getTitle());
						obj.put("url", resource.getUrl());
						obj.put("cruser", resource.getCreatorName());
						obj.put("createTime", resource.getCreateDate());
						arr.put(obj);
					}
				}
			}
		}
		msg="success";
		status=1;
		JSONObject data = new JSONObject();
		data.put("documents", arr);
		result.put("status", status);
		result.put("msg", msg);
		result.put("data", data);
		result.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, result.toString());
	}
   
   
   
  	/**��ȡĳ���û���ע������Ϣ��Դ
  	 * @author Liuzh
  	 * 
  	 */
	@RequestMapping(value="/getMarkedResourceList")
	public void getMarkedResourceList(HttpServletRequest request, HttpServletResponse response)  {
		Date t1 = new Date();
		String msg ="fail";
		int status =0;
		Map map  = WebUtil.requestToMap(request);
		String context = MapUtils.getString(map, "context","");
	    JSONObject data = new JSONObject(context);
	    JSONObject jsonUser  = (JSONObject) data.get("user");
	    String creatorId = jsonUser.getString("id");
	    String pageNo = MapUtils.getString(map, "pageNo","");
	    String pageSize= MapUtils.getString(map, "pageSize","");
	    int currentPage =1;
	    int pageSizes =10;
	    if(StringUtils.isNotBlank(pageNo)){
	    	currentPage = Integer.parseInt(pageNo);
	    }
	    if(StringUtils.isNotBlank(pageSize)){
	    	pageSizes = Integer.parseInt(pageSize);
	    }
	    int total = 0;
	    JSONArray arrData = new JSONArray();
	    if(StringUtils.isNotBlank(creatorId)){
	    	//�����û�ID��ȡ���û������ص���䣻
	    	List<String> resourceIds = resourceSectionService.getResourceIdsByCreatorId(currentPage, pageSizes, creatorId);
	    	for(int q=0; resourceIds.size()>q; q++){
	    		String resourceId = resourceIds.get(q);
		    	//��������ظ�����Ϣ��Դ��
	    		List<ResourceSection> list = resourceSectionService.getListByResourceSignAndUserId(resourceId, creatorId);
			    if(list != null && list.size()>0){
			    		String libNum = list.get(0).getLibNum();
			    		ResourceLib lib = resourceLibService.getByLibNum(libNum);
				    	//�����ص����� LibNum�� ��ԴID��ȡ������Ϣ��Դ
			    		Resource sec = resourceService.findByIdAndCollectionName(list.get(0).getResourceSign(), lib.getCollectionName());	
				    	JSONObject obj = new JSONObject();
				    	if(sec != null){
					    	obj.put("resourceId", sec.getId());
					    	obj.put("title", sec.getTitle());
					    	List<String> catalogIds = sec.getCatalogIds();
					    	String catalogIdStr ="";
					    	if(catalogIds !=null && catalogIds.size()>0){
			                  for(int j=0; catalogIds.size()>j; j++){
					    		if(j==0) {
			                    	catalogIdStr = catalogIdStr + catalogIds.get(j);
			                    } else {
			                    	catalogIdStr = catalogIdStr + ";"+catalogIds.get(j);
			                    }
			                  }
					    	}
					    	obj.put("catalogIds", catalogIdStr);
					    	List<String> catalogNames = sec.getCatalogNames();
					    	String catalogNameStr ="";
					    	if(catalogNames !=null && catalogNames.size()>0){
			                  for(int k=0; catalogNames.size()>k; k++){
					    		if(k==0) {
					    			catalogNameStr = catalogNameStr + catalogNames.get(k);
			                    } else {
			                    	catalogNameStr = catalogNameStr + ";"+catalogNames.get(k);
			                    }
			                  }
						    }
					    	obj.put("catalogNames", catalogNameStr);
					    	obj.put("url", sec.getOriginalAddress());
					    	obj.put("creator", sec.getCreatorName());
					    	obj.put("createDate", sec.getCreateDate());
					    	arrData.put(obj);
				    	}
			    }
	    	}
	    	msg ="success";
			status =1;
	    }
		Date dt2 = new Date();
		long t = dt2.getTime()-t1.getTime();
	    JSONObject jsonResult = new JSONObject();
	    jsonResult.put("data", arrData);
	    jsonResult.put("status", status);
	    jsonResult.put("msg", msg);
	    jsonResult.put("timeCost", t);
	    jsonResult.put("total", total);
	    WebUtil.outputHtml(response, jsonResult.toString());
	}
   
   
   
   	/**�����ص����
   	 * @author Liuzh
   	 * 
   	 */
	@RequestMapping(value="/saveSection")
	public void saveResource(HttpServletRequest request, HttpServletResponse response)  {
		String msg ="fail";
		int status =0;
		Map map  = WebUtil.requestToMap(request);
		String context = MapUtils.getString(map, "context","");
	    String libNum = MapUtils.getString(map, "libNum","");
	    String resourceId= MapUtils.getString(map, "resourceId","");
	    String url= MapUtils.getString(map, "url","");
	    String htmlContent= MapUtils.getString(map, "htmlContent","");
	    JSONObject data = new JSONObject(context);
	    JSONObject jsonUser  = (JSONObject) data.get("user");
	    String creatorId = jsonUser.getString("id");
	    String creatorName = jsonUser.getString("name");
       String regExHtml = "<[^>]+>"; // ����HTML��ǩ��������ʽ 
       String regExBr = "<?+br\\s*/?>"; // ����HTML��ǩ��������ʽ
       String pureContent =  htmlContent.replaceAll(regExBr, "\n").replaceAll("</p>","\n").replaceAll(regExHtml, "");
       ResourceLib resourceLib = resourceLibService.getByLibNum(libNum);
	   JSONObject obj = new JSONObject();
	   JSONObject datas = new JSONObject();
       if(resourceLib != null){
           Resource resource = resourceService.findByUrlAndCollectionName(url, resourceLib.getCollectionName());
           if(resource == null){
        	   resource = resourceService.findByIdAndCollectionName(resourceId, resourceLib.getCollectionName());
           }
    	if(resource != null){
    	    ResourceSection rs= new ResourceSection();
    	    rs.setLibNum(libNum);
    	    rs.setResourceSign(resourceId);
    	    //TODO ������Ҫ����
    	    rs.setResourceType("1");
    	    rs.setDisplayOrder(1);
    	    rs.setSectionTheme("");
    	    rs.setSubWords("");
    	    rs.setStatus(resource.getStatus());
    	    htmlContent = filterSection(htmlContent);
    	    rs.setSectionHtml(htmlContent);
    	    rs.setSectionContent(pureContent);
    	    rs.setResTile(resource.getTitle());
    	    rs.setCreatorId(creatorId);
    	    rs.setCreatorName(creatorName);
    	    rs.setCreateDate(new Date());
    	    rs = resourceSectionService.save(rs);
    	    if(rs.getId()!=null){
    			status=1;
    			msg="success";	
    	    }
    	    datas.put("sectionId", rs.getId());
    	    datas.put("resourceId", resourceId);
    	    datas.put("libNum", libNum);
    	    datas.put("title", rs.getResTile());
    	    datas.put("content", pureContent);
    	    datas.put("theme", rs.getResTile());
    	    datas.put("subWords", rs.getResTile());
    	    datas.put("htmlContent", rs.getResTile());
    	}   
       }
	    obj.put("status", status);
	    obj.put("msg", msg);
	    obj.put("data", datas);
	    WebUtil.outputHtml(response, obj.toString());
	}
	
   	/**��ȡĳ��Դĳ���û�����ע���ص����
   	 * @author Liuzh
   	 * 
   	 */
	@RequestMapping(value="/getMarkedSectionList")
	public void getSections(HttpServletRequest request, HttpServletResponse response)  {
		Date t1 = new Date();
		String msg ="fail";
		int status =0;
		Map map  = WebUtil.requestToMap(request);
		String context = MapUtils.getString(map, "context","");
	    JSONObject data = new JSONObject(context);
	    JSONObject jsonUser  = (JSONObject) data.get("user");
	    String creatorId = jsonUser.getString("id");
	    String libNum = MapUtils.getString(map, "libNum","");
	    String resourceId= MapUtils.getString(map, "resourceId","");
	    List<ResourceSection> rsList = resourceSectionService.getListByResourceSignAndUserId(resourceId, creatorId);
	    JSONObject jsonObj = new JSONObject();
	    JSONArray arr = new JSONArray();
	    if(rsList != null && rsList.size()>0){
		    for(int i =0; rsList.size()>i ;i++){
		    	JSONObject rsJson = new JSONObject();
		    	rsJson.put("sectionId",rsList.get(i).getId());
		    	rsJson.put("resourceId", rsList.get(i).getResourceSign());
		    	rsJson.put("libNum", rsList.get(i).getLibNum());
		    	rsJson.put("title", rsList.get(i).getResTile());
		    	rsJson.put("content", rsList.get(i).getSectionContent());
		    	rsJson.put("theme", rsList.get(i).getSectionTheme());
		    	rsJson.put("subWords", rsList.get(i).getSubWords());
		    	rsJson.put("htmlContent", rsList.get(i).getSectionHtml());
		    	arr.put(rsJson);
		    }
	    }
		Date dt2 = new Date();
		long t = dt2.getTime()-t1.getTime();
		msg ="success";
		status =1;
	    jsonObj.put("msg", msg);
	    jsonObj.put("status", status);
	    jsonObj.put("data", arr);
	    jsonObj.put("timeCost", t);
	    WebUtil.outputHtml(response, jsonObj.toString());
	}
   
	private String filterSection(String text) {
		String pattern = "<[^<>]*>?";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(text);
		StringBuffer sb = new StringBuffer();  
		while(matcher.find()) {
			String temp = matcher.group();
			if(temp.contains("index")) {
				matcher.appendReplacement(sb,"");
			}
		}
		matcher.appendTail(sb);  
		text = sb.toString();
		return text;
	}
	
   /**
    * 5.2.1��ȡָ����Ϣ��Դ��������Ϣ
    * @param request
    * @param response
    */
	@RequestMapping(value="/{libNum}/{id}/info")
	public void getInfo(@PathVariable("libNum") String libNum,@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response){
		long startTime = System.currentTimeMillis();
		ResourceLib lib = resourceLibService.getByLibNum(libNum);
		int status =0;
		String msg ="fail";
		JSONObject obj = new JSONObject();
		if(lib != null && StringUtils.isNotBlank(lib.getCollectionName())){
			Resource resource = resourceService.findByIdAndCollectionName(id, lib.getCollectionName());
			if(resource != null) {
				obj.put("id", resource.getId());
				obj.put("libNum", resource.getLibNum());
				obj.put("catalogId", resource.getCatalogId());
				obj.put("catalogNum", resource.getCatalogNum());
				obj.put("version", resource.getVersion());
				obj.put("issuedNum", resource.getIssuedNum());
				obj.put("title", resource.getTitle());
				obj.put("publishDate", resource.getPublishDate());
				obj.put("genre", resource.getGenre());
				obj.put("granularity", resource.getGranularity());
				obj.put("validTime", resource.getValidTime());
				obj.put("status", resource.getStatus());
				obj.put("abstractText", resource.getAbstractText());
				obj.put("content", resource.getContent());
				obj.put("originalAddress", resource.getOriginalAddress());
				obj.put("creatorId", resource.getCreatorId());
				obj.put("creatorName", resource.getCreatorName());
				obj.put("createDate", resource.getCreateDate());
				obj.put("updateDate", resource.getUpdateDate());
				JSONArray belongDomainList = new JSONArray();
				List bdList = resource.getBelongDomain();
				if(bdList != null){
					for(int i=0; bdList.size()>i; i++){
						belongDomainList.put(bdList.get(i));
					}	
				}
				obj.put("belongDomain", belongDomainList);
				obj.put("source", resource.getSource());
				obj.put("fileExt", resource.getFileExt());	
				JSONArray arr = new JSONArray();
				List<Attribute> exntendAttrs = resource.getExtendAttrs();
				if(exntendAttrs != null){
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
				obj.put("extendAttrs", arr);
				
				JSONArray infos = new JSONArray();
				List<FileInfo> fileInfos = resource.getFileInfos();
				if(fileInfos != null){
					for(int i=0; fileInfos.size()>i; i++){
						JSONObject attrObj = new JSONObject();
						attrObj.put("fileType",fileInfos.get(i).getFileType());
						attrObj.put("createDate",fileInfos.get(i).getCreateDate());
						attrObj.put("creatorName",fileInfos.get(i).getCreatorName());
						attrObj.put("fileExt",fileInfos.get(i).getFileExt());
						attrObj.put("fileSize",fileInfos.get(i).getFileSize());
						attrObj.put("id",fileInfos.get(i).getId());
						attrObj.put("title",fileInfos.get(i).getTitle());
						JSONArray keywordList = new JSONArray();
						List keywords = fileInfos.get(i).getKeywords();
						if(keywords != null && keywords.size() > 0) {
							for(int j=0; keywords.size()>j; j++){
								keywordList.put(keywords.get(j));
							}
							attrObj.put("keywords", keywordList);
						}
						infos.put(attrObj);
					}
				}
				obj.put("fileInfos", infos);
				
				Map<String,List<TagInfo>> tags = resource.getTag();
				JSONObject tagJson = new JSONObject();
				if(tags != null){
					tagJson = resource.getTagByJsonObj();
				}
				obj.put("tag", tagJson);
				status=1;
				msg="success";
			}
		}
		JSONObject result  = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		result.put("data", obj);
		result.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, result.toString());
	}
	
	@RequestMapping(value="/{libNum}/{id}/text")
	public void getContent(@PathVariable("libNum") String libNum,@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResourceLib lib = resourceLibService.getByLibNum(libNum);
		response.setHeader("Content-type", "text/html;charset=GBK");
		int status = -1;
		String msg = "���ؽ���쳣��";
		if(lib != null) {
			Resource resource = resourceService.findByIdAndCollectionName(id, lib.getCollectionName());
			if(resource != null) {
				msg = "�з��ؽ����";
				status = 1;
				JSONObject json = new JSONObject();
				json.put("status", status);
				json.put("msg", msg);
				if(StringUtils.isNotEmpty(resource.getHtmlContent())) {
					json.put("data", resource.toJson());
					response.getOutputStream().write(json.toString().getBytes());
					response.getOutputStream().close();
				} else {
					List<FileInfo> fileList = resource.getFileInfos();
					if(fileList != null && fileList.size() > 0) {
						for(FileInfo fileInfo:fileList) {
							if(fileInfo.getFileType() == 1) {
								String ext = fileInfo.getFileExt();
								String fileId = fileInfo.getId();
								InputStream is = resourceService.getInputStreamById(fileId);
								String content = FileHandlerUtil.getText("."+ext, is);
								response.getOutputStream().write(content.getBytes());
								response.getOutputStream().close();
							}
						}
					}
				}
			}
			
		}
	}
	
	@RequestMapping(value="/{libNum}/{id}/attach/{fileId}")
	public void getAttachContent(@PathVariable("libNum") String libNum,@PathVariable("id") String id,@PathVariable("fileId") String fileId,HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResourceLib lib = resourceLibService.getByLibNum(libNum);
		if(lib != null) {
			Resource resource = resourceService.findByIdAndCollectionName(id, lib.getCollectionName());
			if(StringUtils.isNotEmpty(resource.getContent())) {
				String content = resource.getContent();
				response.getOutputStream().write(content.getBytes());
				response.getOutputStream().close();
			} else {
				List<FileInfo> fileList = resource.getFileInfos();
				if(fileList != null && fileList.size() > 0) {
					for(FileInfo fileInfo:fileList) {
						if(fileInfo.getId() == fileId) {
							String ext = fileInfo.getFileExt();
							InputStream is = resourceService.getInputStreamById(fileId);
							String content = FileHandlerUtil.getText("."+ext, is);
							response.getOutputStream().write(content.getBytes());
							response.getOutputStream().close();
						}
					}
				}
			}
		}
	}
	
	@RequestMapping(value="/count")
	public void getResourceCount(@RequestParam("libNum") String libNum,HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		ResourceLib lib = resourceLibService.getByLibNum(libNum);
		JSONObject obj = new JSONObject();
		int status = -1;
		String msg = "���ؽ���쳣��";
		if(lib != null) {
			msg = "�з��ؽ����";
			status = 1;
			Long resourceCount = resourceService.getCount(libNum);
			obj.put("count", String.valueOf(resourceCount));
		}
		obj.put("status", status);
		obj.put("msg", msg);
		obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, obj.toString());
	}
	
	@RequestMapping(value="/createResource")
	public void createResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map  = WebUtil.requestToMap(request);
	    String catalogId = MapUtils.getString(map, "channelId","");
	    String catalogName= MapUtils.getString(map, "channelName","");
	    String libName = MapUtils.getString(map, "libName","");
	    String title = MapUtils.getString(map, "title","");
	    String content = MapUtils.getString(map, "content","");
	    String catelogNum = "";
	    String collectionName = "ia_resource_catalog";//��Ӧ���ݿ����
		JSONObject obj = new JSONObject();
		int status = -1;
		String msg = "������Դʧ�ܣ�";
		if(StringUtils.equals(libName, "�������׿�")){
			libName = "wcmmetatablepartyliterature";
		}else if(StringUtils.equals(libName, "���ɷ����")){
			libName = "wcmmetatableflfg";
		}else if(StringUtils.equals(libName, "������׼��")){
			libName = "wcmmetatabletechnicalstandard";
		}else if(StringUtils.equals(libName, "ר�����߿�")){
			libName = "wcmmetatablespecialpolicy";
		}else if(StringUtils.equals(libName, "��ѧ�о���")){
			libName = "wcmmetatablescientificresearch";
		}else if(StringUtils.equals(libName, "�쵼ָʾ��")){
			libName = "wcmmetatableleadershipinstruction";
		}else if(StringUtils.equals(libName, "�����¼���")){
			libName = "wcmmetatabletypicalevents";
		}else if(StringUtils.equals(libName, "���������")){
			libName = "wcmmetatablepublicopinion";
		}else if(StringUtils.equals(libName, "������Դ��")){
			libName = "wcmmetatableforeignresources";
		}
		List<ResourceCatalog> resourceCatalogList = resourceCatalogService.findByCatelogNameAndCollectionName(catalogName,libName,collectionName);
		if(resourceCatalogList != null && resourceCatalogList.size() > 0){
			catelogNum = resourceCatalogList.get(0).getCatelogNum();
		}	
		Resource resource = new Resource();
		resource.setCatalogId(catalogId);
		resource.setCatalogNum(catelogNum);
		resource.setLibNum(libName);
		resource.setTitle(title);
		resource.setContent(content);
		Resource result = resourceService.save(resource, libName);
		if(result != null) {
			msg = "������Դ�ɹ���";
			status = 1;
		}
		obj.put("status", status);
		obj.put("msg", msg);
		obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, obj.toString());
	}
	
	
	@RequestMapping(value="/updateResource")
	public void updateResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map  = WebUtil.requestToMap(request);
	    String catalogId = MapUtils.getString(map, "channelId","");
	    String catalogName= MapUtils.getString(map, "channelName","");
	    String libName = MapUtils.getString(map, "libName","");
	    String title = MapUtils.getString(map, "title","");
	    String attributes = MapUtils.getString(map, "attributes","");
	    String catelogNum = "";
	    String collectionName = "ia_resource_catalog";//��Ӧ���ݿ����
		JSONObject obj = new JSONObject();
		int status = -1;
		String msg = "������Դʧ�ܣ�";
		if(StringUtils.equals(libName, "�������׿�")){
			libName = "wcmmetatablepartyliterature";
		}else if(StringUtils.equals(libName, "���ɷ����")){
			libName = "wcmmetatableflfg";
		}else if(StringUtils.equals(libName, "������׼��")){
			libName = "wcmmetatabletechnicalstandard";
		}else if(StringUtils.equals(libName, "ר�����߿�")){
			libName = "wcmmetatablespecialpolicy";
		}else if(StringUtils.equals(libName, "��ѧ�о���")){
			libName = "wcmmetatablescientificresearch";
		}else if(StringUtils.equals(libName, "�쵼ָʾ��")){
			libName = "wcmmetatableleadershipinstruction";
		}else if(StringUtils.equals(libName, "�����¼���")){
			libName = "wcmmetatabletypicalevents";
		}else if(StringUtils.equals(libName, "���������")){
			libName = "wcmmetatablepublicopinion";
		}else if(StringUtils.equals(libName, "������Դ��")){
			libName = "wcmmetatableforeignresources";
		}
		List<ResourceCatalog> resourceCatalogList = resourceCatalogService.findByCatelogNameAndCollectionName(catalogName,libName,collectionName);
		if(resourceCatalogList != null && resourceCatalogList.size() > 0){
			catelogNum = resourceCatalogList.get(0).getCatelogNum();
		}
		JSONObject paramObj = new JSONObject();
		paramObj.put("title", title);
		paramObj.put("libNum", libName);
		paramObj.put("catalogNum", catelogNum);
		Page<Resource>  resourcePage = resourceService.findByParamsAndCollectionName(1, 10, libName, paramObj.toString());
		List<Resource> resourceList = resourcePage.getRows();
		if(resourceList != null && resourceList.size() > 0){
			Resource resource  = resourceList.get(0);
			Method[] methods = resource.getClass().getDeclaredMethods();
			if(StringUtils.isNotBlank(attributes)){
				JSONObject attributesObj = new JSONObject(attributes);
				Iterator it = attributesObj.keys();
				while (it.hasNext()) {
					String key = (String) it.next();
					String value = attributesObj.getString(key);
					String fieldSetName = parSetName(key);//��������ת��Ϊset����
					Method result = this.checkSetMet(methods, fieldSetName);//�������е������Ƿ���resource���д���
					if(result != null){
						Resource resource1 = setFieldValue(resource,result,key,value);
						resourceService.update(resource1, libName);
						status = 1;
						msg = "������Դ�ɹ���";
					}
				}

			}
			
		}
		obj.put("status", status);
		obj.put("msg", msg);
		obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, obj.toString());
	}
	
	
	@RequestMapping(value="/getResources")
	public void getList(HttpServletRequest request, HttpServletResponse response){
		JSONObject json = new JSONObject();
		Map<String,String> condition = new HashMap<String,String>();
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String catalogId = request.getParameter("catalogId");
		int status = -1;
		String msg = "���ؽ���쳣��";
		//ͨ����Դ�����ҵ�collectionName;
		String libNum = request.getParameter("libNum");
		int currentSize = 10;
		int currentPage = 1;
		if(StringUtils.isNotBlank(pageNo)){
			currentPage = Integer.parseInt(pageNo);
		}
		if(StringUtils.isNotBlank(pageSize)){
			currentSize = Integer.parseInt(pageSize);
		}
		Page<Resource> list = resourceService.getListByCatalogId(currentPage, currentSize, libNum, catalogId, null,condition);
		List<Resource> resourceList = new ArrayList<Resource>();
		JSONArray arr = new JSONArray();
		if(list != null){
			resourceList = list.getRows();
			if(resourceList != null && resourceList.size()>0){
				msg = "�з��ؽ����";
				status = 1;
				for(int i=0; resourceList.size()>i; i++){
					Resource r = resourceList.get(i);
					JSONObject obj = new JSONObject();
					obj.put("creator", r.getCreatorName());
					obj.put("id", r.getId());
					obj.put("createDate", r.getCreateDate());
					obj.put("url", r.getUrl());
					obj.put("title", r.getTitle());
					arr.put(obj);
				}
			}
		}
		json.put("total", list.getTotalCount());
		json.put("status", status);
		json.put("msg", msg);
		json.put("data", arr);
		WebUtil.outputHtml(response, json.toString());
	}
	
	@RequestMapping(value="/resourceList")
	public void getResourceList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",15);
		String libNum = MapUtils.getString(map, "libNum","");
		String catalogId = MapUtils.getString(map, "catalogId","");
		String searchTitle = MapUtils.getString(map, "searchTitle","");
		ResourceLib lib = resourceLibService.getByLibNum(libNum);
		JSONObject obj = new JSONObject();
		int status = -1;
		String msg = "���ؽ���쳣��";
		if(lib != null) {
			Page<Resource>  resource = resourceService.getList(pageNo, pageSize, libNum, catalogId, searchTitle);
			List<Resource> resourceList = resource.getRows();
			JSONArray arr = new JSONArray();
			if(resourceList != null && resourceList.size() > 0){
				msg = "�з��ؽ����";
				status = 1;
				obj.put("currentPage", resource.getCurrentPage());
				obj.put("pageSize", resource.getPageSize());
				obj.put("totalCount", resource.getTotalCount());
				obj.put("totalPage", resource.getTotalPage());
				for(Resource rsource1 : resourceList){
					JSONObject obj1 = rsource1.toJson();
					arr.put(obj1);
				}
			}
			
			obj.put("status", status);
			obj.put("msg", msg);
			obj.put("data", arr);
			obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
		}
		WebUtil.outputHtml(response, obj.toString());
	}
		
	
	@RequestMapping(value="/text")
	public void getTextByUrlAndAttributes(@RequestParam("url") String url,HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",15);
		String attributeStr = MapUtils.getString(map, "attributes", "");
		JSONObject attributes = null;
		if(StringUtils.isNotEmpty(attributeStr)) {
			attributes = new JSONObject(attributeStr);
		}
		JSONObject json = new JSONObject();
		JSONObject resourceJson = new JSONObject();
		Resource resource = new Resource();
		int status = -1;
		String msg = "���ؽ���쳣��";
		Page<Resource> page = null;
		// ͨ��url��ȡ��Դ����
		if(StringUtils.isNotEmpty(url)) {
			//�Դ����url���зָ�
			//http://<host>/wcm/�������׿�/�쵼����/������ɼ�����Ƹĸ﹤���쵼С��ίԱ�ὲ��#article=ϰ��ƽǿ����Ҫ����ĸ��ƽ�������������ͷ����ܶ���
			URL urlObject = new URL(url);
			String[] paths = urlObject.getPath().split("/");
			String libName = paths[2];
			String catalogNames = urlObject.getPath().replace("/" + paths[1], "").replace("/" + paths[2], "");
			String[] refs = urlObject.getRef().split("&");
			String regex = "(.*)=(.*)";
			String title = "";
			for (String ref : refs) {
				if (StringUtils.equals(ref.replaceAll(regex, "$1"), "article")) {
					title = ref.replaceAll(regex, "$2");
				}
			}
			if(StringUtils.isNotEmpty(libName) && paths.length > 2) {
				ResourceLib resourceLib = resourceLibService.getByLibName(libName);
				if(resourceLib != null) {
					String collectionName = resourceLib.getCollectionName();
					String[] cNs = catalogNames.split("/");
					
					List<String> catalogNameList = new ArrayList<String>();
					for(String c:cNs) {
						if(StringUtils.isNotBlank(c)) {
							catalogNameList.add(c);
						}
					}
					Map<String, String> params = new HashMap<String, String>();
					params.put("title", title);
					if(attributes != null) {
						// �������ӵĲ�ѯ����
						Field[] fields = Resource.class.getDeclaredFields();
						List<String> resourceFieldNames = new ArrayList<String>();
						for (int i = 0 , len = fields.length; i < len; i++) {
							resourceFieldNames.add(fields[i].getName());
						}
						Iterator<String> iter = attributes.keys();
						while (iter.hasNext()) {
							String key = iter.next();
							if (resourceFieldNames.contains(key)) {// �����ֶ�
								params.put(key, attributes.getString(key));
							} else {// �����ֶ�
								params.put("extendAttrs." + key, attributes.getString(key));
							}
						}
					}
					page = resourceService.getListByCatalogNames(pageNo, pageSize, collectionName, catalogNameList, params);
				}
			}
		}
		// ת����Դ���ϵĽṹ
		if (page != null) {
			List<Resource> resources = page.getRows();
			if (resources != null && resources.size() > 0) {
				msg = "�з��ؽ����";
				status = 1;
				resource = resources.get(0);// ֻȡ��һ������
				resourceJson = resource.toJson();
			}
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("data", resourceJson);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
	}
	
	@RequestMapping(value="/resourceListByUrlAndAttributes")
	public void getResourceListByUrlAndAttributes(@RequestParam("url") String url,HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",15);
		String attributes = MapUtils.getString(map, "attributes","");
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		int status = -1;
		String msg = "���ؽ���쳣��";
		String collectionName = "ia_resource_catalog";//��Ӧ���ݿ����
		String[] str = StringUtils.split(url, "\\/");//�Դ����url���зָ�
		String libNum = str[4];
		String catelogName = str[str.length-1];
		if(StringUtils.equals(libNum, "�������׿�")){
			libNum = "wcmmetatablepartyliterature";
		}else if(StringUtils.equals(libNum, "���ɷ����")){
			libNum = "wcmmetatableflfg";
		}else if(StringUtils.equals(libNum, "������׼��")){
			libNum = "wcmmetatabletechnicalstandard";
		}else if(StringUtils.equals(libNum, "ר�����߿�")){
			libNum = "wcmmetatablespecialpolicy";
		}else if(StringUtils.equals(libNum, "��ѧ�о���")){
			libNum = "wcmmetatablescientificresearch";
		}else if(StringUtils.equals(libNum, "�쵼ָʾ��")){
			libNum = "wcmmetatableleadershipinstruction";
		}else if(StringUtils.equals(libNum, "�����¼���")){
			libNum = "wcmmetatabletypicalevents";
		}else if(StringUtils.equals(libNum, "���������")){
			libNum = "wcmmetatablepublicopinion";
		}else if(StringUtils.equals(libNum, "������Դ��")){
			libNum = "wcmmetatableforeignresources";
		}
		List<ResourceCatalog> resourceCatalogList = resourceCatalogService.findByCatelogNameAndCollectionName(catelogName,libNum,collectionName);
		if(resourceCatalogList != null && resourceCatalogList.size() > 0){
			String catelogNum = resourceCatalogList.get(0).getCatelogNum();
			Page<Resource>  resource = resourceService.findByParamsAndCollectionName(pageNo, pageSize, libNum,attributes);
			List<Resource> resourceList = resource.getRows();
			if(resourceList != null && resourceList.size() > 0){
				msg = "�з��ؽ����";
				status = 1;
				obj.put("currentPage", resource.getCurrentPage());
				obj.put("pageSize", resource.getPageSize());
				obj.put("totalCount", resource.getTotalCount());
				obj.put("totalPage", resource.getTotalPage());
				for(Resource rsource1 : resourceList){
					JSONObject obj1 = rsource1.toJson();
					arr.put(obj1);
				}
			}
		}
		obj.put("status", status);
		obj.put("msg", msg);
		obj.put("data", arr);
		obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, obj.toString());
	}
	/**
	 * ���ܣ���ȡÿ���Ƽ�����
	 * @param extoken			���ʷ��������
	 * @param memberId			��Աid
	 * @param page				��ǰҳ�룬Ĭ��Ϊ1
	 * @param pageSize			��ҳ��С��Ĭ��Ϊ100
	 * @author wangjg
	 * **/
	@RequestMapping(value="/getDailyPushData")
	public void getDailyPushData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "page",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",100);
		String extoken = MapUtils.getString(map, "extoken","");
		String context = MapUtils.getString(map, "context","");
		JSONObject obj = new JSONObject();
		int status = 1;
		String msg = "�з��ؽ����";
		List<TagRecord> tagRecordList = null;
		String userId = "";
		if(StringUtils.isNotBlank(context)){
			try {
				JSONObject obj1 = new JSONObject(context);
				if(obj1.has("user")){
					JSONObject jsonUser = (JSONObject) obj1.get("user");
					userId = jsonUser.get("id")==null ? "" : (String)jsonUser.get("id");
					String userName = jsonUser.get("account")==null ? "" : (String)jsonUser.get("account");
					tagRecordList = tagRecordService.getRankRecords(userId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<String> strs = new ArrayList<String>();
		if(tagRecordList != null && tagRecordList.size() > 0){
			for(TagRecord tagRecord : tagRecordList){
				if(!strs.contains(tagRecord.getTagName())){
					strs.add(tagRecord.getTagName());
				}
				if(strs.size() >= 5){
					break;
				}
			}
		}else{
			Map<String,String> parasMap = new HashMap<String,String>();
			parasMap.put("roleId", userId);
			List<UserDomain> userDomainList = userDomainService.getByCondition(parasMap);
			if(userDomainList != null && userDomainList.size() > 0){
				for(UserDomain userDomain : userDomainList){
					if(!strs.contains(userDomain.getDomainName())){
						strs.add(userDomain.getDomainName());
					}
					if(strs.size() >= 5){
						break;
					}
				}
			}else{
				tagRecordList = tagRecordService.getTagRecordLimit();
			}
		}
		if(strs.size() < 1 && tagRecordList.size() > 0){
			for(TagRecord tagRecord : tagRecordList){
				if(!strs.contains(tagRecord.getTagName())){
					strs.add(tagRecord.getTagName());
				}
				if(strs.size() >= 5){
					break;
				}
			}
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		String text = StringUtils.join(strs.toArray()," ");
		conditions.put("text", text);
		conditions.put("page", pageNo);
		conditions.put("pageSize", pageSize);
		conditions.put("isReturnContent", 0);
		String result = ElasticSearchUtil.elSearch(conditions);
		JSONObject res = new JSONObject();
		if(StringUtils.isBlank(result) || !StringUtils.contains(result, ":")){
			status = -1;
			msg = "���ؽ���쳣��";
		} else {
			res = new JSONObject(result);
			obj.put("data", res);
		}
		obj.put("status", status);
		obj.put("msg", msg);
		obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, obj.toString());
	}
	
	/**
	 * �ж��Ƿ����ĳ���Ե� set����
	 * 
	 * @param methods
	 * @param fieldSetMet
	 * @return boolean
	 */
	public Method checkSetMet(Method[] methods, String fieldSetMet) {
		Method method = null;
		for (Method met : methods) {
			if (fieldSetMet.equals(met.getName())) {
				method = met;
			}
		}
		return method;
	}
	
	/**
	 * ƴ����ĳ���Ե� set����
	 * 
	 * @param fieldName
	 * @return String
	 */
	private String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		int startIndex = 0;
		if (fieldName.charAt(0) == '_')
			startIndex = 1;
		return "set"
				+ fieldName.substring(startIndex, startIndex + 1).toUpperCase()
				+ fieldName.substring(startIndex + 1);
	}
	
	
	/**
	 * ��ʽ��stringΪDate
	 * 
	 * @param datestr
	 * @return date
	 */
	public Date parseDate(String datestr) {
		if (null == datestr || "".equals(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {
				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * set���Ե�ֵ��resource����
	 * 
	 * @param resource			resource����
	 * @param fieldSetMet		�ֶζ�Ӧ�ķ���
	 * @param key				�ֶ���
	 * @param value				��Ҫ�޸ĵ�ֵ
	 */
	public Resource setFieldValue(Resource resource, Method fieldSetMet, String key, String value) {
		Field[] fields = resource.getClass().getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			String fieldName = fields[j].getName();
			if(StringUtils.equals(key, fieldName)){
				try {
					if (null != value && !"".equals(value)) {
						String fieldType = fields[j].getType().getSimpleName();
						if ("String".equals(fieldType)) {
							fieldSetMet.invoke(resource, value);
						} else if ("Date".equals(fieldType)) {
							Date temp = parseDate(value);
							fieldSetMet.invoke(resource, temp);
						} else if ("Integer".equals(fieldType)
								|| "int".equals(fieldType)) {
							Integer intval = Integer.parseInt(value);
							fieldSetMet.invoke(resource, intval);
						} else if ("Long".equalsIgnoreCase(fieldType)) {
							Long temp = Long.parseLong(value);
							fieldSetMet.invoke(resource, temp);
						} else if ("Double".equalsIgnoreCase(fieldType)) {
							Double temp = Double.parseDouble(value);
							fieldSetMet.invoke(resource, temp);
						} else if ("Boolean".equalsIgnoreCase(fieldType)) {
							Boolean temp = Boolean.parseBoolean(value);
							fieldSetMet.invoke(resource, temp);
						} else {
							System.out.println("not supper type" + fieldType);
						}
					}
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 	resource;
	}	
}
