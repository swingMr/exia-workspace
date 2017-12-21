package com.excellence.iamp.web.controller;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excellence.common.UserInfo;
import com.excellence.common.util.GUIDGenerator;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.service.MemberService;
import com.excellence.iamp.service.UserDomainService;
import com.excellence.iamp.vo.Member;
import com.excellence.iamp.vo.UserDomain;
import com.excellence.iamp.vo.enums.EStatus;
import com.excellence.platform.um.exception.CommonAppException;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/userDomain")
public class UserDomainController {
	
	@Autowired
	private UserDomainService userDomainService;
	
	@Autowired
	private MemberService memberService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/domainList.do")
	public String getUserDomains(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, CommonAppException {
		Map map  = WebUtil.requestToMap(request);
		UserInfo user = UserUtil.getCurrentUser(request);
		String memberId = MapUtils.getString(map, "memberId","");
		String memberName = MapUtils.getString(map, "memberName","");
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		Map<String,String> paramap = new HashMap<String, String>();
		paramap.put("roleId", memberId);
		//paramap.put("roleName", memberName);
		PageInfo<UserDomain> page = userDomainService.page(paramap, pageNo, pageSize);
		request.setAttribute("page", page);
		request.setAttribute("roleName", memberName);
		request.setAttribute("memberId", memberId);
		return "/baseManage/memberManage/domainList";
	}
	
	 
	 /**
	  * 创建会员所属领域信息
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author wangjg, 2017-8-28<br>
	  */
	 @SuppressWarnings({ "rawtypes", "unchecked"})
	 @RequestMapping(value="/create.do", method=RequestMethod.POST)
	 public String create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map  = WebUtil.requestToMap(request);
		UserInfo user = UserUtil.getCurrentUser(request);
		JSONObject json = new JSONObject();
		int num = 0;
		String memberIds = MapUtils.getString(map, "memberIds","");
		//String memberNames =  MapUtils.getString(map, "memberNames","");
		String domainNames = MapUtils.getString(map, "domainNames","");
		String userDomainIds = MapUtils.getString(map, "userDomainIds","");
		if(StringUtils.isNotBlank(userDomainIds)){
			Integer deleteNum = userDomainService.deleteByRoleId(memberIds);
		}
		
		JSONArray resultArr = new JSONArray();
		
		if(StringUtils.isNotBlank(memberIds)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd : hh:mm");
			String[] memberIdArr = StringUtils.split(memberIds, ";");
			for(int i=0;i<memberIdArr.length;i++){
				if(StringUtils.isNotBlank(domainNames)){
					String[] domainArr = StringUtils.split(domainNames, ";");
					for(int j=0;j<domainArr.length;j++){
						Map paramMap = new HashMap();
						paramMap.put("domainName", domainArr[j]);
						paramMap.put("roleId", memberIdArr[i]);
						//查询是否存在重复的记录
						List<UserDomain> userDomainList = userDomainService.getByCondition(paramMap);
						if(userDomainList.size() == 0){
							UserDomain userdomain = new UserDomain();
							userdomain.setUserDomainId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
							userdomain.setRoleId(memberIdArr[i]);
							Member member = memberService.findById(memberIdArr[i]);
							userdomain.setRoleName(member.getMemberName());
							userdomain.setStatus(EStatus.normal.getIndex());
							userdomain.setCreateDate(new Date());
							userdomain.setCreatorId(user.getId());
							userdomain.setCreatorName(user.getUsername());
							userdomain.setDomainName(domainArr[j]);
							userdomain.setRoleType(0);
							userdomain.setDisplayOrder(0);
							userDomainService.create(userdomain);
							JSONObject json1 = new JSONObject();
							json1.put("domainName", domainArr[j]);
							json1.put("userDomainId", userdomain.getUserDomainId());
							json1.put("createDate", sdf.format(userdomain.getCreateDate()));
							json1.put("creatorName", userdomain.getCreatorName());
							resultArr.put(json1);
							num++;
						}
					}
				}
				
			}
		}
		json.put("total", num);
		json.put("data", resultArr);
        WebUtil.outputHtml(response, json.toString());
        return null;
    }
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value="/delete.do")
	 public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map  = WebUtil.requestToMap(request);
		UserInfo user = UserUtil.getCurrentUser(request);
		int num = 0;
		String userDomainIds = MapUtils.getString(map, "userDomainIds","");
		if(StringUtils.isNotBlank(userDomainIds)){
			String[] userDomainIdArr = StringUtils.split(userDomainIds, ";");
			for(int i=0;i<userDomainIdArr.length;i++){
				Integer deleteNum = userDomainService.delete(userDomainIdArr[i]);
				if(deleteNum > 0){
					num++;
				}
			}
		}
		WebUtil.outputHtml(response, String.valueOf(num));
        return null;
    }
}
