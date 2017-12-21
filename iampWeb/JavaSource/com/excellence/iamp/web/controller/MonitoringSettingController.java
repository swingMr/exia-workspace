package com.excellence.iamp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.mongodb.service.MonitoringSettingService;
import com.excellence.iamp.mongodb.vo.MonitoringSetting;
import com.excellence.iamp.mongodb.vo.SettingAttribute;
@Controller
@RequestMapping("/monitoringSetting")
public class MonitoringSettingController {
	@Autowired
	private MonitoringSettingService monitoringSettingService;
	

	@RequestMapping("/getOne")
	public ModelAndView getOne(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("knowledgeLib/monitorInfo/settingList");
		List<MonitoringSetting> msList  = monitoringSettingService.getList();
		if(msList != null && msList.size()>0 ){
			MonitoringSetting ms = msList.get(0);
			mv.addObject("id", ms.getId());
			//主体
			String subConcepts = ""; 
			String subParents ="";
			if(ms.getSubjectSetting()!=null && ms.getSubjectSetting().getConcepts() !=null &&ms.getSubjectSetting().getConcepts().size()>0){
				subConcepts = handleListData(subConcepts, ms.getSubjectSetting().getConcepts());
			}
			if(ms.getSubjectSetting()!=null && ms.getSubjectSetting().getParents() !=null &&ms.getSubjectSetting().getParents().size()>0){
                subParents = handleListData(subParents, ms.getSubjectSetting().getParents());
			}
			mv.addObject("subConcepts", subConcepts);
			mv.addObject("subType", ms.getSubjectSetting().getType());
			mv.addObject("subParents", subParents);
			//客体
			String objConcepts ="";
			String objParents = "";
			if(ms.getObjectSetting()!=null && ms.getObjectSetting().getConcepts() !=null &&ms.getObjectSetting().getConcepts().size()>0){
				objConcepts = handleListData(objConcepts, ms.getObjectSetting().getConcepts());
			}
			if(ms.getObjectSetting()!=null && ms.getObjectSetting().getParents() !=null &&ms.getObjectSetting().getParents().size()>0){
				objParents = handleListData(objParents, ms.getObjectSetting().getParents());
			}
			mv.addObject("objConcepts", objConcepts);
			mv.addObject("objParents", objParents);
			mv.addObject("objType", ms.getObjectSetting().getType());
			//行为
			String actConcepts = "";
			String actParents = "";
			if(ms.getActionSetting()!=null && ms.getActionSetting().getConcepts() !=null &&ms.getActionSetting().getConcepts().size()>0){
				actConcepts = handleListData(actConcepts, ms.getActionSetting().getConcepts());
			}
			if(ms.getActionSetting()!=null && ms.getActionSetting().getParents() !=null &&ms.getActionSetting().getParents().size()>0){
				actParents = handleListData(actParents, ms.getActionSetting().getParents());
			}
			mv.addObject("actConcepts", actConcepts);
			mv.addObject("actParents", actParents);
			mv.addObject("actType", ms.getActionSetting().getType());
			//时间
			String timeConcepts =  "";
			String timeParents = "";
			if(ms.getTimeSetting()!=null && ms.getTimeSetting().getConcepts() !=null &&ms.getTimeSetting().getConcepts().size()>0){
				timeConcepts = handleListData(timeConcepts, ms.getActionSetting().getConcepts());
			}
			if(ms.getTimeSetting()!=null && ms.getTimeSetting().getParents() !=null &&ms.getTimeSetting().getParents().size()>0){
				timeParents = handleListData(timeParents, ms.getTimeSetting().getParents());
			}
			mv.addObject("timeConcepts", timeConcepts);
			mv.addObject("timeParents", timeParents);
			mv.addObject("timeType", ms.getTimeSetting().getType());
			//空间
			String spaceConcepts = "";
			String spaceParents = "";
			if(ms.getSpaceSetting()!=null && ms.getSpaceSetting().getConcepts() !=null &&ms.getSpaceSetting().getConcepts().size()>0){
				spaceConcepts = handleListData(spaceConcepts, ms.getActionSetting().getConcepts());
			}
			if(ms.getSpaceSetting()!=null && ms.getSpaceSetting().getParents() !=null &&ms.getSpaceSetting().getParents().size()>0){
				spaceParents = handleListData(spaceParents, ms.getTimeSetting().getParents());
			}
			mv.addObject("spaceConcepts", spaceConcepts);
			mv.addObject("spaceParents", spaceParents);
			mv.addObject("spaceType", ms.getSpaceSetting().getType());
			//关键词
			String keywordConcepts ="";
			String keywordParents = "";
			if(ms.getKeywordSetting()!=null && ms.getKeywordSetting().getConcepts() !=null &&ms.getKeywordSetting().getConcepts().size()>0){
				keywordConcepts = handleListData(keywordConcepts, ms.getKeywordSetting().getConcepts());
			}
			if(ms.getKeywordSetting()!=null && ms.getKeywordSetting().getParents() !=null &&ms.getKeywordSetting().getParents().size()>0){
				keywordParents = handleListData(keywordParents, ms.getKeywordSetting().getParents());
			}
			mv.addObject("keywordConcepts", keywordConcepts);
			mv.addObject("keywordParents", keywordParents);
			//问题
			String proConcepts = "";
			String proParents = "";
			if(ms.getProblemSetting()!=null && ms.getProblemSetting().getConcepts() !=null &&ms.getProblemSetting().getConcepts().size()>0){
				proConcepts = handleListData(proConcepts, ms.getProblemSetting().getConcepts());
			}
			if(ms.getProblemSetting()!=null && ms.getProblemSetting().getParents() !=null &&ms.getProblemSetting().getParents().size()>0){
				proParents = handleListData(proParents, ms.getProblemSetting().getParents());
			}
			mv.addObject("proConcepts", proConcepts);
			mv.addObject("proParents", proParents);
			mv.addObject("proType", ms.getProblemSetting().getType());
		}
		return mv;
	}

