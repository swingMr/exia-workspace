package com.excellence.iaserver.common.util;


import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.yaml.snakeyaml.util.UriEncoder;

import com.excellence.exke.resource.adapter.trs.service.WCMService;

/** 
 * @author liup
 * @date 2017年6月29日 上午11:06:21 
 * @version 1.0 
 */

public class ElasticSearchUtil {
	
	private static final String ENCODE = "UTF-8";
	private static CookieStore cookieStore = null;
	
	public static String getESURL() {
//		return "http://10.1.5.11:9200/";
//		return "http://172.3.4.78:9200/";
		return "http://"+Constant.ELESTICSEARCH_HOST+":"+Constant.ELESTICSEARCH_PORT+"/";
	}
	
	public static String elSearch(Map<String, Object> conditions) {
		
		int page = (Integer) conditions.get("page");
		int pageSize = (Integer) conditions.get("pageSize");
		int isReturnContent = (Integer) conditions.get("isReturnContent");
		String text = (String) conditions.get("text");
		JSONObject object = new JSONObject();
		object.put("from", (page-1)*pageSize);
		object.put("size", pageSize);
		
		if(isReturnContent==0) {
			JSONObject _source = new JSONObject("{'excludes': [ 'content','html_content']}");
			object.put("_source", _source);
		}
		
		JSONObject query = new JSONObject();
		JSONObject bool = new JSONObject();
		JSONObject childTitleBool = new JSONObject();
		JSONObject childKeywordBool = new JSONObject();
		JSONObject childContentBool = new JSONObject();
		JSONObject childHtmlBool = new JSONObject();
		JSONArray filter = new JSONArray();
		
		JSONArray should = new JSONArray();
		JSONArray must = new JSONArray();
		
		if(conditions.containsKey("infoSources")) {
			String infoSources = (String) conditions.get("infoSources");
			if (StringUtils.isNotBlank(infoSources)) {
				String[] infoSource = infoSources.split(",");
				JSONObject sbool = new JSONObject();
				JSONArray sshould = new JSONArray();
				for (String source : infoSource) {
					sshould.put(new JSONObject("{'term': {'_type': '"+source+"'}}"));
				}
				sbool.put("should", sshould);
				sbool.put("minimum_should_match", 1);
				
				JSONObject sbool1 = new JSONObject();
				sbool1.put("bool", sbool);
				filter.put(sbool1);
			}
		}
		bool.put("filter", filter);
		
		if(StringUtils.isNotBlank(text.trim()) && text.trim().indexOf(" ")!=-1) {//or
			
			String[] words = text.trim().split(" ");
			if (words!=null && words.length>0) {
				JSONArray titleMusts = new JSONArray();
				JSONArray keywordMusts = new JSONArray();
				JSONArray contentMusts = new JSONArray();
				JSONArray htmlMusts = new JSONArray();
				JSONArray titleShoulds = new JSONArray();
				JSONArray keywordShoulds = new JSONArray();
				JSONArray contentShoulds = new JSONArray();
				JSONArray htmlShoulds = new JSONArray();
//					should.put(new JSONObject("{'match':{'title': {'query': '"+text.trim()+"','boost': '2'}}}"));
//					should.put(new JSONObject("{'match':{'keywords': {'query': '"+text.trim()+"','boost': '1.5'}}}"));
//					should.put(new JSONObject("{'match':{'content': {'query': '"+text.trim()+"','boost': '1'}}}"));
				for (String word : words) {
					titleShoulds.put(new JSONObject("{'match_phrase':{'title': {'query': '"+word+"','boost': '2'}}}"));
					keywordShoulds.put(new JSONObject("{'match_phrase':{'keywords': {'query': '"+word+"','boost': '1.5'}}}"));
					contentShoulds.put(new JSONObject("{'match_phrase':{'content': {'query': '"+word+"','boost': '1'}}}"));
				}
				
				/*if(conditions.containsKey("categorys")) {
					String categorys = (String) conditions.get("categorys");
					if (StringUtils.isNotBlank(categorys)) {
						must.put(new JSONObject("{'match':{'category': {'query': '"+categorys.trim()+"','boost': '0.5','minimum_should_match': '1'}}}"));
					}
					bool.put("must", must);
				}*/
				JSONArray mustNots = new JSONArray();
				if(conditions.containsKey("ignoreText")) {
					String ignoreText = (String) conditions.get("ignoreText");
					if (StringUtils.isNotEmpty(ignoreText)) {
							mustNots.put(new JSONObject("{'match': { 'title': '"+ignoreText+"'}}"));
							mustNots.put(new JSONObject("{'match': { 'keywords': '"+ignoreText+"'}}"));
							mustNots.put(new JSONObject("{'match': { 'content': '"+ignoreText+"'}}"));
						bool.put("must_not", mustNots);
					}
				}
				JSONObject childShould = new JSONObject();
				childShould.put("should", titleShoulds);
				childTitleBool.put("bool", childShould);
				childShould = new JSONObject();
				childShould.put("should", keywordShoulds);
				childKeywordBool.put("bool", childShould);
				childShould = new JSONObject(); 
				childShould.put("should", contentShoulds);
				childContentBool.put("bool", childShould);
				childShould = new JSONObject();
				childShould.put("should", htmlShoulds);
				childHtmlBool.put("bool", childShould);
				should.put(childTitleBool);
				should.put(childKeywordBool);
				should.put(childContentBool);
				should.put(childHtmlBool);
			}
		}  
		
		bool.put("should", should);
		query.put("bool", bool);
		object.put("query", query);
		long b1 = System.currentTimeMillis();
		String result = qlSearch(Constant.ELESTICSEARCH_DATABASE_NAME,page, object.toString());
		long b2 = System.currentTimeMillis();
//		System.out.println("+++++++++++++++查询信息资源耗时："+(b2-b1)+"ms++++++++++++++++++");
		if(result==null || "".equals(result.trim())) return null;
//		long b3 = System.currentTimeMillis();
		result = newChangeDataStructures(result, page);
//		long b4 = System.currentTimeMillis();
//		System.out.println("+++++++++++++++转换结构耗时："+(b4-b3)+"ms++++++++++++++++++");
		
        return result;
	}
	
	
	public static JSONObject searchBySql(String sql, int page, int pageSize) throws Exception {
		String result = sqlSearch(sql);
		if(result==null || "".equals(result.trim())) return null;
		JSONObject obj = newChangeToInfos(result, page, pageSize);
        return obj;
	}
	
