package com.excellence.iamp.util;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author liup
 * @date 2017-9-11
 * @2014-1015 Excellence
 */
public class ExtractRuleUtil {
	
	public static JSONArray extractRule(InputStream is, String fileType) {
		String zz = "(?<=\\{)(.+?)(?=\\})";  
		Pattern pattern = Pattern.compile(zz);
		try {
			String content = getContent(is,fileType);
			 JSONArray rules = new JSONArray();
			 Matcher matcher = pattern.matcher(content);
			 int start = 0;
			 
			 while(matcher.find()){
				 JSONObject obj = new JSONObject();
				 String text = matcher.group();
				 if(StringUtils.isNotEmpty(text)) {
					 String name = text;
					 String type = "string";
					 if(text.indexOf("|")!=-1) {
						 name = text.substring(0, text.lastIndexOf("|")-1);
						 type = text.substring(text.lastIndexOf("|")+1, text.length()-1);
					 }
					 obj.put("name", name);
					 obj.put("type", type);
					 if(matcher.start()-start>10) {
						 start = matcher.start()-10;
					 }
					 JSONArray ruleArr = new JSONArray();
					 String prev = content.substring(start, matcher.start()-1).trim();
					 String next = "";
					 if(content.length()>=matcher.end()+10) {
						 String end = content.substring(matcher.end()+1, matcher.end()+10);
						 if(end.indexOf("{")!=-1) {
						     end = end.substring(0, end.indexOf("{"));
						 }
						 next = end;
					 } else {
						 String end = content.substring(matcher.end()+1, content.length()-1);
						 if(end.indexOf("{")!=-1) {
							 end = end.substring(0, end.indexOf("{")-1);
						 }
						 next = end;
					 }
					 ruleArr.put(prev+"(.*?)"+next);
					 if(prev.endsWith(":")) {
						 ruleArr.put(prev.substring(0, prev.length()-1)+"£º"+"(.*?)"+next);
					 } else if(prev.endsWith("£º")) {
						 ruleArr.put(prev.substring(0, prev.length()-1)+":"+"(.*?)"+next);
					 }
					 start = matcher.end()+1;
					 obj.put("rule", ruleArr);
					 rules.put(obj);
				 }
				 
			 }
			 
			 return rules;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public static JSONArray extractInfo(InputStream in, JSONArray rules, String fileType) {
		String text = getContent(in, fileType);
		JSONArray res = new JSONArray();
		if(rules.length()>0) {
			for (int i = 0; i < rules.length(); i++) {
				JSONArray ruleArr = rules.getJSONObject(i).getJSONArray("rule");
				String name = rules.getJSONObject(i).getString("name");
				String type = rules.getJSONObject(i).getString("type");
				String rgex = "";
//				rgex.append(ruleArr.getString(0));
				boolean index = false;
				JSONObject obj = new JSONObject();
	        	obj.put("name", name);
	        	obj.put("type", type);
	        	String value = "";
				if(ruleArr.length()>0) {
					for(int j=0; j<ruleArr.length(); j++) {
						if(!index) {
							rgex = ruleArr.getString(j);
							Pattern pattern1 = Pattern.compile(rgex);
					        Matcher m = pattern1.matcher(text);
					        if(m.find()){
					        	value = m.group(1);
					        	text = text.substring(m.end()-1);
					        	index = true;
					        } 
						}
					}
				}
				
		        obj.put("value", value);
		        res.put(obj);
			}
		}
		return res; 
	}
	
	public static JSONArray extractInfo(String text, JSONArray rules) {
		JSONArray res = new JSONArray();
		if(rules.length()>0) {
			for (int i = 0; i < rules.length(); i++) {
				JSONArray ruleArr = rules.getJSONObject(i).getJSONArray("rule");
				String name = rules.getJSONObject(i).getString("name");
				String type = rules.getJSONObject(i).getString("type");
				String rgex = "";
//				rgex.append(ruleArr.getString(0));
				boolean index = false;
				JSONObject obj = new JSONObject();
	        	obj.put("name", name);
	        	obj.put("type", type);
	        	String value = "";
				if(ruleArr.length()>0) {
					for(int j=0; j<ruleArr.length(); j++) {
						if(!index) {
							rgex = ruleArr.getString(j);
							rgex = rgex.replaceAll("\r", " ");
							Pattern pattern1 = Pattern.compile(rgex);
					        Matcher m = pattern1.matcher(text);
					        if(m.find()){
					        	value = m.group(1);
					        	text = text.substring(m.end()-1);
					        	index = true;
					        } 
						}
					}
				}
				
		        obj.put("value", value);
		        res.put(obj);
			}
		}
		return res; 
	}
	
	public static String getContent(InputStream is, String fileType) {
		try {
			if("doc".equals(fileType)) {
				HWPFDocument document = new HWPFDocument(is); 
				return document.getDocumentText();
			} else if("docx".equals(fileType)) {
				XWPFDocument document = new XWPFDocument(is);
				XWPFWordExtractor extractor = new XWPFWordExtractor(document);  
			    String text = extractor.getText();  
			    return text;  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
