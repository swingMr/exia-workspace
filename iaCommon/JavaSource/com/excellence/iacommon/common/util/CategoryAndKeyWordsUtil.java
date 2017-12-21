package com.excellence.iacommon.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class CategoryAndKeyWordsUtil {

	/**
	 * ��ȡ����ʺ�����ӿ�
	 * @param parameter	
	 * 		������������ݿ�����IP��host���û�����user�����룺passwd��������dbName��������tableName��ids�������ļ�����modelName�������ļ�����Ŀ¼��modelDir��	{\"host\":\"10.1.6.63\",\"user\":\"root\",\"passwd\":\"123456\",\"dbName\":\"213trswcmv7\",\"tableName\":\"wcmmetatableflfg\",\"ids\":[\"3413\",\"3414\",\"3415\"],\"modelName\":\"domain\",\"modelDir\":\"\"}	 
	 * @return
	 * 		���ز�����id�����⣬��������ʡ�	[{"id":"","title":"","category":[],"keyWords":[]}]
	 */
	public static String getCategoryAndKeyWords(String parameter){
		String p = parameter.replace("\"", "\'");
		List<String> par = new ArrayList<String>();
		String oapath = System.getProperty("oapath");
		par.add(0, p);
		String pythonFile = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"categoryAndKeyWords.py";
		String resultStr = JavaHandlePython.HandlePython(pythonFile, par);
		System.out.println("22222222222222222222222222"+resultStr);
		return resultStr;
	}
	
	/**
	 * ��ȡ����ʺ�����ӿ�
	 * @param host
	 * @param user
	 * @param passwd
	 * @param dbName
	 * @param tableName
	 * @param idList
	 * @param modelName
	 * @param modelDir
	 * ���ز�����id�����⣬��������ʡ�	[{"id":"","title":"","category":[],"keyWords":[]}]
	 */
	public static String getCategoryAndKeyWords(String host, String user, String passwd, String dbName, String tableName, List<String> idList, String modelName, String modelDir) {
		JSONObject json = new JSONObject();
		json.put("host", host);
		json.put("user", user);
		json.put("passwd", passwd);
		json.put("dbName", dbName);
		json.put("tableName", tableName);
		json.put("dbName", dbName);
		json.put("ids", new JSONArray(idList));
		json.put("modelName", modelName);
		json.put("modelDir", modelDir);
		return getCategoryAndKeyWords(json.toString());
	}
	
	/**
	 * wcm��Ϣ��Դ���ǩ
	 * @param parameter	
	 * 		������������ݿ�����IP��host���û�����user�����룺passwd��������dbName��������tableName��id	 
	 * @return
	 * 		���ز�����success or error
	 */
	public static String importResourceTag(String parameter,String pythonFile){
		String p = parameter.replace("\"", "\'");
		List<String> par = new ArrayList<String>();
		
		par.add(0, p);
		String resultStr = JavaHandlePython.HandlePython(pythonFile, par);
		return resultStr;
	}
	
	/**
	 * �ַ���ת��unicode
	 */
	public static String string2Unicode(String string) {
	 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // ȡ��ÿһ���ַ�
	        char c = string.charAt(i);
	 
	        // ת��Ϊunicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}
	
}
