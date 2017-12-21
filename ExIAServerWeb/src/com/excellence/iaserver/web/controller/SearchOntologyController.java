package com.excellence.iaserver.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.exke.connector.ConnectorException;
import com.excellence.iaserver.common.cache.CacheClient;
import com.excellence.iaserver.common.util.WebUtil;
import com.excellence.iaserver.service.SearchKnowledgeService;
import com.excellence.iaserver.service.TRSProtalService;

/**
 * ����֪ʶ���������
 * @author chegnhq
 *
 */
@Controller
@RequestMapping("/services/ontology/search")
public class SearchOntologyController {
	
	@Autowired
	private SearchKnowledgeService searchKnowledgeService;
	
	@RequestMapping("/getConcept")
	public String getConcept(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String id = MapUtils.getString(map, "id");
		JSONObject resultObj = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		int status = 1;
		if(id != null && !id.equals("")) {
			try {
				resultObj = searchKnowledgeService.getConcept(id);
			} catch (ConnectorException e) {
				e.printStackTrace();
				status = -1;
			}
		}
		jsonObject.put("data", resultObj);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ͨ������ʻ�ȡ���弯��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author liuzehang
	 * @throws ConnectorException 
	 * @created 2017-1-4
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getOntologiesByKeywords")
	public String getOntologiesByKeywords(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String words = MapUtils.getString(map, "words");
		JSONArray ontologyArr = new JSONArray();
		if(StringUtils.isNotEmpty(words)) {
			try{
				ontologyArr =  searchKnowledgeService.getOntologiesByKeywords(words);
			}catch (Exception e) {
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", ontologyArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ͨ������ʻ�ȡ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ConnectorException
	 */
	@RequestMapping("/getConceptsByKeywords")
	public String getConceptsByKeywords(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String words = MapUtils.getString(map, "words");
		JSONArray ontologyArr = new JSONArray();
		if(StringUtils.isNotEmpty(words)) {
			try{
				ontologyArr =  searchKnowledgeService.getConceptsByKeywords(words);
			}catch (Exception e) {
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", ontologyArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ��ر���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author liuzehang
	 * @throws ConnectorException 
	 * @created 2017-1-4
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getOntologiesByIds")
	public String getOntologiesByIds( HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String ids = MapUtils.getString(map, "ids");
		JSONArray ontologyArr = new JSONArray();
		if(StringUtils.isNotEmpty(ids)) {
			try{
				ontologyArr =  searchKnowledgeService.getOntologiesByIds(ids);
			}catch (Exception e) {
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", ontologyArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ��ر���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @throws ConnectorException 
	 * @created 2016-11-3
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getOntologies")
	public String getOntologies( HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String conceptId = MapUtils.getString(map, "conceptId");
		String conditions = MapUtils.getString(map, "conditions");
		int num = MapUtils.getIntValue(map, "num",1000);
		JSONArray ontologyArr = new JSONArray();
		if(StringUtils.isNotEmpty(conceptId)) {
			try{
				ontologyArr =  searchKnowledgeService.getOntologies(conceptId,conditions,num);
			} catch (Exception e) {
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", ontologyArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ�������ͨ·��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2016-11-3
	 * @copyright Excellence co.
	 */
	public String getRoute(HttpServletRequest request, HttpServletResponse response){
		return null;
	}
	
	/**
	 * ��ȡ�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @throws IOException 
	 * @throws ConnectorException 
	 * @created 2016-10-28
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getThemeWordsOutputStream")
	public String getThemeWordsOutputStream(HttpServletRequest request, HttpServletResponse response) throws ConnectorException, IOException {
		OutputStream out = response.getOutputStream();
		searchKnowledgeService.getThemeWordsOutputStream(out);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * �ִ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getTermsByText")
	public String getTermsByText(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String text  = MapUtils.getString(map, "text");
		JSONArray jsonArr = new JSONArray();
		if(StringUtils.isNotEmpty(text)) {
			try {
				jsonArr = searchKnowledgeService.getTermsByText(text);
			} catch (ConnectorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", jsonArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ͨ������Ϳ����ȡ��Ϊ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getActionConceptDefsByBodyAndObject")
	public String getActionConceptDefsByBodyAndObject(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String context  = MapUtils.getString(map, "context");
		String bodyConceptId  = MapUtils.getString(map, "bodyConceptId");//�������id
		String objConceptId  = MapUtils.getString(map, "objConceptId");//�������id
		JSONArray jsonArr = new JSONArray();
		if(StringUtils.isNotEmpty(bodyConceptId) && StringUtils.isNotEmpty(objConceptId)) {
			try{
				jsonArr = searchKnowledgeService.getActionConceptDefsByBodyAndObject(context,bodyConceptId,objConceptId);
			}catch (Exception e) {
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", jsonArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ���֪ʶ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getRelatedKnowledgesByAction")
	public String getRelatedKnowledgesByAction(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String context  = MapUtils.getString(map, "context");
		String actConceptId  = MapUtils.getString(map, "actConceptId");//��Ϊid		
		JSONObject jsonObj = new JSONObject();
		if(StringUtils.isNotBlank(actConceptId)) {
			try{
				jsonObj = searchKnowledgeService.getRelatedKnowledgesByAction(context,actConceptId);
			}catch(Exception e){
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", jsonObj);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ͨ���ؼ��ʻ�ȡ�������Դ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getOntologiesAndInfosByKeywords")
	public String getOntologiesAndInfosByKeywords(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String context  = MapUtils.getString(map, "context");
		String words  = MapUtils.getString(map, "words");		
		int inforMaxSize = MapUtils.getIntValue(map, "inforMaxSize",10);
		JSONArray arr = new JSONArray();
		if(StringUtils.isNotBlank(words)) {
			try{
				arr = searchKnowledgeService.getOntologiesAndInfosByKeywords(context,words,inforMaxSize);
			}catch(Exception e){
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", arr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ���Ӹ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getExtensionalConcepts")
	public String getExtensionalConcepts(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String conceptId  = MapUtils.getString(map, "conceptId");
		String type  = MapUtils.getString(map, "type","");	
		int num = MapUtils.getIntValue(map, "num",1000);
		JSONArray arr = new JSONArray();
		JSONArray conceptArr = new JSONArray();
		if(StringUtils.isNotBlank(conceptId)) {
			JSONObject json = new JSONObject();
			json.put("elementType","relation");
			json.put("direction","outbound");
			json.put("content", "����");
			JSONArray conditions = new JSONArray();
			conditions.put(json);
			if(!type.equals("")) {
				JSONObject condition4Concept = new JSONObject();
				condition4Concept.put("elementType", "concept");
				condition4Concept.put("type",type);
				conditions.put(condition4Concept);
			}
			try {
				arr = searchKnowledgeService.getOntologies(conceptId,conditions.toString(),num);
			} catch (ConnectorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status = -1;
			}
			if(arr != null && arr.length() > 0) {
				for(int i=0;i<arr.length();i++) {
					JSONObject obj = arr.getJSONObject(i);
					if(obj.has("concepts")) {
						JSONArray cArr = obj.getJSONArray("concepts");
						conceptArr.put(cArr.get(cArr.length()-1));
					}
				}
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", conceptArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ�ں�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getIntensionalConcepts")
	public String getIntensionalConcepts(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String conceptId  = MapUtils.getString(map, "conceptId");
		JSONArray arr = new JSONArray();
		JSONArray conceptArr = new JSONArray();
		if(StringUtils.isNotBlank(conceptId)) {
			JSONObject json = new JSONObject();
			json.put("elementType","relation");
			json.put("direction","inbound");
			json.put("content", "����");
			JSONArray conditions = new JSONArray();
			conditions.put(json);
			try {
				arr = searchKnowledgeService.getOntologies(conceptId,conditions.toString());
			} catch (ConnectorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status = -1;
			}
			if(arr != null && arr.length() > 0) {
				for(int i=0;i<arr.length();i++) {
					JSONObject obj = arr.getJSONObject(i);
					if(obj.has("concepts")) {
						JSONArray cArr = obj.getJSONArray("concepts");
						conceptArr.put(cArr.get(cArr.length()-1));
					}
				}
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", conceptArr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getBodysOfObjects")
	public String getBodysOfObjects(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String objectIds  = MapUtils.getString(map, "objectIds");
		JSONArray arr = new JSONArray();
		if(StringUtils.isNotBlank(objectIds)) {
			try{
				arr = searchKnowledgeService.getBodysOfObjects(objectIds);
			}catch(Exception e){
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", arr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ���ݸ���id���Ϻ͹�ϵ���Ƽ���ϵ��ѯ����
	 * @param conceptIds
	 * @param conditions   ��ʽ
	 * [{'relationName':'����', 'direction':'outbound'},{'relationName':'����', 'direction':'any'}...]
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getOntologiesByConceptIdsAndConditions")
	public String getOntologiesByConceptIdsAndConditions(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String conceptIds  = MapUtils.getString(map, "conceptIds");
		String conditions = MapUtils.getString(map, "conditions");
		JSONArray arr = new JSONArray();
		if(StringUtils.isNotBlank(conceptIds)) {
			try{
				arr = searchKnowledgeService.getOntologiesByConceptIdsAndConditions(conceptIds,conditions);
			}catch(Exception e){
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", arr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ͨ�����ֻ�ȡ��ϵ���壬�˷����黺��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-2-23
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getRelationDefinitionsByNames")
	public String getRelationDefinitionsByNames(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String names  = MapUtils.getString(map, "names");
		JSONArray arr = new JSONArray();
		if(StringUtils.isNotBlank(names)) {
			try{
				arr = searchKnowledgeService.getRelationDefinitionsByNames(names);
			}catch(Exception e){
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", arr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡĳ�������ʵ��Ƽ������ʻ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-3-7
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getRecommendedKeywords")
	public String getRecommendedKeywords(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String words  = MapUtils.getString(map, "words");
		JSONArray arr = new JSONArray();
		if(StringUtils.isNotBlank(words)) {
			try{
				arr = searchKnowledgeService.getRecommendedKeywords(words);
			}catch(Exception e){
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", arr);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	
	
	/**
	 * ���ݹؼ��ʾ�������֪ʶ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-3-7
	 * @copyright Excellence co.
	 */
	@RequestMapping("/searchConceptDefsOfText")
	public String searchConceptDefsOfText(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String text  = MapUtils.getString(map, "text");
		JSONObject jsObj = new JSONObject();
		if(StringUtils.isNotBlank(text)) {
			try{
				jsObj = searchKnowledgeService.searchConceptDefsOfText(null,text);
			}catch(Exception e){
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", jsObj);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ��ȡ��Ҫ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author huangjinyuan
	 * @created 2017-3-22
	 * @copyright Excellence co.
	 */
	@RequestMapping("/getRelatedFiveElements")
	public String getRelatedFiveElements(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map map = WebUtil.requestToMap(request);
		String keywords  = MapUtils.getString(map, "keywords");
		JSONObject jsObj = new JSONObject();
		if(StringUtils.isNotBlank(keywords)) {
			try{
				jsObj = searchKnowledgeService.getRelatedFiveElements(keywords);
			}catch(Exception e){
				e.printStackTrace();
				status = -1;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", jsObj);
		jsonObject.put("status", status);
		jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputPlain(response, jsonObject.toString());
		return null;
	}
	
	/**
	 * ���ݴ������Ϣ�������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author Huangyb
	 * @created 2017-3-22
	 * @copyright Excellence co.
	 */
	@RequestMapping("/searchRelevantContent")
	public String searchRelevantContent(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map params = WebUtil.requestToMap(request);
		String text = MapUtils.getString(params, "text");
		String domainIds = MapUtils.getString(params, "domainIds");
		String parentIds = MapUtils.getString(params, "parentIds");
		
		String[] arrayDomainIds = null;
		if(StringUtils.isNotEmpty(domainIds)){
			try{
				List<String> listDomainIds = new ArrayList<String>();
				JSONArray jsonDomainIds = new JSONArray(domainIds);
				for(int i = 0 ; i< jsonDomainIds.length(); i++){
					listDomainIds.add(jsonDomainIds.getString(i));
				}
				arrayDomainIds = new String[listDomainIds.size()];
				listDomainIds.toArray(arrayDomainIds);
			}catch(Exception ex){
				ex.printStackTrace();
				status = -1;
			}
		}
		
		String[] arrayParentIds = null;
		if(StringUtils.isNotEmpty(parentIds)){
			try{
				List<String> listParentIds = new ArrayList<String>();
				JSONArray jsonParentIds = new JSONArray(parentIds);
				for(int i = 0 ; i< jsonParentIds.length(); i++){
					listParentIds.add(jsonParentIds.getString(i));
				}
				arrayParentIds = new String[listParentIds.size()];
				listParentIds.toArray(arrayParentIds);
			}catch(Exception ex){
				ex.printStackTrace();
				status = -1;
			}
		}
		
		JSONArray jsonResult = null;
		if(StringUtils.isNotBlank(text)) {
			jsonResult = searchKnowledgeService.searchRelevantContent(arrayDomainIds,
					arrayParentIds, text);
		}
		if(jsonResult==null){
			WebUtil.outputPlain(response, "null");
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", jsonResult);
			jsonObject.put("status", status);
			jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
			WebUtil.outputPlain(response, jsonObject.toString());
		}
		return null;
	}
	
	@RequestMapping("/searchSBO")
	public String searchSBO(HttpServletRequest request,HttpServletResponse response){
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map params = WebUtil.requestToMap(request);
		String text = MapUtils.getString(params, "text");
		JSONArray jsonResult = searchKnowledgeService.searchSBO(text);
		if(jsonResult==null) {
			WebUtil.outputPlain(response, "null");
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", jsonResult);
			jsonObject.put("status", status);
			jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
			WebUtil.outputPlain(response, jsonObject.toString());
		}
		return null;
	}
	
	@RequestMapping("/ruleCATClassifyText")
	public String ruleCATClassifyText(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		int status = 1;
		Map params = WebUtil.requestToMap(request);
		String text = MapUtils.getString(params, "text");
		TRSProtalService trsProtalService = new TRSProtalService();
		JSONArray jsonDomains = trsProtalService.ruleCATClassifyText(text);
		if(jsonDomains==null) {
			WebUtil.outputPlain(response, "null");
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", jsonDomains);
			jsonObject.put("status", status);
			jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
			WebUtil.outputPlain(response, jsonObject.toString());
		}
		
		return null;
	}
	
	@RequestMapping("/nagaoText")
	public String nagaoText(HttpServletRequest request,HttpServletResponse response) {
		try {
			long startTime = System.currentTimeMillis();
			int status = 1;
			Map params = WebUtil.requestToMap(request);
			String text = MapUtils.getString(params, "text", null);
			
			JSONArray result = searchKnowledgeService.nagaoText(text);
			
			if(result==null) {
				WebUtil.outputPlain(response, "null");
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("data", result);
				jsonObject.put("status", status);
				jsonObject.put("timeCost", System.currentTimeMillis()-startTime+"����");
				WebUtil.outputPlain(response, jsonObject.toString());
			}
			return null;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//------ �����ǻۻ�Ӧ��֧��ƽ̨����ӿڲο��ֲ�V0.2 ------
	/**
	 * 3.2.1	��ȡ��������������嵥
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/domains")
	public String domains(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken", null);
		String domainName = MapUtils.getString(params, "domainName", null);
		JSONObject jsonResult = searchKnowledgeService.getDomains(extoken, domainName); 
		jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, jsonResult.toString());
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("����:/services/ontology/search/domains  ��ʱ"+t+" ����");
		}
		return null;
	}
	
	/**
	 * 3.2.2	��ȡָ�������µ����йؼ���
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/keywords")
	public String keywords(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken", null);
		String domainName = MapUtils.getString(params, "domainName", null);
		int limit = MapUtils.getIntValue(params, "limit", 200);
		JSONObject jsonResult= searchKnowledgeService.getDomainKeywords(extoken, domainName, limit);
		jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, jsonResult.toString());
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("����:/services/ontology/search/keywords  ��ʱ"+t+" ����");
		}
		return null;
	}
	
	/**
	 * 3.2.3	��ȡָ�������µĸ����嵥
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/concepts")
	public String concepts(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken", null);
		String domainName = MapUtils.getString(params, "domainName", null);
		String type = MapUtils.getString(params, "type", null);
		String strParentNames = MapUtils.getString(params, "parentNames", null);
		int limit = MapUtils.getIntValue(params, "limit", 200);
		String[] parentNames = null;
		if(StringUtils.isNotEmpty(strParentNames)){
			parentNames = StringUtils.split(strParentNames, ",");
		}
		JSONObject jsonResult = searchKnowledgeService.getDomainConcepts(
					extoken, domainName, parentNames, type, limit);
		jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, jsonResult.toString());
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("����:/services/ontology/search/concepts  ��ʱ"+t+" ����");
		}
		return null;
	}
	
	/**
	 * 3.2.4	���ݸ������ƻ�ȡ�������
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/conceptsByNames")
	public String conceptsByNames(HttpServletRequest request,HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken", null);
		String strConceptNames = MapUtils.getString(params, "conceptNames", null);
		String[] conceptNames = null;
		if(StringUtils.isNotEmpty(strConceptNames)){
			conceptNames = StringUtils.split(strConceptNames, ",");
		}
		JSONObject jsonResult = searchKnowledgeService.getConceptsByNames(extoken, conceptNames);
		jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, jsonResult.toString());
		
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("����:/services/ontology/search/conceptsByNames  ��ʱ"+t+" ����");
		}
		return null;
	}
	
	/**
	 * 3.2.5	��ȡָ���������ظ���
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/relevance")
	public String relevance(HttpServletRequest request,HttpServletResponse response)
	{
		long startTime = System.currentTimeMillis();
		Map params = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(params, "extoken", null);
		
		String strSrcIds = MapUtils.getString(params, "srcIds", null);
		String[] srcIds = null;
		if(StringUtils.isNotEmpty(strSrcIds)){
			srcIds = StringUtils.split(strSrcIds, ",");
		}
		
		String type = MapUtils.getString(params, "objType", null);
		
		String strRelationDefIds = MapUtils.getString(params, "relationDefIds", null);
		String[] relationDefIds = null;
		if(StringUtils.isNotEmpty(strRelationDefIds)){
			relationDefIds = StringUtils.split(strRelationDefIds, ",");
		}
		
		int direction = MapUtils.getIntValue(params, "direction", 2);
		int limit = MapUtils.getIntValue(params, "objLimit", 30);
		
		String strParentNames = MapUtils.getString(params, "objParentNames", null);
		String[] parentNames = null;
		if(StringUtils.isNotEmpty(strParentNames)){
			parentNames = StringUtils.split(strParentNames, ",");
		}
		JSONObject jsonResult = searchKnowledgeService.getRelevant(extoken,
					srcIds, relationDefIds, direction, parentNames, type,
					limit);
		jsonResult.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, jsonResult.toString());
		Date dt2 = new Date();
		long t = dt2.getTime()-startTime;
		if(t>100)
		{
			System.out.println("����:/services/ontology/search/relevance  ��ʱ"+t+" ����");
		}
		return null;
	}
}
