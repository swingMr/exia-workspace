package com.excellence.iaserver.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.exke.common.vo.ConceptVo;
import com.excellence.iamp.service.OntologyRuleService;
import com.excellence.iamp.vo.OntologyRule;
import com.excellence.iaserver.common.cache.CacheClient;
import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.FileUtil;
import com.excellence.iaserver.common.util.HanLPUtil;
import com.excellence.iaserver.common.util.IndexFilterUtil;
import com.excellence.iaserver.common.util.JavaHandlePython;
import com.excellence.iaserver.service.CalculateService;
import com.excellence.iaserver.service.SearchKnowledgeService;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.common.Term;

@Service
public class CalculateServiceImpl implements CalculateService{

	private String basePath;
	@Autowired
	private SearchKnowledgeService searchKnowledgeService;
	@Autowired
	private OntologyRuleService ontologyRuleService;
	@Autowired
	private CacheClient cacheClient;
	
	private List<OntologyRule> ontologyRuleList = null;
	
	private Map word2Concept = new HashMap<String,JSONObject>();
	
	public CalculateServiceImpl(){
		String oapath = System.getProperty("oapath");
		if(StringUtils.isEmpty(oapath))oapath = Constant.OA_DOC_PATH;
		this.basePath = oapath + File.separator
				+ "exiaserver/data_dig/expy";
	}
	
	public JSONObject participle(String text, Boolean tagPart, int patten) throws FileNotFoundException {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("status", 0);
		jsonResult.put("data", JSONObject.NULL);
		
		if(StringUtils.isEmpty(text)){
			jsonResult.put("msg", "参数text不能为空");
			return jsonResult;
		}
		
		try{
			List words = HanLPUtil.getWords(text, true, tagPart, patten);
			List<String> wds = new ArrayList<String>();
			JSONArray jsonReturnData = new JSONArray();
			if(words!=null && words.size()>0){
				for(int i=0;i<words.size();i++){
					
					JSONObject wordObj = new JSONObject();
					if(tagPart){
						Term word = (Term)words.get(i);
						
						if(wds.contains(word.word)){
							continue;
						}
						wordObj.put("word", word.word);
						wordObj.put("part", word.nature);
						wds.add(word.word);
					}else{
						if(wds.contains(words.get(i))){
							continue;
						}
						wordObj.put("word", words.get(i));
						wds.add((String)words.get(i));
					}
					jsonReturnData.put(wordObj);
				}
			}
			jsonResult.put("status", 1);
			jsonResult.put("data", jsonReturnData);
		}catch(Exception ex){
			jsonResult.put("msg", ex.getMessage());
		}
		return jsonResult;
	}

	@Override
	public JSONObject abs(String title, String text, int keyWordLimit,
			int abstractLength) throws FileNotFoundException {
		String pyfile = this.basePath + File.separator + 
				"textmining" + File.separator + "extractAbstract.py";
		if(!FileUtil.isExists(pyfile))throw new FileNotFoundException();
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("status", 0);
		jsonResult.put("data", JSONObject.NULL);
		
		try {
			JSONArray keyWords = new JSONArray();
			List<String> wordList = new ArrayList<String>();
			String abs = "";
			if(StringUtils.isNotEmpty(title)){
				JSONArray keywords = searchKnowledgeService.getTermsByText(title);
				JSONArray words = IndexFilterUtil.wordsFilter(keywords);
//				List words = HanLPUtil.getWords(title, true, false);
				if(words!=null && words.length()>0){
					for(int i=0;i<words.length();i++){
						JSONObject obj = new JSONObject();
						obj.put("word", words.get(i));
						obj.put("relevantLevel", 1);
						keyWords.put(obj);
						wordList.add((String)words.get(i));
					}
				}
			}
			
			if(StringUtils.isNotEmpty(text)){
				Map<String, Float> keyWordsAndRank = HanLPUtil.getKeyWordsAndRank(text, keyWordLimit, true);
				Set<Map.Entry<String, Float>> entrySet = keyWordsAndRank.entrySet();
		        for (Map.Entry<String, Float> entry : entrySet)
		        {
		        	if(!wordList.contains(entry.getKey())){
		        		JSONObject obj = new JSONObject();
		        		obj.put("word", entry.getKey());
		        		obj.put("relevantLevel", entry.getValue());
		        		keyWords.put(obj);
		        		wordList.add(entry.getKey());
		        	}
		        }
				
				abs = HanLPUtil.getSummary(text, abstractLength);
			}
			
			JSONObject dataObj = new JSONObject();
			dataObj.put("abstract", abs);
			dataObj.put("keywords", keyWords);
			jsonResult.put("status", 1);
			jsonResult.put("data", dataObj);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("msg", e.getMessage());
		}
		
		return jsonResult;
	}

