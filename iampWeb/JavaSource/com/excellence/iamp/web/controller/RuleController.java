package com.excellence.iamp.web.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.excellence.common.UserInfo;
import com.excellence.exke.common.vo.ConceptVo;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.IaServiceClient;
import com.excellence.iacommon.common.util.T;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.service.AppMemberService;
import com.excellence.iamp.service.AppService;
import com.excellence.iamp.service.ExtractRuleService;
import com.excellence.iamp.service.MemberService;
import com.excellence.iamp.util.ExtractRuleUtil;
import com.excellence.iamp.util.Jwt;
import com.excellence.iamp.util.MD5Util;
import com.excellence.iamp.vo.App;
import com.excellence.iamp.vo.AppMember;
import com.excellence.iamp.vo.ExtractRule;
import com.excellence.iamp.vo.enums.EStatus;
import com.excellence.iamp.vo.Member;
import com.excellence.iamp.vo.TextCorpus;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/extractRule")
public class RuleController {
	
	@Autowired
	private ExtractRuleService extractRuleService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list")
	public String getExtractRules(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String[] extractFileType = Constant.EXTRATC_FILE_TYPE;
		List<String> typeList = new ArrayList<String>();
		for (String type : extractFileType) {
			typeList.add(type);
		}
		request.setAttribute("typeList", typeList);
		request.setAttribute("type", "DOC/DOCX");
		return "/extractRule/extractRuleList";
	}
	
	@RequestMapping("/selectFile")
	public String toSelectFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");
		Map map  = WebUtil.requestToMap(request);
		String genreId = MapUtils.getString(map, "genreId");
		String genreName = MapUtils.getString(map, "genreName");
			genreName = URLDecoder.decode(genreName,"UTF-8");
			genreName = URLDecoder.decode(genreName,"UTF-8");
		request.setAttribute("genreId", genreId);
		request.setAttribute("genreName", genreName);
		return "/extractRule/uploadTemplate";
	}
	
	/**
	 * 获取本体库体裁
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getChildGenre")
	public JSONArray getChildGenre(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String genreName = request.getParameter("name");
		String pid = request.getParameter("id");
//		domainName = new String(domainName.getBytes(),"utf-8");
		JSONArray finalArr = new JSONArray();
		
		if("体裁".equals(genreName)){
			ConceptVo rootGenre = IaServiceClient.getConceptByKeyword(genreName);
			if(rootGenre!=null) {
				pid = rootGenre.getId();
			}
			genreName = "";
		}
		
		
		List<ConceptVo> childGenre = IaServiceClient.getExtensionalConcepts(pid, "clazz", 100);
		if(childGenre != null && childGenre.size()>0){
			for(ConceptVo genreDef:childGenre){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", genreDef.getId());
				jsonObj.put("name", genreDef.getContent());
				jsonObj.put("genreId", genreDef.getId());
//				jsonObj.put("iconCls", "tree-domain");
				jsonObj.put("isParent", true);
				finalArr.put(jsonObj);
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(finalArr.toString());
		return null;
	}
	
	 /**
	  * 上传文件识别规则
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception
	  * @author liup, 2017年9月14日.
	  */
	 @SuppressWarnings({ "rawtypesq"})
	 @RequestMapping(value="/extractInfo", method=RequestMethod.POST)
	 public String create(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		boolean result = true;
		String msg = "保存成功";
		Map map  = WebUtil.requestToMap(request);
		String fileType = MapUtils.getString(map, "fileType");
		String genreId = MapUtils.getString(map, "genreId");
		String genreName = MapUtils.getString(map, "genreName");
		ConceptVo genre = IaServiceClient.getConcept(genreId);
		InputStream in = null;
		try{
			if(genre!=null) {
				genreName = genre.getContent();
			}
			UserInfo user = UserUtil.getCurrentUser(request);
			String fileName = file.getOriginalFilename();
			String type = fileName.substring(fileName.lastIndexOf(".")+1);
			in = file.getInputStream();
			JSONArray arr = ExtractRuleUtil.extractRule(in, type);
			json.put("arr", arr);
			ExtractRule rule = extractRuleService.findByGenreAndFiletype(genreName, fileType);
			if(rule!=null) {
				rule.setRuleContent(arr.toString());
				rule.setCreatorId(user.getId());
				rule.setCreatorName(user.getUsername());
				rule = extractRuleService.updateExtractRule(rule);
			} else {
				rule = new ExtractRule();
				rule.setGenre(genreName);
				rule.setFileType(fileType);
				rule.setRuleContent(arr.toString());
				rule.setCreatorId(user.getId());
				rule.setCreatorName(user.getUsername());
				rule = extractRuleService.createExtractRule(rule);
			}
			
		} catch (Exception e) {
			result = false;
			msg = e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		json.put("result", result);
		json.put("msg", msg);
        WebUtil.outputHtml(response, json.toString());
        return null;
    }
	 
	 /**
	 * 获取本体库体裁
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRulesByGenre")
	public JSONArray getRulesByGenre(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		boolean result = false;
		try {
			Map map  = WebUtil.requestToMap(request);
			String genreId = MapUtils.getString(map, "genreId");
			String genreName = MapUtils.getString(map, "genre");
			String fileType = MapUtils.getString(map, "fileType");
			/*ConceptVo genre = IaServiceClient.getConcept(genreId);
			
			String genreName = "";
			if(genre!=null) {
				genreName = genre.getContent();
			}*/
			ExtractRule extractRule = extractRuleService.findByGenreAndFiletype(genreName, fileType);
			if(extractRule!=null) {
				json.put("ruleId", extractRule.getRuleId());
				String ruleStr = extractRule.getRuleContent();
				if(StringUtils.isNotEmpty(ruleStr)) {
					JSONArray ruleArr = new JSONArray(ruleStr);
					if(ruleArr!=null) {
						json.put("ruleArr", ruleArr);
					}
				}
			}
			result = true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json.put("result", result);
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**
	 * 修改保存
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public JSONArray saveExtractRule(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		boolean result = false;
		try {
			Map map  = WebUtil.requestToMap(request);
			UserInfo user = UserUtil.getCurrentUser(request);
			String genreId = MapUtils.getString(map, "genreId");
			String genreName = MapUtils.getString(map, "genreName");
			String fileType = MapUtils.getString(map, "fileType");
			String ruleArr = MapUtils.getString(map, "ruleArr");
			
			ExtractRule extractRule = extractRuleService.findByGenreAndFiletype(genreName, fileType);
			if(extractRule!=null) {
				extractRule.setRuleContent(ruleArr);
				extractRuleService.updateExtractRule(extractRule);
			} else {
				extractRule = new ExtractRule();
				extractRule.setGenre(genreName);
				extractRule.setFileType(fileType);
				extractRule.setRuleContent(ruleArr);
				extractRule.setCreatorId(user.getId());
				extractRule.setCreatorName(user.getUsername());
				extractRuleService.createExtractRule(extractRule);
			}
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json.put("result", result);
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
}
