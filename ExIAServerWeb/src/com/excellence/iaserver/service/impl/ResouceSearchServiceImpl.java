package com.excellence.iaserver.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.excellence.exke.common.ql.QLConstant;
import com.excellence.exke.common.ql.SearchFacade;
import com.excellence.exke.common.ql.processor.IInfoSearcher;
import com.excellence.iaserver.common.util.ElasticSearchUtil;
import com.excellence.iaserver.ql.processor.infosearcher.ElasticSearchSearcher;
import com.excellence.iaserver.ql.processor.weighter.ElasticSearchWeighter;
import com.excellence.iaserver.service.ResouceSearchService;

@Service("resouceSearchService")
public class ResouceSearchServiceImpl implements ResouceSearchService {

	/**
	 * ���������ƣ�ͬ��ʣ���ظ����ͬ��ʷ����ѯ��List
	 * @param conceptArr
	 * @param words
	 */
	private void wordsFromOntoOfReqAddList(JSONArray conceptArr,List<String> words) {
		if(conceptArr!=null && conceptArr.length()>0) {
			for(int i=0;i<conceptArr.length();i++) {
				String word = null;
				if(!conceptArr.getJSONObject(i).isNull("content")) {
					word = conceptArr.getJSONObject(i).getString("content");
				}
				if(StringUtils.isNotBlank(word) && !words.contains(word)) {
					words.add(word);
				}
				//��ȡͬ���
				if(!conceptArr.getJSONObject(i).isNull("synonyms")) {
					JSONArray synonyms = conceptArr.getJSONObject(i).getJSONArray("synonyms");
					for(int j=0;j<synonyms.length();j++)
					{
						if(StringUtils.isNotBlank(synonyms.getString(j)) && !words.contains(synonyms.getString(j)))
						{
							words.add(synonyms.getString(j));
						}
					}
				}
				//��ȡ��������
				if(!conceptArr.getJSONObject(i).isNull("relevantConcepts")) {
					JSONArray relevantConcepts = conceptArr.getJSONObject(i).getJSONArray("relevantConcepts");
					for(int j=0;j<relevantConcepts.length();j++) {
						JSONObject relevantConcept = relevantConcepts.getJSONObject(j);
						if(StringUtils.isNotBlank(relevantConcept.getString("content")) && !words.contains(relevantConcept.getString("content"))){
							words.add(relevantConcept.getString("content"));
						}
						//��ȡͬ���
						if(!relevantConcept.isNull("synonyms")) {
							JSONArray synonyms = relevantConcept.getJSONArray("synonyms");
							for(int k=0;k<synonyms.length();k++)
							{
								if(StringUtils.isNotBlank(synonyms.getString(k)) && !words.contains(synonyms.getString(k)))
								{
									words.add(synonyms.getString(k));
								}
							}
						}
					}
				}
				
			}
		}
	}
	
	/**
	 * ��ȡel��ѯ��Ҫ����Ĵ�
	 * @param ontoOfReq
	 * @return
	 */
	private List<String> getWordsFromOntoOfReq(JSONObject ontoOfReq) {
		List<String> words = new ArrayList<String>();
		//��ȡ���������������
		JSONArray domainArr = getJSONArray(ontoOfReq,"domains");
		wordsFromOntoOfReqAddList(domainArr,words);
		//��ȡ��������������
		JSONArray actArr = getJSONArray(ontoOfReq,"acts");
		wordsFromOntoOfReqAddList(actArr,words);
		//��ȡ��ػ���������
		JSONArray orgPerArr = getJSONArray(ontoOfReq,"organdpersons");
		wordsFromOntoOfReqAddList(orgPerArr,words);
		//��ȡ�����Ʒ
		JSONArray objArr = getJSONArray(ontoOfReq,"objects");
		wordsFromOntoOfReqAddList(objArr,words);
		//��ȡ��ؿռ�
		JSONArray spaceArr = getJSONArray(ontoOfReq,"spaces");
		wordsFromOntoOfReqAddList(spaceArr,words);
		//��ȡ���ʱ��
		JSONArray timeArr = getJSONArray(ontoOfReq,"times");
		wordsFromOntoOfReqAddList(timeArr,words);
		//��ȡ������������
		JSONArray keywordArr = getJSONArray(ontoOfReq,"keywords");
		wordsFromOntoOfReqAddList(keywordArr,words);
		
		return words;		
	}
	