	@Override
	public JSONObject gentag(String title, String text, boolean recDomain,
			boolean recHierarchy, String sourceUnit, String publishDate) throws FileNotFoundException {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("status", 0);
		jsonResult.put("data", JSONObject.NULL);
		
		try{
			
			JSONArray conceptsArr = new JSONArray();
			List<String> contentWords = new ArrayList<String>();
			JSONArray words = new JSONArray();
			//用来临时存放Python脚本查出来词和相似度的键值对
			Map obj = new HashMap();
			JSONObject resultObj = new JSONObject();
			
			if(StringUtils.isNotEmpty(title)){
				JSONArray keywords = searchKnowledgeService.getTermsByText(title);
				
				JSONArray kwords = IndexFilterUtil.wordsFilter(keywords);
//				List kwords = HanLPUtil.getWords(title, true, false);
				if(kwords!=null && kwords.length()>0){
					for(int i=0;i<kwords.length();i++){
						words.put(kwords.get(i));
						obj.put(kwords.get(i), 1);
						contentWords.add((String)kwords.get(i));
					}
				}
				
				//根据规则匹配
				String key = "getOntologyRuleListByCondition";
//				List<OntologyRule> ontologyRuleList = new ArrayList<OntologyRule>();
				/*if(cacheClient.get(key) != null) {
					ontologyRuleList = (List<OntologyRule>)cacheClient.get(key);
					System.out.println("规则匹配缓存设置111111111111111111111");
				}else{
					Map condition = new HashMap();
					ontologyRuleList = ontologyRuleService.getOntologyRuleListByCondition(condition);
					cacheClient.set(key,ontologyRuleList);
				}*/
				if(ontologyRuleList == null){
					Map condition = new HashMap();
					ontologyRuleList = ontologyRuleService.getOntologyRuleListByCondition(condition);
				}
				
				if(ontologyRuleList!=null && ontologyRuleList.size()>0){
					for(OntologyRule vo:ontologyRuleList){
						String ruleContent = vo.getRuleContent();
						if(StringUtils.isNotEmpty(ruleContent)){
							//进行规则匹配
							boolean rel = judgeContainsStr(ruleContent, title);
							if(rel){
								String conceptName = vo.getConceptName();
								words.put(conceptName);
								obj.put(conceptName, 1);
								contentWords.add(conceptName);
							}
						}
					}
				}
			}
			
			if(StringUtils.isNotEmpty(text)){
				Map<String, Float> keyWordsAndRank = HanLPUtil.getKeyWordsAndRank(text, 50, true);
				Set<Map.Entry<String, Float>> entrySet = keyWordsAndRank.entrySet();
		        for (Map.Entry<String, Float> entry : entrySet)
		        {
		        	if(!contentWords.contains(entry.getKey())){
		        		obj.put(entry.getKey(), entry.getValue());
		        		words.put(entry.getKey());
		        		contentWords.add(entry.getKey());
		        	}
		        }
			}
			
			//根据标题去识别领域
			if(recDomain){
				//调用Python获取领域
				//领域预测Python脚本所在路径
				String oapath = System.getProperty("oapath");
				String classifyTextSec = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"classifyTextSec.py";
				//特征文件名
				String modelName = "domain";
				//特征文件所在目录
				String modelDir = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"data"+File.separator+"classify";
				//结果文件所在目录
				String resultFile = modelDir +File.separator + modelName +".result";
				//对text进行所在领域的预测
				List<String> parameter = new ArrayList<String>();
				parameter.add(0, modelName);
				parameter.add(1,modelDir);
				parameter.add(2,title);
				String domainNames11 = JavaHandlePython.HandlePython(classifyTextSec, parameter);
				String resultStr = FileUtil.readFileToString(resultFile,"gb2312");
				System.out.println("6666"+resultStr);
				resultStr = resultStr.replace(".txt", "").replace("(", "").replace(")", "").replace("'", "").replace("\\n", "");
				
				String domainName = resultStr.split(",")[0];
				if(!contentWords.contains(domainName)){
					words.put(domainName);
					contentWords.add(domainName);
					obj.put(domainName, 1);
				}
			}
			
			JSONArray domains = new JSONArray();
			JSONArray acts = new JSONArray();
			JSONArray organdpersons = new JSONArray();
			JSONArray objects = new JSONArray();
			JSONArray spaces = new JSONArray();
			JSONArray times = new JSONArray();
			JSONArray keywords = new JSONArray();
			JSONArray hierarchy = new JSONArray();
			
			List<String> contents = new ArrayList();
			
			words = IndexFilterUtil.wordsFilter(words);
			if(words!=null && words.length()>0){
				
				JSONArray existWords = new JSONArray();
				JSONArray concepts = new JSONArray();
				for(int w=0;w<words.length();w++){
					if(!word2Concept.containsKey(words.getString(w)))
					{
						existWords.put(words.getString(w));
					}
					else
					{
						concepts.put(word2Concept.get(words.getString(w)));
					}
				}
				
				if(existWords.length()>0)
				{
					JSONArray concept1s = searchKnowledgeService.getConceptsByContents(existWords.toString());
					for(int w=0;w<concept1s.length();w++)
					{
						JSONObject jsonObj = concept1s.getJSONObject(w);
						word2Concept.put(jsonObj.getString("content"), jsonObj);
						concepts.put(jsonObj);
					}
				}
				//JSONArray concepts = searchKnowledgeService.getConceptsByContents(queryWords.toString());
				
				
				if(concepts!=null && concepts.length()>0){
					
					for(int i=0;i<concepts.length();i++){
						ConceptVo vo = new ConceptVo();
						vo.parseFormJson(concepts.getJSONObject(i));
						
						if(contents.contains(vo.getContent())){
							continue;
						}
						contents.add(vo.getContent());
						
						JSONObject voObj = new JSONObject();
						voObj.put("content", vo.getContent());
						voObj.put("similarity", obj.get(vo.getContent()));
						voObj.put("type", vo.getType().name());
						voObj.put("parentNames", vo.getParentNames());
						
						if(vo.getParentNames().contains("行为")){
							acts.put(voObj);
						}else if(vo.getParentNames().contains("主体")){
							organdpersons.put(voObj);
						}else if(vo.getParentNames().contains("客体")){
							objects.put(voObj);
						}else if(vo.getParentNames().contains("空间")){
							spaces.put(voObj);
						}else if(vo.getParentNames().contains("时间")){
							times.put(voObj);
						}else if(vo.getParentNames().contains("领域")){
							domains.put(voObj);
						}else{
							keywords.put(voObj);
						}
					}
				}
			}
			
			contentWords.removeAll(contents);
			for(String kw:contentWords){
				JSONObject voObj = new JSONObject();
				voObj.put("content", kw);
				voObj.put("similarity", obj.get(kw));
				keywords.put(voObj);
				word2Concept.put(kw, voObj);
			}
			
			JSONObject hierarchyObj = new JSONObject();
			hierarchyObj.put("content", "所有层级");
			hierarchyObj.put("similarity", 1);
			hierarchy.put(hierarchyObj);
			
			//发文单位不为空，放入主体
			if(StringUtils.isNotEmpty(sourceUnit)){
				JSONObject unitObj = new JSONObject();
				unitObj.put("content", sourceUnit);
				unitObj.put("similarity", 1);
				organdpersons.put(unitObj);
			}
			
			//文件信息资源的发布日期不为空将年份和月份放入时间标签
			if(StringUtils.isNotEmpty(publishDate)){
				String rexp = "^\\d{4}-((0[1-9])|(1[1-2]))-((0[1-9])|([1-2]\\d)|(3[0-1]))";
			    Pattern pat = Pattern.compile(rexp);
			    Matcher mat = pat.matcher(publishDate);
			    boolean dateType = mat.matches();
			    if(dateType){
			    	JSONObject yearObj = new JSONObject();
			    	yearObj.put("content", publishDate.split("-")[0]);
			    	yearObj.put("similarity", 1);
			    	times.put(yearObj);
			    	
			    	JSONObject monthObj = new JSONObject();
			    	monthObj.put("content", publishDate.split("-")[1]);
			    	monthObj.put("similarity", 1);
			    	times.put(monthObj);
			    }
			}
			
			//自然人和法人识别
			List personNames = new ArrayList();
			if(StringUtils.isNotEmpty(title)){
				List titlePersonNames = HanLPUtil.recPersonName(title);
				personNames.addAll(titlePersonNames);
			}
			if(StringUtils.isNotEmpty(text)){
				List textPersonNames = HanLPUtil.recPersonName(text);
				personNames.addAll(textPersonNames);
			}
			if(personNames!=null && personNames.size()>0){
				for(int i=0;i<personNames.size();i++){
					Term term = (Term)personNames.get(i);
					if(!contents.contains(term.word)){
						JSONObject perObj = new JSONObject();
						List<String> parentNames = new ArrayList<String>();
						perObj.put("content", term.word);
						perObj.put("relevantLevel", 1);
						
						if(term.nature.equals(Nature.nr)){
							parentNames.add("自然人");
						}else{
							parentNames.add("法人");
						}
						perObj.put("parentNames", parentNames);
						organdpersons.put(perObj);
						contents.add(term.word);
					}
				}
			}
			
			resultObj.put("domains", domains);
			resultObj.put("acts", acts);
			resultObj.put("organdpersons", organdpersons);
			resultObj.put("objects", objects);
			resultObj.put("spaces", spaces);
			resultObj.put("times", times);
			resultObj.put("keywords", keywords);
			resultObj.put("hierarchy", hierarchy);
			
			jsonResult.put("status", 1);
			jsonResult.put("data", resultObj);
		}catch(Exception ex){
			jsonResult.put("msg", "程序处理异常："+ex.getMessage());
		}
		return jsonResult;
	}
	
	public boolean judgeContainsStr(String regex,String content) {  
        Matcher m=Pattern.compile(regex).matcher(content);  
        return m.matches();  
    }

}
