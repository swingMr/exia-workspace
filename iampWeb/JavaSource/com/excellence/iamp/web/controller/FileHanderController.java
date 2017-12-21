package com.excellence.iamp.web.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.excellence.common.UserInfo;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.FileHandlerUtil;
import com.excellence.iacommon.common.util.HttpClientUtil;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;

@Controller
@RequestMapping("/fileHander")
public class FileHanderController {
	/**
	 * 功能:中文文本分词
	 * @param extoken			用户token
	 * @param text				文本内容
	 * @param tagPart			
	 * @return
	 */
	
	@RequestMapping(value="/participle.do", method=RequestMethod.POST)
    public  static String participle(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {  
		String fileName = "";//上传文件名字
		InputStream in = null;
		String fileExt = "";//文件后缀
		String fileText = "";//文件的文本内容
		String participleResult = "";//中文分词结果
		JSONObject result = new JSONObject();
		Map map  = WebUtil.requestToMap(request);
		String tagPart = MapUtils.getString(map, "tagPart");
		StringBuilder wordResult = new StringBuilder();
		StringBuilder wordTagResult = new StringBuilder();
		try {
			UserInfo user = UserUtil.getCurrentUser(request);
			if(file != null){
				in = file.getInputStream();
				fileName =file.getOriginalFilename();
				int a= fileName.lastIndexOf(".");
				fileExt = fileName.substring(a,fileName.length());
			}
			fileText = FileHandlerUtil.getText(fileExt, in);
			//开始调用中文分词接口进行分词
			String url = "http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/calculate/participle.do";//接口地址
			Map<String,Object> paramMap = new HashMap<String,Object>();//接口参数
			paramMap.put("extoken", "");
			paramMap.put("text", fileText);
			paramMap.put("tagPart", "true");
			participleResult = HttpClientUtil.getHttpClientResult(url, paramMap);//调用httpclient服务获取分词结果
			JSONObject participleJson = new JSONObject(participleResult);
			Object data = participleJson.get("data");
			if(data != null){
				JSONArray participleArr = (JSONArray)data;
				for(int i=0;i<participleArr.length();i++){
					JSONObject obj = (JSONObject)participleArr.get(i);
					String word = obj.getString("word");
					Object wordTagObj = obj.get("part");
					Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			        Matcher m = p.matcher(word);
					if(m.find()){
						if(wordTagObj != null){
							JSONArray wordTagArr = (JSONArray)wordTagObj;
							if(wordTagResult.length() > 0){
								wordTagResult.append("，");
							}
							wordTagResult.append(word);
							for(int j=0;j<wordTagArr.length();j++){
								wordTagResult.append("/");
								wordTagResult.append(wordTagArr.getString(j));
							}
						}
						if(wordResult.length() > 0){
							wordResult.append("，");
						}
						wordResult.append(word);
					}
				}
			}
 		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(in!=null){
				try{ in.close(); }catch(Exception ex){ ex.printStackTrace(); }
			}
		}
		result.put("fileText", fileText);
		result.put("participleResult", wordResult.toString());
		result.put("participleTagResult", wordTagResult.toString());
		WebUtil.outputHtml(response, result.toString());
		return null;
    }
	
	
	/**
	 * 功能:获取上传那文件的关键词和摘要内容
	 * @param extoken			用户token
	 * @param file				上传文件
	 * @param text				分词的文本内容
	 * @param keyWordLimit		关键词个数
	 * @param abstractLength	摘要长度	
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getKeyWordsAndAbs.do", method=RequestMethod.POST)
    public  static String getKeyWordsAndAbs(HttpServletRequest request, HttpServletResponse response) {  
		String keyWordsAndAbsResult = "";//关键词和摘要的结果
		String abstractContent = "";//摘要内容
		StringBuilder keyWords = new StringBuilder();//关键词
		Map map  = WebUtil.requestToMap(request);
		String keyWordLimit = MapUtils.getString(map, "keyWordLimit");
		String abstractLength = MapUtils.getString(map, "abstractLength");
		String fileText = MapUtils.getString(map, "fileText");
		String fileName = MapUtils.getString(map, "fileName");
		JSONObject result = new JSONObject();
		try {
			UserInfo user = UserUtil.getCurrentUser(request);
			
			//开始调用中文分词接口进行分词
			String url = "http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/calculate/abstract.do";//接口地址
			Map<String,Object> paramMap = new HashMap<String,Object>();//接口参数
			paramMap.put("extoken", "");
			paramMap.put("text", fileText);
			paramMap.put("title", fileName);
			paramMap.put("keyWordLimit", Integer.valueOf(keyWordLimit));
			paramMap.put("abstractLength", Integer.valueOf(abstractLength));
			keyWordsAndAbsResult = HttpClientUtil.getHttpClientResult(url, paramMap);//调用httpclient服务获取结果
			JSONObject keyWordsAndAbsJson = new JSONObject(keyWordsAndAbsResult);
			Object data = keyWordsAndAbsJson.get("data");
			if(data != null){
				JSONObject keyWordsAndAbsObj = (JSONObject)data;
				abstractContent = keyWordsAndAbsObj.getString("abstract");
				JSONArray keyWordsAndAbsArr = (JSONArray)keyWordsAndAbsObj.get("keywords");
				for(int i=0;i<keyWordsAndAbsArr.length();i++){
					JSONObject obj = (JSONObject)keyWordsAndAbsArr.get(i);
					String word = obj.getString("word");
					Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			        Matcher m = p.matcher(word);
					if(m.find()){
						if(keyWords.length() > 0){
							keyWords.append("，");
						}
						keyWords.append(word);
					}
				}
			}
 		} catch (Exception e) {
			e.printStackTrace();
		} 
		result.put("abstractContent", abstractContent);
		result.put("keyWords", keyWords.toString());
		WebUtil.outputHtml(response, result.toString());
		return null;
    }
	
	/**
	 * 功能:获取上传文件内容
	 * @param extoken			用户token
	 * @param file				上传文件
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getFileText.do", method=RequestMethod.POST)
    public  static String getFileText(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {  
		String fileName = "";//上传文件名字
		InputStream in = null;
		String fileExt = "";//文件后缀
		String fileText = "";//文件的文本内容
		try {
			UserInfo user = UserUtil.getCurrentUser(request);
			if(file != null){
				in = file.getInputStream();
				fileName =file.getOriginalFilename();
				int a= fileName.lastIndexOf(".");
				fileExt = fileName.substring(a,fileName.length());
			}
			fileText = FileHandlerUtil.getText(fileExt, in);
 		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(in!=null){
				try{ in.close(); }catch(Exception ex){ ex.printStackTrace(); }
			}
		}
		WebUtil.outputHtml(response, fileText);
		return null;
    }
	 
	 
	 
}
