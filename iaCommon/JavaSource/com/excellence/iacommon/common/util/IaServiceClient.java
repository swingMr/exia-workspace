package com.excellence.iacommon.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.exke.common.vo.ConceptVo;
import com.excellence.exke.common.vo.OntologyVo;
import com.excellence.exke.common.vo.RelationDefinitionVo;
import com.excellence.exke.common.vo.RelationVo;



/**
 * 调用IAServiceWeb服务
 * 
 * @author chq
 */
public class IaServiceClient {
	private static final String ENCODE = "UTF-8";	
	private static CookieStore cookieStore = null; 
	
	/**
	 * 查看概念详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static ConceptVo getConcept(String id) 
	{
		if(StringUtils.isBlank(id))
			return null;
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("id", id));
		HttpPost httpPost = null;
		try {
			long begin = System.currentTimeMillis();
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
//			logger.info("http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExKP/searchKnowledge.do?action=getConcept");
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/ontology/search/getConcept";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, ENCODE));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
//			logger.debug("[Result]::" + result);
			long end = System.currentTimeMillis();
			if(end-begin > 100) {
//				logger.info("http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExKP/searchKnowledge.do?action=getConcept" + "耗时：" + (end - begin) + "毫秒");
			}
			if(result==null || "".equals(result.trim())) return null;
			JSONObject res = new JSONObject(result);
			ConceptVo concept = new ConceptVo();
			if(res!=null && res.getInt("status")==1) {
				JSONObject jsonObject = res.getJSONObject("data");
				concept.parseFormJson(jsonObject);
			}
			
	        return concept;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
//			logger.error("connect", ex);
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	
	/**
	 * 通过关键词查询概念
	 * @param word
	 * @return
	 * @author huangjinyuan
	 * @created 2017-6-7
	 * @copyright Excellence co.
	 */
	public static ConceptVo getConceptByKeyword(String word)
	{
		if(StringUtils.isBlank(word))
			return null;
		List<String> words = new ArrayList<String>();
		words.add(word);
		List<ConceptVo> list = getConceptsByKeywords(words);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 通过主题词获取概念集合
	 * @param wordList
	 * @return
	 */
	public static List<ConceptVo> getConceptsByKeywords(List<String> wordListOld)
	{
		if(wordListOld==null)
			return null;
		List<String> wordListNew = new ArrayList<String>();
		//去除重复的词
		for(String word:wordListOld)
		{
			if(!wordListNew.contains(word)&&StringUtils.isNotBlank(word))
			{
				wordListNew.add(word);
			}
		}		
		
		Date dt1 = new Date();
		List<ConceptVo> conceptVoList = new ArrayList<ConceptVo>();
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if (wordListNew!=null && wordListNew.size()>0) {
			parameters.add(new BasicNameValuePair("words", new JSONArray(wordListNew).toString()));
			HttpPost httpPost = null;
			try {
				long begin = System.currentTimeMillis();
				HttpClientContext context = HttpClientContext.create();
				CloseableHttpClient httpClient = HttpClientBuilder.create()
						.setDefaultCookieStore(cookieStore).build();
				String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/ontology/search/getConceptsByKeywords";
				httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(parameters, ENCODE));
		        CloseableHttpResponse response = httpClient.execute(httpPost, context);
		        HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				long end = System.currentTimeMillis();
				if(result==null || "".equals(result.trim())) return null;
				JSONObject res = new JSONObject(result);
				if(res!=null && res.getInt("status")==1) {
					if(res.has("data") && res.get("data") instanceof JSONArray) {
						JSONArray arr = res.getJSONArray("data");
						if (arr!=null && arr.length()>0) {
							List<ConceptVo> resultList = parseConceptJson(arr);
							for(ConceptVo concept:resultList) {
								if(!conceptVoList.contains(concept)) {
									conceptVoList.add(concept);
								}
							}
						}
					}
				}
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}finally{
				if(httpPost!=null){
					httpPost.releaseConnection();
				}
			}			
		} 
		Date dt2 = new Date();
		long l = dt2.getTime()-dt1.getTime();
		if(l>200)
		{
			System.out.println("根据主题词获取概念对象耗时:"+l+" ms");
		}
		return conceptVoList;
	}
	
	private static List<ConceptVo> parseConceptJson(JSONArray arr) {
		List<ConceptVo> list = new ArrayList<ConceptVo>();
		for(int i=0; i<arr.length(); i++) {
			JSONObject jsonObject = arr.getJSONObject(i);
			if (jsonObject!=null) {
				ConceptVo concept = new ConceptVo();
				concept.parseFormJson(jsonObject);
				list.add(concept);
			}
		}
		return list;
	}
	
