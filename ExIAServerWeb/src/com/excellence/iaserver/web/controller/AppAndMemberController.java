package com.excellence.iaserver.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.service.AppMemberService;
import com.excellence.iamp.service.AppService;
import com.excellence.iamp.service.MemberService;
import com.excellence.iamp.service.TagRecordService;
import com.excellence.iamp.service.UserDomainService;
import com.excellence.iamp.util.MD5Util;
import com.excellence.iamp.vo.App;
import com.excellence.iamp.vo.AppMember;
import com.excellence.iamp.vo.Member;
import com.excellence.iamp.vo.TagRecord;
import com.excellence.iamp.vo.UserDomain;
import com.excellence.iaserver.common.util.Jwt;
import com.excellence.iaserver.common.util.WebUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/services/base/member")
public class AppAndMemberController {
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AppMemberService appMemberService;
	
	@Autowired
	private UserDomainService userDomainService;
	
	@Autowired
	private TagRecordService tagRecordService;
	
	
	/**
	 *���ܣ�  ��ȡapp��ע��Ա�嵥 
	 *@param extoken 
	 *@param appGroup   ��������
	 *@param page		ҳ��
	 *@param pageSize	ÿҳ����
	 *@author wangjg
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/appmember/list"/*,method=RequestMethod.POST*/)
	public String getAppMemberList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		String extoken = MapUtils.getString(map, "extoken","");
		String appGroup = MapUtils.getString(map, "appGroup","");
		int page = MapUtils.getInteger(map, "page",1);
		int pageSize = MapUtils.getInteger(map, "pageSize",50);
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		int num = 0;
		JSONObject json2 = new JSONObject();
		String msg = "";
		if(isSuccess) {
			Map paramMap = new HashMap<String, Object>();
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			String appCode = resultObj.getString("appCode");
			String appPwd = resultObj.getString("appPwd");
			App app = appService.getAppByAppCodeAndPwd(appCode, MD5Util.md5Password(appPwd));
			String appId = "";
			if(app != null){
				appId = app.getAppId();
			}
			paramMap.put("appId", appId);
			paramMap.put("appGroup", appGroup);
			JSONArray dataArr = new JSONArray();
			PageInfo<AppMember> appMemberPage = appMemberService.page(paramMap, page, pageSize);
			int Pages = appMemberPage.getPages();
			List<AppMember> memberList = appMemberPage.getList();
			if(memberList != null && memberList.size() > 0){
				for(int i=0;i<memberList.size();i++){
					JSONObject json1 = new JSONObject();
					String appMemberId = memberList.get(i).getId();
					String groupName = memberList.get(i).getAppGroup();
					Member member = memberService.findById(memberList.get(i).getMemberId());
					if(member != null){
						msg = "���ؽ���ɹ�";
						json1.put("memberId", member.getMemberId());
						json1.put("appMemberId", appMemberId);
						if(StringUtils.contains(member.getMemberAccount(), "\\")){
							String memberAccount1 = StringUtils.split(member.getMemberAccount(), "\\")[1];
							json1.put("memberAccount", memberAccount1);
						}else{
							json1.put("memberAccount", member.getMemberAccount());
						}
						json1.put("memberName", member.getMemberName());
						json1.put("qqNum", member.getQqNum());
						json1.put("emailAddress", member.getEmailAddress());
						json1.put("wechatNum", member.getWechatNum());
						json1.put("phoneNumber", member.getPhoneNum());
						json1.put("remark", member.getRemark());
						json1.put("groupName", groupName);
						Map paramMap1 = new HashMap();
						paramMap1.put("roleId", member.getMemberId());
						paramMap1.put("roleName", member.getMemberName());
						JSONArray domainArr = new JSONArray();
						List<UserDomain> userDomainList = userDomainService.getByCondition(paramMap1);
						if(userDomainList != null && userDomainList.size() > 0){
							for(UserDomain userDomain : userDomainList){
								domainArr.put(userDomain.getDomainName());
							}
						}
						json1.put("domains", domainArr.toString());
						dataArr.put(json1);
						num++;
					}
				}
				json2.put("num", num);
				json2.put("page", page);
				json2.put("pageSize", pageSize);
				json2.put("informations", dataArr);
			}else{
				msg = "�޷��ؽ��";
			}
		}else{
			msg = "token���Ϸ���";
		}
		json.put("status", 1);
		json.put("msg", msg);
		json.put("data", json2);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	/**
	 *���ܣ�  ע���Ա��Ϣ�����й�ע��ǰӦ��
	 *@param extoken 
	 *@param memberAccount   	��Ա�˺�
	 *@param memberName			��Ա����
	 *@param qqNum				�󶨵�QQ��
	 *@param emailAddress		�󶨵ĵ�������
	 *@param wechatNum			�󶨵�΢�ź�
	 *@param phoneNumber		�󶨵��ֻ�����
	 *@param remark				��ע
	 *@param memberPsw			��¼���룬Ĭ��Ϊ��ǰ���ڣ�yyyyMMdd��
	 *@author wangjg
	 **/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/register")
	public String createMemberAndFocusApp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(map, "extoken","");
		String memberAccount = MapUtils.getString(map, "memberAccount","");
		String memberName = MapUtils.getString(map, "memberName","");
		String qqNum = MapUtils.getString(map, "qqNum","");
		String emailAddress = MapUtils.getString(map, "emailAddress","");
		String wechatNum = MapUtils.getString(map, "wechatNum","");
		String phoneNumber = MapUtils.getString(map, "phoneNumber","");
		String remark = MapUtils.getString(map, "remark","");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String memberPsw = MapUtils.getString(map, "memberPsw", sdf.format(new Date()));
		if(StringUtils.isEmpty(memberPsw)) {
			memberPsw = sdf.format(new Date());
		}
		String group = MapUtils.getString(map, "group","");
		String domains = MapUtils.getString(map, "domains","");
		String extendAttribute = MapUtils.getString(map, "extendAttribute","");
		String appCodeMemberAccount = "";
		int status = 1;
		String msg = "�з��ؽ��!";
		JSONObject json = new JSONObject();
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		Member member = null;
		AppMember appmember = null;
		if(isSuccess){
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			String appCode = resultObj.getString("appCode");
			String appPwd = resultObj.getString("appPwd");
			Map paramMap3 = new HashMap<String, Object>();
			if(memberAccount.contains(appCode)) {
				appCodeMemberAccount = memberAccount;
			} else {
				appCodeMemberAccount = appCode + "\\" + memberAccount;
			}
			paramMap3.put("memberAccount", appCodeMemberAccount);
			List<Member> memberList = memberService.getByCondition(paramMap3);
			if(memberList != null && memberList.size() > 0){
				status = -1;
				msg = "�û�Ա�Ѿ�����!";
				JSONObject json1 = new JSONObject();
				json1.put("memberId", memberList.get(0).getMemberId());
				json1.put("memberName", memberList.get(0).getMemberName());
				json.put("data", json1);
			}else{
				Map paramMap = new HashMap<String, Object>();
				App app = appService.getAppByAppCodeAndPwd(appCode, MD5Util.md5Password(appPwd));
				String appId = "";
				if(app != null){
					appId = app.getAppId();
				}
				paramMap.put("appId", appId);
				paramMap.put("memberName", memberName);
				List<AppMember> appMemberList = appMemberService.getByCondition(paramMap);
				if(appMemberList != null && appMemberList.size() > 0){
					status = 0;
					msg = "�û�Ա�Ѿ�ע��!";
					JSONObject json1 = new JSONObject();
					json1.put("memberId", appMemberList.get(0).getMemberId());
					json1.put("memberName", appMemberList.get(0).getMemberName());
					json1.put("appMemberId", appMemberList.get(0).getId());
					json.put("data", json1);
				}else{
					member = new Member();
					member.setMemberId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
					member.setCreateDate(new Date());
					member.setEmailAddress(emailAddress);
					member.setMemberAccount(appCodeMemberAccount);
					member.setMemberName(memberName);
					member.setPhoneNum(phoneNumber);
					member.setQqNum(qqNum);
					member.setRemark(remark);
					member.setWechatNum(wechatNum);
					member.setUpdateDate(new Date());
					member.setMemberPsw(MD5Util.md5Password(memberPsw));
					member.setExtendAttribute(extendAttribute);
					int num = memberService.create(member);
					if(StringUtils.isNotBlank(domains)){
						userDomainService.deleteByRoleId(member.getMemberId());//�޸Ĺ�ע����֮ǰ��ɾ�����й�ע������
						JSONArray domainArr = new JSONArray(domains);
						if(domainArr != null && domainArr.length() > 0) {
							for(int j=0;j<domainArr.length();j++){
								UserDomain userDomian = new UserDomain();
								userDomian.setUserDomainId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
								userDomian.setCreateDate(new Date());
								userDomian.setDisplayOrder(1);
								userDomian.setRoleType(0);
								userDomian.setRoleId(member.getMemberId());
								userDomian.setRoleName(member.getMemberName());
								userDomian.setStatus(1);
								userDomian.setCreatorId("admin");
								userDomian.setCreatorName("����Ա");
								userDomian.setDomainName(domainArr.get(j).toString());
								userDomainService.create(userDomian);
							}
						}
					}
					if(num > 0){
						appmember = new AppMember();
						appmember.setAppName(app.getAppName());
						appmember.setAppId(app.getAppId());
						appmember.setMemberId(member.getMemberId());
						appmember.setMemberName(member.getMemberName());
						appmember.setId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
						appmember.setFollowTime(new Date());
						appmember.setAppGroup(group);
						appMemberService.create(appmember);
						if(member != null && appmember != null){
							JSONObject json1 = new JSONObject();
							json1.put("memberId", member.getMemberId());
							json1.put("appMemberId", appmember.getId());
							json.put("data", json1);
						}
					}
				}
			}
		}else{
			status = -9999;
			msg = "token���Ϸ���";
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	
	/**
	 *���ܣ�  ��ȡָ���Ļ�Ա��Ϣ
	 *@param extoken 
	 *@param idType      ���ID���ͣ� memberId--��ԱID��appMemberId--��Ա��עID��memberAccount--��Ա��¼�ʺš�phoneNumber--��Ա�󶨵��ֻ��š�qqNum--��Ա�󶨵�QQ���롢emailAddress--��Ա�󶨵������ַ��wechatNum--��Ա�󶨵�΢�ź�
	 *@param loginId   	   ���ID���Ͷ�Ӧ��ֵ
	 *@author wangjg
	 **/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/appmember")
	public String getAppointMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(map, "extoken","");
		String idType = MapUtils.getString(map, "idType","");
		String loginId = MapUtils.getString(map, "loginId","");
		
		int status = 1;
		String msg = "�з��ؽ��!";
		JSONArray arr =new JSONArray();
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess){
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			//paramMap1.put("appCode", resultObj.getString("appCode"));
			String appCode = resultObj.getString("appCode");
			String appPwd = resultObj.getString("appPwd");
			List<Member> memberList = null;
			String memberAccount = "";
			String appCodeMemberAccount = "";
			if(StringUtils.equals(idType, "memberAccount")){
				if(StringUtils.contains(loginId, appCode)){
					Map paramMap = new HashMap<String, Object>();
					paramMap.put(idType, loginId);
					memberList = memberService.getByCondition(paramMap);
					memberAccount = StringUtils.split(loginId, "\\")[1];
					appCodeMemberAccount = loginId;
				}else{
					Map paramMap = new HashMap<String, Object>();
					appCodeMemberAccount = appCode+"\\"+loginId;
					paramMap.put(idType, appCodeMemberAccount);
					memberList = memberService.getByCondition(paramMap);
					memberAccount = loginId;
				}
			}
			if(memberList != null && memberList.size() > 0){
				for(int i=0;i<memberList.size();i++){
					JSONObject json1 = new JSONObject();
					Map paramMap2 = new HashMap<String, Object>();
					App app = appService.getAppByAppCodeAndPwd(appCode, MD5Util.md5Password(appPwd));
					String appId = "";
					if(app != null){
						appId = app.getAppId();
						Map paramMap1 = new HashMap<String, Object>();
						paramMap1.put("memberId", memberList.get(i).getMemberId());
						paramMap1.put("appId", appId);
						List<AppMember> appMemberList = appMemberService.getByCondition(paramMap1);
						JSONArray arr1 = new JSONArray();
						if(appMemberList != null && appMemberList.size() > 0){
							AppMember appMember = appMemberList.get(0);
							json1.put("memberId", memberList.get(i).getMemberId());
							if(StringUtils.contains(memberList.get(i).getMemberAccount(), app.getAppCode())){
								String memberAccount1 = StringUtils.split(memberList.get(i).getMemberAccount(), "\\")[1];
								json1.put("memberAccount", memberAccount1);
							}else{
								json1.put("memberAccount", memberList.get(i).getMemberAccount());
							}
							json1.put("memberName", memberList.get(i).getMemberName());
							json1.put("qqNum", memberList.get(i).getQqNum());
							json1.put("emailAddress", memberList.get(i).getEmailAddress());
							json1.put("wechatNum", memberList.get(i).getWechatNum());
							json1.put("phoneNumber", memberList.get(i).getPhoneNum());
							json1.put("remark", memberList.get(i).getRemark());
							json1.put("groupName", appMember.getAppGroup());
							json1.put("appMemberId",  appMember.getId());
							Map paramMap5 = new HashMap();
							paramMap5.put("roleId", memberList.get(i).getMemberId());
							paramMap5.put("roleName", memberList.get(i).getMemberName());
							JSONArray domainArr = new JSONArray();
							List<UserDomain> userDomainList = userDomainService.getByCondition(paramMap5);
							if(userDomainList != null && userDomainList.size() > 0){
								for(UserDomain userDomain : userDomainList){
									domainArr.put(userDomain.getDomainName());
								}
							}
							json1.put("domains", domainArr.toString());
							
							JSONObject tags = new JSONObject();
							//�û���ǩ
							Map condition = new HashMap();
							condition.put("subjectAccount", appCodeMemberAccount);
							List<TagRecord> tagRecordList = tagRecordService.getTagRecordByCondition(condition);
							if(tagRecordList!=null && tagRecordList.size()>0){
								for(TagRecord tagRecord:tagRecordList){
									String tagType = tagRecord.getTagType();
									String tagName = tagRecord.getTagName();
									
									if(tags.has(tagType)){
										tags.getJSONArray(tagType).put(tagName);
									}else{
										JSONArray tagNameArr = new JSONArray();
										tagNameArr.put(tagName);
										tags.put(tagType, tagNameArr);
									}
								}
							}
							json1.put("tags", tags);
							
							arr.put(json1);
						}else{
							status = -2;
							msg = "�ʻ�������!";
						}
					}
				}
			}else{
				status = -2;
				msg = "���ؽ���쳣!";
			}
		} else {
			status = -9999;
			msg="token��Ч!";
		}
		
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("msg", msg);
		json.put("data", arr);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	
	/**
	 *���ܣ�  ��֤��Ա����
	 *@param extoken 
	 *@param memberName  ��Ա����
	 *@param memberPsw    ��Ա����
	 *@author wangjg
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/login")
	public String verifyMemberPsw(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		String extoken = MapUtils.getString(map, "extoken","");
		String memberPsw = MapUtils.getString(map, "memberPsw","");
		String memberAccount = MapUtils.getString(map, "memberAccount","");
		int status = 1;
		String msg = "�з��ؽ��!";
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess){
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			String appCode = resultObj.getString("appCode");
			//String appPwd = resultObj.getString("appPwd");
			List<Member> memberList = null;
			Map paramMap = new HashMap<String, Object>();
			//paramMap.put("memberPsw", MD5Util.md5Password(memberPsw));
			String appCodeMemberAccount = "";
			if(StringUtils.contains(memberAccount, appCode)){
				paramMap.put("memberAccount", memberAccount);
				memberAccount = StringUtils.split(memberAccount, "\\")[1];
				memberList = memberService.getByCondition(paramMap);
			}else{
				appCodeMemberAccount = appCode+ "\\" + memberAccount;
				paramMap.put("memberAccount", appCodeMemberAccount);
				memberList = memberService.getByCondition(paramMap);
			}
			if(memberList != null && memberList.size() > 0){
				Member member = memberList.get(0);
				JSONObject json1 = new JSONObject();
				if(StringUtils.equals(member.getMemberPsw(), MD5Util.md5Password(memberPsw))){
					json1.put("memberId", member.getMemberId());
					if(StringUtils.contains(member.getMemberAccount(), appCode)){
						String memberAccount1 = StringUtils.split(member.getMemberAccount(), "\\")[1];
						json1.put("memberAccount", memberAccount1);
					}else{
						json1.put("memberAccount", member.getMemberAccount());
					}
					json1.put("memberName", member.getMemberName());
					json.put("data", json1);
				}else{
					status = -1;
					msg = "�˺��������!";
				}
			}else{
				status = -2;
				msg = "�˺Ų�����!";
			}
			
		} else {
			status = -9999;
			msg="token��Ч!";
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	
	/**
	 *���ܣ�  �޸Ļ�Ա��Ϣ
	 *@param extoken 
	 *@param memberAccount   	��Ա�˺�
	 *@param qqNum				�󶨵�QQ��
	 *@param emailAddress		�󶨵ĵ�������
	 *@param wechatNum			�󶨵�΢�ź�
	 *@param phoneNumber		�󶨵��ֻ�����
	 *@param remark				��ע
	 *@param memberPsw			��¼���룬Ĭ��Ϊ��ǰ���ڣ�yyyyMMdd��
	 *@param extendAttribute	��������
	 *@param domains			��Ա��ע����
	 *@author wangjg
	 **/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/update")
	public String upDateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(map, "extoken","");
		String memberAccount = MapUtils.getString(map, "memberAccount","");
		String qqNum = MapUtils.getString(map, "qqNum","");
		String emailAddress = MapUtils.getString(map, "emailAddress","");
		String wechatNum = MapUtils.getString(map, "wechatNum","");
		String phoneNumber = MapUtils.getString(map, "phoneNumber","");
		String remark = MapUtils.getString(map, "remark","");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String memberPsw = MapUtils.getString(map, "memberPsw","");
		String domains = MapUtils.getString(map, "domains","");
		String extendAttribute = MapUtils.getString(map, "extendAttribute","");
		Member member = null;
		int status = 1;
		String msg = "�޸ĳɹ�!";
		JSONObject json = new JSONObject();
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess){
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			String appCode = resultObj.getString("appCode");
			String appPwd = resultObj.getString("appPwd");
			Map paramMap1 = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(memberPsw)){
				paramMap1.put("memberPsw", MD5Util.md5Password(memberPsw));
			}
			String appCodeMemberAccount = "";
			if(StringUtils.contains(memberAccount, appCode)){
				paramMap1.put("memberAccount", memberAccount);
			}else{
				appCodeMemberAccount = appCode + "\\" + memberAccount;
				paramMap1.put("memberAccount", appCodeMemberAccount);
			}
			List<Member> memberList = memberService.getByCondition(paramMap1);
			if(memberList != null && memberList.size() > 0){
				member = memberList.get(0);
				if(StringUtils.isNotBlank(emailAddress)){
					member.setEmailAddress(emailAddress);
				}
				if(StringUtils.isNotBlank(extendAttribute)){
					member.setExtendAttribute(extendAttribute);
				}
				if(StringUtils.isNotBlank(wechatNum)){
					member.setWechatNum(wechatNum);
				}
				if(StringUtils.isNotBlank(phoneNumber)){
					member.setPhoneNum(phoneNumber);
				}
				if(StringUtils.isNotBlank(qqNum)){
					member.setQqNum(qqNum);
				}
				if(StringUtils.isNotBlank(remark)){
					member.setRemark(remark);
				}
				member.setUpdateDate(new Date());
				/*if(StringUtils.isNotBlank(memberPsw)){
					member.setMemberPsw(MD5Util.md5Password(memberPsw));
				}*/
				if(StringUtils.isNotBlank(domains)){
					userDomainService.deleteByRoleId(member.getMemberId());//�޸�����֮ǰ��ɾ�����й�ע����
					JSONArray domainArr = new JSONArray(domains);
					for(int j=0;j<domainArr.length();j++){
						UserDomain userDomian = new UserDomain();
						userDomian.setUserDomainId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
						userDomian.setCreateDate(new Date());
						userDomian.setDisplayOrder(1);
						userDomian.setRoleType(0);
						userDomian.setRoleId(member.getMemberId());
						userDomian.setRoleName(member.getMemberName());
						userDomian.setCreatorId("admin");
						userDomian.setCreatorName("����Ա");
						userDomian.setStatus(1);
						userDomian.setDomainName(domainArr.get(j).toString());
						userDomainService.create(userDomian);
					}
				}
				memberService.update(member);
			}else{
				status = -2;
				msg = "�û�Ա������!";
			}
		}else{
			status = -9999;
			msg = "token���Ϸ���";
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	
	/**
	 *���ܣ�  �޸Ļ�Ա����
	 *@param extoken 
	 *@param memberName  ��Ա����
	 *@param memberPsw    ��Ա����
	 *@param newMemberPsw    ��Ա����
	 *@author wangjg
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/modifyMemberPsw")
	public String modifyMemberPsw(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		String extoken = MapUtils.getString(map, "extoken","");
		String memberPsw = MapUtils.getString(map, "memberPsw","");
		String newMemberPsw = MapUtils.getString(map, "newMemberPsw","");
		String memberAccount = MapUtils.getString(map, "memberAccount","");
		int status = 1;
		String msg = "�з��ؽ��,����δ�޸�!";
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess){
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			String appCode = resultObj.getString("appCode");
			//String appPwd = resultObj.getString("appPwd");
			List<Member> memberList = null;
			Map paramMap = new HashMap<String, Object>();
			paramMap.put("memberPsw", MD5Util.md5Password(memberPsw));
			String appCodeMemberAccount = "";
			if(StringUtils.contains(memberAccount, appCode)){
				paramMap.put("memberAccount", memberAccount);
				memberAccount = StringUtils.split(memberAccount, "\\")[1];
				memberList = memberService.getByCondition(paramMap);
			}else{
				appCodeMemberAccount = appCode+ "\\" + memberAccount;
				paramMap.put("memberAccount", appCodeMemberAccount);
				memberList = memberService.getByCondition(paramMap);
			}
			if(memberList != null && memberList.size() > 0){
				Member member = memberList.get(0);
				if(StringUtils.isNotBlank(newMemberPsw)){
					member.setMemberPsw(MD5Util.md5Password(newMemberPsw));
					memberService.update(member);
					msg = "�����޸ĳɹ�!";
				}
				JSONObject json1 = new JSONObject();
				json1.put("memberId", member.getMemberId());
				if(StringUtils.contains(member.getMemberAccount(), appCode)){
					String memberAccount1 = StringUtils.split(member.getMemberAccount(), "\\")[1];
					json1.put("memberAccount", memberAccount1);
				}else{
					json1.put("memberAccount", member.getMemberAccount());
				}
				json1.put("memberName", member.getMemberName());
				json.put("data", json1);
			}else{
				status = -2;
				msg = "�˻��������!";
			}
			
		} else {
			status = -9999;
			msg="token��Ч!";
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"����");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
}
