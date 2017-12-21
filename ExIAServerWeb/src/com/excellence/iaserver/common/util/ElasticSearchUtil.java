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
 * @date 2017��6��29�� ����11:06:21 
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
//		System.out.println("+++++++++++++++��ѯ��Ϣ��Դ��ʱ��"+(b2-b1)+"ms++++++++++++++++++");
		if(result==null || "".equals(result.trim())) return null;
//		long b3 = System.currentTimeMillis();
		result = newChangeDataStructures(result, page);
//		long b4 = System.currentTimeMillis();
//		System.out.println("+++++++++++++++ת���ṹ��ʱ��"+(b4-b3)+"ms++++++++++++++++++");
		
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
		fmt.setMaximumFractionDigits(0);//�����λ�ٷ�С������25.23%
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
//					System.out.println("title:"+node.getString("title")+"-----"+node.getInt("id")+"----��ضȣ�"+node.getString("belong"));
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
		fmt.setMaximumFractionDigits(0);//�����λ�ٷ�С������25.23%
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
//					System.out.println("title:"+node.getString("title")+"-----"+node.getString("id")+"----��ضȣ�"+node.getString("relevance"));
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
				System.out.println("ִ��"+ElasticSearchUtil.class.getName()+".qlSearch������ʱ��"+t+"����");
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
				System.out.println("ִ��"+ElasticSearchUtil.class.getName()+".sqlSearch������ʱ��"+t+"����");
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
//		conditions.put("text", "��չ ���� �����Թ�Ȩ ��Ȩ�г� �淶��չ�����Թ�Ȩ�г� ��֤ȯ�ڻ��г�ʵ�м���ͳһ��� ֤ȯ�ල���� �л����񹲺͹�����Ժ ���Һ���� �л����񹲺͹�˾���� �л����񹲺͹������� ����ú��ȫ���� ����������� ������ҽҩ����� ��������� ���������� ������·�� ���Ҳ�������Ϣ�� �л����񹲺͹�ũҵ�� ���ҹ���Ա�� �������ר�Ҿ� ���ҹ����Ƽ���ҵ�� ������Դ�� ������ʳ�� �����ŷþ� ������Ȼ��ѧ����ίԱ�� ȫ����ᱣ�ϻ������»� �й����ռල����ίԱ�� �л����񹲺͹���ѧ������ �� ���� �Ÿ��� ���Ӷ� ���ǿ �л����񹲺͹��⽻�� �л����񹲺͹���첿 �л����񹲺͹������� �л����񹲺͹�ˮ���� �й�֤ȯ�ල����ίԱ�� �л����񹲺͹����� �л����񹲺͹���ҵ����Ϣ���� �л����񹲺͹������� �л����񹲺͹���ͨ���䲿 �л����񹲺͹����Ұ�ȫ�� �л����񹲺͹����������� �л����񹲺͹�������Դ�� �л����񹲺͹�ס���ͳ��罨�貿 �л����񹲺͹������� �»�ͨѶ�� �л����񹲺͹����ҷ�չ�͸ĸ�ίԱ�� �й�����ѧѧԺ �л����񹲺͹������� ���������ܾ� �����̲�ר���� �л����񹲺͹�������Դ����ᱣ�ϲ� ���������ල�������ܾ� �л����񹲺͹����������ͼƻ�����ίԱ�� ����ʳƷҩƷ����ܾ� �й�������ҵ�ල����ίԱ�� ����Ժ���ư칫�� ����Ժ�۰�����칫�� ����Ժ����칫�� ����Ժ������ ����Ժ�о��� �л����񹲺͹�����Ժ�칫�� ����Ժ�칫�� ���Ұ�ȫ�����ල�����ܾ� �������ų������ܾ� ����֪ʶ��Ȩ�� �й������ �й������ ��������ѧԺ ����Ժ��չ�о����� �й�����Ժ �й���ѧԺ ���һ����������� �����ڽ������ �������ξ� �л����񹲺͹������ ������ҵ�� ����ͳ�ƾ� ���ҹ������������ܾ� ����˰���ܾ� ����Ժ�����ʲ��ල����ίԱ�� �й��������� �л����񹲺͹��Ļ��� �л����񹲺͹��������� ����Ժ�칫�� �л����񹲺͹�����Ժ ������ Ф�� ������ ������ �Ͼ�Ȫ ��С� �г� �ںӺ��� �ۿ���ˮ���� ��������վ ˮ·��ͨ���� ��վ ��Ӫ�Թ�· ����Ȩ���ල����칫�� ����������ҵ���� �ۿڰ��� ũ�幫· �ۿ� ��������ͨ����· �շѹ�· ũ�幫·���칤�� ����������· ��ͨ�� ��ͷ �ڰ� ���䳡վ ����� �Ļ��Ƽ����»�����ƽ̨ ��ѧ��������������� Ͷ�ݷ���վ �����ʱ����� �����г� ҽ��ʵ���� �齭�����޵��� ����ǰ���屣˰���� ��Ȼ��ѧ������������ �ڻ��г� �����Ծ�� ��г���� ���ʿƼ��������� ������ʿ�¼����й� �����ߵ�վַ ������· ���ԭ�ز� ���ǻ��� ���������Ϲ�ҵ���� ���û���������� �������� ��ʿ��԰ ֱ������ ��Ȼ���۸��г� ������ʿ������ �г� ��Ҷ����վ ��С���� ���û�����· �ƻ����̳� ƶ���س��ڹ�· ��ͨ���л��� ���˵� ����� ��·����վ ��·��ͨ���� ��ͨս�����ٹ�· ��Ʊ��Ȩ����ҵ���Ե� ��ҵ�������� ����ֱ������ �����ߵ�ַ̨ ����ʵ���� ���û�����ַ �����о����� ���տڰ� ��ҵ���»��� �ֳ� ������ʹ����ص�ʵ���� ֤ȯ�н���ʦ������ ��Ʊ������ ��Ʊ��Ȩ�����Ե� С΢��ҵ��ҵ���»��� Ͷ���Ե� �����Ե� ��˾ծȯ�����Ե� �ͻ������� ����֤ȯͶ���Ե� �ڻ��г�ҵ�����Ե� ֤ȯͶ���Ե� �ڻ����׳��� ֤ȯ���׳��� ֤ȯ�г� ��̬���� ���� ���ٳ��뾳 ����ͼ��� ��··�� �ڽ̻���� �������г� ������û��� ����ǰ������ִ�����ҵ������ �����г� ��Ȼ��ѧ���������� �Ļ���ҵʾ��԰�� ����ʯ�Ϳ�̽�������� �������� �������л��� ũ���Ȩ��ת�����г� �Ļ��ݣ�վ�� �ش��ҵ���� ֪ͨ ���� ���� ֪ͨ ��� ���� ���� ���� ͨ�� ͨ�� �鰸 ���� ��ʾ ���� �� �����Ҫ ��Ȩ Ȩ�� Ȩ������ ����ר�� ���й�Ȩ �ɶ�Ȩ�� ���ú���������Ȩ �籣�����Ȩ ����Ȩ�� ���ú�������ѺȨ ���ú���������Ȩ ���ú�����ռ��Ȩ ר�� ʵ������ר�� ��ҵ��Ӫ��ר�� �й�ר�� ����Ȩ�� ���й�˾��Ȩ ��Ȩ ���� ");
//		conditions.put("text", "��չ ���� �����Թ�Ȩ ��Ȩ�г� �淶��չ�����Թ�Ȩ�г� ֤ȯ�ල���� �л����񹲺͹�����Ժ ����Ժ�칫�� �г� ֪ͨ ��Ȩ ��֤ȯ�ڻ��г�ʵ�м���ͳһ��� ֤ȯ�ල���� �л����񹲺͹�����Ժ ���Һ���� �л����񹲺͹�˾���� �л����񹲺͹������� ����ú��ȫ���� ����������� ������ҽҩ����� ��������� ���������� ������·�� ���Ҳ�������Ϣ�� �л����񹲺͹�ũҵ��");
		conditions.put("text", "���� ������� ������Ȩ ��-��������");
		//{"text":"����","categorys":"��չ�ĸ�","orText":"�滮  �� ","page":1,"pageSize":30,"isReturnContent":0}
