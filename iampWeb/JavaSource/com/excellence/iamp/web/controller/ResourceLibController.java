package com.excellence.iamp.web.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.common.UserInfo;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.mongodb.service.ResourceLibService;
import com.excellence.iamp.mongodb.service.ResourceService;
import com.excellence.iamp.mongodb.vo.Attribute;
import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.enums.EStatus;

@Controller
@RequestMapping("/resourceLib")
public class ResourceLibController {
   @Autowired
   private ResourceLibService resourceLibService;
   @Autowired
   private ResourceService resourceService;
	/**查询所有信息资源库定义；
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list.do")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		String type = request.getParameter("type");
		int pageSize = 10;
		int currentPage = 1;
		if(StringUtils.isNotBlank(pageNo)){
			currentPage = Integer.parseInt(pageNo);
		}
		Map<String, Object> map  = new HashMap<String, Object>();
		if(type != null && !type.equals("all")) {
			map.put("type", Integer.parseInt(type));
			request.setAttribute("type", type);
		}
		Page<ResourceLib> list = resourceLibService.getList(map,currentPage, pageSize);
		for(int i=0; list.getRows().size()>i; i++){
			long count =0;
			if(StringUtils.isNotBlank(list.getRows().get(i).getCollectionName())){
				count = resourceService.getCount(list.getRows().get(i).getCollectionName());
			}
			list.getRows().get(i).setCount(count);
			//list.getRows().get(i).setCount(Long.parseLong(format.format((count))));
		}
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/list");
		mv.addObject("list", list.getRows());
		mv.addObject("pages", list.getTotalPage());
		mv.addObject("currentPage", list.getCurrentPage());
		return mv;
	}
	
	@RequestMapping("/save.do")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserInfo user= UserUtil.getCurrentUser(request);
			int result =0;
			String id = request.getParameter("id");
			String libNum = request.getParameter("libNum");
			String libName = request.getParameter("libName");
			String type = request.getParameter("type");
			String adapter = request.getParameter("adapter");
			String resType = request.getParameter("resType");
			String collectionName = request.getParameter("collectionName");
			String tempDisplayOrder = request.getParameter("displayOrder");
			int displayOrder = 0;
			if(StringUtils.isNotBlank(tempDisplayOrder)){
				displayOrder = Integer.parseInt(tempDisplayOrder);	
			}
			String remark  = request.getParameter("remark");
			String attr  = request.getParameter("attr");
			JSONArray arr = new JSONArray(attr);
			ResourceLib lib = new ResourceLib();
			if(StringUtils.isNotBlank(id)){
				//更新
				lib = resourceLibService.findOne(id);
				lib.setLibNum(libNum);
				lib.setLibName(libName);
				lib.setType(Integer.parseInt(type));
				lib.setAdapter(adapter);
				lib.setResType(Integer.parseInt(resType));
				lib.setCollectionName(collectionName);
				lib.setDisplayOrder(displayOrder);
				lib.setRemark(remark);
				lib.setUpdateDate(new Date());
				lib.setCreatorName(user.getUsername());
				lib.setCreatorId(user.getId());
				lib.setStatus(EStatus.normal.getIndex());
				if(arr.length()>0){
					lib = handleExtendAttr(arr, lib);	
				}
				resourceLibService.update(lib);
				result=1;
			}else{
				//新增
				lib.setLibNum(libNum);
				lib.setLibName(libName);
				lib.setType(Integer.parseInt(type));
				lib.setAdapter(adapter);
				lib.setResType(Integer.parseInt(resType));
				lib.setCollectionName(collectionName);
				lib.setDisplayOrder(displayOrder);
				lib.setRemark(remark);
				lib.setUpdateDate(new Date());
				lib.setCreatorName(user.getUsername());
				lib.setCreatorId(user.getId());
				lib.setCreateDate(new Date());
				lib.setStatus(EStatus.normal.getIndex());
				System.out.println("长度"+arr.length());
				if(arr.length()>0){
					lib = handleExtendAttr(arr, lib);	
				}
				resourceLibService.save(lib);
				result=1;
			}
			
			JSONObject json = new JSONObject();
			if(result == 1){
				json.put("result", "success");
			}else{
				json.put("result", "false");
			}
			WebUtil.outputHtml(response, json.toString());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**处理扩展属性。
	 * @author Liuzh
	 * @param arr
	 * @param lib
	 * @return
	 */
	private ResourceLib handleExtendAttr(JSONArray arr,ResourceLib lib){
		List<Attribute> list = new ArrayList();
		if(lib.getExtendAttrs() != null){
			list = lib.getExtendAttrs();
			//清空再导入
			for(int j=0; list.size()>j; j++){
				lib.delAttribute(list.get(j).getAttrName());
			}
		};
		for(int i=0; arr.length()>i; i++){
			JSONObject obj = arr.getJSONObject(i);
			Attribute attrObj = new Attribute();
			attrObj.setAttrName(obj.get("attrName").toString());
			attrObj.setAttrCName(obj.get("attrCName").toString());
			attrObj.setRequire(obj.getBoolean("require"));
			attrObj.setType(obj.get("type").toString());
			attrObj.setValue(obj.get("value").toString());
			lib.addAttribute(attrObj);
		}
		return lib;
	}
	
	
	/**
	 *  跳转到更新或新增页面；
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/showDetailView.do")
	public ModelAndView showUpdateView(HttpServletRequest request, HttpServletResponse response ){
		String id = request.getParameter("id");
		ResourceLib lib = new ResourceLib();
		if(StringUtils.isNotBlank(id)){
			lib = resourceLibService.findOne(id);
		}
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/detail");
		mv.addObject("obj", lib);
		return mv;
	}
	
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request, HttpServletResponse response ){
		String ids = request.getParameter("ids");
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(ids);
		ResourceLib lib = new ResourceLib();
		for(int i=0; arr.length()>i; i++){
			//物理删除
			//resourceLibService.del(arr.get(i).toString());
			//逻辑删除
			lib = resourceLibService.findOne(arr.get(i).toString());
			lib.setStatus(EStatus.delete.getIndex());
			resourceLibService.update(lib);
		}
		json.put("result", "success");
		WebUtil.outputHtml(response, json.toString());
	}
	
	@RequestMapping("/getAll.do")
	public ModelAndView getAllList(HttpServletRequest request, HttpServletResponse response ){
		ModelAndView mv = new ModelAndView("knowledgeLib/resourceLib/tree");
		List list = resourceLibService.getAll();
		mv.addObject("list", list);
		return mv;
	}
}
