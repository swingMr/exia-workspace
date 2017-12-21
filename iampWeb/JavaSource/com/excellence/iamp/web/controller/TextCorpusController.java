package com.excellence.iamp.web.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.excellence.common.UserInfo;
import com.excellence.common.util.GUIDGenerator;
import com.excellence.iacommon.common.util.ExcelHandlerUtil;
import com.excellence.iacommon.common.util.T;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.service.TextCorpusService;
import com.excellence.iamp.vo.TextCorpus;
import com.excellence.iamp.vo.enums.EStatus;
import com.excellence.iamp.vo.excel.TextCorpusExcelVo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/textcorpus")
public class TextCorpusController {
	
	@Autowired
	private TextCorpusService textCorpusService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list.do")
	public String getList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String corpusTitle = MapUtils.getString(map, "corpusTitle");
		String belongDomain = MapUtils.getString(map, "belongDomain");
		String keyWord = MapUtils.getString(map, "keyWord");
		String orderBy = MapUtils.getString(map, "orderBy","createDate");
		String orderDirection = MapUtils.getString(map, "orderDirection","desc");
		Map paramMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(corpusTitle)) {
			paramMap.put("corpusTitle", corpusTitle);
			request.setAttribute("corpusTitle", corpusTitle);
		}
		if(StringUtils.isNotEmpty(belongDomain)) {
			paramMap.put("belongDomain", belongDomain);
			request.setAttribute("belongDomain", belongDomain);
		}
		if(StringUtils.isNotEmpty(keyWord)) {
			paramMap.put("keyWord", keyWord);
			request.setAttribute("keyWord", keyWord);
		}
		paramMap.put("status", EStatus.normal.getIndex());
		paramMap.put("orderBy", orderBy);
		paramMap.put("orderDirection", orderDirection);
		PageInfo<TextCorpus> page = textCorpusService.page(paramMap, pageNo, pageSize);
		request.setAttribute("page", page);
		return "/knowledgeLib/textcorpus/list";
	 }
	 
	 @RequestMapping(value="/create.do", method=RequestMethod.GET)
	 public String showCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 request.setAttribute("method", "create");
	     return "/knowledgeLib/textcorpus/detail";
    }
	 
	 /**
	  * 创建
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author huangjinyuan, 2014年8月29日.<br>
	  */
	 @SuppressWarnings({ "rawtypes"})
	 @RequestMapping(value="/create.do", method=RequestMethod.POST)
	 public String create(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		boolean result = true;
		String msg = "保存成功";
		Map map  = WebUtil.requestToMap(request);
		try{
			UserInfo user = UserUtil.getCurrentUser(request);
			String corpusTitle = MapUtils.getString(map, "corpusTitle");
			String keyWord = MapUtils.getString(map, "keyWord");
			String belongDomain = MapUtils.getString(map, "belongDomain");
			String oapath =System.getProperty("oapath");
			String relativePath = File.separator+"exiaserver"+File.separator+"data_dig"+File.separator+"expy"+File.separator+"custom" + File.separator+"digfile"+File.separator;
			String absolutePath = oapath + relativePath;
			File dir = new File(absolutePath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			String contentType = file.getContentType();
			String fileName = file.getOriginalFilename();
			int a= fileName.lastIndexOf(".");
			String fileExt = fileName.substring(a+1,fileName.length());
			String dname = T.format(T.getNow(), "yyyyMMddHHmmss") + "." + fileExt;
			String absoluteFilePath = absolutePath + File.separator + dname;
			File newFile=new File(absoluteFilePath);
			file.transferTo(newFile);
			TextCorpus textCorpus = new TextCorpus();
			textCorpus.setCorpusTitle(corpusTitle);
			textCorpus.setKeyWord(keyWord);
			textCorpus.setBelongDomain(belongDomain);
			textCorpus.setStatus(EStatus.normal.getIndex());
			textCorpus.setCreatorId(user.getId());
			textCorpus.setCreatorName(user.getUsername());
			textCorpus.setCreateDate(T.getNow());
			String accessPath = "file://{oapath}" + relativePath + File.separator + dname;
			textCorpus.setAccessPath(accessPath);
			textCorpus.setUpdateDate(T.getNow());
			textCorpusService.create(textCorpus);
		} catch (Exception e) {
			result = false;
			msg = e.getMessage();
			e.printStackTrace();
		}
		json.put("result", result);
		json.put("msg", msg);
        WebUtil.outputHtml(response, json.toString());
        return null;
     }
	 
	 @RequestMapping(value="/update.do", method=RequestMethod.GET)
	 public String showUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String corpusId = request.getParameter("corpusId");
		 if(StringUtils.isNotEmpty(corpusId)) {
			 TextCorpus textCorpus = textCorpusService.findById(corpusId);
			 request.setAttribute("corpus", textCorpus);
		 }
		 request.setAttribute("method", "update");
	     return "/knowledgeLib/textcorpus/detail";
     }
	 
	 @SuppressWarnings("rawtypes")
	 @RequestMapping(value="/update.do", method=RequestMethod.POST)
	 public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 JSONObject json = new JSONObject();
	     boolean result = true;
		 String msg = "保存成功";
		 Map map  = WebUtil.requestToMap(request);
		 String corpusId = MapUtils.getString(map, "corpusId");
		 String corpusTitle = MapUtils.getString(map, "corpusTitle");
		 String keyWord = MapUtils.getString(map, "keyWord");
		 String belongDomain = MapUtils.getString(map, "belongDomain");
		 String accessPath = null;
		 if (request instanceof MultipartHttpServletRequest) {
			 	MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			 	MultipartFile file = multiRequest.getFile("file");
			 	if(file != null) {
					String oapath =System.getProperty("oapath");
					String relativePath = File.separator+"exiaserver"+File.separator+"data_dig"+File.separator+"expy"+File.separator+"custom" + File.separator+"digfile"+File.separator;
					String absolutePath = oapath + relativePath;
					File dir = new File(absolutePath);
					if(!dir.exists()) {
						dir.mkdirs();
					}
					String fileName = file.getOriginalFilename();
					int a= fileName.lastIndexOf(".");
					String fileExt = fileName.substring(a+1,fileName.length());
					String dname = T.format(T.getNow(), "yyyyMMddHHmmss") + "." + fileExt;
					String absoluteFilePath = absolutePath + File.separator + dname;
					File newFile=new File(absoluteFilePath);
					file.transferTo(newFile);
					accessPath = "file://{oapath}" + relativePath + File.separator + dname;
				}
		    } 
	 	TextCorpus textCorpus = textCorpusService.findById(corpusId);
		textCorpus.setCorpusTitle(corpusTitle);
		textCorpus.setKeyWord(keyWord);
		textCorpus.setBelongDomain(belongDomain);
		textCorpus.setCreateDate(T.getNow());
		if(StringUtils.isNotEmpty(accessPath)) {
			textCorpus.setAccessPath(accessPath);
		}
		textCorpus.setUpdateDate(T.getNow());
		textCorpusService.update(textCorpus);
		json.put("result", result);
		json.put("msg", msg);
        WebUtil.outputHtml(response, json.toString());
        return null;
     }
	 
	 @RequestMapping(value="/delete.do")
	 public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String corpusId = request.getParameter("corpusId");
        TextCorpus textCorpus = textCorpusService.findById(corpusId);
        if(textCorpus != null) {
        	textCorpusService.delete(textCorpus);
        }
        JSONObject json = new JSONObject();
        boolean result = true;
		String msg = "删除成功";
		json.put("result", result);
		json.put("msg", msg);
		WebUtil.outputHtml(response, json.toString());
        return null;
    }
	 
	 /**
	  * 根据导入的excel创建文本语料
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author wangjg	
	  * @date 2017-7-25
	  */
	 @SuppressWarnings({ "rawtypes"})
	 @RequestMapping(value="/createCorpus.do", method=RequestMethod.POST)
	 public String createCorpus(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map map  = WebUtil.requestToMap(request);
		String belongDomain = MapUtils.getString(map, "belongDomain");
		String excelFileName = "";
		InputStream in = null;
		List<Object> corpusList = new ArrayList<Object>();
		try{
			UserInfo user = UserUtil.getCurrentUser(request);
			if(file != null){
				in = file.getInputStream();
				excelFileName =file.getOriginalFilename();
			}
			TextCorpusExcelVo vo = new TextCorpusExcelVo();
			long begin = System.currentTimeMillis();   
			corpusList = ExcelHandlerUtil.importDataFromExcel(vo, in, excelFileName);
			List<TextCorpus> textCorpusList = new ArrayList<TextCorpus>();
			for(int i=0;i<corpusList.size();i++){
				TextCorpus textCorpus = new TextCorpus();
				TextCorpusExcelVo textCorpusExcelVo = (TextCorpusExcelVo)corpusList.get(i);
				if(StringUtils.isEmpty(textCorpus.getCorpusId())) {
					textCorpus.setCorpusId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
				}
				textCorpus.setCorpusTitle(textCorpusExcelVo.getCorpusTitle());
				textCorpus.setBelongDomain(belongDomain);
				textCorpus.setCreatorId(user.getId());
				textCorpus.setKeyWord("");
				textCorpus.setAccessPath("");
				textCorpus.setCreatorName(user.getUsername());
				textCorpus.setStatus(EStatus.normal.getIndex());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    Date date = null;
			    // 设置日期转化成功标识
			    boolean dateflag=true;
		    	if(StringUtils.isNotBlank(textCorpusExcelVo.getCreateDate())){
		    		try {
		    			date = format.parse(textCorpusExcelVo.getCreateDate());
					} catch (Exception e) {
						dateflag=false;
					}
		    	} else {
		    		date = T.getNow();
		    	}
		    	if(dateflag){
		    		textCorpus.setCreateDate(date);
					textCorpus.setUpdateDate(date);
		    	}
		    	textCorpusList.add(textCorpus);
		    	if(i % 10000 == 0){
		    		textCorpusService.batchInsert(textCorpusList);
		    		textCorpusList.clear();
		    	}
			}
			textCorpusService.batchInsert(textCorpusList);
			long end = System.currentTimeMillis();
			System.out.println("createCorpus执行耗时:" + (end - begin) + "豪秒");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try{ in.close(); }catch(Exception ex){ ex.printStackTrace(); }
			}
		}
		Integer addNum = corpusList.size();
        WebUtil.outputHtml(response, addNum.toString());
        return null;
     }
	 
	 
	 /**
	  * 根据导入的txt文件创建文本语料
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author wangjg	
	  * @date 2017-7-25
	  */
	 @SuppressWarnings({ "rawtypes"})
	 @RequestMapping(value="/createCorpusByTxt.do", method=RequestMethod.POST)
	 public String createCorpusByTxt(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map map  = WebUtil.requestToMap(request);
		String belongDomain = MapUtils.getString(map, "belongDomain");
		InputStream in = null;
		List<TextCorpus> textCorpusList = new ArrayList<TextCorpus>();
		BufferedReader br=null;
		try{
			UserInfo user = UserUtil.getCurrentUser(request);
			if(file != null){
				in = file.getInputStream();
			}
			br = new BufferedReader(new InputStreamReader(in,  "utf-8"));  
			String temp = null;
			while(StringUtils.isNotBlank(br.readLine())){
				temp = br.readLine().trim();
				TextCorpus textCorpus = new TextCorpus();
				if(StringUtils.isEmpty(textCorpus.getCorpusId())) {
					textCorpus.setCorpusId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
				}
				textCorpus.setCorpusTitle(temp);
				textCorpus.setBelongDomain(belongDomain);
				textCorpus.setCreatorId(user.getId());
				textCorpus.setKeyWord("");
				textCorpus.setAccessPath("");
				textCorpus.setCreatorName(user.getUsername());
				textCorpus.setStatus(EStatus.normal.getIndex());
			    Date date = new Date();
			    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		    	textCorpus.setCreateDate(sqlDate);
				textCorpus.setUpdateDate(sqlDate);
				textCorpusList.add(textCorpus);
	        } 
			textCorpusService.batchInsert(textCorpusList);
			
		} catch (Exception e) {
			textCorpusList.clear();
			e.printStackTrace();
		}finally{
			if(in!=null){
				try{ in.close(); }catch(Exception ex){ ex.printStackTrace(); }
			}
		}
		Integer addNum = textCorpusList.size();
        WebUtil.outputHtml(response, addNum.toString());
        return null;
     }
	 
	 /**
	  * 导出文本语料excel
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author wangjg	
	  * @throws FileNotFoundException 
	  * @date 2017-7-25
	  */
	 @SuppressWarnings({ "rawtypes"})
	 @RequestMapping(value="/exportTextCorpus.do", method=RequestMethod.POST)
	 public void exportTextCorpus(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String corpusTitle = MapUtils.getString(map, "corpusTitle");
		String belongDomain = MapUtils.getString(map, "belongDomain");
		String keyWord = MapUtils.getString(map, "keyWord");
		String orderBy = MapUtils.getString(map, "orderBy","createDate");
		String orderDirection = MapUtils.getString(map, "orderDirection","desc");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(corpusTitle)) {
			paramMap.put("corpusTitle", corpusTitle);
			request.setAttribute("corpusTitle", corpusTitle);
		}
		if(StringUtils.isNotEmpty(belongDomain)) {
			paramMap.put("belongDomain", belongDomain);
			request.setAttribute("belongDomain", belongDomain);
		}
		if(StringUtils.isNotEmpty(keyWord)) {
			paramMap.put("keyWord", keyWord);
			request.setAttribute("keyWord", keyWord);
		}
		paramMap.put("status", EStatus.normal.getIndex());
		paramMap.put("orderBy", orderBy);
		paramMap.put("orderDirection", orderDirection);
		PageInfo<TextCorpus> page = textCorpusService.page(paramMap, pageNo, pageSize);
		List<Object> list = new ArrayList<Object>();//需要导出的文本语料list
		for(int i=0;i<page.getList().size();i++){
			TextCorpusExcelVo textCorpusExcelVo = new TextCorpusExcelVo();
			textCorpusExcelVo.setCorpusTitle(page.getList().get(i).getCorpusTitle());
			textCorpusExcelVo.setCreateDate(T.format(page.getList().get(i).getCreateDate()));
			list.add(textCorpusExcelVo);
		}
		
		
		String[] headers = new String[]{"标题","发布时间(yyyy-MM-dd)","来源地址"};//表格的表头
		String title = "分类训练语料采集模板.xls";//表格的标题
		HSSFWorkbook workbook = new HSSFWorkbook();//创建一个新表格
		workbook = ExcelHandlerUtil.exportDataToExcel(list, headers, title);
		try {
			response.reset();
			response.setContentType("multipart/form-data"); //自动识别
			response.setHeader("Content-Disposition", "attachment;filename="
			          + new String((title + ".xls").getBytes(), "iso-8859-1"));
            //文件流输出到response里
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
        	System.out.println("#Error ["+e.getMessage()+"] ");
        }
     }

}
