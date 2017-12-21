package com.excellence.iaserver.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.exke.common.vo.ConceptVo;
import com.excellence.exke.common.vo.enums.EConceptType;
import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.service.OntologyRuleService;
import com.excellence.iamp.service.TagRecordService;
import com.excellence.iamp.vo.OntologyRule;
import com.excellence.iaserver.common.cache.CacheClient;
import com.excellence.iaserver.common.util.FileUtil;
import com.excellence.iaserver.common.util.HanLPUtil;
import com.excellence.iaserver.common.util.IndexFilterUtil;
import com.excellence.iaserver.common.util.JavaHandlePython;
import com.excellence.iaserver.common.util.T;
import com.excellence.iaserver.service.CalculateService;
import com.excellence.iaserver.service.RecogniseOntologyService;
import com.excellence.iaserver.service.SearchKnowledgeService;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.common.Term;

@Service("recogniseOntologyService")
public class RecogniseOntologyServiceImpl implements RecogniseOntologyService {

	@Autowired
	private OntologyRuleService ontologyRuleService;
	@Autowired
	private CalculateService calculateService;
	@Autowired
	private SearchKnowledgeService searchKnowledgeService;
	@Autowired
	private TagRecordService tagRecordService;
	@Autowired
	private CacheClient cacheClient;
	
	private List<OntologyRule> ontologyRuleList = null;
	
	private Map word2Concept = new HashMap<String,JSONObject>();
	
	private Map word2RelevantConcepts = new HashMap<String,JSONArray>();
	
	@Override
	public List<Map> recognizeDomainsOfTextSec(String text) throws Exception {
		return null;
	}
	
	private String getAlias(ConceptVo concept){
		return (String)concept.getAttribute("ALIAS");
	}
	
