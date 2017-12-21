package com.excellence.iacommon.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONObject;

/**
 * xml工具类
 * 
 * @author lishuowen
 * @created 2016-12-01
 * @copyright Excellence co.
 */
public class XMLUtil {
	private static Map<String, Object> xmlMap = new HashMap<String, Object>();// 该map不包含xml里的root节点
	
	public static Map<String, Object> getXml() {
		return xmlMap;
	}
	
	/**
	 * 新建
	 * 
	 * @param xmlPath xml路径
	 * @param xmlMap xml数据map
	 */
	public static void create(String xmlPath, Map<String, Object> xmlMap) {
		Document xmlDoc = DocumentHelper.createDocument();
		
		// 递归遍历Map，构造Document对象
		Element element = xmlDoc.addElement("root");// 设置一个root节点
		mapToElement(xmlMap, element);
		
		create(xmlPath, xmlDoc);
	}
	
	/**
	 * 将map转换成document
	 * 
	 * @param map 当前map集合
	 * @param element 当前节点
	 */
	private static void mapToElement(Map<String, Object> map, Element element) {
		Object value;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			value = entry.getValue();
			Element childElement = element.addElement(entry.getKey());
			if (value instanceof Map) {
				mapToElement((Map<String, Object>) value, childElement);
			} else {
				childElement.setText(value.toString());
			}
		}
	}
	
	/**
	 * 新建
	 * 
	 * @param xmlPath xml路径
	 * @param xmlDoc 
	 */
	private static void create(String xmlPath, Document xmlDoc) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
	        XMLWriter writer = new XMLWriter(new FileOutputStream(xmlPath), format);
	        writer.write(xmlDoc); //输出到文件
	        writer.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取xml的某个配置值
	 * 
	 * @param attributePath 属性路径，例如：contentPage.bbrq.replaceAll.regex
	 * @return 配置值，如果没有配置则返回空字符串
	 */
	public static String get(String attributePath) {
		String[] paths = attributePath.split("\\.");
		
		String value = "";
		Map<String, Object> map = xmlMap;
		Object temp;
		
		for (String p : paths) {
			temp = map.get(p);
			
			if (temp instanceof Map) {
				map = (Map<String, Object>) temp;
			} else if (temp != null) {
				value = temp.toString();
			}
		}
		
		return value;
	}
	
	/**
	 * 更新
	 * 暂时只支持更新节点值
	 * 
	 * @param xmlPath xml文件路径
	 * @param map 包含除root节点外完整的xml路径
	 */
	public static void update(String xmlPath, Map<String, Object> map) {
		try {
			if (xmlMap.size() == 0) {
				readAllConfig(xmlPath, false);
			}
			
			// 更新xmlMap
			List<String> attributePathAndValues = new ArrayList<String>();
			String attributePathAndValue = "";
			attributePathAndValues = xmlMapToArray(attributePathAndValues, attributePathAndValue, map);
			for (String a : attributePathAndValues) {
				updateXMLMap(xmlPath, a.split(":")[0], a.split(":")[1]);
			}
			
			// 更新xml文件
			create(xmlPath, xmlMap);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将xmlMap转换成数组格式
	 * 
	 * @param map
	 * @param attributePathAndValue
	 * @param attributePathAndValues
	 * @return 格式为 {"a.b.c.d:1", "a.e.f:2"}
	 */
	private static List<String> xmlMapToArray(List<String> attributePathAndValues, String attributePathAndValue, Map<String, Object> map) {
		Object temp;
		Map<String, Object> childMap = map;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String newAttributePathAndValue = attributePathAndValue;// 清除缓存
			temp = entry.getValue();
			if (temp instanceof Map) {
				childMap = (Map<String, Object>) temp;
				newAttributePathAndValue += entry.getKey() + ".";
				xmlMapToArray(attributePathAndValues, newAttributePathAndValue, childMap);
			} else {
				newAttributePathAndValue += (entry.getKey() + ":" +temp.toString());
				attributePathAndValues.add(newAttributePathAndValue);
			}
		}
		
		return attributePathAndValues;
	}
	
	/**
	 * 更新xmlMap
	 * 
	 * @param xmlPath xml文件路径
	 * @param attributePath 属性路径，例如：contentPage.bbrq.replaceAll.regex
	 * @param attributeValue 属性值，其值有可能为map，或者String
	 */
	private static void updateXMLMap(String xmlPath, String attributePath, Object attributeValue) {
		if (xmlMap.size() == 0) {
			readAllConfig(xmlPath, false);
		}
		String[] paths = attributePath.split("\\.");
		Object temp;
		Map<String, Object> childMap = xmlMap;
		for (String p : paths) {
			temp = childMap.get(p);
			if (temp instanceof Map) {
				childMap = (Map<String, Object>) temp;
			} else {
				childMap.put(p, attributeValue);
			}
		}
	}
	
	/**
	 * 修改资源来源的某个配置值
	 * 
	 * @param xmlPath xml文件路径
	 * @param attributePath 属性路径，例如：contentPage.bbrq.replaceAll.regex
	 * @param attributeValue 属性值
	 */
	public static void set(String xmlPath, String attributePath, String attributeValue) {
		try {
			String originValue = get(attributePath);
			if (!originValue.equals(attributeValue)) {// 值有变化
				// 更新xmlMap
				updateXMLMap(xmlPath, attributePath, attributeValue);
				
				// 更新文件
				create(xmlPath, xmlMap);
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 读取xml文件配置并保存到map中
	 * 
	 * @param xmlPath xml文件路径
	 * @param clearMap 是否删除缓存map
	 */
	public static void readAllConfig(String xmlPath, Boolean clearMap) {
		try {
			// 清空map
			if (clearMap) {
				xmlMap.clear();
			}
			
			// 获取xml文档
			Document xmlDoc = readXML(xmlPath);
			
			Element root = xmlDoc.getRootElement();// 根节点
			
			// 获取所有配置信息并保存到map中
			elementToMap(root, xmlMap);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将节点以及其子节点转换成Map结构
	 * 
	 * @param element 当前节点
	 * @param map 
	 */
	private static void elementToMap(Element element, Map<String, Object> map) {
		List<Element> children = element.elements();
		
		String value, key;
		Element childElement;
		for (int i=0; i<children.size(); i++) {
			childElement = children.get(i);
			key = children.get(i).getName();
			value = children.get(i).getTextTrim();
			if (value != null && !"".equals(value)) {
				map.put(key, value);
			} else {
				Map<String, Object> childMap = new HashMap<String, Object>();
				map.put(key, childMap);
				
				elementToMap(childElement, childMap);
			}
		}
	}
	
	/**
	 * 解析xml字符串，并转换成map结构，将不读取xml字符串的根节点
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Map<String, Object> parseXmlStr(String xmlStr) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr); // 将字符串转为XML
			Element rootElt = doc.getRootElement();
			Map<String, Object> map = new HashMap<String, Object>();
			elementToMap(rootElt, map);
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 读取xml文档
	 * 
	 * @param xmlPath xml绝对文件路径
	 * @return
	 */
	private static Document readXML(String xmlPath) {
		try {
			SAXReader sax = new SAXReader();
			sax.setEncoding("UTF-8");
			return sax.read(new File(xmlPath));
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
