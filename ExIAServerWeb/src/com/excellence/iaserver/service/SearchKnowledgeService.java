package com.excellence.iaserver.service;


import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.excellence.exke.common.vo.ConceptVo;
import com.excellence.exke.connector.Configure;
import com.excellence.exke.connector.ConnectorException;
import com.excellence.exke.connector.Host;
import com.excellence.exke.connector.HttpConnector;
import com.excellence.exke.resource.adapter.IResourceAdapter;
import com.excellence.exke.resource.adapter.ResourceAdapterFactory;
import com.excellence.exke.resource.adapter.trs.service.WCMService;
import com.excellence.iamp.service.TagRecordService;
import com.excellence.iamp.vo.TagRecord;
import com.excellence.iaserver.common.cache.CacheClient;
import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.HanLPUtil;
import com.excellence.iaserver.common.util.SHAUtil;


/**
 * 知识查询类
 * 
 * @author huangjinyuan
 * @created 2016-11-7
 * @copyright Excellence co.
 */
@Service("searchKnowledgeService")
public class SearchKnowledgeService {
	
	@SuppressWarnings("static-access")
	public SearchKnowledgeService() {
		resourceAdapterFactory.init(Constant.ADAPTER_CLASS);
	}
	
	private ResourceAdapterFactory resourceAdapterFactory;
	
	@Autowired
	private CacheClient cacheClient;
	@Autowired
	private TagRecordService tagRecordService;
	
	private HttpConnector getConnector() throws ConnectorException {
		return this.getConnector("ExKE", "ExKE");
	}
	
	private HttpConnector getConnector(String connectContext, String serviceContext) throws ConnectorException{
		Host host = new Host(Constant.EXKE_HOST, Constant.EXKE_PORT);
		Configure configure = new Configure();
		configure.setHost(host);
		configure.setContext(connectContext);
		configure.setAccount(Constant.EXKE_ACCOUNT);
		configure.setPassword(Constant.EXKE_PASSWORD);
		configure.setServiceContext(serviceContext);
		HttpConnector connector = new HttpConnector(configure);
		connector.connect();
		return connector;
	}
	
	public JSONObject getConcept(String id) throws ConnectorException {
		JSONObject json;
		String key = "_sk_getConcept_"+(id != null ?id:"");
		if(cacheClient.get(key) != null) {
			json = new JSONObject((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getConcept(id);
			cacheClient.set(key, json.toString());
		 return json;
	}
	
	public JSONArray getOntologiesByKeywords(String words) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getOntologiesByKeywords_"+(words != null ?words:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getOntologiesByKeywords(words);
			cacheClient.set(key, json.toString());
		 return json;
	}
	
	public JSONArray getOntologiesByIds(String ids) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getOntologiesByIds_"+(ids != null ?ids:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getOntologiesByIds(ids);
			cacheClient.set(key, json.toString());
		 return json;
	}
	
	public JSONArray getOntologies(String conceptId,String conditions) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getOntologies_"+(conceptId != null ?conceptId:"")+"_"+(conditions != null ?conditions:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getOntologies(conceptId,conditions);
			cacheClient.set(key, json.toString());
		 return json;
	}
	
