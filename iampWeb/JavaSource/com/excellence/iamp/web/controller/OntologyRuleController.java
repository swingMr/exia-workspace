package com.excellence.iamp.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excellence.common.UserInfo;
import com.excellence.common.util.GUIDGenerator;
import com.excellence.exke.common.vo.ConceptVo;
import com.excellence.iacommon.common.util.IaServiceClient;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iamp.service.OntologyRuleService;
import com.excellence.iamp.vo.OntologyRule;
import com.excellence.platform.um.exception.CommonAppException;

@Controller
@RequestMapping("/ontologyRule")
public class OntologyRuleController {
	
	@Autowired
	private OntologyRuleService ontologyRuleService;
	
	/**
	 * ��ȡ��������pageList
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/pageList")
	public String getOntologyRules(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "ontologyRecognise/ontologyRuleList";
	}
	
	/**
	 * �����������
	 * @param request
	 * @param response
	 * @return
	 * @throws CommonAppException 
	 */
	@RequestMapping("/add")
	public @ResponseBody OntologyRule addOntologyRule(HttpServletRequest request, HttpServletResponse response) throws CommonAppException {
		String ruleContent = request.getParameter("ruleContent");
		String conceptId = request.getParameter("conceptId");
		String conceptName = request.getParameter("conceptName");
		
		UserInfo user = UserUtil.getCurrentUser(request);
		OntologyRule rule = new OntologyRule();
		if(user!=null){
			rule.setCreatorId(user.getId());
			rule.setConceptName(user.getUsername());
		}
		
		rule.setConceptId(conceptId);
		rule.setConceptName(conceptName);
		rule.setRuleContent(ruleContent);
		
		OntologyRule newRule = ontologyRuleService.insert(rule);
	
		return newRule;
	}
	
	/**
	 * ���±������
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody String updateOntologyRule(HttpServletRequest request, HttpServletResponse response) {
		String ruleId = request.getParameter("ruleId");
		String ruleContent = request.getParameter("ruleContent");
		
		OntologyRule rule = new OntologyRule();
		rule.setRuleId(ruleId);
		rule.setRuleContent(ruleContent);
		
		String msg = "success";
		try {
			ontologyRuleService.update(rule);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}
	
	/**
	 * ���ݱ������Idɾ���������
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody String deleteOntologyRule(HttpServletRequest request, HttpServletResponse response) {
		String ruleId = request.getParameter("ruleId");
		
		String msg = "success";
		try {
			ontologyRuleService.delById(ruleId);
		} catch (Exception e) {
			e.printStackTrace();
			msg="error";
		}
		return msg;
	}
	
	/**
	 * ��ȡ�������µĹ����ģ��
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ruleAndTemplateByDomain")
	public @ResponseBody List domainAndRule(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String domainId = request.getParameter("domainId");
		
		Map condition = new HashMap();
		condition.put("conceptId", domainId);
		List resultList = ontologyRuleService.getOntologyRuleListByCondition(condition);
		
		return resultList;
	}
	
	@RequestMapping("/getChildDomain")
	public JSONArray getChildDomain(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String domainName = request.getParameter("name");
//		domainName = new String(domainName.getBytes(),"utf-8");
		JSONArray finalArr = new JSONArray();
		JSONArray domainArr = new JSONArray();
		
		if("����ʵ��".equals(domainName)){
			domainName = "";
		}
		
		List<ConceptVo> childDomain = IaServiceClient.getChildDomain(domainName);
		if(childDomain != null && childDomain.size()>0){
			for(ConceptVo domainDef:childDomain){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", domainDef.getId());
				jsonObj.put("name", domainDef.getContent());
				jsonObj.put("domainId", domainDef.getId());
//				jsonObj.put("iconCls", "tree-domain");
				jsonObj.put("isParent", true);
				domainArr.put(jsonObj);
			}
		}
		
		if("".equals(domainName)){
			finalArr = domainArr;
		}else{
			JSONObject ztObject = getObjByDomain(domainName, "����");//�������µ�����������
			JSONObject xwObject = getObjByDomain(domainName, "��Ϊ");
			JSONObject ktObject = getObjByDomain(domainName, "����");
			JSONObject sjObject = getObjByDomain(domainName, "ʱ��");
			JSONObject kjObject = getObjByDomain(domainName, "�ռ�");
			JSONObject childDomainObj = new JSONObject();
			
			if(domainArr!=null && domainArr.length()>0){
				childDomainObj.put("id", "");
				childDomainObj.put("name", "������");
				childDomainObj.put("children", domainArr);
			}
			
			if(childDomainObj!=null && childDomainObj.length()>0){
				finalArr.put(childDomainObj);
			}
			if(ztObject!=null && ztObject.length()>0){
				finalArr.put(ztObject);
			}
			if(xwObject!=null && xwObject.length()>0){
				finalArr.put(xwObject);
			}
			if(ktObject!=null && ktObject.length()>0){
				finalArr.put(ktObject);
			}
			if(sjObject!=null && sjObject.length()>0){
				finalArr.put(sjObject);
			}
			if(kjObject!=null && kjObject.length()>0){
				finalArr.put(kjObject);
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(finalArr.toString());
		return null;
	}
	
	/**
	 * ��ȡ�����µ���ظ����ztree Obj
	 * @param domainName ������
	 * @param parentName ���塢���塢��Ϊ��ʱ�䡢�ռ�
	 * @return
	 */
	public JSONObject getObjByDomain(String domainName,String parentName){
		JSONObject obj = new JSONObject();
		
		String[] parentNames = new String[]{parentName};
		JSONArray conceptArr = new JSONArray();
		//��ȡ�����µ���ظ���
		List<ConceptVo> conceptVos = IaServiceClient.getConceptByDomain(domainName, parentNames);
		if(conceptVos!=null && conceptVos.size()>0){
			List<String> conceptNames = new ArrayList<String>();
			for(ConceptVo vo:conceptVos){
				conceptNames.add(vo.getContent());
			}
			
			for(ConceptVo vo:conceptVos){
				List<String> parNames = vo.getParentNames();
				parNames.retainAll(conceptNames);
				if(parNames==null || parNames.size()==0){
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("id", vo.getId());
					jsonObj.put("name", vo.getContent());
					jsonObj.put("domainId", vo.getId());
					jsonObj.put("isParent", true);
					JSONArray childArr = leavelConcepts(conceptVos,vo);
					if(childArr!=null && childArr.length()>0){
						jsonObj.put("children", childArr);
					}
					conceptArr.put(jsonObj);
				}
			}
		}
		
		if(conceptArr!=null && conceptArr.length()>0){
			obj.put("id", "");
			obj.put("name", parentName);
			obj.put("children", conceptArr);
		}
		return obj;
	}

	private JSONArray leavelConcepts(List<ConceptVo> conceptVos, ConceptVo vo) {
		JSONArray jsonArr = new JSONArray();
		
		for(ConceptVo concept:conceptVos){
			if(concept.getParentNames().contains(vo.getContent())){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", concept.getId());
				jsonObj.put("name", concept.getContent());
				jsonObj.put("domainId", concept.getId());
				jsonObj.put("isParent", true);
//				System.out.println(concept.getContent());
				if(!concept.getContent().equals(vo.getContent())){
					JSONArray childArr = leavelConcepts(conceptVos,concept);
					if(childArr!=null && childArr.length()>0){
						jsonObj.put("children", childArr);
					}
				}
				jsonArr.put(jsonObj);
			}
		}
		
		return jsonArr;
	}
	
}