	private static JSONObject changeToInfos(String result, int page, int pageSize) {
		JSONObject res = new JSONObject(result);
		JSONObject newResultObj = new JSONObject();
		if (res.has("error")) {
			return res;
		} else {
			JSONArray dataArr = new JSONArray();
			WCMService wcmService = new WCMService();
			JSONObject hits = res.getJSONObject("hits");
			int total = hits.getInt("total");
			newResultObj.put("num", total);
			newResultObj.put("page", page);
			newResultObj.put("pageSize", pageSize);
			JSONArray informations = new JSONArray();
			JSONArray datas = hits.getJSONArray("hits");
			if(datas!=null && datas.length()>0) {
				for (int i = 0; i < datas.length(); i++) {
					JSONObject info = new JSONObject();
					JSONObject data = datas.getJSONObject(i);
					JSONObject node = data.getJSONObject("_source");
					if (node.has("MetaDataId")) {
						info.put("id", ""+node.getInt("MetaDataId"));
						String viewPageUrl = "";
						try {
							viewPageUrl =  wcmService.generateSimpleUrl(node.getInt("id") + "");
						} catch (Exception e) {
							e.printStackTrace();
						}
						info.put("viewPageUrl",viewPageUrl);
					} else {
						info.put("viewPageUrl","");
					}
					
					if (node.has("title") && node.get("title") instanceof String) {
						info.put("title", node.getString("title"));
					} else {
						info.put("title", "");
					}
					
					if (node.has("url") && node.get("url") instanceof String) {
						info.put("url", node.getString("url"));
					} else {
						info.put("url", "");
					}
					
					JSONObject attributes = new JSONObject();
					/*if (node.has("content") && node.get("content") instanceof String) {
						attributes.put("content", node.getString("content"));
					} else {
						attributes.put("content", "");
					}*/
					if (node.has("abstract") && node.get("abstract") instanceof String) {
						attributes.put("abstract", node.getString("abstract"));
					} else {
						attributes.put("abstract", "");
					}
					if (node.has("genre") && node.get("genre") instanceof String) {
						attributes.put("genre", node.getString("genre"));
					} else {
						attributes.put("genre", "");
					}
					if (node.has("symbol") && node.get("symbol") instanceof String) {
						attributes.put("symbol", node.getString("symbol"));
					} else {
						attributes.put("symbol", "");
					}
					if (node.has("form") && node.get("form") instanceof String) {
						attributes.put("form", node.getString("form"));
					} else {
						attributes.put("form", "");
					}
					if (node.has("keywords") && node.get("keywords") instanceof JSONArray) {
						attributes.put("keywords", node.getJSONArray("keywords"));
					} else {
						attributes.put("keywords", new JSONArray());
					}
					String bbrq = "";
					if (node.has("publish_time") && node.get("publish_time") instanceof String) {
						bbrq = node.getString("publish_time");
						if (StringUtils.isNotEmpty(bbrq)) {
							bbrq = bbrq.substring(0, 10);
						}
					}
					if(StringUtils.isEmpty(bbrq)) {
						if (node.has("bbrq") && node.get("bbrq") instanceof String) {
							bbrq = node.getString("bbrq");
							bbrq = bbrq.substring(0, 10);
						}
					}
					attributes.put("bbrq", bbrq);
					attributes.put("belong", data.getString("_type"));
					info.put("attributes", attributes);
					informations.put(info);
				}
			}
			newResultObj.put("informations", informations);
		}
		return newResultObj;
	}
	
