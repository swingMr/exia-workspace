package com.excellence.iamp.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;













import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tools.zip.ZipEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.common.UserInfo;
import com.excellence.exke.common.vo.InformationVo;
import com.excellence.exke.connector.ConnectorException;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.FileHandlerUtil;
import com.excellence.iacommon.common.util.IaServiceClient;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.mongodb.service.ResourceCatalogService;
import com.excellence.iamp.mongodb.service.ResourceLibService;
import com.excellence.iamp.mongodb.service.ResourceService;
import com.excellence.iamp.mongodb.vo.Attribute;
import com.excellence.iamp.mongodb.vo.FileInfo;
import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.mongodb.vo.ResourceCatalog;
import com.excellence.iamp.mongodb.vo.Resource.TagInfo;
import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iamp.resource.service.ExkeOntologService;
import com.excellence.iamp.service.ResourceSectionService;
import com.excellence.iamp.util.AsyncInfo;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.enums.EStatus;
import com.excellence.platform.um.exception.CommonAppException;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResourceLibService resourceLibService;
	@Autowired
	private ResourceCatalogService resourceCatalogService;
	@Autowired
	private ResourceSectionService resourceSectionService;
   @Autowired
   private AsyncInfo asyncInfo;
	private List<MultipartFile> uploadFileList = new ArrayList<MultipartFile>();
	
	private List<String> uploadFileNameList = new ArrayList<String>();
	
	private static Map<String, Object> cacheMap = new HashMap<String, Object>();
	
	/**
	 * 分页列表
	 * @author Liuzh
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list.do")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		String searchAttr = request.getParameter("searchAttr"); 
		String order = "0";
		String titleOrder = "";
		String orderByDate = request.getParameter("orderByDate");
		String orderByTitle = request.getParameter("orderByTitle");
		if(StringUtils.isNotBlank(orderByDate)){
			//0默认倒序，1有值升序
			order = orderByDate;	
		}
		if(StringUtils.isNotBlank(orderByTitle)){
			//默认倒序，有值升序
			//0默认倒序，1有值升序
			titleOrder = orderByTitle;	
			order ="";
		}
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("order", order);
		condition.put("orderByTitle", titleOrder);
		if(StringUtils.isNotBlank(searchAttr)){
			condition.put("searchAttr", searchAttr);
		}
		String pageNo = request.getParameter("pageNo");
		String displayMode = request.getParameter("displayMode");
		int mode = 0;
		if(StringUtils.isNotBlank(displayMode)){
			Integer.parseInt(displayMode);
			if(mode != 1){
				mode = 0;
			}
		}
		//通过资源库编号找到collectionName;
		String libNum = request.getParameter("libNum");
		ResourceLib lib = new ResourceLib();
		lib = resourceLibService.getByLibNum(libNum);
		String collectionName ="noCollectionName";
		if(lib != null){
		  collectionName = lib.getCollectionName();	
		}
		//目录编号
		String catalogNum = request.getParameter("catalogNum");
		String catalogId = request.getParameter("catalogId");
		int pageSize = 10;
		int currentPage = 1;
		if(StringUtils.isNotBlank(pageNo)){
			currentPage = Integer.parseInt(pageNo);
		}
		Page<Resource> list = resourceService.getListByCatalogId(currentPage, pageSize, collectionName, catalogId, null,condition);
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/resourceList");
		mv.addObject("list", list.getRows());
		mv.addObject("pages", list.getTotalPage());
		mv.addObject("currentPage", list.getCurrentPage());
		mv.addObject("libNum", libNum);
		mv.addObject("catalogNum", catalogNum);
		mv.addObject("catalogId", catalogId);
		mv.addObject("order", order);
		mv.addObject("mode",mode);
		mv.addObject("orderByTitle",titleOrder);
		mv.addObject("searchAttr",searchAttr);
		return mv;
	}
	
	@RequestMapping("/judgeExist.do")
	public ModelAndView judgeExist(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String id = request.getParameter("id");
		String libNum = request.getParameter("libNum");
		String msg ="fail";
		Resource resource = resourceService.findByIdAndCollectionName(id, libNum);
		if(resource !=null){
			//该资源未被删除；
			msg = "success";
		}
		result.put("msg", msg);
		WebUtil.outputHtml(response, result.toString());
		return null;
	} 
	
	/** 新增或更新对象。
	 * @author Liuzh
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/save.do")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		int result =0;
		JSONObject json = new JSONObject();
		try{
			Resource resource = new Resource();
			UserInfo user= UserUtil.getCurrentUser(request);
			String id = request.getParameter("id");
			String tags = request.getParameter("tag");
			String libNum = request.getParameter("libNum");
			String catalogId = request.getParameter("catalogId");
			String catalogNum = request.getParameter("catalogNum");
			String version = request.getParameter("version");
			String issuedNum = request.getParameter("issuedNum");
			String title = request.getParameter("title");
			String publisher =request.getParameter("publisher");
			String publishTime = request.getParameter("publishDate");
			String authority = request.getParameter("authority");
			String domainConfirmStatus = request.getParameter("domainConfirmStatus");
			String classify = request.getParameter("classify");
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			Date publishDate = sdf.parse(publishTime);
			String genre = request.getParameter("genre");
			String granularity =request.getParameter("granularity");
			String validTime = request.getParameter("validTime");
			//接口用。
			String oldContent = request.getParameter("oldContent");
			String oldTitle = request.getParameter("oldTitle");
			int domainConfirmStatu = 0;
			if(StringUtils.isNotBlank(domainConfirmStatus) && domainConfirmStatus.equals("1")){
				domainConfirmStatu = Integer.parseInt(domainConfirmStatus);
			}
			Calendar validDate = Calendar.getInstance();
			if(validTime.equals("1")){
				validDate.add(Calendar.MONTH, 6);
			}else if(validTime.equals("2")){
				validDate.add(Calendar.YEAR, 1);
			}else if(validTime.equals("3")){
				validDate.add(Calendar.YEAR, 3);
			}else if(validTime.equals("5")){
				validDate.add(Calendar.YEAR, 5);
			}else if(validTime.equals("10")){
				validDate.add(Calendar.YEAR, 10);
			}else if(validTime.equals("all")){
				//永久
				//目前mysql最大可以暂时向后扩展2037年
				validDate = new GregorianCalendar(2037,0,1,1,1,1);
			}
			Date validDates = new Date();
			if(!validTime.equals("nChange")){
				 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
				 String formatTime  = dateFormat.format(validDate.getTime());
				 validDates = dateFormat.parse(formatTime);	
			}
			 String abstractText = request.getParameter("abstractText");
			 String content = request.getParameter("content");
			 String originalAddress = request.getParameter("originalAddress");
			 String belongDomain = request.getParameter("belongDomain").replaceAll(";", ",");
			 List<String> belongDomainList = handleAttrList(belongDomain);
			 String source = request.getParameter("source");
			 String fileExt = request.getParameter("fileExt");
			 String keyword = request.getParameter("keyword");
			 List<String> keywordList = new ArrayList();
			 if(StringUtils.isNotBlank(keyword)){
				 keywordList = handleAttrList(keyword);
			 }
			 String relatedDomain = request.getParameter("relatedDomain");
			 List<String> relatedDomainList = new ArrayList();
			 if(StringUtils.isNotBlank(relatedDomain)){
				 relatedDomainList = handleAttrList(relatedDomain); 
			 }
			 String relatedItem = request.getParameter("relatedItem");
			 List<String> relatedItemList = new ArrayList();
			 if(StringUtils.isNotBlank(relatedItem)){
				 relatedItemList = handleAttrList(relatedItem); 
			 }
			 String relatedOrg = request.getParameter("relatedOrg");
			 List<String> relatedOrgList = new ArrayList();
			 if(StringUtils.isNotBlank(relatedOrg)){
				 relatedOrgList = handleAttrList(relatedOrg); 
			 }
			 String relatedPeople = request.getParameter("relatedPeople");
			 List<String> relatedPeopleList = new ArrayList();
			 if(StringUtils.isNotBlank(relatedPeople)){
				 relatedPeopleList = handleAttrList(relatedPeople); 
			 }
			 String relatedRes = request.getParameter("relatedRes");
			 List<String> relatedResList = new ArrayList();
			 if(StringUtils.isNotBlank(relatedRes)){
				 relatedResList = handleAttrList(relatedRes); 
			 }
			 String relatedTime =request.getParameter("relatedTime");
			 List<String> relatedTimeList = new ArrayList();
			 if(StringUtils.isNotBlank(relatedTime)){
				 relatedTimeList = handleAttrList(relatedTime);
			 }
			 String relatedArea  = request.getParameter("relatedArea");
			 List<String> relatedAreaList = new ArrayList();
			 if(StringUtils.isNotBlank(relatedArea)){
				 relatedAreaList = handleAttrList(relatedArea);
			 }
			 String relatedProblem = request.getParameter("relatedProblem");
			 List<String> relatedProblemList =  new ArrayList();
			 if(StringUtils.isNotBlank(relatedProblem)){
				 relatedProblemList = handleAttrList(relatedProblem); 
			 }
			 String attr = request.getParameter("attr");
			 String fileInfo = request.getParameter("FileInfo");
			 JSONArray attrArr;
			 JSONArray infoArr;
			 if(StringUtils.isNotBlank(attr)){
				  attrArr = new JSONArray(attr); 
			 }else{
				  attrArr = new JSONArray(); 
			 }
			 if(StringUtils.isNotBlank(fileInfo)){
				  infoArr = new JSONArray(fileInfo); 
			 }else{
				  infoArr = new JSONArray(); 
			 }
			 String collectionName = "";
			 if(StringUtils.isNotBlank(libNum)){
				 collectionName = resourceLibService.getByLibNum(libNum).getCollectionName();
			 }
			 InformationVo info = new InformationVo();
			 List<Attribute> attrList  = resource.getExtendAttrs();
			 handleInfo(attrList,info);
			 if(StringUtils.isNotBlank(id)){
				 //更新
				 resource = resourceService.findByIdAndCollectionName(id, collectionName);
				 if(StringUtils.isNotBlank(tags)){
					 tags = tags.replaceAll("'", "\"");
					 System.out.println(tags);
					 JSONObject tagObj = new JSONObject(tags);
					 resource.setTagByJsonObj(tagObj);
				 }
				 resource.setClassify(classify);
				 resource.setDomainConfirmStatus(domainConfirmStatu);
				 resource.setLibNum(libNum);
				 resource.setCatalogId(catalogId);
				 resource.setCatalogNum(catalogNum);
				 resource.setVersion(version);
				 resource.setIssuedNum(issuedNum);
				 resource.setTitle(title);
				 int aut = Integer.parseInt(authority);
				 resource.setAuthority(aut);
				 resource.setPublishDate(publishDate);
				 resource.setPublisher(publisher);
				 resource.setGenre(genre);
				 resource.setGranularity(granularity);
				 if(!validTime.equals("nChange")){
					 resource.setValidTime(validDates);
				 }else{
					 validDates = resource.getValidTime();
				 }
				 resource.setStatus(EStatus.normal.getIndex());
				 resource.setAbstractText(abstractText);
			        String regExHtml = "<[^>]+>"; // 定义HTML标签的正则表达式 
			        String regExBr = "<?+br\\s*/?>"; // 定义HTML标签的正则表达式
			        String pureContent =  content.replaceAll(regExBr, "\n").replaceAll("</p>","\n").replaceAll(regExHtml, "");
			         oldContent =  oldContent.replaceAll(regExBr, "\n").replaceAll("</p>","\n").replaceAll(regExHtml, "");
				 resource.setContent(pureContent);
				 resource.setHtmlContent(content);
				 resource.setOriginalAddress(originalAddress);
				 resource.setUpdateDate(new Date());
				 resource.setBelongDomain(belongDomainList);
				 resource.setSource(source);
				 resource.setCreatorName(user.getUsername());
				 resource.setFileExt(fileExt);
				 resource = handleExtendAttr(attrArr, resource); 
				 if(infoArr.length()>0){
					 resource = handleFileInfo(infoArr, resource);
				 }
				 List<String> catalogNames = resource.getCatalogNames();
				 String catalogNameStr ="";
				 for(int t=0;catalogNames.size()>t; t++){
					 if(t==0){
						 catalogNameStr = catalogNameStr + catalogNames.get(t);
					 }else {
						 catalogNameStr = catalogNameStr +"/"+ catalogNames.get(t);
					 }
				 }
				 ResourceLib lib = resourceLibService.getByLibNum(libNum);
				 String url = "http://<host>/wcm/"+lib.getLibName()+"/"+catalogNameStr+"#article="+resource.getTitle();
				 resource.setUrl(url);
				 resourceService.update(resource,collectionName);
				 String type="update";
				 String updateIndexResult = IaServiceClient.updateIndex(libNum, title, resource.getId(), validDates,type);
				 System.out.println(updateIndexResult);
				 json.put("resourceId", resource.getId());
				 result = 1;
				 
				//信息资源同步接口：修改信息资源
				String newContent="";
				String updateTitle ="";
				String theOldContent = "";
				String oldUrl ="http://<host>/wcm/"+lib.getLibName()+"/"+catalogNames+"#article="+oldTitle;
				 info.setId(user.getId());
				 info.setCreator(user.getUsername());
				 info.setUrl(url);
				 info.setTitle(title);
				//标题没变 判断内容是否有改变
				 
				if(!oldContent.equals(resource.getContent())){
					//有变化 新旧内容都不能为空
					newContent = resource.getContent();
					theOldContent = oldContent;
				}
				// 先判断标题有无改变
				if(!oldTitle.equals(resource.getTitle())){
					//标题变 ，url也需要修改  info 存的都是旧值；
					info.setTitle(oldTitle);
					info.setUrl(oldUrl);
					updateTitle = resource.getTitle();
					//需要传值
					newContent = resource.getContent();
					theOldContent = resource.getContent();
				}else{
					url = "";
				}
				for(int i=0; attrList.size()>i; i++){
					String attrName = attrList.get(i).getAttrCName();
					String attrType  = attrList.get(i).getType();
					Object value = attrList.get(i).getValue();
					if(attrType.equals("int")  ){
						double attrValue = Double.parseDouble(value.toString());
						info.addAttribute(attrName, attrValue);
					}else if(attrType.equals("date")){
						String str = value.toString();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
						Date date;
							date = format.parse(str);
	
						info.addAttribute(attrName, date);
					}else if(attrType.equals("String") || attrType.equals("longtext")){
						String str = value.toString();
						info.addAttribute(attrName, str);
					}else{
						//TODO 数组
					}
				}
				 asyncInfo.informationUpdated(info, newContent, theOldContent, updateTitle, url);
			   
			 }else{
				 //新增
			        String regExHtml = "<[^>]+>"; // 定义HTML标签的正则表达式 
			        String regExBr = "<?+br\\s*/?>"; // 定义HTML标签的正则表达式
			        String pureContent =  content.replaceAll(regExBr, "\n").replaceAll("</p>","\n").replaceAll(regExHtml, "");
				 String tag = IaServiceClient.gentag(title, pureContent, true, true, "","");
				 JSONObject obj = new JSONObject(tag);
				 if(obj.getInt("status") > 0) {
					 JSONObject data = (JSONObject) obj.get("data");
					 resource.setTagByJsonObj(data);
				 }
				 resource.setLibNum(libNum);
				 resource.setDomainConfirmStatus(domainConfirmStatu);
				 int aut = Integer.parseInt(authority);
				 resource.setAuthority(aut);
				 resource.setCatalogNum(catalogNum);
				 resource.setContent(pureContent);
				 resource.setCatalogId(catalogId);
				 resource.setClassify(classify);
				 resource.setVersion(version);
				 resource.setIssuedNum(issuedNum);
				 resource.setTitle(title);
				 resource.setPublishDate(publishDate);
				 resource.setPublisher(publisher);
				 resource.setGenre(genre);
				 resource.setGranularity(granularity);
				 resource.setValidTime(validDates);
				 resource.setStatus(EStatus.normal.getIndex());
				 resource.setAbstractText(abstractText);
				 resource.setHtmlContent(content);
				 resource.setOriginalAddress(originalAddress);
				 resource.setUpdateDate(new Date());
				 resource.setBelongDomain(belongDomainList);
				 resource.setSource(source);
				 resource.setFileExt(fileExt);
				 resource.setCreatorId(user.getId());
				 resource.setCreateDate(new Date());
				 resource.setCreatorName(user.getUsername());
				 List<String> catalogIds = new ArrayList<String>();
				 List<String> catalogNames = new ArrayList<String>();
				 resourceCatalogService.getCatalogInfos(catalogId, catalogIds, catalogNames);
				 Collections.reverse(catalogIds);
				 Collections.reverse(catalogNames);
				 resource.setCatalogIds(catalogIds);
				 resource.setCatalogNames(catalogNames);
				 if(attrArr.length()>0){
					  resource = handleExtendAttr(attrArr, resource); 
				 }
				 if(infoArr.length()>0){
					 resource = handleFileInfo(infoArr, resource);
				 }
				 String catalogNameStr ="";
				 for(int t=0;catalogNames.size()>t; t++){
					 if(t==0){
						 catalogNameStr = catalogNameStr + catalogNames.get(t);
					 }else {
						 catalogNameStr = catalogNameStr +"/"+ catalogNames.get(t);
					 }
				 }
				 ResourceLib lib = resourceLibService.getByLibNum(libNum);
				 String url = "http://<host>/wcm/"+lib.getLibName()+"/"+catalogNameStr+"#article="+resource.getTitle();
				 resource.setUrl(url);
				 resource = resourceService.save(resource,collectionName);
				 result = 1;
				 String type ="create";
				 
				//信息资源同步接口：新增信息资源
				 info.setId(user.getId());
				 info.setCreator(user.getUsername());
				 info.setUrl(url);
				 info.setTitle(title);
				for(int i=0; attrList.size()>i; i++){
					String attrName = attrList.get(i).getAttrCName();
					String attrType  = attrList.get(i).getType();
					Object value = attrList.get(i).getValue();
					if(attrType.equals("int")  ){
						double attrValue = Double.parseDouble(value.toString());
						info.addAttribute(attrName, attrValue);
					}else if(attrType.equals("date")){
						String str = value.toString();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
						Date date;
							date = format.parse(str);
	
						info.addAttribute(attrName, date);
					}else if(attrType.equals("String") || attrType.equals("longtext")){
						String str = value.toString();
						info.addAttribute(attrName, str);
					}else{
						//TODO 数组
					}
				}
				  asyncInfo.informationAdded(info, pureContent);
				 String updateIndexResult = IaServiceClient.updateIndex(libNum, title, resource.getId(), validDates,type);
				 System.out.println(updateIndexResult);
				 json.put("resourceId", resource.getId());
				 
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		if(result == 1){
			json.put("result", "success");
		}else{
			json.put("result", "false");
		}
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	
	private void handleInfo(List<Attribute> attrList , InformationVo info){
		if(attrList !=null && attrList.size()>0){
			try {
				for(int i=0; attrList.size()>i; i++){
					String attrName = attrList.get(i).getAttrCName();
					String attrType  = attrList.get(i).getType();
					Object value = attrList.get(i).getValue();
					if(attrType.equals("int")  ){
						double attrValue = Double.parseDouble(value.toString());
						info.addAttribute(attrName, attrValue);
					}else if(attrType.equals("date")){
						String str = value.toString();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
						Date date;
						date = format.parse(str);
						info.addAttribute(attrName, date);
					}else if(attrType.equals("String") || attrType.equals("longtext")){
						String str = value.toString();
						info.addAttribute(attrName, str);
					}else{
						//TODO 数组
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**重新打标签
	 * @author Liuzh
	 */
	
	@RequestMapping("/resetTag")
	public void resetTag(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
        String regExHtml = "<[^>]+>"; // 定义HTML标签的正则表达式 
        String regExBr = "<?+br\\s*/?>"; // 定义HTML标签的正则表达式
        String pureContent =  content.replaceAll(regExBr, "\n").replaceAll("</p>","\n").replaceAll(regExHtml, "");
		String tag = IaServiceClient.gentag(title, pureContent, true, true, "","");
		JSONObject obj = new JSONObject(tag);
		int judge = (Integer)obj.get("status");
		JSONObject data = new JSONObject();
		if(judge == 0){
			data.put("status", "fail");
		}else{
			data.put("data",(JSONObject) obj.get("data"));	
			data.put("status", "success");
		}
		WebUtil.outputHtml(response, data.toString());
	}
	
	/**处理正文&附件信息。
	 * @author Liuzh
	 * @param arr
	 * @param lib
	 * @return
	 */
	private Resource handleFileInfo(JSONArray arr,Resource resource){
		List<FileInfo> list = resource.getFileInfos();
		//清空再导入
		for(int j=0; list.size()>j; j++){
			resource.delFileInfo(list.get(j).getTitle());;
		}
		for(int i=0; arr.length()>i; i++){
			JSONObject obj = arr.getJSONObject(i);
			FileInfo info = new FileInfo();
			info.setId(obj.get("id").toString());
			info.setTitle(obj.get("title").toString());
			info.setFileExt(obj.get("fileExt").toString());
			info.setCreateDate((Date)obj.get("createDate"));
			resource.addFileInfo(info);
		}
		return resource;
	}
	
	/**处理扩展属性。
	 * @author Liuzh
	 * @param arr
	 * @param lib
	 * @return
	 */
	private Resource handleExtendAttr(JSONArray arr,Resource resource){
		List<Attribute> list = resource.getExtendAttrs();
		if(list != null){
			//清空再导入
			list.clear();
/*			int listSize = list.size();
			for(int j=0; listSize>j; j++){
				resource.delAttribute(list.get(0).getAttrName());
			}*/
		}
		for(int i=0; arr.length()>i; i++){
			JSONObject obj = arr.getJSONObject(i);
			Attribute attrObj = new Attribute();
			attrObj.setAttrName(obj.get("attrName").toString());
			attrObj.setAttrCName(obj.get("attrCName").toString());
			attrObj.setType(obj.get("type").toString());
			attrObj.setValue(obj.get("value"));
			resource.addAttribute(attrObj);
		}
		return resource;
	}
	
	/**
	 * 处理添加list类型字段；
	 * @author Liuzehang
	 * @param attr
	 * @return
	 */
	private List<String> handleAttrList(String attr){
		List<String> list = new ArrayList();
        JSONArray arr = new JSONArray(attr);
	    if(StringUtils.isNotEmpty(attr) && arr.length()>0) {
	        if(arr != null && arr.length() > 0 ) {
	            for(int i=0;i<arr.length();i++) {
	            	list.add(arr.getString(i));
	            }
	        }
	    }	
		return list;
	}
	
	/**
	 * 用于后台数据传到前端时转变成String类型并用分号隔开;
	 * @author Liuzehang
	 * @param list
	 * @return
	 */
	private String listChangeString(List<String> list){
		String targetString = "";
		if(list != null && list.size() > 0) {
			for(int i=0; list.size()>i; i++){
				if(i==0){
					targetString = targetString + list.get(i);
				}else{
					targetString = targetString + ";" +list.get(i);
				}
			}
		}
		return targetString;
	}
	
	
	private CookieStore cookieStore = null; 
	
	/**跳转到目录界面
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 * @throws ConnectorException 
	 */
	@RequestMapping("/showCatalogView")
	public ModelAndView showCatalogView(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("resourceId");
		String libNum = request.getParameter("libNum");
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/editCatalog");
		mv.addObject("resourceId", id);
		mv.addObject("libNum", libNum);
		return mv;
	}
	
	@RequestMapping("/updateCatalog")
	public ModelAndView updateCatalog(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String msg ="fail";
		String id = request.getParameter("resourceId");
		String catalogId = request.getParameter("catalogId");
		String libNum = request.getParameter("libNum");
		Resource resource = resourceService.findByIdAndCollectionName(id, libNum);
		try {
			if(resource != null){
	            List<String> catalogIds = new ArrayList<String>();
				List<String> catalogNames = new ArrayList<String>();
				resourceCatalogService.getCatalogInfos(catalogId, catalogIds, catalogNames);
				Collections.reverse(catalogIds);
				Collections.reverse(catalogNames);
				resource.setCatalogId(catalogId);
				resource.setCatalogIds(catalogIds);
				resource.setCatalogNames(catalogNames);
				resourceService.update(resource, libNum);
				msg = "success";
				final UserInfo userInfo = UserUtil.getCurrentUser(request);
				InformationVo info = new InformationVo();
				info.setId(userInfo.getId());
				info.setCreator(userInfo.getUsername());
				info.setTitle(resource.getTitle());
				info.setUrl(resource.getUrl());
				List<Attribute> attrList  = resource.getExtendAttrs();
				for(int i=0; attrList.size()>i; i++){
					String attrName = attrList.get(i).getAttrCName();
					String attrType  = attrList.get(i).getType();
					Object value = attrList.get(i).getValue();
					if(attrType.equals("int")  ){
						double attrValue = Double.parseDouble(value.toString());
						info.addAttribute(attrName, attrValue);
					}else if(attrType.equals("date")){
						String str = value.toString();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
						Date date;
						date = format.parse(str);
	
						info.addAttribute(attrName, date);
					}else if(attrType.equals("String") || attrType.equals("longtext")){
						String str = value.toString();
						info.addAttribute(attrName, str);
					}else{
						//TODO 数组
					}
				}
				asyncInfo.informationUpdated(info, "", "", "", "");
			}
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		result.put("msg", msg);
		WebUtil.outputHtml(response, result.toString());
		return null;
	}
	/**获取所属领域
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 * @throws ConnectorException 
	 */
	@RequestMapping("/getDomain")
	public ModelAndView getDomain(HttpServletRequest request, HttpServletResponse response) {
		HttpPost httpPost = null;
		try{
			String domainName = request.getParameter("domainName");
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("account", Constant.EXKE_ACCOUNT));
			parameters.add(new BasicNameValuePair("password", Constant.EXKE_PASSWORD));
			parameters.add(new BasicNameValuePair("mode", "client"));
			parameters.add(new BasicNameValuePair("domainName", domainName));
			
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String targetMothod = "/ExIAServer/services/ontology/search/domains";	
			httpPost = new HttpPost("http://" + Constant.ExIAServer_HOST
					+ ":" + Constant.ExIAServer_PORT
					+ targetMothod);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "GBK"));
	        CloseableHttpResponse responses = httpClient.execute(httpPost, context);
	        HttpEntity entity = responses.getEntity();
			String result = EntityUtils.toString(entity);
			JSONObject obj = new JSONObject(result);
			JSONArray arr = (JSONArray)obj.get("data");
			String targetString = "";
			for(int i=0; arr.length()>i; i++){
				if(i==0){
					targetString = targetString + arr.getJSONObject(i).get("content");
				}else{
					targetString = targetString + ";" +arr.getJSONObject(i).get("content");
				}
			}
			JSONObject jsonResult = new JSONObject();
			jsonResult.put("result", targetString);
			WebUtil.outputHtml(response, jsonResult.toString());
		}catch(Exception ex){
			
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
        return null;
    }
	
	//根据概念名查概念清单
	/**
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getConcepts.do")
	public ModelAndView getConcepts(HttpServletRequest request, HttpServletResponse response ){
		List<String> nzNames = new ArrayList<String>();
		nzNames.add("BELONG_DOMAIN_IDS");
		nzNames.add("BELONG_DOMAIN_NAMES");
		nzNames.add("MNG_DOMAIN_ID");
		nzNames.add("_ShowOrder");
		nzNames.add("ALIAS");
		nzNames.add("S_COMMON");
		nzNames.add("IS_COMMON");
		nzNames.add("BELONG_DOMAIN_OBJECT_IDS");
		HttpPost httpPost = null;
		try{
			JSONArray resultList = new JSONArray();
			String names = request.getParameter("names");
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("account", Constant.EXKE_ACCOUNT));
			parameters.add(new BasicNameValuePair("password", Constant.EXKE_PASSWORD));
			parameters.add(new BasicNameValuePair("mode", "client"));
			parameters.add(new BasicNameValuePair("conceptNames",names));
			
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String targetMothod = "/ExIAServer/services/ontology/search/conceptsByNames";	
			httpPost = new HttpPost("http://" + Constant.ExIAServer_HOST
					+ ":" + Constant.ExIAServer_PORT
					+ targetMothod);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "GBK"));
	        CloseableHttpResponse responses = httpClient.execute(httpPost, context);
	        HttpEntity entity = responses.getEntity();
			String result = EntityUtils.toString(entity);
			JSONObject obj = new JSONObject(result);
			JSONArray datas = (JSONArray) obj.get("data");
			for(int j=0; datas.length()>j; j++){
				JSONObject concept = datas.getJSONObject(j);
				System.out.println(concept.toString());
				JSONArray attrArray = (JSONArray) concept.get("attributes");
				if(attrArray != null && attrArray.length() > 0){
					for(int i=0;i<attrArray.length();i++) {
						JSONObject json = attrArray.getJSONObject(i);
						JSONObject attribiute = new JSONObject();
						if(json.has("name")) {
							attribiute.put("attrName", json.getString("name"));
							attribiute.put("attrCName", json.getString("name"));
						}
						if(json.has("dataType")) {
							attribiute.put("type", json.getString("dataType"));
						}
						if(json.has("value")) {
							attribiute.put("value", json.get("value"));
						}
						if(!json.getString("dataType").equals("concept") && !nzNames.contains(json.getString("name"))) {
							resultList.put(attribiute);
						}
					}
					
				}
			}
		WebUtil.outputHtml(response, resultList.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	
	
	/**
	 *  跳转到更新或新增页面；
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/showDetailView.do")
	public ModelAndView showUpdateView(HttpServletRequest request, HttpServletResponse response ){
		 List<String> granularityList = new ArrayList();
		 if(cacheMap.containsKey("lidu")) {
			 granularityList = (List<String>)cacheMap.get("lidu");
		 } else {
			 String[] conceptName = {"粒度"};
			 String jsonResult = ExkeOntologService.getConceptsByKeywords(conceptName);
			 JSONObject obj = new JSONObject(jsonResult);
			 String conceptId="";
			 JSONArray dataArr = null;
			 Object arr = obj.get("data");
			 if(arr != null){
			     dataArr = (JSONArray)obj.get("data");
		     }
			 if(dataArr != null){
				 JSONObject obj1 = (JSONObject)dataArr.get(0);
				 conceptId = obj1.getString("id");
			  }
			 String result = "";
			 if(StringUtils.isNotBlank(conceptId)){
		     	   JSONObject json = new JSONObject();
		     	   json.put("elementType","relation");
				   json.put("direction","outbound");
				   json.put("content", "外延");
		     		JSONArray conditions = new JSONArray();
					conditions.put(json);
					result = ExkeOntologService.getChildConceptsById(conceptId, conditions.toString(), 100);
			    }
				 JSONArray jsonArr = new JSONArray(result);
				 for(int i=1; jsonArr.length()>i; i++){
					 JSONObject jsonObj = jsonArr.getJSONObject(i);
		     		 JSONArray conceptsArr = (JSONArray)jsonObj.get("concepts");
		     		 JSONObject resultObj = (JSONObject) conceptsArr.get(1);
		     		 String name = resultObj.getString("content");
		     		granularityList.add(name);
				}
				if(granularityList != null && granularityList.size() > 0) {
					cacheMap.put("lidu", granularityList);
				}
		 }
		//该信息资源的Id
		String id = request.getParameter("id");
		//所属资源库编号；
		String libNum = request.getParameter("libNum");
		String catalogNum = request.getParameter("catalogNum");
		String catalogId = request.getParameter("catalogId");
		Resource resource = new Resource();
		resource.setStatus(EStatus.normal.getIndex());
		String belongDomain ="";
		String collectionName = "";
		String fileExt = "";
		ResourceLib lib = new ResourceLib();
		lib = resourceLibService.getByLibNum(libNum);
		if(StringUtils.isNotBlank(id)){
			collectionName = lib.getCollectionName();
			resource = resourceService.findByIdAndCollectionName(id, collectionName);
			if(resource.getFileExt()!=null){
				fileExt = resource.getFileExt();
			};
			List<String> bList = resource.getBelongDomain();
			if(bList !=null && bList.size()>0){
				belongDomain = listChangeString(bList);	
			}
			request.setAttribute("method", "update");
		} else {
			request.setAttribute("method", "create");
			List<Attribute> extengAttrs = lib.getExtendAttrs();
			request.setAttribute("extengAttrs", extengAttrs);
		}
		String formatTime="";
		String validTime="";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(resource.getPublishDate() != null){
			 formatTime  = dateFormat.format(resource.getPublishDate());
		}else{
			formatTime = dateFormat.format(new Date());
		}
		if(resource.getValidTime() != null){
			validTime  = dateFormat.format(resource.getValidTime());
			validTime.contains("2037");
			validTime ="永久";
		}
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/resourceDetail");
		if(StringUtils.isNotBlank(resource.getHtmlContent())){
			String htmlContent = resource.getHtmlContent().replace("'", "&#039;");
			resource.setHtmlContent(htmlContent);
		}
		mv.addObject("obj", resource);
		mv.addObject("lib", lib.getExtendAttrs());
		mv.addObject("publishDate", formatTime);
		mv.addObject("belongDomainList", belongDomain);
		mv.addObject("libNum", libNum);
		mv.addObject("catalogId",catalogId);
		mv.addObject("catalogNum", catalogNum);
		mv.addObject("granularityList", granularityList);
		mv.addObject("validTime", validTime);
		mv.addObject("fileExt", fileExt);
		return mv;
	}
	
	/**删除处理
	 * @author Liuzh
	 * @param request
	 * @param response
	 */
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request, HttpServletResponse response ){
		String ids = request.getParameter("ids");
		String libNum = request.getParameter("libNum");
		String collectionName = resourceLibService.getByLibNum(libNum).getCollectionName();
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(ids);
		String msg  = "success";
		for(int i=0; arr.length()>i; i++){
			//物理删除
			Resource resource = resourceService.findByIdAndCollectionName(arr.get(i).toString(), collectionName);
			if(resource != null){
				IaServiceClient.removeIndex(resource.getLibNum(), resource.getTitle(), resource.getId(), resource.getValidTime());
				resourceService.del(arr.get(i).toString(),collectionName);
				 List<String> catalogNames = resource.getCatalogNames();
				 String catalogNameStr ="";
				 for(int t=0;catalogNames.size()>t; t++){
					 if(t==0){
						 catalogNameStr = catalogNameStr + catalogNames.get(t);
					 }else {
						 catalogNameStr = catalogNameStr +"/"+ catalogNames.get(t);
					 }
				 }
				IaServiceClient.informationRemoved(arr.get(i).toString(), libNum, catalogNameStr);	
			}else{
				msg = "fail";
				break;
			}
		}
		json.put("result", msg);
		WebUtil.outputHtml(response, json.toString());
	}
	
	@RequestMapping("/getTree.do")
	public ModelAndView tree(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		String rootName  = request.getParameter("rootName");
		rootName = URLDecoder.decode(rootName, "UTF-8");
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/domainTreeByName");
		mv.addObject("rootName", rootName);
		return mv;
	}
	
	@RequestMapping("/getGenre.do")
	public ModelAndView getGenre(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		String rootName  = request.getParameter("rootName");
		rootName = URLDecoder.decode(rootName, "UTF-8");
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/genreTree");
		mv.addObject("rootName", rootName);
		return mv;
	}
	
	/**批量上传文件
	 * @author wangjg
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/uploadFile.do", method=RequestMethod.POST)
	public void uploadFile(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		ExecutorService  exec = null;//线程池,处理文件的批量上传
		final UserInfo userInfo = UserUtil.getCurrentUser(request);
		Map map  = WebUtil.requestToMap(request);
		final String libNum = MapUtils.getString(map, "libNum","");
		final String catalogNum = MapUtils.getString(map, "catalogNum","");
		final String catalogId = MapUtils.getString(map, "catalogId","");
		String fileName1 = MapUtils.getString(map, "fileName","");
		int fileNum = MapUtils.getInteger(map, "fileNum",0);
		fileName1 = URLDecoder.decode(fileName1, "UTF-8"); 
		String msg = "success";
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
			Iterator<String> fii = multipartRequest.getFileNames();
	        while (fii.hasNext()) {  
	        	String fileName = fii.next();     
	            MultipartFile file = multipartRequest.getFile(fileName);
	            if(file != null){
	            	uploadFileNameList.add(fileName1);
	            	uploadFileList.add(file);
	            }
	            if(fileNum == 1){
	            	exec = Executors.newCachedThreadPool();//创建一个无固定长度的线程池,处理文件的批量上传
	        		final int sleepMillers = 1000;//线程暂停时间1s
	            	for(int i=0;i<uploadFileList.size();i++){
	            		final MultipartFile mr = uploadFileList.get(i);
	            		final String fileName2 = uploadFileNameList.get(i);
	            		Runnable task = new Runnable() {
							public void run() {
								//为每个文件都创建一个线程单独执行
								saveResource(mr,userInfo,catalogNum,libNum,catalogId,fileName2);
							}
						};
						Thread.sleep(sleepMillers);
						exec.execute(task);
	    	         }
	            	}
	            }
		} catch (Exception e) {
			e.printStackTrace();
			msg = "fail";
		}finally{
			try {
				if(exec != null){
					exec.shutdown();
					while(true){  
			           if(exec.isTerminated()){  
			                System.out.println("所有的子线程都结束了！");
			                uploadFileList.clear();
			                uploadFileNameList.clear();
			                break;  
			            }  
			        }
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		WebUtil.outputHtml(response, msg);
	}
	
	/**上传文件的分析处理和保存
	 * @author wangjg
	 * @param request
	 * @param response
	 */
	private void saveResource(MultipartFile mr,UserInfo userInfo,String catalogNum,String libNum,String catalogId,String fileName){
		ResourceLib resourceLib = resourceLibService.getByLibNum(libNum);
		String collectionName = "";
		InputStream in = null;
		if(resourceLib != null){
			collectionName = resourceLib.getCollectionName();
        }
		try {
			in = mr.getInputStream();
			//String realFileName = new String(mr.getOriginalFilename().getBytes(), "UTF-8");//上传文件全名
			String realFileName = fileName;
            String fileExt = realFileName.substring(realFileName.lastIndexOf(".")+1);//后缀名
            String title = realFileName.substring(0,realFileName.lastIndexOf("."));//文件名
            //获取文件的正文内容(excel文件不会读取到内容)
            final String content = FileHandlerUtil.getText("."+fileExt, in);
            List<String> keyWordList = new ArrayList<String>();//关键词list
            List<String> belongDomainList = new ArrayList<String>();//所属领域list
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            final String publishDate = sdf.format(new Date());
            String keyWordsAndAbs = "";//提取关键词和摘要
            String abstractText = "";
            JSONArray keyWords = null;
            String gentagResult = "";//生成指定文本数据的标签
            long begin = System.currentTimeMillis();
            if(StringUtils.isNotBlank(content) && !StringUtils.equals(content, "null")){
                keyWordsAndAbs = IaServiceClient.absText("","",content,20,250);
                gentagResult = IaServiceClient.gentag("", content, true, true, "", publishDate);
            }
            
            long end = System.currentTimeMillis();
			System.out.println("uploadFile执行耗时:" + (end - begin) + "豪秒");
            if(StringUtils.isNotBlank(keyWordsAndAbs)){
            	JSONObject obj = new JSONObject(keyWordsAndAbs);
 	            if(obj.get("data") != null){
 	            	abstractText = obj.getJSONObject("data").getString("abstract");
 	            	keyWords = obj.getJSONObject("data").getJSONArray("keywords");
 	            }
 	            if(keyWords != null){
 	            	for(int j=0;j<keyWords.length();j++){
 	            		JSONObject obj2 = (JSONObject)keyWords.get(j);
 	            		String word = obj2.getString("word");
 	            		keyWordList.add(word);
 	            	}
 	            }
            }
            
            
            //将文件保存到mongoDB数据库
            String fileInfoId =resourceService.saveFileToMongo(mr,title);
            
            //根据上传文件创建FileInfo对象
            FileInfo fileInfo = new FileInfo();
            fileInfo.setTitle(title);
            fileInfo.setFileExt(fileExt);
            fileInfo.setId(fileInfoId);
            fileInfo.setCreateDate(new Date());
            fileInfo.setCreatorName(userInfo.getUsername());
            fileInfo.setFileSize(mr.getSize());
            fileInfo.setFileType(1);//表示正文
            fileInfo.setKeywords(keyWordList);
            List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
            fileInfoList.add(fileInfo);
            //根据上传文件创建资源对象
            Resource resource = new Resource();
            //处理文本数据标签
            if(StringUtils.isNotBlank(gentagResult)){
            	JSONObject resultObj = new JSONObject(gentagResult);
            	int status = resultObj.getInt("status");
            	Object data1 = resultObj.get("data");
            	if(status > 0){
            		JSONObject data = resultObj.getJSONObject("data");
            		resource.setTagByJsonObj(data);
            		Object obj4 = null;
            		if(StringUtils.contains(gentagResult, "domains")){
            			obj4 = data.get("domains");
            		}
            		 
            		if(obj4 != null){
            			JSONArray arr1 = (JSONArray)obj4;
            			for(int k=0;k<arr1.length();k++){
            				belongDomainList.add(arr1.getJSONObject(k).getString("content"));
            			}
            		}
            	}
            }
            resource.setDomainConfirmStatus(EStatus.draft.getIndex());
            resource.setCatalogNum(catalogNum);
            resource.setCatalogId(catalogId);
            List<String> catalogIds = new ArrayList<String>();
			List<String> catalogNames = new ArrayList<String>();
			resourceCatalogService.getCatalogInfos(catalogId, catalogIds, catalogNames);
			Collections.reverse(catalogIds);
			Collections.reverse(catalogNames);
			resource.setCatalogIds(catalogIds);
			resource.setAuthority(EStatus.normal.getIndex());
			resource.setCatalogNames(catalogNames);
            resource.setContent(content);
            resource.setCreateDate(new Date());
            resource.setCreatorId(userInfo.getId());
            resource.setCreatorName(userInfo.getUsername());
            resource.setFileExt(fileExt);
            resource.setLibNum(libNum);
            resource.setPublishDate(new Date());
            resource.setTitle(title);
            resource.setAbstractText(abstractText);
            resource.setFileInfos(fileInfoList);
            resource.setStatus(EStatus.normal.getIndex());
            resource.setBelongDomain(belongDomainList);
            resourceService.save(resource, collectionName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null)in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	/**跳转附件上传页面
	 * @author liuzehang
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/attachmentUpload.do")
	public ModelAndView showUploadView(HttpServletRequest request, HttpServletResponse response ){
		ModelAndView mv = new  ModelAndView("knowledgeLib/resourceLib/attachmentUpload");
		String resourceId = request.getParameter("resourceId");
		String libNum = request.getParameter("libNum");
		request.setAttribute("libNum", libNum);
		request.setAttribute("resourceId", resourceId);
		return mv;
	}
	
	/**上传
	 * @author liuzehang
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws CommonAppException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/upload.do", produces = "application/json; charset=utf-8")
	public void upload(HttpServletRequest request, HttpServletResponse response ) throws IllegalStateException, IOException, CommonAppException{
		UserInfo userInfo = UserUtil.getCurrentUser(request);
		String resourceId = request.getParameter("resourceId");
		String libNum = request.getParameter("libNum");
		String name =request.getParameter("fileName");
		name = URLDecoder.decode(name, "UTF-8"); 
        JSONArray arr = new JSONArray();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
		Iterator<String> fii = multipartRequest.getFileNames();
        while (fii.hasNext()) {  
        	String fileName = fii.next();     
            MultipartFile file = multipartRequest.getFile(fileName);
    		JSONObject jsObj = new JSONObject();
            InputStream in = null;
            in = file.getInputStream();
            String realFileName = name;//上传文件全名
            String fileExt = realFileName.substring(realFileName.lastIndexOf(".")+1,realFileName.length());//后缀名
            String title = realFileName.substring(0,realFileName.lastIndexOf("."));//文件名
            //获取文件的正文内容(excel文件不会读取到内容)
            final String content = FileHandlerUtil.getText("." +fileExt, in);
            List<String> keyWordList = new ArrayList<String>();//关键词list
            //List<String> belongDomainList = new ArrayList<String>();//所属领域list
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //final String publishDate = sdf.format(new Date());
            String keyWordsAndAbs = "";//提取关键词和摘要
            //String abstractText = "";
            JSONArray keyWords = null;
            long begin = System.currentTimeMillis();
            if(StringUtils.isNotBlank(content)){
                keyWordsAndAbs = IaServiceClient.absText("",title,content,20,250);
            }
            long end = System.currentTimeMillis();
    		System.out.println("uploadFile执行耗时:" + (end - begin) + "豪秒");
            if(StringUtils.isNotBlank(keyWordsAndAbs)){
            	JSONObject obj = new JSONObject(keyWordsAndAbs);
    	            if(obj.get("data") != null){
    	            	//abstractText = obj.getJSONObject("data").getString("abstract");
    	            	keyWords = obj.getJSONObject("data").getJSONArray("keywords");
    	            }
    	            if(keyWords != null){
    	            	for(int j=0;j<keyWords.length();j++){
    	            		JSONObject obj2 = (JSONObject)keyWords.get(j);
    	            		String word = obj2.getString("word");
    	            		keyWordList.add(word);
    	            	}
    	            }
            }
            //将文件保存到mongoDB数据库
            String fileInfoId =resourceService.saveFileToMongo(file,title);
            //根据上传文件创建FileInfo对象
            FileInfo fileInfo = new FileInfo();
            fileInfo.setTitle(title);
            fileInfo.setFileExt(fileExt);
            fileInfo.setId(fileInfoId);
            fileInfo.setCreateDate(new Date());
            fileInfo.setCreatorName(userInfo.getUsername());
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileType(2);//表示正文
            fileInfo.setKeywords(keyWordList);
            if(StringUtils.isNotEmpty(resourceId) && StringUtils.isNotEmpty(libNum)) {
            	ResourceLib resourceLib = resourceLibService.getByLibNum(libNum);
            	if(resourceLib != null) {
            		Resource resource = resourceService.findByIdAndCollectionName(resourceId, resourceLib.getCollectionName());
                	if(resource != null) {
                		resource.addFileInfo(fileInfo);
                		resourceService.update(resource, resourceLib.getCollectionName());
                	}
            	}
            }
            jsObj.put("fileInfo", fileInfo.toFormJson());
            arr.put(jsObj);
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json"); 
        PrintWriter out = response.getWriter(); 
        out.print(arr.toString());
        out.flush();
	}
 
    
    /**  
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception 
     * @throws Exception  
     */  
    @RequestMapping(value="/download")  
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	String arr = request.getParameter("arr");
		JSONArray jsonArr = new JSONArray(arr);
		if(jsonArr.length()>1){
			//批量处理
			       //在服务器端创建打包下载的临时文件
					String oapath = System.getProperty("oapath");
			        String outFilePath = oapath+File.separator+UUID.randomUUID().toString() + ".zip";
			        File file = new File(outFilePath);
			        //文件输出流
			        FileOutputStream outStream = new FileOutputStream(file);
			        //压缩流
			        ZipOutputStream toClient = new ZipOutputStream(outStream);
			for(int i=0; jsonArr.length()>i; i++){
				JSONObject obj = jsonArr.getJSONObject(i);
				String id = (String)obj.get("id");
				String ext = (String)obj.getString("ext");
				String fileName = (String)obj.getString("name");
				InputStream inputStream  = resourceService.getInputStreamById(id);
				zipFile(fileName,ext,inputStream,toClient);
			}	
			toClient.close();
			outStream.close();
			this.downloadZip(file, response);
		}else{
			//单独文件
			BufferedOutputStream out = null;
			try{
				for(int i=0; jsonArr.length()>i; i++){
					JSONObject obj = jsonArr.getJSONObject(i);
					String id = (String)obj.get("id");
					String ext = (String)obj.getString("ext");
					InputStream inputStream  = resourceService.getInputStreamById(id);
					String fileName = (String)obj.getString("name");
			        //转码，免得文件名中文乱码  
			        fileName = URLEncoder.encode(fileName,"UTF-8");  
			        //设置文件下载头  
			        response.addHeader("Content-Disposition", "attachment;filename=" + fileName+"."+ext);    
			        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
			        response.setContentType("multipart/form-data");
			        response.setHeader("Content-type", "application-download");
			        
			        out = new BufferedOutputStream(response.getOutputStream());  
			        int len = 0;  
			        while((len = inputStream.read()) != -1){  
			            out.write(len);  
			            out.flush();  
			        }   
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
    
    
    	/**
        * 下载打包的文件
        * @param file
        * @param response
        */
       public void downloadZip(File file,HttpServletResponse response) {
           try {
               // 以流的形式下载文件。
               BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
               byte[] buffer = new byte[fis.available()];
               fis.read(buffer);
               fis.close();
               // 清空response
               response.reset();
       
               OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
               response.setContentType("application/octet-stream");
               response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
               toClient.write(buffer);
               toClient.flush();
               toClient.close();
               file.delete();        //将生成的服务器端文件删除
            } 
            catch (IOException ex) {
               ex.printStackTrace();
          }
       }
    
    /**
       * 将文件写入到zip文件中
       * @param inputFile
        * @param outputstream
        * @throws Exception
        */
        public static void zipFile(String name, String ext,InputStream inputStream, ZipOutputStream outputstream) throws IOException,ServletException{
            try{
	              BufferedInputStream bInStream = new BufferedInputStream(inputStream);
	              ZipEntry entry = new ZipEntry(name+'.'+ext);
	              outputstream.putNextEntry(entry);
                  int nNumber;  
                  byte[] buffer = new byte[512];  
                  while ((nNumber = bInStream.read(buffer)) != -1) {  
                	  outputstream.write(buffer, 0, nNumber);  
                  }  
                  // 关闭创建的流对象     
                  bInStream.close();  
         }
         catch(IOException e)
         {
             throw e;
         }
    }

    
    /**
     * 文件删除
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/delFile")
    public void delFile(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
    	JSONObject json = new JSONObject();
    	String fileId = request.getParameter("fileId");
    	String resourceId = request.getParameter("resourceId");
    	String libNum = request.getParameter("libNum");
    	if(StringUtils.isNotEmpty(fileId) && StringUtils.isNotEmpty(resourceId) && StringUtils.isNotEmpty(libNum)) {
    		ResourceLib resourceLib = resourceLibService.getByLibNum(libNum);
    		if(resourceLib != null) {
    			Resource resource = resourceService.findByIdAndCollectionName(resourceId, resourceLib.getCollectionName());
    			List<FileInfo> list = resource.getFileInfos();
    			if(list != null && list.size() > 0) {
    				for (Iterator<FileInfo> it = list.iterator(); it.hasNext();) {
    					FileInfo fileInfo = it.next();
    					if(fileInfo.getId().equals(fileId)) {
    						resourceService.deleteFile(fileId);
    						it.remove(); 
    					}
    				}
    			}
    			resourceService.update(resource, resourceLib.getCollectionName());
    		}
    	}
    	json.put("result", true);
    	json.put("msg","操作成功!");
    	WebUtil.outputHtml(response, json.toString());
    }

   @RequestMapping(value="/searchAttr")
   public ModelAndView searchAttr(HttpServletRequest request , HttpServletResponse response){
	   ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/searchAttr");
	   return mv;
   }
   
   
   @SuppressWarnings("unchecked")
   @RequestMapping(value="/updateBelongDomain")
   public void updateBelongDomain(HttpServletRequest request , HttpServletResponse response) throws Exception{
	   Map map  = WebUtil.requestToMap(request);
	   String belongName = MapUtils.getString(map, "belongName","");//归属领域名字
	   String resourceIds = MapUtils.getString(map, "resourceIds","");//需要修改的数据id
	   String resourceTitles = MapUtils.getString(map, "resourceTitles","");//需要修改的数据标题
	   String validTimes = MapUtils.getString(map, "validTimes","");//需要修改的有效时间
	   String libNum = MapUtils.getString(map, "libNum","");//库名字
	   //IaServiceClient.updateIndex(libNum, resTitle, resId, expireDate, "update");
	   int num = 0;
	   String[] resourceIdsArr = StringUtils.split(resourceIds, ";");
	   String[] resourceTitlesArr = StringUtils.split(resourceTitles, ";");
	   String[] belongNameArr = StringUtils.split(belongName, ";");
	   String[] validTimesArr = StringUtils.split(validTimes, ";");
	   List<String> belongDomainList = new ArrayList<String>();
	   for(int j=0;j<belongNameArr.length;j++){
		   belongDomainList.add(belongNameArr[j]);
	   }
	   Calendar validDate = Calendar.getInstance();
	   for(int i=0;i<resourceIdsArr.length;i++){
		   Resource resource = resourceService.findByIdAndCollectionName(resourceIdsArr[i], libNum);
		   if(resource != null){
			   resource.setBelongDomain(belongDomainList);
			   resourceService.update(resource, libNum);
			   Date d;
			   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			   if(validTimesArr[i].equals("forever")){
				   //mysql time类型最大支持到2037年；
					validDate = new GregorianCalendar(2037,0,1,1,1,1);
					 String formatTime  = dateFormat.format(validDate.getTime());
					 d = dateFormat.parse(formatTime);	
			   }else{
				   d = dateFormat.parse(validTimesArr[i]);
			   }
			String result = IaServiceClient.updateIndex(libNum, resourceTitlesArr[i], resourceIdsArr[i], d, "update");
			   System.out.println(result);
			   num++;
		   }
	   }
	   WebUtil.outputHtml(response, String.valueOf(num));
   }
   
   /**浏览页面 不能编辑
    * @author Liuzehang
    * @param request
    * @param response
    * @return
    */
   @RequestMapping("/showDetail")
	public ModelAndView showDetail(HttpServletRequest request, HttpServletResponse response ){
		 List<String> granularityList = new ArrayList();
		 if(cacheMap.containsKey("lidu")) {
			 granularityList = (List<String>)cacheMap.get("lidu");
		 } else {
			 String[] conceptName = {"粒度"};
			 String jsonResult = ExkeOntologService.getConceptsByKeywords(conceptName);
			 JSONObject obj = new JSONObject(jsonResult);
			 String conceptId="";
			 JSONArray dataArr = null;
			 Object arr = obj.get("data");
			 if(arr != null){
			     dataArr = (JSONArray)obj.get("data");
		     }
			 if(dataArr != null){
				 JSONObject obj1 = (JSONObject)dataArr.get(0);
				 conceptId = obj1.getString("id");
			  }
			 String result = "";
			 if(StringUtils.isNotBlank(conceptId)){
		     	   JSONObject json = new JSONObject();
		     	   json.put("elementType","relation");
				   json.put("direction","outbound");
				   json.put("content", "外延");
		     		JSONArray conditions = new JSONArray();
					conditions.put(json);
					result = ExkeOntologService.getChildConceptsById(conceptId, conditions.toString(), 100);
			    }
				 JSONArray jsonArr = new JSONArray(result);
				 for(int i=1; jsonArr.length()>i; i++){
					 JSONObject jsonObj = jsonArr.getJSONObject(i);
		     		 JSONArray conceptsArr = (JSONArray)jsonObj.get("concepts");
		     		 JSONObject resultObj = (JSONObject) conceptsArr.get(1);
		     		 String name = resultObj.getString("content");
		     		granularityList.add(name);
				}
				if(granularityList != null && granularityList.size() > 0) {
					cacheMap.put("lidu", granularityList);
				}
		 }
		//该信息资源的Id
		String id = request.getParameter("id");
		//该信息资源的Url;
		String url = request.getParameter("url");
		if(StringUtils.isNotBlank(url)){
			try {
				url = URLDecoder.decode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		}
		//所属资源库编号；
		String libNum = request.getParameter("libNum");
		String catalogNum = request.getParameter("catalogNum");
		String catalogId = request.getParameter("catalogId");
		Resource resource = new Resource();
		resource.setStatus(EStatus.normal.getIndex());
		String belongDomain ="";
		String collectionName = "";
		String fileExt = "";
		ResourceLib lib = new ResourceLib();
		lib = resourceLibService.getByLibNum(libNum);
		collectionName = lib.getCollectionName();
		if(StringUtils.isNotBlank(id)){
			resource = resourceService.findByIdAndCollectionName(id, collectionName);	
		}else if(StringUtils.isNotBlank(url)){
			int start = url.indexOf("exck/");
			url  = url.substring(start+5);
			int end = url.indexOf("/");
			String libName = url.substring(0, end);
			if(lib != null){
				lib = resourceLibService.getByLibName(libName);				
			}
			resource = resourceService.findByUrlAndCollectionName(url, lib.getCollectionName());
		}
		if(resource.getFileExt()!=null){
			fileExt = resource.getFileExt();
		};
		List<String> bList = resource.getBelongDomain();
		if(bList !=null && bList.size()>0){
			belongDomain = listChangeString(bList);	
		}
		request.setAttribute("method", "update");

		String formatTime="";
		String validTime="";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(resource.getPublishDate() != null){
			 formatTime  = dateFormat.format(resource.getPublishDate());
		}else{
			formatTime = dateFormat.format(new Date());
		}
		if(resource.getValidTime() != null){
			validTime  = dateFormat.format(resource.getValidTime());
			validTime.contains("2037");
			validTime ="永久";
		}
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/showDetailView");
		if(StringUtils.isNotBlank(resource.getHtmlContent())){
			String htmlContent = resource.getHtmlContent().replace("'", "&#039;");
			resource.setHtmlContent(htmlContent);
		}
		mv.addObject("obj", resource);
		mv.addObject("lib", lib.getExtendAttrs());
		mv.addObject("publishDate", formatTime);
		mv.addObject("belongDomainList", belongDomain);
		mv.addObject("libNum", libNum);
		mv.addObject("catalogId",catalogId);
		mv.addObject("catalogNum", catalogNum);
		mv.addObject("granularityList", granularityList);
		mv.addObject("validTime", validTime);
		mv.addObject("fileExt", fileExt);
		return mv;
	}
}