	private Map<String,Double> getWordRelevancies(JSONObject ontologies){
		Map<String,Double> wordRelevancies = new HashMap<String,Double>();
		String[] ontologyTypes = new String[]{"domains", "acts", "organdpersons", "objects", "spaces", "times", "keywords"};
		for(int i = 0 ; i < ontologyTypes.length ; i++){
			JSONArray jsonTmpOntologies = getJSONArray(ontologies, ontologyTypes[i]);
			if(jsonTmpOntologies==null || jsonTmpOntologies.length()==0)continue;
			for(int j=0 ; j< jsonTmpOntologies.length(); j++){
				JSONObject jsonTmpOntology = jsonTmpOntologies.getJSONObject(j);
				if(!jsonTmpOntology.has("relevantLevel") || jsonTmpOntology.isNull("relevantLevel"))continue;
				Double relevantLevel = jsonTmpOntology.getDouble("relevantLevel");
				String word = null;
				if(!jsonTmpOntology.isNull("content"))
					word = jsonTmpOntology.getString("content");
				if(StringUtils.isNotBlank(word)){
					wordRelevancies.put(word, relevantLevel);
				}
				
				if(jsonTmpOntology.has("synonyms")){
					JSONArray jsonSynonyms = jsonTmpOntology.getJSONArray("synonyms");
					for(int k=0; k<jsonSynonyms.length(); k++){
						String synonym = jsonSynonyms.getString(k);
						if(StringUtils.isNotBlank(synonym)){
							wordRelevancies.put(synonym, relevantLevel);
						}
					}
				}
				
				if(relevantLevel>0 && jsonTmpOntology.has("relevantConcepts")){
					JSONArray jsonRelevantConcepts = jsonTmpOntology.getJSONArray("relevantConcepts");
					if(jsonRelevantConcepts==null || jsonRelevantConcepts.length()==0)continue;
					double avgRelevantLevel = (double)1 / jsonRelevantConcepts.length();
					avgRelevantLevel *= 0.5 * relevantLevel;  
					for(int k = 0 ; k < jsonRelevantConcepts.length() ; k++){
						JSONObject jsonRelevantConcept = jsonRelevantConcepts.getJSONObject(k);
						String relevantConceptWord = jsonRelevantConcept.getString("content");
						if(StringUtils.isNotEmpty(relevantConceptWord) 
							&& !wordRelevancies.containsKey(relevantConceptWord)){
							wordRelevancies.put(relevantConceptWord, avgRelevantLevel);
						}
					}
				}
			}
		}	
		return wordRelevancies;
	}
	
	/**
	 * ������Ҫ�ز�ѯ���
	 */
	@Override
	public JSONObject searchByOntologies(Map context,JSONObject ontoOfReq,
			String[] infoSourceCodes, JSONArray infoYears, Integer page,
			Integer pageSize) throws Exception {
		if(ontoOfReq==null)return null;
		
		List<String> categorys = null;
		JSONArray jsonDomains = getJSONArray(ontoOfReq,"domains");
		if(jsonDomains!=null && jsonDomains.length()>0){
			categorys = new ArrayList<String>();
			for (int i = 0; i < jsonDomains.length(); i++) {
				String content = jsonDomains.getJSONObject(i).getString("content");
				if(StringUtils.isNotEmpty(content))categorys.add(content);
			}
		}
		
		SearchFacade facade = new SearchFacade();
		IInfoSearcher infoSearcher = new ElasticSearchSearcher();
		facade.addInfoSearcher(infoSearcher);
		
		//��ȡȫ�ļ������йؼ���
		List<String> ontologyWords = getWordsFromOntoOfReq(ontoOfReq);
		List<List<String>> words = new ArrayList<List<String>>();
		words.add(ontologyWords);
		
		Map<String,Double> wordRelevancies = getWordRelevancies(ontoOfReq);
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("pageSize", pageSize);
		if(categorys!=null && categorys.size()>0)
			params.put("categorys", StringUtils.join(categorys, " "));
		
		if(infoSourceCodes!=null && infoSourceCodes.length>0)
			params.put("infoSources", StringUtils.join(infoSourceCodes, ","));
		
		params.put("wordRelevancies", wordRelevancies);
		
		JSONObject jsonResult = new JSONObject();
		try{
			params.put(QLConstant.PARAMS_DATA_KEY_WEIGHT_EXP, ElasticSearchWeighter.class.getName());
			String res = facade.executeInfoSearch(null, words, params);
			JSONArray jsonData = new JSONArray(res);
			jsonResult.put("data", jsonData);
			jsonResult.put("num", params.get("num"));
			jsonResult.put("page", params.get("page"));
		}catch(Exception ex){
			jsonResult.put("num", 0);
			jsonResult.put("page", 0);
		}
		
		return jsonResult;
	}

