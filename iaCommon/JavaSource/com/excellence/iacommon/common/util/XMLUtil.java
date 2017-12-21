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
 * xml������
 * 
 * @author lishuowen
 * @created 2016-12-01
 * @copyright Excellence co.
 */
public class XMLUtil {
	private static Map<String, Object> xmlMap = new HashMap<String, Object>();// ��map������xml���root�ڵ�
	
	public static Map<String, Object> getXml() {
		return xmlMap;
	}
	
	/**
	 * �½�
	 * 
	 * @param xmlPath xml·��
	 * @param xmlMap xml����map
	 */
	public static void create(String xmlPath, Map<String, Object> xmlMap) {
		Document xmlDoc = DocumentHelper.createDocument();
		
		// �ݹ����Map������Document����
		Element element = xmlDoc.addElement("root");// ����һ��root�ڵ�
		mapToElement(xmlMap, element);
		
		create(xmlPath, xmlDoc);
	}
	
	/**
	 * ��mapת����document
	 * 
	 * @param map ��ǰmap����
	 * @param element ��ǰ�ڵ�
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
	 * �½�
	 * 
	 * @param xmlPath xml·��
	 * @param xmlDoc 
	 */
	private static void create(String xmlPath, Document xmlDoc) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
	        XMLWriter writer = new XMLWriter(new FileOutputStream(xmlPath), format);
	        writer.write(xmlDoc); //������ļ�
	        writer.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��ȡxml��ĳ������ֵ
	 * 
	 * @param attributePath ����·�������磺contentPage.bbrq.replaceAll.regex
	 * @return ����ֵ�����û�������򷵻ؿ��ַ���
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
	 * ����
	 * ��ʱֻ֧�ָ��½ڵ�ֵ
	 * 
	 * @param xmlPath xml�ļ�·��
	 * @param map ������root�ڵ���������xml·��
	 */
	public static void update(String xmlPath, Map<String, Object> map) {
		try {
			if (xmlMap.size() == 0) {
				readAllConfig(xmlPath, false);
			}
			
			// ����xmlMap
			List<String> attributePathAndValues = new ArrayList<String>();
			String attributePathAndValue = "";
			attributePathAndValues = xmlMapToArray(attributePathAndValues, attributePathAndValue, map);
			for (String a : attributePathAndValues) {
				updateXMLMap(xmlPath, a.split(":")[0], a.split(":")[1]);
			}
			
			// ����xml�ļ�
			create(xmlPath, xmlMap);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��xmlMapת���������ʽ
	 * 
	 * @param map
	 * @param attributePathAndValue
	 * @param attributePathAndValues
	 * @return ��ʽΪ {"a.b.c.d:1", "a.e.f:2"}
	 */
	private static List<String> xmlMapToArray(List<String> attributePathAndValues, String attributePathAndValue, Map<String, Object> map) {
		Object temp;
		Map<String, Object> childMap = map;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String newAttributePathAndValue = attributePathAndValue;// �������
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
	 * ����xmlMap
	 * 
	 * @param xmlPath xml�ļ�·��
	 * @param attributePath ����·�������磺contentPage.bbrq.replaceAll.regex
	 * @param attributeValue ����ֵ����ֵ�п���Ϊmap������String
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
	 * �޸���Դ��Դ��ĳ������ֵ
	 * 
	 * @param xmlPath xml�ļ�·��
	 * @param attributePath ����·�������磺contentPage.bbrq.replaceAll.regex
	 * @param attributeValue ����ֵ
	 */
	public static void set(String xmlPath, String attributePath, String attributeValue) {
		try {
			String originValue = get(attributePath);
			if (!originValue.equals(attributeValue)) {// ֵ�б仯
				// ����xmlMap
				updateXMLMap(xmlPath, attributePath, attributeValue);
				
				// �����ļ�
				create(xmlPath, xmlMap);
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��ȡxml�ļ����ò����浽map��
	 * 
	 * @param xmlPath xml�ļ�·��
	 * @param clearMap �Ƿ�ɾ������map
	 */
	public static void readAllConfig(String xmlPath, Boolean clearMap) {
		try {
			// ���map
			if (clearMap) {
				xmlMap.clear();
			}
			
			// ��ȡxml�ĵ�
			Document xmlDoc = readXML(xmlPath);
			
			Element root = xmlDoc.getRootElement();// ���ڵ�
			
			// ��ȡ����������Ϣ�����浽map��
			elementToMap(root, xmlMap);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ���ڵ��Լ����ӽڵ�ת����Map�ṹ
	 * 
	 * @param element ��ǰ�ڵ�
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
	 * ����xml�ַ�������ת����map�ṹ��������ȡxml�ַ����ĸ��ڵ�
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Map<String, Object> parseXmlStr(String xmlStr) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr); // ���ַ���תΪXML
			Element rootElt = doc.getRootElement();
			Map<String, Object> map = new HashMap<String, Object>();
			elementToMap(rootElt, map);
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��ȡxml�ĵ�
	 * 
	 * @param xmlPath xml�����ļ�·��
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
