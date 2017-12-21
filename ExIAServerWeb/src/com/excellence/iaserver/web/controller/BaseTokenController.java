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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.service.AppMemberService;
import com.excellence.iamp.service.AppService;
import com.excellence.iamp.service.MemberService;
import com.excellence.iamp.util.MD5Util;
import com.excellence.iamp.vo.App;
import com.excellence.iamp.vo.AppMember;
import com.excellence.iamp.vo.Member;
import com.excellence.iaserver.common.cache.CacheClient;
import com.excellence.iaserver.common.util.Jwt;
import com.excellence.iaserver.common.util.WebUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;

@Controller
@RequestMapping("/services/base/security")
public class BaseTokenController {
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AppMemberService appMemberService;
	
	@Autowired
	private CacheClient cacheClient;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/gettoken"/*,method=RequestMethod.POST*/)
	public String getToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		String appCode = MapUtils.getString(map, "appCode");
		String appPwd = MapUtils.getString(map, "appPwd");
		int status = 0;
		String msg = "获取token失败";
		if(StringUtils.isNotEmpty(appCode) && StringUtils.isNotEmpty(appPwd)) {
			App app = appService.getAppByAppCodeAndPwd(appCode, MD5Util.md5Password(appPwd));
			if(app != null) {
				Map playLoad  = new HashMap<String, Object>();
				playLoad.put("appCode", app.getAppCode());
				playLoad.put("appName", app.getAppName());
				playLoad.put("appPwd", appPwd);
				playLoad.put("expireDate", app.getExpireDate());
				String tokenid = Jwt.createToken(playLoad);
				status = 1;
				msg = "操作成功";
				json.put("tokenid", tokenid);
			}
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	@RequestMapping(value="/clearCache")
	public String clearCache(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		String group = request.getParameter("group");
		if(StringUtils.isNotEmpty(group)) {
			cacheClient.removePartmatch(null, group);
		} else {
			cacheClient.flushAll();
		}
		JSONObject json = new JSONObject();
		int status = 1;
		String msg = "清除缓存成功!";
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**
	 *功能：授权某个会员使用APP
	 *@param extoken 
	 *@param memberId   		会员账号
	 *@param memberName			会员姓名
	 *@author wangjg
	 **/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/grant/member")
	public String accreditMemberUseApp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(map, "extoken","");
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		String memberId = MapUtils.getString(map, "memberId","");
		String memberName = MapUtils.getString(map, "memberName","");
		
		int status = 1;
		String msg = "关注应用成功!";
		AppMember appmember = null;
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess){
			Map paramMap = new HashMap<String, Object>();
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			//paramMap1.put("appCode", resultObj.getString("appCode"));
			String appCode = resultObj.getString("appCode");
			String appPwd = resultObj.getString("appPwd");
			App app = appService.getAppByAppCodeAndPwd(appCode, MD5Util.md5Password(appPwd));
			String appId = "";
			if(app != null){
				appId = app.getAppId();
			}
			paramMap.put("appId", appId);
			paramMap.put("memberName", memberName);
			paramMap.put("memberId", memberId);
			List<AppMember> appMemberList = appMemberService.getByCondition(paramMap);
			if(appMemberList != null && appMemberList.size() > 0){
				status = -1;
				msg = "该会员已经关注该应用!";
			}else{
				appmember = new AppMember();
				if(app != null){
					appmember.setAppName(app.getAppName());
					appmember.setAppId(app.getAppId());
				}
				if(StringUtils.isNotBlank(memberId)){
					appmember.setMemberId(memberId);
					Member member1 = memberService.findById(memberId);
					appmember.setMemberName(member1.getMemberName());
				}else{
					Map paramMap2 = new HashMap<String, Object>();
					paramMap2.put("memberName", memberName);
					List<Member> memberList = memberService.getByCondition(paramMap2);
					if(memberList != null && memberList.size() > 0){
						appmember.setMemberName(memberList.get(0).getMemberName());
						appmember.setMemberId(memberList.get(0).getMemberId());
						appmember.setId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
						appMemberService.create(appmember);
					}else{
						msg = "该会员不存在！";
					}
				}
			}
		}else{
			msg = "token不合法！";
		}
		
		
		JSONObject json = new JSONObject();
		if(appmember != null){
			JSONObject json1 = new JSONObject();
			json1.put("memberId", appmember.getMemberId());
			json1.put("appMemberId", appmember.getId());
			json.put("data", json1);
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
}