	public JSONArray getOntologies(String conceptId,String conditions,int num) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getOntologies_"+(conceptId != null ?conceptId:"")+"_"+(conditions != null ?conditions:"")+num;
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getOntologies(conceptId,conditions,num);
			cacheClient.set(key, json.toString());
		 return json;
	}
	
	public JSONArray getInformationsByIdsAndCondition(String ids,String infoCondition) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getInformationsByIdsAndCondition_"+(ids != null ?ids:"")+"_"+(infoCondition != null ?infoCondition:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		json = this.getConnector().getInformationsByIdsAndCondition(ids, infoCondition);
		cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONArray getInformationsByKeywordsAndCondition(String words,String infoCondition) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getInformationsByKeywordsAndCondition_"+(words != null ?words:"")+"_"+(infoCondition != null ?infoCondition:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		
		 json = this.getConnector().getInformationsByKeywordsAndCondition(words, infoCondition);
			cacheClient.set(key, json.toString());
		 return json;
	}
	
	public JSONArray getInformations(String conceptId, String conditions, String infoCondition) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getInformations_"+(conceptId != null ?conceptId:"")+"_"+(conditions != null ?conditions:"")+"_"+(infoCondition != null ?infoCondition:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getInformations(conceptId, conditions, infoCondition);
			cacheClient.set(key, json.toString());
		 return json;
	}
	
	public void getThemeWordsOutputStream(OutputStream out) throws ConnectorException {
		this.getConnector().getThemeWordsOutputStream(out);
	}
	
	public JSONArray getTermsByText(String text) throws ConnectorException 
	{
		if(StringUtils.isBlank(text))
			return null;
		JSONArray json;
		String key = "_sk_getTermsByText_";
		try {
			key = "_sk_getTermsByText_"+SHAUtil.encryptSHA(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(cacheClient.get(key) != null) {
			String val = (String)cacheClient.get(key);
			json = new JSONArray(val);
		} else {
			json = this.getConnector().getTermsByText(text);
			cacheClient.set(key, json.toString());
		}
		return json;
	}
	
	public JSONArray getActionConceptDefsByBodyAndObject(String context, String bodyConceptId, String objConceptId) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getActionConceptDefsByBodyAndObject_"+(context != null ?context:"")+"_"+(bodyConceptId != null ?bodyConceptId:"")+"_"+(objConceptId != null ?objConceptId:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		
		 json = this.getConnector().getActionConceptDefsByBodyAndObject(context, bodyConceptId, objConceptId);
			cacheClient.set(key, json.toString());
		 return json;
	}
	
	public JSONObject getRelatedKnowledgesByAction(String context, String actConceptId) throws ConnectorException {
		JSONObject json;
		String key = "_sk_getRelatedKnowledgesByAction_"+(context != null ?context:"")+"_"+(actConceptId != null ?actConceptId:"");
		if(cacheClient.get(key) != null) {
			json = new JSONObject((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getRelatedKnowledgesByAction(context, actConceptId);
			cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONArray getOntologiesAndInfosByKeywords(String context, String words, int inforMaxSize) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getOntologiesAndInfosByKeywords_"+(context != null ?context:"")+"_"+(words != null ?words:"")+"_"+inforMaxSize;
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getOntologiesAndInfosByKeywords(context, words, inforMaxSize);
			cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONArray getConceptsByKeywords(String words) throws Exception {
		JSONArray json;
		String key = "_sk_getConceptsByKeywords_"+(StringUtils.isNotBlank(words) ?SHAUtil.encryptSHA(words):"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		json = this.getConnector().getConceptsByKeywords(words);
		cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONArray getConceptsByContents(String contents) throws Exception {
		if(StringUtils.isEmpty(contents))return null;
		JSONArray jsonConcepts;
		String key = "_sk_getBodysOfObjects_"+(contents != null ?contents:"");
		if(cacheClient.get(key) != null) {
			jsonConcepts = new JSONArray((String)cacheClient.get(key));
			return jsonConcepts;
		}
		
		jsonConcepts = 	this.getConceptsByKeywords(contents);
		if(jsonConcepts==null || jsonConcepts.length()==0)return null;
		try{
			for(int i = jsonConcepts.length()-1; i >=0 ; i--){
				JSONObject jsonConcept = jsonConcepts.getJSONObject(i);
				String content = jsonConcept.getString("content");
				if(contents.indexOf("'"+content+"'")==-1
					&& contents.indexOf("\""+content+"\"") ==-1){
					jsonConcepts.remove(i);
				}
			}
			cacheClient.set(key, jsonConcepts.toString());
			return jsonConcepts;
		}catch(Exception ex){
			return null;
		}
	}
	
	public JSONArray getBodysOfObjects(String objectIds) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getBodysOfObjects_"+(objectIds != null ?objectIds:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getBodysOfObjects(objectIds);
			cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONArray getOntologiesByConceptIdsAndConditions(String conceptIds,String conditions) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getOntologiesByConceptIdsAndConditions_"+(conceptIds != null ?conceptIds:"")+"_"+(conditions != null ?conditions:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getOntologiesByConceptIdsAndConditions(conceptIds,conditions);
			cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONArray getRelationDefinitionsByNames(String names) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getRelationDefinitionsByNames_"+(names != null ?names:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getRelationDefinitionsByNames(names);
			cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONArray getRecommendedKeywords(String words) throws ConnectorException {
		JSONArray json;
		String key = "_sk_getRecommendedKeywords_"+(words != null ?words:"");
		if(cacheClient.get(key) != null) {
			json = new JSONArray((String)cacheClient.get(key));
			return json;
		}
		
		 json = this.getConnector().getRecommendedKeywords(words);
			cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONObject searchConceptDefsOfText(String context, String text) throws ConnectorException {
		JSONObject json;
		String key = "_sk_searchConceptDefsOfText_"+(context != null ?context:"")+"_"+(text!=null ?text:"");
		if(cacheClient.get(key) != null) {
			json = new JSONObject((String)cacheClient.get(key));
			return json;
		}
		
		 json = this.getConnector().searchConceptDefsOfText(context,text);
			cacheClient.set(key, json.toString());
		return json;
	}
	
	public JSONObject getRelatedFiveElements(String keywords) throws ConnectorException {
		JSONObject json;
		String key = "_sk_getRelatedFiveElements_"+(keywords !=null?keywords:"");
		if(cacheClient.get(key) != null) {
			json = new JSONObject((String)cacheClient.get(key));
			return json;
		}
		 json = this.getConnector().getRelatedFiveElements(keywords);
		cacheClient.set(key, json.toString());
		return json;
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	public OutputStream getInformationsByUrlAndAttribute(String url,String attribute) {
		System.out.println("--------------"+url+"-----------------------");
		try {
			URL url_ = new URL(url);
			if(!resourceAdapterFactory.isInit()) {
				System.out.println("CONSTANT IS " + Constant.ADAPTER_CLASS);
				resourceAdapterFactory.init(Constant.ADAPTER_CLASS);
			}
			System.out.println("is init():" + resourceAdapterFactory.isInit());
			IResourceAdapter adapter = resourceAdapterFactory.getAdapter(url_);
			if(adapter != null) {
				Map<String, Object> attrMap = new HashMap<String, Object>();
				if (attribute != null && !attribute.equals("")) {
					JSONObject attrJson = new JSONObject(attribute);
					Iterator keys = attrJson.keys();
					while(keys.hasNext()) {
						String key = keys.next().toString();
						attrMap.put(key, attrJson.get(key));
					}
				}
				return adapter.getResource(url_, attrMap);
			} else {
				System.out.println("adapter is null!");
				return null;
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查找相关性资源
	 * @param domainIds 领域过滤的ID列表
	 * @param parentIds 父类过滤的ID列表
	 * @param originalText 需要匹配查找的相关资源
	 * @return 相关概念
	 */
	public JSONArray searchRelevantContent(String[] domainIds,
			String[] parentIds, String originalText) {	
		JSONArray jsonConcepts;
		String key = "_sk_searchRelevantContent_"+(domainIds != null ?StringUtils.join(domainIds,";"):"")+"_"+(parentIds!=null ?StringUtils.join(parentIds,";"):"")+"_"+(originalText!=null?originalText:"");
		if(cacheClient.get(key) != null) {
			jsonConcepts = new JSONArray((String)cacheClient.get(key));
			return jsonConcepts;
		}
		try{
			List words = HanLPUtil.getWords(originalText, false, false, 1);
			JSONArray jsonWords = new JSONArray(words);
			if(words==null || words.size()==0)return null;
			 jsonConcepts = this.getConnector()
					.getRelevantConceptDefs(jsonWords.toString(), 1, 100);
			
			if(jsonConcepts==null || jsonConcepts.length()==0)return null;
			
			jsonConcepts = this.similarOrder(originalText, jsonConcepts);
			
			// 过滤领域
			if(domainIds!=null && domainIds.length>0){
				List<String> listDomainIds = Arrays.asList(domainIds);
				JSONArray jsonTemp = new JSONArray();
				for(int i = 0 ; i < jsonConcepts.length() ; i++){
					JSONObject jsonConcept = jsonConcepts.getJSONObject(i);
					JSONArray jsonAttributes = jsonConcept.getJSONArray("attributes");
					for(int j = 0 ; j < jsonAttributes.length() ; j++){
						JSONObject jsonAttribute = jsonAttributes.getJSONObject(j);
						if(jsonAttribute.getString("name").equals("MNG_DOMAIN_ID")){
							// 判断“主管领域”
							String mngDomainId = jsonAttribute.getString("value");
							if(listDomainIds.indexOf(mngDomainId)>-1){
								jsonTemp.put(jsonConcept);
								break;
							}
						}else if(jsonAttribute.getString("name").equals("BELONG_DOMAIN_IDS")){
							//判断“所属领域”
							JSONArray jsonBelongDomainIds = jsonAttribute.getJSONArray("value");
							if(jsonBelongDomainIds!=null && jsonBelongDomainIds.length()>0){
								for(int k = 0 ; k < jsonBelongDomainIds.length() ; k++){
									String belongDomainId = jsonBelongDomainIds.getString(k);
									if(listDomainIds.indexOf(belongDomainId)>-1){
										jsonTemp.put(jsonConcept);
										break;
									}
								}
							}
						}
					}
				}
				jsonConcepts = jsonTemp;
			}
			
			// 过滤父类
			if(parentIds!=null&&parentIds.length>0){
				List<String> listParentIds = Arrays.asList(parentIds);
				JSONArray jsonTemp = new JSONArray();
				for(int i = 0 ; i < jsonConcepts.length() ; i++){
					JSONObject jsonConcept = jsonConcepts.getJSONObject(i);
					JSONArray jsonParentIds = jsonConcept.getJSONArray("parentIds");
					for(int j = 0 ; j < jsonParentIds.length(); j++){
						String parentId = jsonParentIds.getString(j);
						if(listParentIds.indexOf(parentId)>-1){
							jsonTemp.put(jsonConcept);
							break;
						}
					}
				}
				jsonConcepts = jsonTemp;
			}
			cacheClient.set(key, jsonConcepts.toString());
			return jsonConcepts;
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	public JSONArray searchSBO(String text){
		JSONArray jsonResult = new JSONArray();
		JSONArray jsonObjects = new JSONArray();
		String key = "_sk_searchSBO_"+(text!=null?text.hashCode():"");
		if(cacheClient.get(key) != null) {
			jsonResult = new JSONArray((String)cacheClient.get(key));
			return jsonResult;
		}
		
		try{
			JSONArray jsonWords = this.getConnector().getTermsByText(text);
			JSONArray jsonConcepts = null;
			if(jsonWords!=null && jsonWords.length()>0){
				jsonConcepts = this.getConceptsByKeywords(jsonWords.toString());
				if(jsonConcepts!=null && jsonConcepts.length()>0){
					for(int i=0;i<jsonConcepts.length();i++){
						JSONObject jsonConcept = jsonConcepts.getJSONObject(i);
						JSONArray parents = jsonConcept.getJSONArray("parentNames");
						if(parents.toString().indexOf("\"客体\"")>-1){
							jsonObjects.put(jsonConcept);
						}
						jsonResult.put(jsonConcept);
					}
				}
			}
			
			TRSProtalService trsProtalService = new TRSProtalService();
			JSONArray jsonSegWords = trsProtalService.segTextHasPos(text);
			JSONArray jsonBehavioresText = new JSONArray();
			String prevText = null;
			for(int i = 0 ; i < jsonSegWords.length() ; i++){
				JSONObject jsonSegWord = jsonSegWords.getJSONObject(i);
				String word = jsonSegWord.getString("text");
				String pos =  jsonSegWord.getString("pos").toLowerCase();
				if(pos.indexOf("v")>-1){
					for(int j = 0 ; j < jsonObjects.length(); j++){
						JSONObject jsonObject = jsonObjects.getJSONObject(j);
						String content = jsonObject.getString("content");
						if(content.equals(word))continue;
						if(prevText==null || !prevText.equals(word+content)){
							jsonBehavioresText.put(word+content);
						}
						prevText = word+content;
					}
				}
			}
			
			jsonConcepts = this.getConceptsByKeywords(jsonBehavioresText.toString());
			if(jsonConcepts!=null && jsonConcepts.length()>0){
				for(int i=0; i < jsonConcepts.length() ; i++){
					JSONObject jsonConcept = jsonConcepts.getJSONObject(i);
					jsonResult.put(jsonConcept);
				}
			}
			cacheClient.set(key, jsonResult.toString());
			return jsonResult;
		}catch(Exception ex){
			return null;
		}
	}
	
	private JSONArray similarOrder(String originalText, JSONArray jsonConcepts){
		if(jsonConcepts==null || jsonConcepts.length()==0) return jsonConcepts;
		
		List<JSONObject> list = new ArrayList<JSONObject>();
		for(int i = 0 ; i < jsonConcepts.length(); i++){
			JSONObject jsonConcept = jsonConcepts.getJSONObject(i);
			String currentContent = jsonConcept.getString("content");
			double similarity = CosineSimilarAlgorithm.getSimilarity(
					originalText, currentContent);
			jsonConcept.put("similarity", similarity);
			list.add(jsonConcept);
		}
		
		Collections.sort(list, new Comparator<JSONObject>(){
			public int compare(JSONObject o1, JSONObject o2) {
				if(o1==null || o2==null) return 0;
				if(!o1.has("similarity") || !o2.has("similarity"))return 0;
				double o1Similarity = o1.getDouble("similarity");
				double o2Similarity = o2.getDouble("similarity");
				if(o1Similarity>o2Similarity)return -1;
				else if(o1Similarity<o2Similarity)return 1;
				else return 0;
			}
		});
		
		for(JSONObject jsonConcept : list){
			jsonConcepts.put(jsonConcept);
		}
		return jsonConcepts;
	}
	
	public JSONObject getSimpleUrl(String complexUrl) {
		JSONObject json = new JSONObject();
		String key = "_sk_getSimpleUrl_"+(complexUrl!=null?complexUrl:"");
		if(cacheClient.get(key) != null) {
			json = new JSONObject((String)cacheClient.get(key));
			return json;
		}
		try {
			WCMService wcmService = new WCMService();
			json.put("simpleUrl", wcmService.complexUrl2SimpleUrl(complexUrl));
			cacheClient.set(key, json.toString());
			return json;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public JSONObject getComplexUrl(String simpleUrl) {
		JSONObject json = new JSONObject();
		String key = "_sk_getComplexUrl_"+(simpleUrl!=null?simpleUrl:"");
		if(cacheClient.get(key) != null) {
			json = new JSONObject((String)cacheClient.get(key));
			return json;
		}
		try {
			WCMService wcmService = new WCMService();
			json.put("complexUrl", wcmService.simpleUrl2ComplexUrl(simpleUrl));
			cacheClient.set(key, json.toString());
			return json;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public JSONArray nagaoText(String text){
		JSONArray jsonResult = new JSONArray();
		String key = "_sk_nagaoText_"+(text!=null?text:"");
		if(cacheClient.get(key) != null) {
			jsonResult = new JSONArray((String)cacheClient.get(key));
			return jsonResult;
		}
		String result = NagaoAlgorithm.applyNagao(text);
		if(StringUtils.isNotEmpty(result)){
			String[] words = StringUtils.split(result, '\n');
			for(int i = 0 ; i < words.length ; i++){
				jsonResult.put(words[i]);
			}
			cacheClient.set(key, jsonResult.toString());
		}
		return jsonResult; 
	}
	
	//------ 京华智慧化应用支撑平台服务接口参考手册V0.2 ------
	/**
	 * 3.2.1	获取大领域或子领域清单
	 * @param extoken
	 * @param parentDomainName
	 * @return
	 */
	public JSONObject getDomains(String extoken, String parentDomainName){
		JSONObject jsonResult;
		String key = "_sk_getDomains_"+(parentDomainName!=null?parentDomainName:"");
		if(cacheClient.get(key) != null) {
			jsonResult = new JSONObject((String)cacheClient.get(key));
			return jsonResult;
		}
		try{
			jsonResult = this.getConnector().getDomains(extoken, parentDomainName);
			cacheClient.set(key, jsonResult.toString());
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", "连接ExKE失败");
		}
		return jsonResult;
	}
	
	/**
	 * 3.2.2	获取指定领域下的所有关键词
	 * @param extoken
	 * @param domainName
	 * @param limit
	 * @return
	 * @throws ConnectorException
	 */
	public JSONObject getDomainKeywords(String extoken, String domainName, int limit){
		JSONObject jsonResult;
		String key = "_sk_getDomainKeywords_"+(domainName !=null?domainName:"")+"_"+limit;
		if(cacheClient.get(key) != null) {
			jsonResult = new JSONObject((String)cacheClient.get(key));
			return jsonResult;
		}
		try{
			jsonResult = this.getConnector().getDomainKeywords(extoken, domainName, limit);
			cacheClient.set(key, jsonResult.toString());
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", "连接ExKE失败");
		}
		return jsonResult;
	}
	
	/**
	 * 3.2.3	获取指定领域下的概念清单
	 * @param extoken
	 * @param domainName
	 * @param limit
	 * @return
	 * @throws ConnectorException
	 */
	public JSONObject getDomainConcepts(String extoken, String domainName, String[] parentNames, String type, int limit){
		JSONObject jsonResult;
		String key = "_sk_getDomainConcepts_"+(domainName!=null?domainName:"")+"_"+(parentNames !=null ? StringUtils.join(parentNames,";"):"")+"_"+limit;
		if(cacheClient.get(key) != null) {
			jsonResult = new JSONObject((String)cacheClient.get(key));
			return jsonResult;
		}
		try{
			jsonResult = this.getConnector().getDomainConcepts(extoken, domainName, parentNames, type, limit);
			cacheClient.set(key, jsonResult.toString());
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", "连接ExKE失败");
		}
		return jsonResult;
	}
	
	/**
	 * 3.2.4	根据概念名称获取概念对象
	 * @param extoken
	 * @param domainName
	 * @param limit
	 * @return
	 * @throws ConnectorException
	 */
	public JSONObject getConceptsByNames(String extoken, String[] conceptNames){
		JSONObject jsonResult;
		String key = "_sk_getConceptsByNames_"+(conceptNames!=null?StringUtils.join(conceptNames,";"):"");
		if(cacheClient.get(key) != null) {
			jsonResult = new JSONObject((String)cacheClient.get(key));
			return jsonResult;
		}
		try{
			jsonResult = this.getConnector().getConceptsByNames(extoken, conceptNames);
			cacheClient.set(key, jsonResult.toString());
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", "连接ExKE失败");
		}
		return jsonResult;
	}
	
	/**
	 * 3.2.5	获取指定概念的相关概念
	 * @param extoken
	 * @param domainName
	 * @param limit
	 * @return
	 * @throws ConnectorException
	 */
	public JSONObject getRelevant(String extoken, String[] srcIds, String[] relationDefIds,
			int direction, String[] parentNames, String conceptType, int limit){
		JSONObject jsonResult;
		String key = "_sk_getRelevant_"+(srcIds != null ?StringUtils.join(srcIds,";"):"")+"_"+(relationDefIds!=null ?StringUtils.join(relationDefIds,";"):"")+"_"+direction+"_"+(parentNames!=null?StringUtils.join(parentNames,";"):"")+"_"+(conceptType!=null?conceptType:"")+"_"+limit;
		if(cacheClient.get(key) != null) {
			jsonResult = new JSONObject((String)cacheClient.get(key));
			return jsonResult;
		}
		try{
			jsonResult = this.getConnector().getRelevance(extoken, srcIds, relationDefIds,
					direction, parentNames, conceptType, limit);
			cacheClient.set(key, jsonResult.toString());
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", "连接ExKE失败");
		}
		return jsonResult;
	}
	
	/**
	 * 3.2.6	获取wcm树结构数据
	 * @param url
	 * @return
	 * @throws ConnectorException
	 */
	public JSONObject getWCMCatalogTree(String url){
		JSONObject jsonResult;
		String key = "_sk_getWCMCatalogTree_"+(url!=null ? url:"");
		if(cacheClient.get(key) != null) {
			jsonResult = new JSONObject((String)cacheClient.get(key));
			return jsonResult;
		}
		try{
			jsonResult = this.getConnector("ExKE", "ExKEResource").getWCMCatalogTree(url);
			cacheClient.set(key, jsonResult.toString());
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
		}
		return jsonResult;
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
	
	@Async
	public void updateTagRecord(String userIp,String userName,String tagType,String tagName,String subjectAccount) throws Exception{
		Map queryCondition = new HashMap();
		queryCondition.put("subjectAccount", subjectAccount);
		queryCondition.put("tagType", tagType);
		queryCondition.put("tagName", tagName);
		List<TagRecord> tagRecordList = tagRecordService.getTagRecordByCondition(queryCondition);
		Map map = new HashMap();
		map.put("领域", "TT01");
		map.put("主体", "TT02");
		map.put("行为", "TT03");
		map.put("客体", "TT04");
		map.put("空间", "TT05");
		map.put("时间", "TT06");
		
		
		if(tagRecordList!=null && tagRecordList.size()>0){
			TagRecord tagRecordByCondition = tagRecordList.get(0);
			tagRecordByCondition.setNum(tagRecordByCondition.getNum()+1);
			tagRecordByCondition.setUpdateDate(new Date());
			
			tagRecordService.updateTagRecord(tagRecordByCondition);
		}else{
			TagRecord tagRecord = new TagRecord();
			tagRecord.setSubjectId(userIp);
			tagRecord.setSubjectAccount(subjectAccount);
			tagRecord.setSubjectName(userName);
			tagRecord.setTagCode((String)map.get(tagType));
			tagRecord.setTagName(tagName);
			tagRecord.setTagType(tagType);
			tagRecord.setNum(1);
			
			tagRecordService.createTagRecord(tagRecord);
		}
	}
}