	@SuppressWarnings("unused")
	@Override
	public JSONObject recognizeConceptsOfTextSec(String appCode,String context,String title,String keyWord,String text, String clsNames)throws Exception {
		String ip = "";
		String subjectAccount = "";
		String userName = "";
		if(StringUtils.isNotBlank(context)){
			JSONObject obj = new JSONObject(context);
			if(obj.has("user")){
				JSONObject jsonUser = (JSONObject) obj.get("user");
				ip = jsonUser.getString("ip");
				String account = jsonUser.getString("account");
				if(account.contains(appCode)){
					subjectAccount = account;
				}else{
					subjectAccount = appCode + "\\" + account;
				}
				userName = jsonUser.getString("name");
			}
		}
		
		String oapath = System.getProperty("oapath");
		JSONArray clsNamesArr = null;
		if(StringUtils.isNotEmpty(clsNames)){
			clsNamesArr = new JSONArray(clsNames);
		}
		JSONObject data = new JSONObject();
		List<String> contents = new ArrayList();
		JSONArray conceptsArr = new JSONArray();
		JSONArray words = new JSONArray();
		List<String> contentWords = new ArrayList<String>();
		if(StringUtils.isNotEmpty(title)){
//			SearchKnowledgeService searchKnowledgeService = new SearchKnowledgeService();
			//先用输入的文本直接进行概念查询
//			title = "[\""+title+"\"]";
			
			JSONArray textConcepts = new JSONArray();
			textConcepts = handleWord2Concept(new String[]{title});
			
			boolean isPy = true;
			if(textConcepts!=null && textConcepts.length()>0){
				conceptsArr.put(textConcepts.getJSONObject(0));
				
				ConceptVo vo = new ConceptVo();
				vo.parseFormJson(textConcepts.getJSONObject(0));
				List<String> ids = (List<String>) vo.getAttribute("BELONG_DOMAIN_IDS");
				if(ids!=null && ids.size()>0){
					for(String id:ids){
						if(id.equals("concept_concrete/domaininst")){
							continue;
						}
						JSONObject concept = searchKnowledgeService.getConcept(id);
						if(concept!=null && concept.length()>0){
							conceptsArr.put(concept);
						}
					}
				}
			}else{
				word2Concept.put(title, null);
				
				//根据知识本题库去进行识别，先分词
				JSONArray keywords = searchKnowledgeService.getTermsByText(title);
				words = IndexFilterUtil.wordsFilter(keywords);
				
				if(words!=null && words.length()>0){
					String[] wordstr = new String[words.length()];
					JSONArray concepts = new JSONArray();
					for(int w=0;w<words.length();w++){
						wordstr[w] = words.getString(w);
					}
					
					concepts = handleWord2Concept(wordstr);
					
					if(concepts!=null && concepts.length()>0){
						for(int i=0;i<concepts.length();i++){
							conceptsArr.put(concepts.getJSONObject(i));
							
							ConceptVo vo = new ConceptVo();
							vo.parseFormJson(concepts.getJSONObject(i));
							String id = (String) vo.getAttribute("MNG_DOMAIN_ID");
							if(StringUtils.isNotEmpty(id)&&!id.equals("concept_concrete/domaininst")){
								JSONObject concept = searchKnowledgeService.getConcept(id);
								if(concept!=null && concept.length()>0){
									conceptsArr.put(concept);
									isPy=false;
								}
							}
						}
					}
				}
				//根据规则匹配
				if(ontologyRuleList == null){
					Map condition = new HashMap();
					ontologyRuleList = ontologyRuleService.getOntologyRuleListByCondition(condition);
				}
//				Map condition = new HashMap();
//				List<OntologyRule> ontologyRuleList = ontologyRuleService.getOntologyRuleListByCondition(condition);
				String conceptNames = "";
				if(ontologyRuleList!=null && ontologyRuleList.size()>0){
					for(OntologyRule vo:ontologyRuleList){
						String ruleContent = vo.getRuleContent();
						if(StringUtils.isNotEmpty(ruleContent)){
							//进行规则匹配
							boolean rel = judgeContainsStr(ruleContent, title);
							if(rel){
								String conceptName = vo.getConceptName();
								conceptNames += conceptName+";";
							}
						}
					}
				}
				
				
				if(StringUtils.isNotEmpty(conceptNames)){
					JSONArray domainConcepts = handleWord2Concept(conceptNames.split(";"));
					if(domainConcepts!=null && domainConcepts.length()>0){
						for(int i=0;i<domainConcepts.length();i++){
							conceptsArr.put(domainConcepts.getJSONObject(i));
							ConceptVo vo = new ConceptVo();
							vo.parseFormJson(domainConcepts.getJSONObject(i));
							if(vo.getParentNames().contains("领域")){
								isPy = false;
							}
						}
					}
				}
				
				if(isPy){
					//调用Python获取领域
					//领域预测Python脚本所在路径
					
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
					resultStr = resultStr.replace(".txt", "").replace("(", "").replace(")", "").replace("'", "").replace("\\n", "");
					
					String domainName = resultStr.split(",")[0];
//					String gld = resultStr.split(",")[1].replace("\n", "");
					JSONArray domainArr = new JSONArray();
					domainArr.put(domainName);
					String str = domainArr.toString().replace("\\n", "");
					JSONArray domainConcepts = searchKnowledgeService.getConceptsByContents(str);
					if(domainConcepts!=null && domainConcepts.length()>0){
						for(int i=0;i<domainConcepts.length();i++){
							conceptsArr.put(domainConcepts.getJSONObject(i));
						}
					}
					
				}
			}
			
			
			//根据输入文本获取相关概念
			JSONArray wordArr = new JSONArray();
			if(StringUtils.isNotEmpty(keyWord)){
				wordArr = new JSONArray(keyWord);
			}
			
			if(StringUtils.isNotEmpty(text)){
				JSONObject abs = calculateService.abs(title, text, 100, 5);
				
				if(abs!=null && abs.length()>0){
					if(abs.get("data")!=null){
						JSONObject dataObj = abs.getJSONObject("data");
						JSONArray keyWordsArr = dataObj.getJSONArray("keywords");
						if(keyWordsArr!=null && keyWordsArr.length()>0){
							for(int z=0;z<keyWordsArr.length();z++){
								String wd = keyWordsArr.getJSONObject(z).getString("word");
								wordArr.put(wd);
								contentWords.add(wd);
							}
						}
					}
				}
			}
			
			JSONArray inputWords = IndexFilterUtil.wordsFilter(wordArr);
			
			if(inputWords!=null && inputWords.length()>0){
				String[] inputW = new String[inputWords.length()];
				for(int y=0;y<inputWords.length();y++){
					words.put(inputWords.getString(y));
					inputW[y] = inputWords.getString(y);
				}
				
				JSONArray concepts = handleWord2Concept(inputW);
				
				if(concepts!=null && concepts.length()>0){
					for(int i=0;i<concepts.length();i++){
						conceptsArr.put(concepts.getJSONObject(i));
					}
				}
			}
			
			
			List<String> synonymsLists = new ArrayList<String>();
			//组装返回的data
			JSONArray domains = new JSONArray();
			JSONArray acts = new JSONArray();
			JSONArray organdpersons = new JSONArray();
			JSONArray objects = new JSONArray();
			JSONArray spaces = new JSONArray();
			JSONArray times = new JSONArray();
			JSONArray keywords = new JSONArray();
			
			if(conceptsArr!=null && conceptsArr.length()>0){
				for(int i=0;i<conceptsArr.length();i++){
					ConceptVo vo = new ConceptVo();
					vo.parseFormJson(conceptsArr.getJSONObject(i));
					word2Concept.put(vo.getContent(), conceptsArr.getJSONObject(i));
					JSONObject conceptMap = new JSONObject();
					JSONArray relevantConcepts = new JSONArray();
					//根据要素类型做过滤
					if(clsNamesArr!=null &&clsNamesArr.length()>0){
						for(int y=0;y<clsNamesArr.length();y++){
							if(!contents.contains(vo.getContent())&&vo.getParentNames().contains(clsNamesArr.getString(y))){
								conceptMap.put("id", vo.getId());
								conceptMap.put("content", vo.getContent());
								conceptMap.put("parentNames", vo.getParentNames());
								conceptMap.put("type", vo.getType().toString());
								conceptMap.put("ALIAS", this.getAlias(vo));
								conceptMap.put("synonyms", vo.getSynonyms());
								conceptMap.put("relevantConcepts", relevantConcepts);
								
								double relevantLevel = 0;
								double relacant = 1;
								if(contentWords.contains(vo.getContent())){
									relacant = 0.5;
								}
								if(vo.getParentNames().contains("行为")){
									relevantLevel = 5*relacant;
									conceptMap.put("relevantLevel", relevantLevel);
									if(word2RelevantConcepts.containsKey(vo.getContent())){
										relevantConcepts = (JSONArray) word2RelevantConcepts.get(vo.getContent());
									}else{
										getRelevantConcept(vo, relevantConcepts, relevantLevel, "行为");
										word2RelevantConcepts.put(vo.getContent(), relevantConcepts);
									}
									acts.put(conceptMap);
									searchKnowledgeService.updateTagRecord(ip, userName, "行为", vo.getContent(),subjectAccount);
								}else if(vo.getParentNames().contains("主体")){
									relevantLevel = 3*relacant;
									conceptMap.put("relevantLevel", relevantLevel);
									
									if(word2RelevantConcepts.containsKey(vo.getContent())){
										relevantConcepts = (JSONArray) word2RelevantConcepts.get(vo.getContent());
									}else{
										getRelevantConcept(vo, relevantConcepts, relevantLevel, "主体");
										word2RelevantConcepts.put(vo.getContent(), relevantConcepts);
									}
									
									organdpersons.put(conceptMap);
									searchKnowledgeService.updateTagRecord(ip, userName, "主体", vo.getContent(),subjectAccount);
								}else if(vo.getParentNames().contains("客体")){
									relevantLevel = 3*relacant;
									conceptMap.put("relevantLevel", relevantLevel);
									
									if(word2RelevantConcepts.containsKey(vo.getContent())){
										relevantConcepts = (JSONArray) word2RelevantConcepts.get(vo.getContent());
									}else{
										getRelevantConcept(vo, relevantConcepts, relevantLevel, "客体");
										word2RelevantConcepts.put(vo.getContent(), relevantConcepts);
									}
									
									objects.put(conceptMap);
									
									searchKnowledgeService.updateTagRecord(ip, userName, "客体", vo.getContent(),subjectAccount);
								}else if(vo.getParentNames().contains("领域")){
									relevantLevel = 2*relacant;
									conceptMap.put("relevantLevel", relevantLevel);
									domains.put(conceptMap);
									
									searchKnowledgeService.updateTagRecord(ip, userName, "领域", vo.getContent(),subjectAccount);
								}else if(vo.getParentNames().contains("空间")){
									relevantLevel = 1*relacant;
									conceptMap.put("relevantLevel", relevantLevel);
									if(word2RelevantConcepts.containsKey(vo.getContent())){
										relevantConcepts = (JSONArray) word2RelevantConcepts.get(vo.getContent());
									}else{
										getRelevantConcept(vo, relevantConcepts, relevantLevel, "空间");
										word2RelevantConcepts.put(vo.getContent(), relevantConcepts);
									}
									
									spaces.put(conceptMap);
									
									searchKnowledgeService.updateTagRecord(ip, userName, "空间", vo.getContent(),subjectAccount);
									
								}else if(vo.getParentNames().contains("时间")){
									relevantLevel = 1*relacant;
									conceptMap.put("relevantLevel", relevantLevel);
									times.put(conceptMap);
									searchKnowledgeService.updateTagRecord(ip, userName, "时间", vo.getContent(),subjectAccount);
								}
								
								contents.add(vo.getContent());
								if(vo.getSynonyms()!=null && vo.getSynonyms().size()>0){
									synonymsLists.addAll(vo.getSynonyms());
								}
							}
						}
					}else{
						if(!contents.contains(vo.getContent())){
							conceptMap.put("id", vo.getId());
							conceptMap.put("content", vo.getContent());
							conceptMap.put("parentNames", vo.getParentNames());
							conceptMap.put("type", vo.getType().toString());
							conceptMap.put("ALIAS", this.getAlias(vo));
							conceptMap.put("synonyms", vo.getSynonyms());
							
							double relevantLevel = 0;
							double relacant = 1;
							if(contentWords.contains(vo.getContent())){
								relacant = 0.5;
							}
							if(vo.getParentNames().contains("行为")){
								relevantLevel = 5*relacant;
								conceptMap.put("relevantLevel", relevantLevel);
								if(word2RelevantConcepts.containsKey(vo.getContent())){
									relevantConcepts = (JSONArray) word2RelevantConcepts.get(vo.getContent());
								}else{
									getRelevantConcept(vo, relevantConcepts, relevantLevel, "行为");
									word2RelevantConcepts.put(vo.getContent(), relevantConcepts);
								}
								acts.put(conceptMap);
								searchKnowledgeService.updateTagRecord(ip, userName, "行为", vo.getContent(),subjectAccount);
							}else if(vo.getParentNames().contains("主体")){
								relevantLevel = 3*relacant;
								conceptMap.put("relevantLevel", relevantLevel);
								
								if(word2RelevantConcepts.containsKey(vo.getContent())){
									relevantConcepts = (JSONArray) word2RelevantConcepts.get(vo.getContent());
								}else{
									if(vo.getParentNames().contains("法人")){
										getRelevantConcept(vo, relevantConcepts, relevantLevel, "法人");
									}else{
										getRelevantConcept(vo, relevantConcepts, relevantLevel, "自然人");
									}
									word2RelevantConcepts.put(vo.getContent(), relevantConcepts);
								}
								
								organdpersons.put(conceptMap);
								searchKnowledgeService.updateTagRecord(ip, userName, "主体", vo.getContent(),subjectAccount);
							}else if(vo.getParentNames().contains("客体")){
								relevantLevel = 3*relacant;
								conceptMap.put("relevantLevel", relevantLevel);
								
								if(word2RelevantConcepts.containsKey(vo.getContent())){
									relevantConcepts = (JSONArray) word2RelevantConcepts.get(vo.getContent());
								}else{
									getRelevantConcept(vo, relevantConcepts, relevantLevel, "客体");
									word2RelevantConcepts.put(vo.getContent(), relevantConcepts);
								}
								objects.put(conceptMap);
								searchKnowledgeService.updateTagRecord(ip, userName, "客体", vo.getContent(),subjectAccount);
							}else if(vo.getParentNames().contains("领域")){
								relevantLevel = 2*relacant;
								conceptMap.put("relevantLevel", relevantLevel);
								domains.put(conceptMap);
								searchKnowledgeService.updateTagRecord(ip, userName, "领域", vo.getContent(),subjectAccount);
							}else if(vo.getParentNames().contains("空间")){
								relevantLevel = 1*relacant;
								conceptMap.put("relevantLevel", relevantLevel);
								if(word2RelevantConcepts.containsKey(vo.getContent())){
									relevantConcepts = (JSONArray) word2RelevantConcepts.get(vo.getContent());
								}else{
									getRelevantConcept(vo, relevantConcepts, relevantLevel, "空间");
									word2RelevantConcepts.put(vo.getContent(), relevantConcepts);
								}
								spaces.put(conceptMap);
								searchKnowledgeService.updateTagRecord(ip, userName, "空间", vo.getContent(),subjectAccount);
							}else if(vo.getParentNames().contains("时间")){
								relevantLevel = 1*relacant;
								conceptMap.put("relevantLevel", relevantLevel);
								times.put(conceptMap);
								searchKnowledgeService.updateTagRecord(ip, userName, "时间", vo.getContent(),subjectAccount);
							}
							conceptMap.put("relevantConcepts", relevantConcepts);
							
							contents.add(vo.getContent());
							if(vo.getSynonyms()!=null && vo.getSynonyms().size()>0){
								synonymsLists.addAll(vo.getSynonyms());
							}
						}
					}
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
					if(!contents.contains(term.word)&&!synonymsLists.contains(term.word)){
						JSONObject perObj = new JSONObject();
						List<String> parentNames = new ArrayList<String>();
						double relacant=1;
						perObj.put("content", term.word);
						if(!title.contains(term.word)){
							relacant = 0.5;
						}
						perObj.put("relevantLevel", relacant*3);
						
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
			
			data.put("domains", domains);
			data.put("acts", acts);
			data.put("organdpersons", organdpersons);
			data.put("objects", objects);
			data.put("spaces", spaces);
			data.put("times", times);
			data.put("keywords", keywords);
			
			List<String> keyW = new ArrayList();
			if(words!=null && words.length()>0){
				for(int z=0;z<words.length();z++){
					if(!keyW.contains(words.getString(z))){
						keyW.add(words.getString(z));
					}
				}
				keyW.removeAll(contents);
				keyW.removeAll(synonymsLists);
				List<String> parName = new ArrayList();
				parName.add("关键词");
				for(String keyStr:keyW){
					Map conceptMap = new HashMap();
					double relacant = 1;
					if(contentWords.contains(keyStr)){
						relacant = 0.5;
					}
//					conceptMap.put("id", "");
					conceptMap.put("content", keyStr);
//					conceptMap.put("parentNames", parName);
//					conceptMap.put("type", "");
//					conceptMap.put("ALIAS", keyStr);
					conceptMap.put("relevantLevel", 1*relacant);
//					conceptMap.put("synonyms", null);
					keywords.put(conceptMap);
				}
			}
			
		}
		return data;
	}
	
	/**
	 * word2Concept缓存进行过滤
	 * @param words
	 * @return
	 */
	public JSONArray handleWord2Concept(String[] words){
		JSONArray concepts = new JSONArray();
		String wordstr="";
		for(int w=0;w<words.length;w++){
			if(word2Concept.containsKey(words[w])){
				if(word2Concept.get(words[w])!=null){
					concepts.put(word2Concept.get(words[w]));
				}
			}else{
				wordstr+=words[w]+";";
			}
		}
		
		if(StringUtils.isNotEmpty(wordstr)){
			JSONObject wdConcepts = searchKnowledgeService.getConceptsByNames("",wordstr.split(";"));
			
			if(wdConcepts!=null && wdConcepts.has("data") && wdConcepts.get("data") instanceof JSONArray){
				JSONArray wdArray = wdConcepts.getJSONArray("data");
				if(wdArray!=null && wdArray.length()>0){
					for(int i=0;i<wdArray.length();i++){
						JSONObject wdObj = wdArray.getJSONObject(i);
						concepts.put(wdObj);
					}
				}
			}
		}
		
		return concepts;
	}
	
	public void getRelevantConcept(ConceptVo vo, JSONArray relevantConcepts, double relevantLevel, String parName) throws Exception{
		List<String> contents = new ArrayList<String>();
		if(vo.getType().equals(EConceptType.concrete)){
			JSONObject relevant = searchKnowledgeService.getRelevant(null, new String[]{vo.getId()}, null, 2, new String[]{parName}, "concrete", 100);
			if(relevant.has("data")&& relevant.get("data") != null && relevant.get("data") instanceof JSONArray){
				JSONArray relaData = relevant.getJSONArray("data");
				if(relaData!=null && relaData.length()>0){
					for(int z=0;z<relaData.length();z++){
						JSONObject relaObj = relaData.getJSONObject(z);
						if(!contents.contains(relaObj.getString("content"))){
							relaObj.put("relevantLevel", relevantLevel*0.5);
							relevantConcepts.put(relaObj);
							contents.add(relaObj.getString("content"));
						}
					}
				}
			}
			
		}else{
			List<String> parentNames = vo.getParentNames();
			parentNames.remove(parName);
			JSONArray arr = new JSONArray(parentNames);
			arr = IndexFilterUtil.wordsFilter(arr);
			
			String[] arrStr = new String[arr.length()];
			for(int i=0;i<arr.length();i++){
				arrStr[i] = arr.getString(i);
			}
			JSONObject relaObj = searchKnowledgeService.getConceptsByNames("",arrStr);
			JSONArray relaArrs = new JSONArray();
			if(relaObj!=null && relaObj.has("data") && relaObj.get("data") instanceof JSONArray){
				relaArrs=relaObj.getJSONArray("data");
			}
			
			if(relaArrs!=null && relaArrs.length()>0){
				for(int x=0;x<relaArrs.length();x++){
					JSONObject jsonObject = relaArrs.getJSONObject(x);
					ConceptVo vo1 = new ConceptVo();
					vo1.parseFormJson(jsonObject);
					
					JSONObject obj = new JSONObject();
					if(!contents.contains(vo1.getContent())){
						obj.put("id", vo1.getId());
						obj.put("content", vo1.getContent());
						obj.put("parentNames", vo1.getParentNames());
						obj.put("type", vo1.getType().toString());
						obj.put("ALIAS", this.getAlias(vo1));
						obj.put("relevantLevel", relevantLevel*0.5);
						obj.put("synonyms", vo1.getSynonyms());
						
						relevantConcepts.put(obj);
						contents.add(vo1.getContent());
					}
				}
			}
			
			// 如果是类，获取父类和子类作为该概念的相关概念，相关度为当前概念相关度的一半；同一级的类也需要作为相关概念，相关度为当前概念相关度的0.25
			if(parName.equals("客体")){
				//获取同一级的类
				JSONObject json = new JSONObject();
				json.put("elementType","relation");
				json.put("direction","inbound");
				json.put("content", "外延");
				JSONArray conditions = new JSONArray();
				conditions.put(json);
				JSONArray parArrs = searchKnowledgeService.getOntologies(vo.getId(),conditions.toString());
				
				ConceptVo conceptVo = new ConceptVo();
				
				/*if(parArrs != null && parArrs.length() > 0) {
					for(int i=0;i<parArrs.length();i++) {
						JSONObject obj = parArrs.getJSONObject(i);
						if(obj.has("concepts")) {
							JSONArray cArr = obj.getJSONArray("concepts");
							conceptVo.parseFormJson(cArr.getJSONObject(cArr.length()-1));
							break;
						}
					}
				}
				
				JSONObject relevant = searchKnowledgeService.getRelevant(null, new String[]{conceptVo.getId()}, null, 0, null, "clazz", 100);
				if(relevant.has("data")&& relevant.get("data") != null && relevant.get("data") instanceof JSONArray){
					JSONArray relaData = relevant.getJSONArray("data");
					if(relaData!=null && relaData.length()>0){
						for(int z=0;z<relaData.length();z++){
							JSONObject childObj = relaData.getJSONObject(z);
							if(!contents.contains(childObj.getString("content"))){
								childObj.put("relevantLevel", relevantLevel*0.25);
								relevantConcepts.put(childObj);
								contents.add(childObj.getString("content"));
							}
						}
					}
				}*/
				
				JSONObject childObj = searchKnowledgeService.getRelevant(null, new String[]{vo.getId()}, null, 0, null, "clazz", 100);
				if(childObj.has("data")&& childObj.get("data") != null && childObj.get("data") instanceof JSONArray){
					JSONArray relaData = childObj.getJSONArray("data");
					if(relaData!=null && relaData.length()>0){
						for(int z=0;z<relaData.length();z++){
							JSONObject childObjConcept = relaData.getJSONObject(z);
							if(!contents.contains(childObjConcept.getString("content"))){
								childObjConcept.put("relevantLevel", relevantLevel*0.5);
								relevantConcepts.put(childObjConcept);
								contents.add(childObjConcept.getString("content"));
							}
						}
					}
				}
				
			}
		}
	}

	@Override
	public void generateModelOfClassify(String modelName, String inputDir,
			String outputDir) throws Exception {
		String oaPath = System.getProperty("oapath");
		//训练领域语料Python脚本所在路径
		String generateModelOfClassify = oaPath+"exiaserver\\data_dig_tools\\expy\\classify\\generateModelOfClassify.py";
		//对领域语料进行训练
		List<String> parameter = new ArrayList<String>();
		parameter.add(0, modelName);
		parameter.add(1,inputDir);
		parameter.add(2,outputDir);
		String domainNames = JavaHandlePython.HandlePython(generateModelOfClassify, parameter);
	}
	
	public boolean judgeContainsStr(String regex,String content) {  
        Matcher m=Pattern.compile(regex).matcher(content);  
        return m.matches();  
    }

	public String getDocIndexes(String text, String wordClazz,String words) throws Exception {
		List<ConceptVo> conceptlist = new ArrayList<ConceptVo>();
		List<String> existKey = new ArrayList<String>();
		if(StringUtils.isNotEmpty(text)) {
			/*String key = "_sk_getOntologiesByKeywords_"+SHAUtil.encryptSHA(text)+"_"+wordClazz+"_"+words;
			if(cacheClient.get(key) != null) {
				text = (String)cacheClient.get(key);
				return text;
			}*/
			JSONArray keywords = searchKnowledgeService.getTermsByText(text);
			JSONArray conceptArr = searchKnowledgeService.getConceptsByKeywords(keywords.toString());
			if(conceptArr != null && conceptArr.length() > 0) {
				for(int i=0;i<conceptArr.length();i++) {
					JSONObject json = conceptArr.getJSONObject(i);
					ConceptVo c = new ConceptVo();
					String alias = this.getAlias(c);
					c.parseFormJson(json);
					String finalName = StringUtils.isNotEmpty(alias)?alias:c.getContent();
					if(!existKey.contains(finalName)) {
						conceptlist.add(c);
						existKey.add(finalName);
					}
				}
				conceptlist = IndexFilterUtil.indexFilter(conceptlist);
				if(StringUtils.isNotEmpty(words)) {
					JSONArray customWords = new JSONArray(words);
					if(customWords != null && customWords.length() > 0) {
						for(int i=0;i<customWords.length();i++) {
							//name,type
							JSONObject json = customWords.getJSONObject(i);
							String name = json.has("name")?json.getString("name"):"";
							String type = json.has("type")?json.getString("type"):"";
							if(!existKey.contains(name)) {
								existKey.add(name);
								ConceptVo vo = new ConceptVo();
								vo.setContent(name);
								vo.setId("");
								if(StringUtils.isNotEmpty(type)) {
									List<String> parentNames = new ArrayList<String>();
									parentNames.add(type);
									vo.setParentNames(parentNames);
								}
								conceptlist.add(vo);
							}
						}
					}
				}
			}
			if(conceptlist != null && conceptlist.size() > 0){
				Collections.sort(conceptlist, new Comparator<ConceptVo>() {
					public int compare(ConceptVo o1, ConceptVo o2) {
						if(o1 == null || o2 == null)return -1;
						String o1Name = getAlias(o1);
						String o2Name = getAlias(o2);
						if(StringUtils.isEmpty(o1Name))o1Name = o1.getContent();
						if(StringUtils.isEmpty(o2Name))o2Name = o2.getContent();
		                if(o1Name.length() > o2Name.length()){
		                    return -1;
		                }
		                if(o1Name.length() < o2Name.length()){
		                    return 1;
		                }
		                return 0;
					}
				});
				String strList = "";
				for(ConceptVo concept : conceptlist) {
					String finalName = this.getAlias(concept); 
					if(StringUtils.isEmpty(finalName)){
						finalName = concept.getContent();
					}
					if (!strList.contains(finalName) && text.contains(finalName)) {
						strList += finalName + ",";
						String replaceStr = "<index class=\""+ wordClazz +"\"";
						if(concept.getParentNames() != null && concept.getParentNames().size() > 0) {
							//replaceStr = replaceStr + " data-parentNames='" + StringUtils.join(concept.getParentNames(), ";") + "'";
							if(concept.getParentNames().contains("法人")) {
								replaceStr = replaceStr + " data-type=\"corporation\"";
							} else if(concept.getParentNames().contains("自然人")) {
								replaceStr = replaceStr + " data-type=\"person\"";
							} else {
								replaceStr = replaceStr + " data-type=\"other\"";
							}
						}
						replaceStr = replaceStr + " data-id=\"" + concept.getId() + "\" data-content=\"" + concept.getContent() + "\">" + concept.getContent()
								+ "</index>";
						text = text.replaceAll(finalName, replaceStr);
					}
				}
				text = replaceBookTitle(text,wordClazz);
				text = T.toHTML(text);
				/*cacheClient.set(key, text);*/
			}
		}
		return text;
	}
	
	/**
	 * 把标签里的内容过滤
	 * @param text
	 * @param wordClazz
	 * @return
	 */
	public String replaceBookTitle(String text, String wordClazz) {
		Pattern pattern = Pattern.compile("(?<=[《]).*?(?=[》])");
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()) {
        	String temp = matcher.group();
        	String tempStr = T.cleanHtml(temp);
        	tempStr = "<index class=\""+wordClazz+"\" data-type=\"res_title\" data-content=\""+tempStr+"\">"+tempStr+"</index>";
        	matcher.appendReplacement(sb, tempStr);
        }
        matcher.appendTail(sb);  
        return sb.toString();
	}
	
	public static void main(String[] args) {
		/*String text = "你好《中国人民法律》，自然的祝福《<span data-id=\"22\" data-type=\"333\">深渊</span>来自》出新书啦";
		System.out.println(text);
		Pattern pattern = Pattern.compile("(?<=[《]).*?(?=[》])");
        Matcher matcher = pattern.matcher(text);
        List<String> ls=new ArrayList<String>();
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()) {
        	String temp = matcher.group();
        	String tempStr = T.cleanHtml(temp);
        	tempStr = "<span class=\""+"ia-section"+"\" data-type=\"ref\" data-content=\""+tempStr+"\">"+tempStr+"</span>";
        	matcher.appendReplacement(sb, tempStr);
        }
        matcher.appendTail(sb);  
        System.out.println(sb.toString());  */
		String text = "第一条 根据《中华人民共和国药品管理法》(以下简称《药品管理法》)，制定本条例。<br>第二条<span class=\""+"ia-section"+"\"> 国务院药品<span class=\""+"ia-section"+"\" data-type=\"ref\" data-content=\"测试来的\">测试来的</span>监督</span>管理部门设置国家药品检验机构。";
		System.out.println(text);
		//String pattern = "<[^<>]*>?";
		String pattern = "<(span)([^>]*?)(class=\"ia-section\")(.*?)>(.*?)</span>";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(text);
		StringBuffer sb = new StringBuffer();  
		while(matcher.find()) {
			String temp = matcher.group();
			System.out.println(matcher.group());
		}
		matcher.appendTail(sb);  
		System.out.println(sb.toString());
	}
	
	
	public String filterSection(String text) {
		String pattern = "<[^<>]*>?";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(text);
		StringBuffer sb = new StringBuffer();  
		while(matcher.find()) {
			String temp = matcher.group();
			if(temp.contains("index")) {
				matcher.appendReplacement(sb,"");
			}
		}
		matcher.appendTail(sb);  
		text = sb.toString();
		return text;
	}
	
	/**
	 * 获取标引内容
	 */
	public String getSectionIndexes(String text, String sectionClazz, String sections) {
		JSONArray sectionsArr = new JSONArray(sections);
		if(sectionsArr != null && sectionsArr.length() > 0) {
			for(int i=0;i<sectionsArr.length();i++) {
				String section = sectionsArr.getString(i);
				section = filterSection(section);
				String replaceSection = "<index class=\""+sectionClazz+"\">"+section+"</index>";
				text = text.replaceFirst(section, replaceSection);
			}
		}
		return text;
	}

	@Override
	public ChatMsg recognizeChatMsg(ChatMsg chatMsg) {
		try {
			JSONObject obj = recognizeConceptsOfTextSec(chatMsg.getAppCode(), "", chatMsg.getTextContent(), "", "", "");
			if(obj!=null) {
				chatMsg.setOntoInfo(obj.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chatMsg;
	}

}
