package com.excellence.iacommon.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class CategoryAndKeyWordsUtil {

	/**
	 * 获取主题词和领域接口
	 * @param parameter	
	 * 		传入参数：数据库所在IP：host，用户名：user，密码：passwd，库名：dbName，表名：tableName，ids，特征文件名：modelName，特征文件所在目录：modelDir。	{\"host\":\"10.1.6.63\",\"user\":\"root\",\"passwd\":\"123456\",\"dbName\":\"213trswcmv7\",\"tableName\":\"wcmmetatableflfg\",\"ids\":[\"3413\",\"3414\",\"3415\"],\"modelName\":\"domain\",\"modelDir\":\"\"}	 
	 * @return
	 * 		返回参数：id，标题，领域，主题词。	[{"id":"","title":"","category":[],"keyWords":[]}]
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
	 * 获取主题词和领域接口
	 * @param host
	 * @param user
	 * @param passwd
	 * @param dbName
	 * @param tableName
	 * @param idList
	 * @param modelName
	 * @param modelDir
	 * 返回参数：id，标题，领域，主题词。	[{"id":"","title":"","category":[],"keyWords":[]}]
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
	 * wcm信息资源打标签
	 * @param parameter	
	 * 		传入参数：数据库所在IP：host，用户名：user，密码：passwd，库名：dbName，表名：tableName，id	 
	 * @return
	 * 		返回参数：success or error
	 */
	public static String importResourceTag(String parameter,String pythonFile){
		String p = parameter.replace("\"", "\'");
		List<String> par = new ArrayList<String>();
		
		par.add(0, p);
		String resultStr = JavaHandlePython.HandlePython(pythonFile, par);
		return resultStr;
	}
	
	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
	 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
	        char c = string.charAt(i);
	 
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}
	
}