	private static JSONObject newChangeToInfos(String result, int page, int pageSize) {
		
		JSONObject res = new JSONObject(result);
		JSONObject newResultObj = new JSONObject();
		if (res.has("error")) {
			return res;
		} else {
			JSONArray dataArr = new JSONArray();
			WCMService wcmService = new WCMService();
			JSONObject hits = res.getJSONObject("hits");
			int total = hits.getInt("total");
			newResultObj.put("num", total);
			newResultObj.put("page", page);
			newResultObj.put("pageSize", pageSize);
			JSONArray informations = new JSONArray();
			JSONArray datas = hits.getJSONArray("hits");
			if(datas!=null && datas.length()>0) {
				for (int i = 0; i < datas.length(); i++) {
					JSONObject info = new JSONObject();
					JSONObject data = datas.getJSONObject(i);
					JSONObject node = data.getJSONObject("_source");
					info.put("id", data.getString("_id"));
					info.put("viewPageUrl",node.getString("url"));
					
					if (node.has("title") && node.get("title") instanceof String) {
						info.put("title", node.getString("title"));
					} else {
						info.put("title", "");
					}
					
					if (node.has("originalAddress") && node.get("originalAddress") instanceof String) {
						info.put("url", node.getString("originalAddress"));
					} else {
						info.put("url", "");
					}
					
					JSONObject attributes = new JSONObject();
					if(node.has("content") && node.get("content") instanceof String) {
						attributes.put("content", node.getString("content"));
					} else {
						attributes.put("content", "");
					}
					if(node.has("html_content") && node.get("html_content") instanceof String) {
						attributes.put("html_content", node.getString("html_content"));
					} else {
						attributes.put("html_content", "");
					}
					if (node.has("abstractText") && node.get("abstractText") instanceof String) {
						attributes.put("abstract", node.getString("abstractText"));
					} else {
						attributes.put("abstract", "");
					}
					if (node.has("genre") && node.get("genre") instanceof String) {
						attributes.put("genre", node.getString("genre"));
					} else {
						attributes.put("genre", "");
					}
					if (node.has("symbol") && node.get("symbol") instanceof String) {
						attributes.put("symbol", node.getString("symbol"));
					} else {
						attributes.put("symbol", "");
					}
					if (node.has("form") && node.get("form") instanceof String) {
						attributes.put("form", node.getString("form"));
					} else {
						attributes.put("form", "");
					}
					if (node.has("keywords") && node.get("keywords") instanceof JSONArray) {
						attributes.put("keywords", node.getJSONArray("keywords"));
					} else {
						attributes.put("keywords", new JSONArray());
					}
					String bbrq = "";
					if (node.has("publishDate") && node.get("publishDate") instanceof String) {
						bbrq = node.getString("publishDate");
						if (StringUtils.isNotEmpty(bbrq)) {
							bbrq = bbrq.substring(0, 10);
						}
					}
					attributes.put("bbrq", bbrq);
					attributes.put("belong", data.getString("_type"));
					info.put("attributes", attributes);
					informations.put(info);
				}
			}
			newResultObj.put("informations", informations);
		}
		return newResultObj;
	}