//		conditions.put("orText", "�滮  ����");
//		conditions.put("ignoreText", "��Ȩ");
		conditions.put("page", 1);
		conditions.put("pageSize", 1);
		conditions.put("isReturnContent", 0);
//		conditions.put("categorys", "����ʵ��");
//		conditions.put("sortType", 0);
//		conditions.put("sortField", "-bbrq");
		conditions.put("infoSources", "wcmmetatableflfg,wcmmetatablepartyliterature,wcmmetatabletechnicalstandard,wcmmetatablespecialpolicy,wcmmetatablescientificresearch,wcmmetatablepublicopinion,wcmmetatableleadershipinstruction,wcmmetatabletypicalevents,wcmmetatableforeignresources");
//		String result = elSearch(conditions);
		String ql = "select * from 213trswcmv7 where title='����Ժ�칫�����ڹ淶��չ�����Թ�Ȩ�г���֪ͨ' limit 0,1";
//		String ql = "select * from 213trswcmv7 where url='http://<host>/exck/ר�����߿�/����/�ط�����/����/��������#article=����Ժӡ����֪ͨ���淶��չ�����Թ�Ȩ�г�' limit 0,1";
//		ql = UriEncoder.encode(ql);
//		System.out.println(ql);
		JSONObject result = searchBySql(ql, 1, 1);
		System.out.println(result);
	}
}