	/** 保存监控设置
	 * @author liuzh
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response){
		MonitoringSetting  ms = new MonitoringSetting();
		String result = "false";
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			//更新。不新增
			ms = monitoringSettingService.findOne(id);
		}
		ModelAndView mv = new ModelAndView();
		SettingAttribute subject = new SettingAttribute();
		SettingAttribute object =  new SettingAttribute();
		SettingAttribute action = new SettingAttribute();
		SettingAttribute time = new SettingAttribute();
		SettingAttribute space = new SettingAttribute();
		SettingAttribute  problem = new SettingAttribute();
		SettingAttribute  keyword = new SettingAttribute();
		String data = request.getParameter("data");
		JSONObject jsonObj = new JSONObject(data);
		//主体 
		String subParents = (String) jsonObj.get("subParents");
		String subConcepts = (String) jsonObj.get("subConcepts");
		List<String> parents = new ArrayList<String>();
		List<String> concepts = new ArrayList<String>();
		handleList(subParents, subConcepts, parents, concepts);
		//客体
		String objParents = (String) jsonObj.get("objParents");
		String objConcepts = (String) jsonObj.get("objConcepts");
		List<String> objparentList = new ArrayList<String>();
		List<String> objconceptList = new ArrayList<String>();
		handleList(objParents, objConcepts, objparentList, objconceptList);
		//行为
		String actParents = (String) jsonObj.get("actParents");
		String actConcepts = (String) jsonObj.get("actConcepts");
		List<String> actparentList = new ArrayList<String>();
		List<String> actconceptList = new ArrayList<String>();
		handleList(actParents, actConcepts, actparentList, actconceptList);
		//时间
		String timeParents = (String) jsonObj.get("timeParents");
		String timeConcepts = (String) jsonObj.get("timeConcepts");
		List<String> timeparentList = new ArrayList<String>();
		List<String> timeconceptList = new ArrayList<String>();
		handleList(timeParents, timeConcepts, timeparentList, timeconceptList);
		//空间
		String spaceParents = (String) jsonObj.get("spaceParents");
		String spaceConcepts = (String) jsonObj.get("spaceConcepts");
		List<String> spaceparentList = new ArrayList<String>();
		List<String> spaceconceptList = new ArrayList<String>();
		handleList(spaceParents, spaceConcepts, spaceparentList, spaceconceptList);
		//问题
		String proParents = (String) jsonObj.get("proParents");
		String proConcepts = (String) jsonObj.get("proConcepts");
		List<String> proparentList = new ArrayList<String>();
		List<String> proconceptList = new ArrayList<String>();
		handleList(proParents, proConcepts, proparentList, proconceptList);
		
		//关键词
		String keywordParents = (String) jsonObj.get("kyParents");
		String keywordConcepts = (String) jsonObj.get("kyConcepts");
		List<String> kwparentList = new ArrayList<String>();
		List<String> kwconceptList = new ArrayList<String>();
		handleList(keywordParents, keywordConcepts, kwparentList, kwconceptList);
		
		String subType = (String) jsonObj.get("subType");
		String objType = (String) jsonObj.get("objType");
		String actType = (String) jsonObj.get("actType");
		String timeType = (String) jsonObj.get("timeType");
		String spaceType = (String) jsonObj.get("spaceType");
		String proType = (String) jsonObj.get("proType");
		subject.setParents(parents);
		subject.setType(subType);
		subject.setConcepts(concepts);
		object.setParents(objparentList);
		object.setConcepts(objconceptList);
		object.setType(objType);
		action.setParents(actparentList);
		action.setConcepts(actconceptList);
		action.setType(actType);
		time.setParents(timeparentList);
		time.setConcepts(timeconceptList);
		time.setType(timeType);
		space.setParents(spaceparentList);
		space.setConcepts(spaceconceptList);
		space.setType(spaceType);
		space.setParents(proparentList);
		problem.setConcepts(proconceptList);
		problem.setType(proType);
		keyword.setParents(kwparentList);
		keyword.setConcepts(kwconceptList);
		//补参数；
		keyword.setType("no");
		
		ms.setSubjectSetting(subject);
		ms.setObjectSetting(object);
		ms.setActionSetting(action);
		ms.setSpaceSetting(space);
		ms.setTimeSetting(time);
		ms.setProblemSetting(problem);
		ms.setKeywordSetting(keyword);
		if(StringUtils.isNotBlank(id)){
			monitoringSettingService.update(ms);
		}else{
			monitoringSettingService.save(ms);	
		}
		
		result="success";
		obj.put("result", result);
		WebUtil.outputHtml(response, obj.toString());
		return null;
	}
	
	/** 把list转成字符串
	 * @author Liuzh
	 * @param targetVal
	 * @param list
	 * @return
	 */
	private String handleListData(String targetVal,List<String> list){
        for(int i=0; list.size()>i; i++){
			if(i==0) {
				targetVal = targetVal + list.get(i);
            } else {
            	targetVal = targetVal + ";"+list.get(i);
            }	
        }
		return targetVal;
}
	
	//处理list
	/**
	 * @author Liuzh
	 * @param parent
	 * @param subConcept
	 * @param parents
	 * @param concepts
	 */
	private void handleList(String parent , String subConcept,List<String> parents,List<String> concepts){
		if(StringUtils.isNotBlank(parent)){
			parent = parent.replaceAll(";", ",");
			JSONArray arr = new JSONArray(parent);
	        if(arr != null && arr.length() > 0 ) {
	            for(int i=0;i<arr.length();i++) {
	            	parents.add((String) arr.get(i));
	            }
	        }
		}
		if(StringUtils.isNotBlank(subConcept)){
			subConcept = subConcept.replaceAll(";", ",");
			JSONArray arr = new JSONArray(subConcept);
	        if(arr != null && arr.length() > 0 ) {
	            for(int i=0;i<arr.length();i++) {
	            	concepts.add((String) arr.get(i));
	            }
	        }
		}
	}
}