	private static String changeDataStructures(String result, int page) {
		NumberFormat fmt = NumberFormat.getPercentInstance();  
		fmt.setMaximumFractionDigits(0);//最多两位百分小数，如25.23%
		JSONObject res = new JSONObject(result);
		JSONObject newResultObj = new JSONObject();
		if (res.has("error")) {
			return newResultObj.toString();
		} else {
			JSONArray dataArr = new JSONArray();
			WCMService wcmService = new WCMService();
			JSONObject hits = res.getJSONObject("hits");
			int total = hits.getInt("total");
			double max_score = hits.getDouble("max_score");
			JSONArray datas = hits.getJSONArray("hits");
			if(datas!=null && datas.length()>0) {
				for (int i = 0; i < datas.length(); i++) {
					JSONObject data = datas.getJSONObject(i);
					JSONObject node = data.getJSONObject("_source");
					node.put("belong", data.getString("_type"));
					String channelName = "";
//					try {
//						channelName = wcmService.getChannelName(node.getInt("ChannelId"));
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
					node.put("channelName", channelName);
					node.put("channelId", node.getInt("ChannelId"));
					node.remove("ChannelId");
					node.put("id", node.getInt("MetaDataId"));
					node.remove("MetaDataId");
					String resourceUrl = "";
					try {
						resourceUrl =  wcmService.generateSimpleUrl(node.getInt("id") + "");
					} catch (Exception e) {
						e.printStackTrace();
					}
					node.put("resourceUrl",resourceUrl);
					double _score = data.getDouble("_score");
					if(_score>100){
						_score = 100;
					}
					String score = String.valueOf(_score);
					node.put("relevance", score.substring(0, score.indexOf("."))+"%");
					String bbrq = "";
					if (node.has("bbrq") && node.get("bbrq") instanceof String) {
						bbrq = node.getString("bbrq");
						if (StringUtils.isNotEmpty(bbrq)) {
							bbrq = bbrq.substring(0, 10);
						}
					}
					if(StringUtils.isEmpty(bbrq)) {
						if (node.has("publish_time") && node.get("publish_time") instanceof String) {
							bbrq = node.getString("publish_time");
							bbrq = bbrq.substring(0, 10);
						}
					}
					node.put("bbrq", bbrq);
					if (!node.has("keywords") || ("null").equals(node.get("keywords").toString())) {
						node.put("keywords", "");
					}
					if (!node.has("abstract") || ("null").equals(node.get("abstract").toString())) {
						node.put("abstract", "");
					}
					if (!node.has("form") || ("null").equals(node.get("form").toString())) {
						node.put("form", "");
					}
					if (!node.has("genre") || ("null").equals(node.get("genre").toString())) {
						node.put("genre", "");
					}
					if (!node.has("symbol") || ("null").equals(node.get("symbol").toString())){
						node.put("symbol", "");
					}
					if (!node.has("provider") || ("null").equals(node.get("provider").toString())) {
						node.put("provider", "");
					}
					if (!node.has("url")) {
						node.put("url", "");
					}
					if (!node.has("content")) {
						node.put("content", "");
					}
					if (!node.has("html_content")) {
						node.put("html_content", "");
					}
					if (!node.has("category") || ("null").equals(node.get("category").toString())) {
						node.put("category", "");
					}
//					System.out.println("title:"+node.getString("title")+"-----"+node.getInt("id")+"----相关度："+node.getString("belong"));
					dataArr.put(node);
				}
			}
			newResultObj.put("num", total);
			newResultObj.put("page", page);
			newResultObj.put("data", dataArr);
		}
		return newResultObj.toString();
	}
	
