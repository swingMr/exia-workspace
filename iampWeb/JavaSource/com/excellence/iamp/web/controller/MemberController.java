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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.excellence.common.UserInfo;
import com.excellence.common.util.GUIDGenerator;
import com.excellence.iacommon.common.util.ExcelHandlerUtil;
import com.excellence.iacommon.common.util.T;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
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
import com.excellence.iamp.vo.TextCorpus;
import com.excellence.iamp.vo.UserDomain;
import com.excellence.iamp.vo.enums.EStatus;
import com.excellence.iamp.vo.excel.TextCorpusExcelVo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService iaMemberService;
	
	@Autowired
	private AppMemberService iaAppMemberService;
	
	@Autowired
	private AppService iaAppService;
	
	@Autowired
	private UserDomainService userDomainService;
	
	@Autowired
	private TagRecordService tagRecordService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list.do")
	public String getMembers(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String memberAccount = MapUtils.getString(map, "memberAccount");
		String memberName = MapUtils.getString(map, "memberName");
		String qqNum = MapUtils.getString(map, "qqNum");
		String emailAddress = MapUtils.getString(map, "emailAddress");
		String wechatNum = MapUtils.getString(map, "wechatNum");
		String phoneNum = MapUtils.getString(map, "phoneNum");
		String orderBy = MapUtils.getString(map, "orderBy","create_date");
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
		if(StringUtils.isNotEmpty(qqNum)) {
			paramMap.put("qqNum", qqNum);
		}
		if(StringUtils.isNotEmpty(emailAddress)) {
			paramMap.put("emailAddress", emailAddress);
		}
		if(StringUtils.isNotEmpty(wechatNum)) {
			paramMap.put("wechatNum", wechatNum);
		}
		if(StringUtils.isNotEmpty(phoneNum)) {
			paramMap.put("phoneNum", phoneNum);
		}
		if(StringUtils.isNotEmpty(queryCondition)) {
			paramMap.put(selectOpt, queryCondition);
			request.setAttribute("selectOpt", selectOpt);
			request.setAttribute("queryCondition", queryCondition);
		}
		paramMap.put("orderBy", orderBy);
		paramMap.put("orderDirection", orderDirection);
		PageInfo<Member> page = iaMemberService.page(paramMap, pageNo, pageSize);
		List<Member> memberList = page.getList();
		List<Map<String,String>> pageList = new ArrayList<Map<String,String>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(memberList != null && memberList.size() > 0){
			for(int i=0;i<memberList.size();i++){
				Map<String,String> map1 = new HashMap<String, String>();
				Map paramMap1 = new HashMap<String, Object>();
				paramMap1.put("memberId", memberList.get(i).getMemberId());
				StringBuilder str = new StringBuilder();
				List<AppMember> iaAppMemberList = iaAppMemberService.getByCondition(paramMap1);
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
				if(StringUtils.isNotBlank(memberList.get(i).getEmailAddress())){
					map1.put("emailAddress", memberList.get(i).getEmailAddress());
				}else{
					map1.put("emailAddress", "");
				}
				if(StringUtils.isNotBlank(memberList.get(i).getQqNum())){
					map1.put("qqNum", memberList.get(i).getQqNum());
				}else{
					map1.put("qqNum", "");
				}
				if(StringUtils.isNotBlank(memberList.get(i).getWechatNum())){
					map1.put("wechatNum", memberList.get(i).getWechatNum());
				}else{
					map1.put("wechatNum","");
				}
				if(StringUtils.isNotBlank(String.valueOf(memberList.get(i).getPhoneNum())) && !StringUtils.equals(String.valueOf(memberList.get(i).getPhoneNum()), "null")){
					map1.put("phoneNum", String.valueOf(memberList.get(i).getPhoneNum()));
				}else{
					map1.put("phoneNum", "");
				}
				map1.put("createDate", sdf.format(memberList.get(i).getCreateDate()));
				map1.put("appNames", str.toString());
				//map1.put("remark", memberList.get(i).getRemark());
				pageList.add(map1);
			}
		}
		
		request.setAttribute("page", page);
		request.setAttribute("pageList", pageList);
		return "/baseManage/memberManage/memberList";
	}
	
	
	 /**
	  * 根据导入的excel创建会员信息
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author wangjg	
	  * @date 2017-8-4
	  */
	 @SuppressWarnings({ "rawtypes"})
	 @RequestMapping(value="/createMembers.do", method=RequestMethod.POST)
	 public String createMembers(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map map  = WebUtil.requestToMap(request);
		String excelFileName = "";
		InputStream in = null;
		List<Object> membersList = new ArrayList<Object>();
		List<Member> memberList = new ArrayList<Member>();
		try{
			UserInfo user = UserUtil.getCurrentUser(request);
			if(file != null){
				in = file.getInputStream();
				excelFileName =file.getOriginalFilename();
			}
			Member vo = new Member();
			long begin = System.currentTimeMillis();   
			membersList = ExcelHandlerUtil.importDataFromExcel(vo, in, excelFileName);
			for(int i=0;i<membersList.size();i++){
				Member Member = (Member)membersList.get(i);
				if(StringUtils.isEmpty(Member.getMemberId())) {
					Member.setMemberId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
				}
				if(StringUtils.isEmpty(Member.getMemberPsw())) {
					Member.setMemberPsw("");
				}else{
					Member.setMemberPsw(MD5Util.md5Password(Member.getMemberPsw()));
				}
				if(StringUtils.isEmpty(Member.getCreatorId())) {
					Member.equals(user.getId());
				}
				if(Member.getCreateDate() == null) {
					Member.equals(new Date());
				}
				if(StringUtils.isEmpty(Member.getCreatorName())) {
					Member.setCreatorName(user.getUsername());
				}
				if(Member.getUpdateDate() == null) {
					Member.setUpdateDate(new Date());
				}
				memberList.add(Member);
			}
			iaMemberService.batchInsert(memberList);
			long end = System.currentTimeMillis();
			System.out.println("createMembers执行耗时:" + (end - begin) + "豪秒");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try{ in.close(); }catch(Exception ex){ ex.printStackTrace(); }
			}
		}
	   Integer addNum = membersList.size();
       WebUtil.outputHtml(response, addNum.toString());
       return null;
    }
	 
	 /**
	  * 导出会员信息excel
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author wangjg	
	  * @throws FileNotFoundException 
	 * @throws ParseException 
	 * @throws JSONException 
	  * @date 2017-8-4
	  */
	 @SuppressWarnings({ "rawtypes", "unchecked"})
	 @RequestMapping(value="/exportMembers.do", method=RequestMethod.POST)
	 public void exportMembers(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, JSONException, ParseException {
		Map map  = WebUtil.requestToMap(request);
		String selectedData = MapUtils.getString(map, "selectedData");
		JSONArray membersArr = new JSONArray(selectedData);
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		List<Object> list = new ArrayList<Object>();//需要导出的会员信息list
		for(int i=0;i<membersArr.length();i++){
			Member member = new Member();
			JSONObject obj = (JSONObject) membersArr.get(i);
			member.setMemberId(obj.getString("memberId"));
			member.setMemberAccount(obj.getString("memberAccount"));
			member.setMemberName(obj.getString("memberName"));
			member.setEmailAddress(obj.getString("emailAddress"));
			member.setQqNum(obj.getString("qqNum"));
			member.setWechatNum(obj.getString("wechatNum"));
			String obj1 = obj.getString("phoneNum");
			if(StringUtils.isNotBlank(obj.getString("phoneNum")) && !StringUtils.equals(obj.getString("phoneNum"), "null")){
				member.setPhoneNum(obj.getString("phoneNum"));
			}else{
				member.setPhoneNum("");
			}
			if(StringUtils.isNotBlank(obj.getString("createDate")) && !StringUtils.equals(obj.getString("createDate"), "null")){
				member.setCreateDate(format1.parse(obj.getString("createDate")));
			}else{
				member.setCreateDate(format1.parse(""));
			}
			member.setRemark(obj.getString("remark"));
			list.add(member);
		}
		String[] headers = new String[]{"唯一标识","会员账号","会员姓名","绑定QQ号","绑定邮箱","会员密码","绑定微信号","电话号码","关注应用"};//表格的表头
		String title = "会员信息采集模板.xls";//表格的标题
		HSSFWorkbook workbook = new HSSFWorkbook();//创建一个新表格
		workbook = ExcelHandlerUtil.exportDataToExcel(list, headers, title);
		try {
			response.reset();
			response.setContentType("multipart/form-data"); //自动识别
			response.setHeader("Content-Disposition", "attachment;filename="
			          + new String((title + ".xls").getBytes(), "iso-8859-1"));
            //文件流输出到response里
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
        	System.out.println("#Error ["+e.getMessage()+"] ");
        }
     }
	 
	 @RequestMapping(value="/create.do", method=RequestMethod.GET)
	 public String showCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId = request.getParameter("memberId");
		String appId = request.getParameter("appId");
	    Member member = iaMemberService.findById(memberId);
	    List<String> domainNameList = new ArrayList<String>();
	    List<String> tagList = new ArrayList<String>();
	    List<Map<String,Object>> tagListNew = new ArrayList<Map<String,Object>>();
	    if(StringUtils.isNotBlank(memberId)){
	    	Map param = new HashMap();
	    	param.put("roleId", memberId);
	    	List<UserDomain> userDomainList = userDomainService.getByCondition(param);
	    	if(userDomainList != null && userDomainList.size() > 0){
	    		for(UserDomain userDomain : userDomainList){
	    			domainNameList.add(userDomain.getDomainName());
	    		}
	    	}
	    	List<TagRecord> tagRecordList = tagRecordService.getRankRecords(memberId);
	    	List<String> tagTypeList = tagRecordService.getAllTagTypes();
	    	if(tagTypeList != null && tagTypeList.size() > 0){
	    		for(String tagType : tagTypeList){
	    			if(tagRecordList != null && tagRecordList.size() > 0){
	    	    		List<String> tagList1 = new ArrayList<String>();
	    	    		Map<String,Object> map = new HashMap<String, Object>();
	    	    		for(TagRecord tagRecord : tagRecordList){
	    	    			if(StringUtils.equals(tagType, tagRecord.getTagType())){
	    	    				tagList1.add(tagRecord.getTagName());
	    	    			}
	    	    		}
	    	    		map.put(tagType, tagList1);
	    	    		tagListNew.add(map);
	    	    	}
	    		}
	    	}
	    }
		request.setAttribute("member", member);
		request.setAttribute("appId", appId);
		request.setAttribute("domainNameList", domainNameList);
		request.setAttribute("tagList", tagList);
		request.setAttribute("tagListNew", tagListNew);
        return "/baseManage/memberManage/newMember";
    }
	 
	 /**
	  * 创建或者更新会员信息
	  * @param req
	  * @param resp
	  * @return
	  * @throws Exception<br>
	  * @author wangjg, 2017-8-4<br>
	  */
	 @SuppressWarnings({ "rawtypes", "unchecked"})
	 @RequestMapping(value="/create.do", method=RequestMethod.POST)
	 public String create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map  = WebUtil.requestToMap(request);
		UserInfo user = UserUtil.getCurrentUser(request);
		JSONObject json = new JSONObject();
		String msg = "保存成功";
		String memberId = MapUtils.getString(map, "memberId","");
		String memberAccount = MapUtils.getString(map, "memberAccount","");
		String memberName =  MapUtils.getString(map, "memberName","");
		String memberPsw =  MapUtils.getString(map, "memberPsw","");
		String emailAddress = MapUtils.getString(map, "emailAddress","");
		String qqNum = MapUtils.getString(map, "qqNum","");
		String phoneNum = MapUtils.getString(map, "phoneNum","");
		String wechatNum = MapUtils.getString(map, "wechatNum","");
		String appId = MapUtils.getString(map, "appId","");
		String appGroup = MapUtils.getString(map, "appGroup","");
		//memberId不为空时进行更新，为空时执行创建
		if(StringUtils.isBlank(memberId)){
			Member member = new Member();
			member.setMemberId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
			member.setMemberAccount(memberAccount);
			member.setMemberName(memberName);
			member.setMemberPsw(MD5Util.md5Password(memberPsw));
			member.setEmailAddress(emailAddress);
			if(StringUtils.isNotBlank(phoneNum)){
				member.setPhoneNum(phoneNum);
			}
			member.setQqNum(qqNum);
			member.setWechatNum(wechatNum);
			member.setCreateDate(T.getNow());
			member.setCreatorId(user.getId());
			member.setCreatorName(user.getUsername());
			member.setUpdateDate(T.getNow());
			try {
				iaMemberService.create(member);
				memberId = member.getMemberId();
			} catch (Exception e) {
				msg = e.getMessage();
			}
		}else{
			Member member = iaMemberService.findById(memberId);
			if(member != null) {
				if(StringUtils.isNotEmpty(memberPsw)) {
					member.setMemberPsw(MD5Util.md5Password(memberPsw));
				}
				member.setMemberAccount(memberAccount);
				member.setMemberName(memberName);
				member.setEmailAddress(emailAddress);
				member.setQqNum(qqNum);
				member.setPhoneNum(phoneNum);
				member.setWechatNum(wechatNum);
				member.setUpdateDate(T.getNow());
				try {
					iaMemberService.update(member);
				} catch (Exception e) {
					msg = e.getMessage();
				}
			}
		}
		
		if(StringUtils.isNotBlank(appId)){
			App app = iaAppService.findById(appId);
			AppMember appMember ;
			Map paramMap = new HashMap<String, Object>();
			paramMap.put("appId", appId);
			paramMap.put("memberId", memberId);
			List<AppMember> appMembers= iaAppMemberService.getByCondition(paramMap);
			if(appMembers.size()>0){
				msg="该会员已关注！";
			}else{
				appMember = new AppMember();
				appMember.setAppId(appId);
				appMember.setAppName(app.getAppName());
				appMember.setMemberId(memberId);
				appMember.setMemberName(memberName);
				appMember.setAppGroup(appGroup);
				appMember.setFollowTime(new Date());
				try {
					iaAppMemberService.create(appMember);
				} catch (Exception e) {
					msg = e.getMessage();
				}
			}
			
		}
		
		json.put("msg", msg);
        WebUtil.outputHtml(response, json.toString());
        return null;
    }
	 
	 @RequestMapping(value="/delete.do")
	 public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId = request.getParameter("memberId");
		JSONArray memberIdArr = new JSONArray(memberId);
		int num =0;
		for(int i=0;i<memberIdArr.length();i++){
			Integer deleteNum = iaMemberService.delete(memberIdArr.getString(i));
			if(deleteNum > 0){
				num++;
			}
		}
		Integer deleteNum = iaMemberService.delete(memberId);
        JSONObject json = new JSONObject();
        boolean result = true;
		if(num > 0){
			json.put("msg", "删除成功，共删除"+num+"条记录！");
		}else{
			json.put("msg", "删除失败！");
		}
		json.put("result", result);
		
		WebUtil.outputHtml(response, json.toString());
        return null;
    }
}
