package com.excellence.iamp.web.controller;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excellence.common.UserInfo;
import com.excellence.iacommon.common.util.T;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.service.AppMemberService;
import com.excellence.iamp.service.AppService;
import com.excellence.iamp.service.MemberService;
import com.excellence.iamp.util.Jwt;
import com.excellence.iamp.util.MD5Util;
import com.excellence.iamp.vo.App;
import com.excellence.iamp.vo.AppMember;
import com.excellence.iamp.vo.enums.EStatus;
import com.excellence.iamp.vo.Member;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private AppService appService;
	@Autowired
	private AppMemberService appMemberService;
	@Autowired
	private MemberService  memberService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list.do")
	public String getApps(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String appName = MapUtils.getString(map, "appName");
		String appCode = MapUtils.getString(map, "appCode");
		String orderBy = MapUtils.getString(map, "orderBy","createDate");
		String orderDirection = MapUtils.getString(map, "orderDirection","desc");
		Map paramMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(appName)) {
			paramMap.put("appName", appName);
			request.setAttribute("appName", appName);
		}
		if(StringUtils.isNotEmpty(appCode)) {
			paramMap.put("appCode", appCode);
			request.setAttribute("appCode", appCode);
		}
		paramMap.put("status", EStatus.normal.getIndex());
		paramMap.put("orderBy", orderBy);
		paramMap.put("orderDirection", orderDirection);
		PageInfo<App> page = appService.page(paramMap, pageNo, pageSize);
		request.setAttribute("page", page);
		return "/baseManage/app/list";
	}
	
	 @RequestMapping(value="/create.do", method=RequestMethod.GET)
	 public String showCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("method", "create");
        return "/baseManage/app/detail";
    }
	 
	 /**
	  * 创建
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author huangjinyuan, 2014年8月29日.<br>
	  */
	 @RequestMapping(value="/create.do", method=RequestMethod.POST)
	 public String create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map  = WebUtil.requestToMap(request);
		UserInfo user = UserUtil.getCurrentUser(request);
		JSONObject json = new JSONObject();
		boolean result = true;
		String msg = "保存成功";
		String appName = MapUtils.getString(map, "appName");
		String appCode =  MapUtils.getString(map, "appCode");
		String appPwd =  MapUtils.getString(map, "appPwd");
		String restrictIp = MapUtils.getString(map, "restrictIp");
		String remark = MapUtils.getString(map, "remark");
		String appUnit = MapUtils.getString(map, "appUnit");
		int expireType = MapUtils.getIntValue(map, "expireType");
		Calendar c=Calendar.getInstance();
		Date expireDate = null;
		switch (expireType) {
		case 0://永久(100)
			expireDate = T.dateValue("2038-01-01", "yyyy-MM-dd", T.getNow());
			break;
		case 1://3个月
			c.setTime(T.getNow());
			c.add(Calendar.MONTH,3);
			expireDate = c.getTime();
			break;
		case 2://6个月
			c.setTime(T.getNow());
			c.add(Calendar.MONTH,6);
			expireDate = c.getTime();
			break;
		case 3://一年
			c.setTime(T.getNow());
			c.add(Calendar.YEAR,1);
			expireDate = c.getTime();
			break;
		case 4://三年
			c.setTime(T.getNow());
			c.add(Calendar.YEAR,3);
			expireDate = c.getTime();
			break;
		case 5://五年
			c.setTime(T.getNow());
			c.add(Calendar.YEAR,5);
			expireDate = c.getTime();
			break;
		default:
			break;
		}
		if(result) {
			App app = new App();
			app.setAppCode(appCode);
			app.setAppName(appName);
			app.setAppPwd(MD5Util.md5Password(appPwd));
			app.setRemark(remark);
			app.setExpireDate(expireDate);
			app.setRestrictIp(restrictIp);
			app.setCreateDate(T.getNow());
			app.setCreatorId(user.getId());
			app.setCreatorName(user.getUsername());
			app.setStatus(EStatus.normal.getIndex());
			app.setUpdateDate(T.getNow());
			app.setAppUnit(appUnit);
			Map playLoad  = new HashMap<String, Object>();
			playLoad.put("appCode", app.getAppCode());
			playLoad.put("appName", app.getAppName());
			playLoad.put("appPwd", appPwd);
			playLoad.put("expireDate", app.getExpireDate());
			String tokenid = Jwt.createToken(playLoad);
			app.setToken(tokenid);
			try {
				appService.createApp(app);
			} catch (Exception e) {
				msg = e.getMessage();
				result = false;
			}
		}
		json.put("result", result);
		json.put("msg", msg);
        WebUtil.outputHtml(response, json.toString());
        return null;
    }
	 
	 @RequestMapping(value="/update.do", method=RequestMethod.GET)
	 public String showUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String appId = request.getParameter("appId");
        App app = appService.findById(appId);
        request.setAttribute("method", "update");
        request.setAttribute("app", app);
        return "/baseManage/app/detail";
    }
	 
	 @RequestMapping(value="/delete.do")
	 public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String appId = request.getParameter("appId");
        App app = appService.findById(appId);
        if(app != null) {
        	app.setStatus(EStatus.delete.getIndex());
        	appService.updateApp(app);
        }
        JSONObject json = new JSONObject();
        boolean result = true;
		String msg = "删除成功";
		json.put("result", result);
		json.put("msg", msg);
		WebUtil.outputHtml(response, json.toString());
        return null;
    }
	 
	 /**
	  * 更新应用信息
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author huangjinyuan, 2014年8月29日.<br>
	  */
	 @RequestMapping(value="/update.do", method=RequestMethod.POST)
	 public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map map  = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		boolean result = true;
		String msg = "保存成功";
		String appId = MapUtils.getString(map, "appId");
		String appName = MapUtils.getString(map, "appName");
		String appCode =  MapUtils.getString(map, "appCode");
		String appPwd =  MapUtils.getString(map, "appPwd");
		String restrictIp = MapUtils.getString(map, "restrictIp");
		String remark = MapUtils.getString(map, "remark");
		int expireType = MapUtils.getIntValue(map, "expireType");
		String appUnit = MapUtils.getString(map, "appUnit");
		Calendar c=Calendar.getInstance();
		Date expireDate = null;
		switch (expireType) {
		case 0://永久(100)
			expireDate = T.dateValue("2038-01-01", "yyyy-MM-dd", T.getNow());
			break;
		case 1://3个月
			c.setTime(T.getNow());
			c.add(Calendar.MONTH,3);
			expireDate = c.getTime();
			break;
		case 2://6个月
			c.setTime(T.getNow());
			c.add(Calendar.MONTH,6);
			expireDate = c.getTime();
			break;
		case 3://一年
			c.setTime(T.getNow());
			c.add(Calendar.YEAR,1);
			expireDate = c.getTime();
			break;
		case 4://三年
			c.setTime(T.getNow());
			c.add(Calendar.YEAR,3);
			expireDate = c.getTime();
			break;
		case 5://五年
			c.setTime(T.getNow());
			c.add(Calendar.YEAR,5);
			expireDate = c.getTime();
			break;
		default:
			break;
		}
		if(StringUtils.isEmpty(appName) | StringUtils.isEmpty(appCode)) {
			result = false;
			msg = "请填写必要事项";
		}
		if(result) {
			App app = appService.findById(appId);
			if(app != null) {
				if(StringUtils.isNotEmpty(appPwd)) {
					app.setAppPwd(MD5Util.md5Password(appPwd));
					Map playLoad  = new HashMap<String, Object>();
					playLoad.put("appCode", app.getAppCode());
					playLoad.put("appName", app.getAppName());
					playLoad.put("appPwd", appPwd);
					playLoad.put("expireDate", app.getExpireDate());
					String tokenid = Jwt.createToken(playLoad);
					app.setToken(tokenid);
				}
				app.setRestrictIp(restrictIp);
				app.setRemark(remark);
				app.setAppUnit(appUnit);
				app.setExpireDate(expireDate);
				app.setUpdateDate(T.getNow());
				appService.updateApp(app);
			}
		}
		json.put("result", result);
		json.put("msg", msg);
        WebUtil.outputHtml(response, json.toString());
        return null;
    }
	 
	 /**
	  * 跳转至用户列表
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/appMembers/{appId}")
	 public String appMembers(@PathVariable("appId") String appId,HttpServletRequest request, HttpServletResponse response) throws Exception {
		 request.setAttribute("appId", appId);
		 return "/baseManage/app/followMember";
	 }
	 
	/**
	 * 获取所关注的用户
	 * @param appId 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping("/members/{appId}")
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public String members(@PathVariable("appId") String appId,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map  = WebUtil.requestToMap(request);
		Map paramMap = new HashMap<String, Object>();
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String appGroup = MapUtils.getString(map, "appGroup");
		if(StringUtils.isNotBlank(appId)){
			paramMap.put("appId", appId);
		}
		if(StringUtils.isNotBlank(appGroup)){
			paramMap.put("appGroup", appGroup);
		}
		PageInfo<AppMember> memberPage = appMemberService.page(paramMap, pageNo, pageSize);
		int Pages = memberPage.getPages();
		List<AppMember> memberList = memberPage.getList();
		JSONObject json = new JSONObject();
		json.put("pages", Pages);
		json.put("appId", appId);
		json.put("count", memberPage.getTotal());
		JSONArray jsonArray = new JSONArray();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		for (AppMember appMember : memberList) {
			Member member = memberService.findById(appMember.getMemberId());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("memberAccount", member.getMemberAccount());
			jsonObject.put("memberName", member.getMemberName());
			jsonObject.put("emailAddress", member.getEmailAddress());
			jsonObject.put("qqNum", member.getQqNum());
			jsonObject.put("wechatNum", member.getWechatNum());
			jsonObject.put("appGroup", appMember.getAppGroup() != null ?appMember.getAppGroup():"");
			jsonObject.put("followTime", appMember.getFollowTime() != null ?dateFormater.format(appMember.getFollowTime()):"");
			if(member.getPhoneNum()!=null){
				jsonObject.put("phoneNum", member.getPhoneNum());
			}else{
				jsonObject.put("phoneNum", "");
			}
			jsonObject.put("appId", appMember.getAppId());
			jsonObject.put("memberId", appMember.getMemberId());
			jsonArray.put(jsonObject);
		}
		json.put("members", jsonArray);
		WebUtil.outputHtml(response, json.toString());
		return null;
	 }
	 
	 /**
	  * 剔除关注
	  * @author lih
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/memDel")
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public String delMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 Map map  = WebUtil.requestToMap(request);
		 String appIds = MapUtils.getString(map, "appIds");
		 String memberIds = MapUtils.getString(map, "memberIds");
		 String msg ="剔除失败" ;
		 Integer count = 0;
		 boolean result = false;
		 if(StringUtils.isNotBlank(appIds)&&StringUtils.isNotBlank(memberIds)){
			 String[] appIdArr = appIds.split(";");
			 String[] memberIdArr = memberIds.split(";");
			 for(int i=0;i<appIdArr.length;i++){
				 if(StringUtils.isNotEmpty(appIdArr[i])&&StringUtils.isNotEmpty(memberIdArr[i])){
					 count +=  appMemberService.deleteByDoubleId(appIdArr[i], memberIdArr[i]);
				 }
			 }
			 msg = "成功剔除"+count+"条";
			 result = true;
		 }
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("msg", msg);
		 jsonObject.put("result", result);
		 WebUtil.outputHtml(response, jsonObject.toString());
		 return null;
	 }
	 
	 /**
	  * 显示可导入会员列表、查询
	  * @author lih
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/memberList/{appId}")
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public String memberList(@PathVariable("appId") String appId,HttpServletRequest request, HttpServletResponse response) throws Exception {
			Map map  = WebUtil.requestToMap(request);
			int pageNo = MapUtils.getIntValue(map, "pageNo",1);
			int pageSize = MapUtils.getIntValue(map, "pageSize",10);
			String memberAccount = MapUtils.getString(map, "memberAccount");
			String memberName = MapUtils.getString(map, "memberName");
			String orderBy = MapUtils.getString(map, "orderBy","createDate");
			String orderDirection = MapUtils.getString(map, "orderDirection","desc");
			String selectOpt = MapUtils.getString(map, "selectOpt","memberAccount");
			String queryCondition = MapUtils.getString(map, "queryCondition","");
			Map paramMap = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(memberAccount)) {
				paramMap.put("memberAccount", memberAccount);
			}
			if(StringUtils.isNotEmpty(memberName)) {
				paramMap.put("memberName", memberName);
			}
			if(StringUtils.isNotEmpty(appId)) {
				paramMap.put("appId", appId);
			}
			if(StringUtils.isNotEmpty(queryCondition)) {
				paramMap.put(selectOpt, queryCondition);
				request.setAttribute("selectOpt", selectOpt);
				request.setAttribute("queryCondition", queryCondition);
			}
			paramMap.put("orderBy", orderBy);
			paramMap.put("orderDirection", orderDirection);
			PageInfo<Member> page = memberService.pageExcludeAppId(paramMap, pageNo, pageSize);
			List<Member> memberList = page.getList();
			List<Map<String,String>> pageList = new ArrayList<Map<String,String>>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if(memberList != null && memberList.size() > 0){
				for(int i=0;i<memberList.size();i++){
					Map<String,String> map1 = new HashMap<String, String>();
					Map paramMap1 = new HashMap<String, Object>();
					paramMap1.put("memberId", memberList.get(i).getMemberId());
					StringBuilder str = new StringBuilder();
					List<AppMember> iaAppMemberList = appMemberService.getByCondition(paramMap1);
					if(iaAppMemberList != null && iaAppMemberList.size() > 0){
						for(int j=0;j<iaAppMemberList.size();j++){
							if(str.length()>0){
								str.append(";");
							}
							str.append(iaAppMemberList.get(j).getAppName());
						}
					}
					map1.put("memberId", memberList.get(i).getMemberId());
					map1.put("memberAccount", memberList.get(i).getMemberAccount());
					map1.put("memberName", memberList.get(i).getMemberName());
					map1.put("createDate", sdf.format(memberList.get(i).getCreateDate()));
					map1.put("remark", memberList.get(i).getRemark());
					pageList.add(map1);
				}
			}
			
			request.setAttribute("page", page);
			request.setAttribute("pageList", pageList);
			request.setAttribute("appId", appId);
			return "/baseManage/app/importMember";
	 }
	 /**
	  * 导入会员到关注
	  * @author lih
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/imported")
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public String importMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map  = WebUtil.requestToMap(request);
		String memberIds = MapUtils.getString(map, "memberIds");
		String appId = MapUtils.getString(map, "appId");
		String appGroup = MapUtils.getString(map, "appGroup");
		String memberNames  = MapUtils.getString(map, "memberNames");
		String msg = "";
		boolean result = false;
		if(StringUtils.isNotBlank(appId)&&StringUtils.isNotBlank(memberIds)){
			App app = appService.findById(appId);
			String[] memberIdArr = memberIds.split(";");
			String[] memberNameArr = memberNames.split(";");
			for(int i=0;i<memberIdArr.length;i++){
				if(StringUtils.isNotEmpty(memberIdArr[i])&&StringUtils.isNotEmpty(memberNameArr[i])){
					AppMember appMember = new AppMember();
					appMember.setAppId(appId);
					appMember.setAppName(app.getAppName());
					appMember.setMemberId(memberIdArr[i]);
					appMember.setMemberName(memberNameArr[i]);
					appMember.setAppGroup(appGroup);
					appMember.setFollowTime(new Date());
					result = true;
					msg="导入成功";
					try {
						appMemberService.create(appMember);
					} catch (Exception e) {
						msg = e.getMessage();
						result = false;
					}
				}
			}
			 
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", msg);
		jsonObject.put("result", result);
		WebUtil.outputHtml(response, jsonObject.toString());
		return null;
	 }
}
