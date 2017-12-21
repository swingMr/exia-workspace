package com.excellence.iaserver.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.iamp.mongodb.service.ResourceService;
import com.excellence.iamp.mongodb.service.UserOpLogService;
import com.excellence.iamp.service.ExtractRuleService;
import com.excellence.iamp.util.ExtractRuleUtil;
import com.excellence.iamp.vo.ExtractRule;
import com.excellence.iaserver.common.util.Jwt;
import com.excellence.iaserver.common.util.UserOpLogUtil;
import com.excellence.iaserver.common.util.WebUtil;
import com.excellence.iaserver.service.RecogniseOntologyService;
import com.excellence.iaserver.service.SearchKnowledgeService;


/**
 * 识别知识概念控制器
 * @author chegnhq
 *
 */
@Controller
@RequestMapping(value = "/services/ontology/recognise")
public class RecogniseOntologyController {
	@Autowired
	private RecogniseOntologyService recogniseOntologyService;
	@Autowired
	private UserOpLogService userOpLogService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private SearchKnowledgeService searchKnowledgeService;
	@Autowired
	private ExtractRuleService extractRuleService;
	@Autowired
	private UserOpLogUtil userOpLogUtil;
	
	/**
	 * 根据文本输入获取五要素
	 * @param request
	 * @param response
	 * @param params extoken:访问服务的令牌   text:词句   clsNames:限定的要素类型（为空时，不限定）
	 * @return
	 */
	@RequestMapping(value = "/concepts")
	public String getConcepts(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, Object> params) {
		long startTime = System.currentTimeMillis();
		
		Map map = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(map, "extoken","");
		//输入的标题
		String title = MapUtils.getString(map, "title");
		//输入的主题词
		String keyWord = MapUtils.getString(map, "keyWord");
		//输入的文本
		String text = MapUtils.getString(map, "text");
		//要素类型
		String clsNames = MapUtils.getString(map, "clsNames");
		String context = MapUtils.getString(params, "context");
		String appCode = "";
		JSONObject textObj = new JSONObject();
		textObj.put("title", title);
		textObj.put("keyWord", keyWord);
		textObj.put("text", text);
		textObj.put("clsNames", clsNames);
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess){
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			appCode = resultObj.getString("appCode");
			String appName = resultObj.getString("appName");
			String appPwd = resultObj.getString("appPwd");
			if(StringUtils.isNotBlank(context)){
				userOpLogUtil.recordOpLog(context,appCode,appName,"XW014","本体识别",textObj.toString(),title);
			}
		}
		
		/*clsNames = new String[]{"空间","领域","时间"};
		
		text="广州市城乡总体规划书(2011-2020)";*/
		
		JSONObject resultObj = new JSONObject();
		JSONObject dataArr = new JSONObject();
		int status = 1;
		String msg = "success";
		