	private static String newChangeDataStructures(String result, int page) {
		NumberFormat fmt = NumberFormat.getPercentInstance();  
		fmt.setMaximumFractionDigits(0);//最多两位百分小数，如25.23%
		JSONObject res = new JSONObject(result);
		JSONObject newResultObj = new JSONObject();
		if (res.has("error")) {
			return newResultObj.toString();
		} else {
			JSONArray dataArr = new JSONArray();
			JSONObject hits = res.getJSONObject("hits");
			int total = hits.getInt("total");
			JSONArray datas = hits.getJSONArray("hits");
			if(datas!=null && datas.length()>0) {
				for (int i = 0; i < datas.length(); i++) {
					JSONObject data = datas.getJSONObject(i);
					JSONObject node = data.getJSONObject("_source");
					JSONObject newNode = new JSONObject();
					newNode.put("belong", data.getString("_type"));
					newNode.put("id", data.getString("_id"));
					newNode.put("resourceUrl", node.getString("url"));
					newNode.put("title", node.getString("title"));
					double _score = data.getDouble("_score");
					if(_score>100){
						_score = 100;
					}
					String score = String.valueOf(_score);
					newNode.put("relevance", score.substring(0, score.indexOf("."))+"%");
					String bbrq = "";
					if (node.has("publishDate") && node.get("publishDate") instanceof String) {
						bbrq = node.getString("publishDate");
						if (StringUtils.isNotEmpty(bbrq)) {
							bbrq = bbrq.substring(0, 10);
						}
					}
					newNode.put("bbrq", bbrq);
					if (node.has("keyWords") && node.get("keyWords") instanceof JSONArray) {
						newNode.put("keyWords", node.getJSONArray("keyWords"));
					} else {
						newNode.put("keyWords", new JSONArray());
					}
					if (node.has("tags") && node.get("tags") instanceof JSONObject) {
						newNode.put("tags", node.getJSONObject("tags"));
					} else {
						newNode.put("tags", new JSONObject());
					}
					if (node.has("abstractText") && node.get("abstractText") instanceof String) {
						newNode.put("abstractText", node.getString("abstractText"));
					} else {
						newNode.put("abstractText", "");
					}
					if (node.has("form") && node.get("form") instanceof String) {
						newNode.put("form", node.getString("form"));
					} else {
						newNode.put("form", "");
					}
					if (node.has("genre") && node.get("genre") instanceof String) {
						newNode.put("genre", node.getString("genre"));
					} else {
						newNode.put("genre", "");
					}
					if (node.has("symbol") && node.get("symbol") instanceof String) {
						newNode.put("symbol", node.getString("symbol"));
					} else {
						newNode.put("symbol", "");
					}
					if (node.has("provider") && node.get("provider") instanceof String) {
						newNode.put("provider", node.getString("provider"));
					} else {
						newNode.put("provider", "");
					}
					if (node.has("originalAddress") && node.get("originalAddress") instanceof String) {
						newNode.put("url", node.getString("originalAddress"));
					} else {
						newNode.put("url", "");
					}
					if (node.has("content") && node.get("content") instanceof String) {
						newNode.put("content", node.getString("content"));
					} else {
						newNode.put("content", "");
					}
					if (node.has("html_content") && node.get("html_content") instanceof String) {
						newNode.put("html_content", node.getString("html_content"));
					} else {
						newNode.put("html_content", "");
					}
					if (node.has("belongDomain") && node.get("belongDomain") instanceof JSONArray) {
						newNode.put("category", node.getJSONArray("belongDomain"));
					} else {
						newNode.put("category", new JSONArray());
					}
//					System.out.println("title:"+node.getString("title")+"-----"+node.getString("id")+"----相关度："+node.getString("relevance"));
					dataArr.put(newNode);
				}
			}
			newResultObj.put("num", total);
			newResultObj.put("page", page);
			newResultObj.put("data", dataArr);
		}
		return newResultObj.toString();
		
	}
	
