package com.excellence.iaserver.common.util;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * Web通用工具类
 * @author huangyb
 * @date 2015-5-19
 * @2014-1015 Excellence
 */
public class WebUtil {
	public static void outputPlain(HttpServletResponse response,String msg){ output(response, "text/plain", System.getProperty("file.encoding"), msg); }
	public static void outputPlain(HttpServletResponse response,String encode, String msg){ output(response,"text/plain", encode, msg); }
	public static void outputHtml(HttpServletResponse response,String msg){ output(response, "text/html", System.getProperty("file.encoding"), msg); }
	public static void outputHtml(HttpServletResponse response,String encode, String msg){ output(response, "text/html", encode, msg); }
	private static void output(HttpServletResponse response, String contentType, String encode, String msg){
		response.setContentType(contentType+";charset="+encode);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.print(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				writer.flush();
				writer.close();
			}
		}
	}
	
	public static Map requestToMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		if(properties==null)return null;
		// 返回值Map
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				value = "";
				for (int i = 0; i < values.length; i++) {
					if(StringUtils.isEmpty(values[i])) continue;
					value += values[i] + ",";
				}
				if(StringUtils.isNotEmpty(value)){
					value = value.substring(0, value.length() - 1);
				}
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
}
