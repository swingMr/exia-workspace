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
	 * ����:�����ı��ִ�
	 * @param extoken			�û�token
	 * @param text				�ı�����
	 * @param tagPart			
	 * @return
	 */
	
	@RequestMapping(value="/participle.do", method=RequestMethod.POST)
    public  static String participle(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {  
		String fileName = "";//�ϴ��ļ�����
		InputStream in = null;
		String fileExt = "";//�ļ���׺
		String fileText = "";//�ļ����ı�����
		String participleResult = "";//���ķִʽ��
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
			//��ʼ�������ķִʽӿڽ��зִ�
			String url = "http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/calculate/participle.do";//�ӿڵ�ַ
			Map<String,Object> paramMap = new HashMap<String,Object>();//�ӿڲ���
			paramMap.put("extoken", "");
			paramMap.put("text", fileText);
			paramMap.put("tagPart", "true");
			participleResult = HttpClientUtil.getHttpClientResult(url, paramMap);//����httpclient�����ȡ�ִʽ��
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
								wordTagResult.append("��");
							}
							wordTagResult.append(word);
							for(int j=0;j<wordTagArr.length();j++){
								wordTagResult.append("/");
								wordTagResult.append(wordTagArr.getString(j));
							}
						}
						if(wordResult.length() > 0){
							wordResult.append("��");
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
	 * ����:��ȡ�ϴ����ļ��Ĺؼ��ʺ�ժҪ����
	 * @param extoken			�û�token
	 * @param file				�ϴ��ļ�
	 * @param text				�ִʵ��ı�����
	 * @param keyWordLimit		�ؼ��ʸ���
	 * @param abstractLength	ժҪ����	
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getKeyWordsAndAbs.do", method=RequestMethod.POST)
    public  static String getKeyWordsAndAbs(HttpServletRequest request, HttpServletResponse response) {  
		String keyWordsAndAbsResult = "";//�ؼ��ʺ�ժҪ�Ľ��
		String abstractContent = "";//ժҪ����
		StringBuilder keyWords = new StringBuilder();//�ؼ���
		Map map  = WebUtil.requestToMap(request);
		String keyWordLimit = MapUtils.getString(map, "keyWordLimit");
		String abstractLength = MapUtils.getString(map, "abstractLength");
		String fileText = MapUtils.getString(map, "fileText");
		String fileName = MapUtils.getString(map, "fileName");
		JSONObject result = new JSONObject();
		try {
			UserInfo user = UserUtil.getCurrentUser(request);
			
			//��ʼ�������ķִʽӿڽ��зִ�
			String url = "http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/calculate/abstract.do";//�ӿڵ�ַ
			Map<String,Object> paramMap = new HashMap<String,Object>();//�ӿڲ���
			paramMap.put("extoken", "");
			paramMap.put("text", fileText);
			paramMap.put("title", fileName);
			paramMap.put("keyWordLimit", Integer.valueOf(keyWordLimit));
			paramMap.put("abstractLength", Integer.valueOf(abstractLength));
			keyWordsAndAbsResult = HttpClientUtil.getHttpClientResult(url, paramMap);//����httpclient�����ȡ���
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
							keyWords.append("��");
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
	 * ����:��ȡ�ϴ��ļ�����
	 * @param extoken			�û�token
	 * @param file				�ϴ��ļ�
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getFileText.do", method=RequestMethod.POST)
    public  static String getFileText(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {  
		String fileName = "";//�ϴ��ļ�����
		InputStream in = null;
		String fileExt = "";//�ļ���׺
		String fileText = "";//�ļ����ı�����
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