	private static List<Map<String, List<OntologyVo>>> parseOntologyJson(JSONArray arr) {
		List<Map<String, List<OntologyVo>>> list = new ArrayList<Map<String,List<OntologyVo>>>();
		for(int i=0; i<arr.length(); i++) {
			JSONObject jsonObject = arr.getJSONObject(i);
			Map<String, List<OntologyVo>> map = new HashMap<String, List<OntologyVo>>();
			if(jsonObject.has("concepts")
					&& !JSONObject.NULL.equals(jsonObject.get("concepts"))){
				List<OntologyVo> conceptList = new ArrayList<OntologyVo>();
				JSONArray concepts = jsonObject.getJSONArray("concepts");
				if (concepts!=null&& concepts.length()>0) {
					for (int j = 0; j < concepts.length(); j++) {
						if (!JSONObject.NULL.equals(concepts.get(j)) && concepts.get(j) instanceof JSONObject) {
							JSONObject obj = concepts.getJSONObject(j);
							if(obj.has("fromId") && obj.has("toId")) {
								RelationVo relation = new RelationVo();
								relation.parseFormJson(obj);
								conceptList.add(relation);
								continue;
							} else if(obj.has("collection")) {
								RelationDefinitionVo relationDefinition = new RelationDefinitionVo();
								relationDefinition.parseFormJson(obj);
								conceptList.add(relationDefinition);
								continue;
							} else if(obj.has("definition")){
								ConceptVo concept = new ConceptVo();
								concept.parseFormJson(obj);
								conceptList.add(concept);
								continue;
//							} else {
//								WordVo word = new WordVo();
//								word.parseJson(obj);
//								continue;
							}
						}
					}
				}
				map.put("concepts", conceptList);
			}
			
			if(jsonObject.has("relations")
					&& !JSONObject.NULL.equals(jsonObject.get("relations"))){
				List<OntologyVo> relationList = new ArrayList<OntologyVo>();
				JSONArray relations = jsonObject.getJSONArray("relations");
				if (relations!=null&& relations.length()>0) {
					for (int j = 0; j < relations.length(); j++) {
						if (!JSONObject.NULL.equals(relations.get(j)) && relations.get(j) instanceof JSONObject) {
							JSONObject obj = relations.getJSONObject(j);
								RelationVo relation = new RelationVo();
								relation.parseFormJson(obj);
								relationList.add(relation);
						}
					}
				}
				map.put("relations", relationList);
			}
			
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 获取子领域
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static List<ConceptVo> getChildDomain(String domainName) 
	{
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("domainName", domainName));
		HttpPost httpPost = null;
		try {
			long begin = System.currentTimeMillis();
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/ontology/search/domains";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, ENCODE));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			long end = System.currentTimeMillis();
			if(end-begin > 100) {
//				logger.info("http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExKP/searchKnowledge.do?action=getConcept" + "耗时：" + (end - begin) + "毫秒");
			}
			if(result==null || "".equals(result.trim())) return null;
			JSONObject jsonObj = new JSONObject(result);
			JSONArray jsonArray = new JSONArray();
			if(jsonObj.getInt("status")==1){
				jsonArray = jsonObj.getJSONArray("data");
			}
			
			List<ConceptVo> resultArr = new ArrayList<ConceptVo>();
			for(int i=0;i<jsonArray.length();i++){
				ConceptVo concept = new ConceptVo();
				concept.parseFormJson(jsonArray.getJSONObject(i));
				resultArr.add(concept);
			}
			
	        return resultArr;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
//			logger.error("connect", ex);
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	
	/**
	 * 获取领域下的概念清单
	 * @param domainName:领域名称,parentNames:过滤概念的父类名数组
	 * @return
	 * @throws Exception
	 */
	public static List<ConceptVo> getConceptByDomain(String domainName,String[] parentNames) 
	{
		if(StringUtils.isBlank(domainName) || parentNames ==null || parentNames.length==0)
			return null;
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("domainName", domainName));
		for(String str:parentNames){
			parameters.add(new BasicNameValuePair("parentNames", str));
		}
		
		HttpPost httpPost = null;
		try {
			long begin = System.currentTimeMillis();
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/ontology/search/concepts";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, ENCODE));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			long end = System.currentTimeMillis();
			if(end-begin > 100) {
//				logger.info("http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExKP/searchKnowledge.do?action=getConcept" + "耗时：" + (end - begin) + "毫秒");
			}
			if(result==null || "".equals(result.trim())) return null;
			JSONObject jsonObj = new JSONObject(result);
			JSONArray jsonArray = new JSONArray();
			if(jsonObj.getInt("status")==1){
				jsonArray = jsonObj.getJSONArray("data");
			}
			
			List<ConceptVo> resultArr = new ArrayList<ConceptVo>();
			for(int i=0;i<jsonArray.length();i++){
				ConceptVo concept = new ConceptVo();
				concept.parseFormJson(jsonArray.getJSONObject(i));
				resultArr.add(concept);
			}
			
	        return resultArr;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
//			logger.error("connect", ex);
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	
	 
    /**
	 * 提取文本摘要和主题词
	 * @param content
	 * @param numOfSub 主题词个数
	 * @param percent 摘要长度占文章长度百分比
	 * @param dictName 摘要词典名称（为空表示加载系统默认词典）默认词典为./ DICT/absdict;
	 * @return
	 */
	public static String absText(String extoken, String title, String content, Integer keyWordLimit, Integer abstractLength) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("extoken", extoken));
		if (StringUtils.isNotEmpty(title)) {
			parameters.add(new BasicNameValuePair("title", title));
		}
		if (StringUtils.isNotEmpty(content)) {
			parameters.add(new BasicNameValuePair("text", content));
		}
		if (keyWordLimit!=null) {
			parameters.add(new BasicNameValuePair("keyWordLimit", String.valueOf(keyWordLimit)));
		}
		if (abstractLength!=null) {
			parameters.add(new BasicNameValuePair("abstractLength", String.valueOf(abstractLength)));
		}
		
		HttpPost httpPost = null;
		try {
			long begin = System.currentTimeMillis();
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/calculate/abstract";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			long end = System.currentTimeMillis();
			if(end-begin > 100) {
			}
			if(result==null || "".equals(result.trim())) return null;
	        return result;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	
	/**
	 * 生成指定文本数据的标签
	 * @param title
	 * @param text
	 * @param recDomain
	 * @param recHierarchy
	 * @param sourceUnit
	 * @param publishDate 
	 * @return
	 */
	public static String gentag(String title, String text, boolean recDomain,
			boolean recHierarchy, String sourceUnit, String publishDate) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("title", title));
		parameters.add(new BasicNameValuePair("text", text));
		parameters.add(new BasicNameValuePair("sourceUnit", sourceUnit));
		parameters.add(new BasicNameValuePair("publishDate", publishDate));
		parameters.add(new BasicNameValuePair("recDomain", String.valueOf(recDomain)));
		parameters.add(new BasicNameValuePair("recHierarchy", String.valueOf(recHierarchy)));
		HttpPost httpPost = null;
		try {
			long begin = System.currentTimeMillis();
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/calculate/gentag";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			long end = System.currentTimeMillis();
			if(end-begin > 100) {
			}
			if(result==null || "".equals(result.trim())) return null;
	        return result;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	
	public static String updateIndex(String libNum, String resTitle, String resId, Date expireDate,String type) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("libNum", libNum));
		parameters.add(new BasicNameValuePair("resTitle", resTitle));
		parameters.add(new BasicNameValuePair("resId", resId));
		parameters.add(new BasicNameValuePair("type", type));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(expireDate);
		parameters.add(new BasicNameValuePair("expireDate", dateString));
		HttpPost httpPost = null;
		String results ="";
		try {
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/resource/task/save";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			JSONObject obj = new JSONObject(result);
			results = (String) obj.get("status");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return results;
	}
	
	public static String removeIndex(String libNum, String resTitle, String resId, Date expireDate) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("libNum", libNum));
		parameters.add(new BasicNameValuePair("resTitle", resTitle));
		parameters.add(new BasicNameValuePair("resId", resId));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = "2037-01-01";
		//默认永久；
		if(expireDate != null){
			dateString = dateFormat.format(expireDate);	
		}
		parameters.add(new BasicNameValuePair("expireDate", dateString));
		HttpPost httpPost = null;
		String results ="";
		try {
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/resource/task/del";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			JSONObject obj = new JSONObject(result);
			results = (String) obj.get("status");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return results;
	}
	
	
	/**
	 * 信息资源同步接口：新增、更新信息资源
	 * @author Liuzh
	 * {
	    "user": {
	        "id": "当前id",
	        "name": "当前用户姓名"
	    },
	    "resource": {
	        "type": "create/update",
	        "libNum": "资源库编码",
	        "id": "资源ID"
	        "oldContent": ""
	        "oldTitle": ""
	        "catalogNames": ""
	    }
	 *
	 */
	public static String informationSave(String contexts){
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("context", contexts));
		HttpPost httpPost = null;
		String results ="";
		try {
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/res/saveInfo";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			JSONObject obj = new JSONObject(result);
			results = (String) obj.get("result");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return results;
	}
	
	
	/**@author LiuZeHang
	 * 信息资源同步接口：删除信息资源
	 * @param resourceId
	 * @param libNum
	 * @param catalogNameStr
	 * @return
	 */
	public static String informationRemoved(String resourceId,String libNum,String catalogNameStr){
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("libNum", libNum));
		parameters.add(new BasicNameValuePair("id", resourceId));
		parameters.add(new BasicNameValuePair("catalogNames", catalogNameStr));
		HttpPost httpPost = null;
		String results ="";
		try {
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/res/removeInfo";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			JSONObject obj = new JSONObject(result);
			results = (String) obj.get("result");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return results;
	}
	
	/**
	 * 获取外延概念
	 * @param conceptId
	 * @param type
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public static List<ConceptVo> getExtensionalConcepts(String conceptId, String type, int num) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("conceptId", conceptId));
		parameters.add(new BasicNameValuePair("type", type));
		parameters.add(new BasicNameValuePair("num", String.valueOf(num)));
		HttpPost httpPost = null;
		try {
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url ="http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+"/ExIAServer/services/ontology/search/getExtensionalConcepts";
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, ENCODE));
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			if(result==null || "".equals(result.trim())) return null;
			JSONObject res = new JSONObject(result);
			if(res!=null && res.getInt("status")==1) {
				if(res.has("data") && res.get("data") instanceof JSONArray) {
					JSONArray arr = res.getJSONArray("data");
					return parseConceptJson(arr);
				}
			}
			return null;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return null;
	}
}
