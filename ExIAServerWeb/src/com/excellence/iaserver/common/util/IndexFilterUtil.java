package com.excellence.iaserver.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;

import com.excellence.exke.common.vo.ConceptVo;
import com.excellence.exke.connector.util.PropertyUtil;


/**�ж�ɸѡ�� Ҫȥ���ĸ�����,���������򣬸����������
 * @Date   2017-05-11
 * @author LiuZeHang
 *
 */
public class IndexFilterUtil {
	private static String oaPath =System.getProperty("oapath");
	
	private static String propertiesFile= oaPath+ File.separator + "exiaserver" + File.separator + "indexFilter.properties";
	
	private static Properties properties = null;
	
	public static void init() throws Exception {
		if(properties == null) {
			properties = PropertyUtil.loadProperties(propertiesFile);
		}
	}
	
	/**
	 * ���˸����
	 * @param conceptList
	 * @return
	 * @throws Exception
	 * @author huangjinyuan
	 * @created 2017-5-18
	 * @copyright Excellence co.
	 */
	public static List<ConceptVo> indexFilter(List<ConceptVo> conceptList) throws Exception {
		init();
		List<ConceptVo> list = new ArrayList<ConceptVo>();
		if(conceptList != null && conceptList.size() > 0) {
			for(ConceptVo concept:conceptList) {
				if(indexFilter(concept)) {
					list.add(concept);
				}
			}
		}
		return list;
	}
	
	public static JSONArray wordsFilter(JSONArray words) throws Exception{
		init();
		String contents = properties.getProperty("content");
		if(StringUtils.isNotEmpty(contents)){
			String[] contentArr = StringUtils.split(contents, ";");
			if(words!=null && words.length()>0){
				JSONArray resultArr = new JSONArray();
				for(int i=0;i<words.length();i++){
					boolean msg = true;
					for(String str:contentArr){
						if(words.getString(i).equals(str)){
							msg = false;
							break;
						}
					}
					if(msg){
						resultArr.put(words.getString(i));
					}
				}
				return resultArr;
			}
		}
		return words;
	} 
	
	/**
	 * 
	 * ͨ���ַ��������������ж�
	 * @param indexContent
	 * @return
	 * @throws Exception
	 */
	public static Boolean indexFilter(String indexContent) throws Exception{
		init();
		Boolean result = true;
		String contents = properties.getProperty("content");
		if(StringUtils.isNotEmpty(contents)){
			String []contentArr = StringUtils.split(contents, ";");
			if(StringUtils.isNotEmpty(indexContent)){
				for(String name : contentArr){
					if(name.equals(indexContent)){
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}
	
	
	//ɸѡ��ȥ���ĸ�����,���������򣬸������
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Boolean indexFilter(ConceptVo concept) throws Exception{
		init();
		Boolean conceptNameResult = true;
		String concepts = properties.getProperty("content");
		if(StringUtils.isNotEmpty(concepts)){
			String []conceptArr = StringUtils.split(concepts, ";");
			String conceptName = concept.getContent();
			if(StringUtils.isNotEmpty(conceptName)){
				for(String name : conceptArr){
					if(name.equals(conceptName)){
						conceptNameResult = false;
						break;
					}
				}
			}
		}
		
		//�жϱ���
		Boolean ALIASResult = true;
		String aliasArr = properties.getProperty("ALIASs");
		if(StringUtils.isNotEmpty(aliasArr)){
			String [] ALIASs = StringUtils.split(aliasArr, ";");
			if(ALIASs !=null && ALIASs.length>0){
				Map aliasMap = new HashMap<String, Object>();
				aliasMap = concept.getAttributes();
				String ALIAS = (String) aliasMap.get("ALIAS");
				if(StringUtils.isNotEmpty(ALIAS)){
					for(String name : ALIASs) {
						if(ALIAS.equals(name)) {
							ALIASResult = false;
							break;
						}
					}
				}	
			}
		}
		//�ж�����
		Boolean domainResult = true;
		//TODO
		String domainArr = properties.getProperty("domains");
		if(StringUtils.isNotEmpty(domainArr)){
			String [] domains = StringUtils.split(domainArr,";");
			if(domains !=null && domains.length>0){
				Map domainMap = new HashMap<String, Object>();
				domainMap = concept.getAttributes();
				List<String> arr = (List<String>) domainMap.get("BELONG_DOMAIN_IDS");
				if(arr != null && arr.size()>0){
					for(int t=0;arr.size()>t; t++){
						for(String name : domains) {
							if(arr.get(t).equals(name)) {
								domainResult = false;
								break;
							}
						}
					}
				}	
			}			
		}

		//�жϸ��������ϵ
		Boolean parentsResult = true;
		String parentArr = properties.getProperty("parents");
		if(StringUtils.isNotEmpty(parentArr)){
			String [] parents = StringUtils.split(parentArr,";");
			if(parents !=null && parents.length>0){
				List<String> parentNames = concept.getParentNames();
				if(parentNames !=null&& parentNames.size()>0){
					for(String name : parents) {
						if(parentNames.contains(name)) {
							parentsResult = false;
							break;
						}
					}
				}	
			}
		}
		return conceptNameResult&&ALIASResult&&domainResult&&parentsResult;
	}
}