		if(StringUtils.isNotEmpty(title)){
			try {
				dataArr = recogniseOntologyService.recognizeConceptsOfTextSec(appCode,context,title, keyWord, text, clsNames);
//				dataArr = new JSONArray(dataList);
				
			} catch (Exception e) {
				e.printStackTrace();
				status=0;
				msg="程序发生异常："+e.getMessage();
			}
		}
		resultObj.put("status", status);
		resultObj.put("msg", msg);
		resultObj.put("data", dataArr);
		resultObj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputPlain(response, resultObj.toString());
		
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("警告:/services/ontology/recognise/concepts  耗时"+t+" 毫秒");
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/docIndex")
	public String getDocIndexes(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		JSONObject resultObj = new JSONObject();
		int status = 0;
		String msg = "";
		Map map = WebUtil.requestToMap(request);
		//extoken
		String extoken = MapUtils.getString(map, "extoken");
		//输入的主题词
		String context = MapUtils.getString(map, "context");
		//输入的文本
		String text = MapUtils.getString(map, "text");
		//要素类型
		String wordClazz = MapUtils.getString(map, "wordClazz", "ia-link");
		
		String sectionClazz = MapUtils.getString(map, "sectionClazz","ia-section");
		
		String sections = MapUtils.getString(map, "sections");
		
		if(StringUtils.isEmpty(wordClazz)) {
			wordClazz = "ia-link";
		}
		if(StringUtils.isEmpty(sectionClazz)) {
			sectionClazz = "ia-section";
		}
		String words = MapUtils.getString(map, "words");
		if(StringUtils.isNotEmpty(text)){
			if(StringUtils.isNotBlank(sections)) {
				text = recogniseOntologyService.getSectionIndexes(text, sectionClazz, sections);
			}
			try {
				text = recogniseOntologyService.getDocIndexes(text, wordClazz, words);
				resultObj.put("data", text);
				status = 1;
			} catch (Exception e) {
				e.printStackTrace();
				status=0;
				msg="程序发生异常："+e.getMessage();
			}
		} else {
			status = -1;
			msg = "输入文本不能为空！";
		}
		resultObj.put("status", status);
		resultObj.put("msg", msg);
		resultObj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputPlain(response, resultObj.toString());
		
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("警告:/services/ontology/recognise/docIndex  耗时"+t+" 毫秒");
		}
		return null;
	}
	
	/**
	 * 从文件中提取信息：从DOC、PDF、HTML、TXT文件提取结构化数据
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fileExtract")
	public ModelAndView fileExtract(MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		long startTime = System.currentTimeMillis();
		JSONObject resultObj = new JSONObject();
		int status = 0;
		String msg = "";
		JSONObject obj;
		InputStream in = null;
		try {
			Map map = WebUtil.requestToMap(request);
			String genre = MapUtils.getString(map,"genre");
			String fileType = MapUtils.getString(map,"fileType");
			obj = new JSONObject();
			ExtractRule extractRule = extractRuleService.findByGenreAndFiletype(genre, fileType);
			String str = extractRule.getRuleContent();
			JSONArray rules = new JSONArray();
			if(StringUtils.isNotEmpty(str)) {
				rules = new JSONArray(str);
			}
			JSONArray data = new JSONArray();
			//创建一个临时文件。
			String type = file.getOriginalFilename();
			in = file.getInputStream();
			type = type.substring(type.lastIndexOf(".")+1, type.length());
			data = ExtractRuleUtil.extractInfo(in, rules, type);
			msg = "提取数据成功！";
			status = 1;
			resultObj.put("data", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = -1;
			msg = "提取数据失败！";
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        resultObj.put("status", status);
		resultObj.put("msg", msg);
		resultObj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
        WebUtil.outputHtml(response, resultObj.toString());
        return null; 
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/textExtract")
	public ModelAndView textExtract(HttpServletRequest request, HttpServletResponse response){
		long startTime = System.currentTimeMillis();
		JSONObject resultObj = new JSONObject();
		int status = 0;
		String msg = "";
		try {
			Map map = WebUtil.requestToMap(request);
			String genre = MapUtils.getString(map,"genre");
			String fileType = MapUtils.getString(map,"fileType");
			String text = MapUtils.getString(map,"text");
			ExtractRule extractRule = extractRuleService.findByGenreAndFiletype(genre, fileType);
			String str = extractRule.getRuleContent();
			JSONArray rules = new JSONArray();
			if(StringUtils.isNotEmpty(str)) {
				rules = new JSONArray(str);
			}
			JSONArray data = new JSONArray();
			//创建一个临时文件。
			data = ExtractRuleUtil.extractInfo(text, rules);
			msg = "提取数据成功！";
			status = 1;
			resultObj.put("data", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = -1;
			msg = "提取数据失败！";
		} 
        resultObj.put("status", status);
		resultObj.put("msg", msg);
		resultObj.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
        WebUtil.outputHtml(response, resultObj.toString());
        return null; 
	}
}