	@Override
	public JSONObject searchBySql(String sql, String[] infoSourceCodes,
			Integer page, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public JSONArray getJSONArray(JSONObject obj,String propName)
	{
		if(obj.has(propName) && !obj.isNull(propName))
			return obj.getJSONArray(propName);
		return null;
	}

	@Override
	public JSONObject searchByCondition(String resOfReqJson,
			String[] infoSources, String orderName, String orderType, int page,
			int pageSize) throws Exception {
		String esSql = "select * from ";
		
		if(infoSources!=null && infoSources.length>0){
			for(int i=0;i<infoSources.length;i++){
				if(i==0){
					esSql += "213trswcmv7/"+infoSources[i];
				}else{
					esSql += ",213trswcmv7/"+infoSources[i];
				}
			}
			esSql +=" where 1=1";
		}else{
			esSql += "213trswcmv7 where 1=1";
		}
		
		if(StringUtils.isNotEmpty(resOfReqJson)){
			JSONObject obj = new JSONObject(resOfReqJson);
			String titleSql = "";
			String title = "";
			JSONArray titlesArr = new JSONArray();
			
			if(obj.has("title")){
				title = obj.getString("title");
			}
			
			if(obj.has("titles") && obj.get("titles") instanceof JSONArray){
				titlesArr= obj.getJSONArray("titles");
			}
			if(StringUtils.isNotEmpty(title)){
				titlesArr.put(title);
			}
			if(titlesArr !=null && titlesArr.length()>0){
				titleSql +="(";
				for(int y=0;y<titlesArr.length();y++){
					if(y==0){
						titleSql += "title='"+titlesArr.getString(y)+"'";
					}else{
						titleSql += " or title='"+titlesArr.getString(y)+"'";
					}
				}
				titleSql +=")";
			}
			if(StringUtils.isNotEmpty(titleSql)){
				esSql += " and "+titleSql;
			}
			
			//��Ϣ��Դ��Ų�ѯ
			String resourceIdStr = "";
			if(obj.has("resourceIds")){
				JSONArray resourceIds = obj.getJSONArray("resourceIds");
				if(resourceIds!=null && resourceIds.length()>0){
					resourceIdStr += "(";
					for(int r=0;r<resourceIds.length();r++){
						if(r==0){
							resourceIdStr += resourceIds.getString(r);
						}else{
							resourceIdStr += ","+resourceIds.getString(r);
						}
					}
					resourceIdStr += ")";
				}
			}
			if(StringUtils.isNotEmpty(resourceIdStr)){
				esSql += " and MetaDataId in "+resourceIdStr;
			}
			
			//ԭ�ĵ�ַ��׼��ѯ
			if(obj.has("originalAddress")){
				String originalAddress = obj.getString("originalAddress");
				esSql += " and url='"+originalAddress+"'";
			}
			//source,��Դ��Դƽ̨���ƣ���ȷ��ѯ
			if(obj.has("source")){
				String source = obj.getString("source");
				esSql += " and publish_place='"+source+"'";
			}
			//keywords������ַ������龫ȷ��ѯ
			if(obj.has("keywords") && obj.get("keywords") instanceof JSONArray){
				JSONArray keywords = obj.getJSONArray("keywords");
				String keywordStr = "";
				if(keywords!=null && keywords.length()>0){
					for(int z=0;z<keywords.length();z++){
						if(z==0){
							keywordStr +="'" +keywords.getString(z) +"'";
						}else{
							keywordStr += ","+"'"+keywords.getString(z)+"'";
						}
					}
				}
				if(StringUtils.isNotEmpty(keywordStr)){
					esSql += " and keywords in ("+keywordStr+")";
				}
			}
			//publishDateMin �������ڵ����ֵ
			if(obj.has("publishDateMax")){
				String publish_time = obj.getString("publishDateMax");
				esSql +=" and publish_time < '"+publish_time+"'";
			}
			if(obj.has("publishDateMin")){
				String publish_time = obj.getString("publishDateMin");
				esSql +=" and publish_time > '"+publish_time+"'";
			}
		}
		if(StringUtils.isNotEmpty(orderName) && StringUtils.isNotEmpty(orderType)){
			esSql +=" order by "+orderName+" "+orderType;
		}
		
		esSql += " limit "+(page-1)*pageSize+","+page*pageSize;
		JSONObject data = ElasticSearchUtil.searchBySql(esSql, page, pageSize);
		return data;
	}

}