	private static String qlSearch(String dbName,int page, String ql) {
		StringEntity filters=new StringEntity(ql, "UTF-8");
	    filters.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
		HttpPost httpPost = null;
		try {
			long begin = System.currentTimeMillis();
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url = getESURL()+dbName+"/_search";
			//String url = getESURL()+"213trswcmv7/_search";
			httpPost = new HttpPost(url);
			httpPost.setEntity(filters);
			
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			long end = System.currentTimeMillis();
			long t = end-begin;
			if(t>200)
			{
				System.out.println("执行"+ElasticSearchUtil.class.getName()+".qlSearch方法耗时："+t+"毫秒");
			}
			return result;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null)
			{
				httpPost.releaseConnection();
			}
		}
		return null;
	}

	private static String sqlSearch(String sql) throws Exception {
//		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
//		parameters.add(new BasicNameValuePair("sql", sql));
		HttpPost httpPost = null;
		try {
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			String url = getESURL()+"_sql?sql="+UriEncoder.encode(sql);
			System.out.println("----111111111111111111-------------------------"+url+"--------------------------------");
			httpPost = new HttpPost(url);
//			httpPost.setEntity(new UrlEncodedFormEntity(parameters, ENCODE));
			long begin = System.currentTimeMillis();
	        CloseableHttpResponse response = httpClient.execute(httpPost, context);
	        HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			long end = System.currentTimeMillis();
			long t = end-begin;
			if(t>200) 
			{
				System.out.println("执行"+ElasticSearchUtil.class.getName()+".sqlSearch方法耗时："+t+"毫秒");
			}
			return result;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			if(httpPost!=null)
			{
				httpPost.releaseConnection();
			}
		}
		
		return null;
	}
	public static void main(String[] args) throws Exception {
		Map conditions = new HashMap();
//		conditions.put("text", text);
//		conditions.put("text", "发展 区域 区域性股权 股权市场 规范发展区域性股权市场 对证券期货市场实行集中统一监管 证券监督管理 中华人民共和国国务院 国家海洋局 中华人民共和国司法部 中华人民共和国公安部 国家煤矿安全监察局 国家外汇管理局 国家中医药管理局 国家文物局 国家邮政局 国家铁路局 国家测绘地理信息局 中华人民共和国农业部 国家公务员局 国家外国专家局 国家国防科技工业局 国家能源局 国家粮食局 国家信访局 国家自然科学基金委员会 全国社会保障基金理事会 中国保险监督管理委员会 中华人民共和国科学技术部 马凯 汪洋 张高丽 刘延东 李克强 中华人民共和国外交部 中华人民共和国监察部 中华人民共和国国防部 中华人民共和国水利部 中国证券监督管理委员会 中华人民共和国商务部 中华人民共和国工业和信息化部 中华人民共和国教育部 中华人民共和国交通运输部 中华人民共和国国家安全部 中华人民共和国环境保护部 中华人民共和国国土资源部 中华人民共和国住房和城乡建设部 中华人民共和国财政部 新华通讯社 中华人民共和国国家发展和改革委员会 中国社会科学学院 中华人民共和国民政部 国家体育总局 国家烟草专卖局 中华人民共和国人力资源和社会保障部 国家质量监督检验疫总局 中华人民共和国国家卫生和计划生育委员会 国家食品药品监管总局 中国银行行业监督管理委员会 国务院法制办公室 国务院港澳事务办公室 国务院侨务办公室 国务院参事室 国务院研究室 中华人民共和国国务院办公厅 国务院办公厅 国家安全生产监督管理总局 国家新闻出版广电总局 国家知识产权局 中国气象局 中国地震局 国家行政学院 国务院发展研究中心 中国工程院 中国科学院 国家机关事务管理局 国家宗教事务局 国家旅游局 中华人民共和国审计署 国家林业局 国家统计局 国家工商行政管理总局 国家税务总局 国务院国有资产监督管理委员会 中国人民银行 中华人民共和国文化部 中华人民共和国海关总署 国务院办公厅 中华人民共和国国务院 汪永清 肖捷 舒晓琴 丁向阳 毕井泉 江小涓 市场 内河航道 港口深水岸线 汽车客运站 水路交通环境 车站 经营性公路 行政权力监督管理办公室 航空运输企业航线 港口岸线 农村公路 港口 西部地区通县油路 收费公路 农村公路改造工程 政府还贷公路 交通网 码头 口岸 运输场站 博物馆 文化科技创新基地与平台 科学基金地区联络网点 投递服务站 国有资本布局 汽车市场 医疗实验室 珠江三角洲地区 深圳前海湾保税港区 自然科学基金联络网点 期货市场 地震活跃带 和谐社区 国际科技合作基地 革命烈士事迹陈列馆 民航无线电站址 机场道路 多边原地产 引智基地 东北地区老工业基地 民用机场公共活动区 减隔震建筑 烈士陵园 直升机场 天然气价格市场 革命烈士纪念堂 市场 烟叶工作站 民航小机场 民用机场道路 计划外烟厂 贫困县出口公路 交通科研基地 起运地 到达地 道路运输站 公路交通环境 交通战备高速公路 股票期权经纪业务试点 企业技术中心 民用直升机场 民航无线电台址 工程实验室 民用机场场址 工程研究中心 航空口岸 创业创新基地 林场 海洋地质国家重点实验室 证券中介律师事务所 股票交易所 股票期权交易试点 小微企业创业创新基地 投资试点 交易试点 公司债券发行试点 客户交易区 境内证券投资试点 期货市场业务创新试点 证券投资试点 期货交易场所 证券交易场所 证券市场 生态环境 国库 横琴出入境 公共图书馆 铁路路基 宗教活动场所 出版物市场 军民合用机场 深圳前海深港现代服务业合作区 集邮市场 自然科学基金工作地区 文化产业示范园区 海洋石油勘探开发环境 旅游区域 卫生科研基地 农村产权流转交易市场 文化馆（站） 重大产业基地 通知 决议 公报 通知 意见 命令（令） 决定 公告 通告 通报 议案 报告 请示 批复 函 会议纪要 股权 权利 权利义务 国防专利 国有股权 股东权利 民用航空器所有权 社保基金股权 排污权利 民用航空器抵押权 民用航空器优先权 民用航空器占有权 专利 实用新型专利 企业运营类专利 有关专利 旅游权利 上市公司股权 股权 内容 ");
//		conditions.put("text", "发展 区域 区域性股权 股权市场 规范发展区域性股权市场 证券监督管理 中华人民共和国国务院 国务院办公厅 市场 通知 股权 对证券期货市场实行集中统一监管 证券监督管理 中华人民共和国国务院 国家海洋局 中华人民共和国司法部 中华人民共和国公安部 国家煤矿安全监察局 国家外汇管理局 国家中医药管理局 国家文物局 国家邮政局 国家铁路局 国家测绘地理信息局 中华人民共和国农业部");
		conditions.put("text", "经济 经济社会 简政放权 党-机构编制");
		//{"text":"广州","categorys":"发展改革","orText":"规划  市 ","page":1,"pageSize":30,"isReturnContent":0}
//		conditions.put("orText", "规划  广州");
//		conditions.put("ignoreText", "授权");
		conditions.put("page", 1);
		conditions.put("pageSize", 1);
		conditions.put("isReturnContent", 0);
//		conditions.put("categorys", "领域实例");
//		conditions.put("sortType", 0);
//		conditions.put("sortField", "-bbrq");
		conditions.put("infoSources", "wcmmetatableflfg,wcmmetatablepartyliterature,wcmmetatabletechnicalstandard,wcmmetatablespecialpolicy,wcmmetatablescientificresearch,wcmmetatablepublicopinion,wcmmetatableleadershipinstruction,wcmmetatabletypicalevents,wcmmetatableforeignresources");
//		String result = elSearch(conditions);
		String ql = "select * from 213trswcmv7 where title='国务院办公厅关于规范发展区域性股权市场的通知' limit 0,1";
//		String ql = "select * from 213trswcmv7 where url='http://<host>/exck/专项政策库/政府/地方政府/西藏/西藏政府#article=国务院印发《通知》规范发展区域性股权市场' limit 0,1";
//		ql = UriEncoder.encode(ql);
//		System.out.println(ql);
		JSONObject result = searchBySql(ql, 1, 1);
		System.out.println(result);
	}
}
