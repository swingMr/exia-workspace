package com.excellence.iamp.web.controller;

import java.util.HashMap;
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

import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.robot.vo.AnswerPart;
import com.excellence.iamp.robot.vo.RobotDef;
import com.excellence.iamp.service.AnswerPartService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/answerPart")
public class AnswerPartController {
	@Autowired
	private AnswerPartService answerPartService;
	
	/**
	 * ·ÖÒ³²éÑ¯
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list.do")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		Map paramMap = new HashMap();
		String pNo  = request.getParameter("pageNo");
		int pageNo = 1;
		String pSize = request.getParameter("pageSize");
		int pageSize = 10;
		if(StringUtils.isNotBlank(pNo)){
			pageNo = Integer.parseInt(pNo);
		}
		if(StringUtils.isNotBlank(pSize)){
			pageSize = Integer.parseInt(pSize);
		}
		PageInfo<AnswerPart>  pageList = answerPartService.pageAnswerPart(paramMap, pageNo, pageSize);
		ModelAndView mv = new ModelAndView("/robot/answerPartList");
		mv.addObject("list", pageList.getList());
		mv.addObject("pages", pageList.getPages());
		mv.addObject("currentPage", pageNo);
		return mv;
	}
	
	@RequestMapping("/save.do")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/robot/answerPartList");
		return mv;
	}
	
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request, HttpServletResponse response) {
		String ids =request.getParameter("ids");
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(ids);
		if(arr != null && arr.length()>0){
			for(int i=0; arr.length()>i; i++){
				answerPartService.delete(arr.get(i).toString());
			}
			json.put("result", "success");
		}else{
			json.put("result", "fail");
		}
		WebUtil.outputHtml(response, json.toString());
	}
	
	@RequestMapping("/showDetail.do")
	public ModelAndView showDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/robot/answerPartList");
		return mv;
	}
	
	@RequestMapping("/deletePartRule.do")
	public ModelAndView deletePartRule(RobotDef robotDef , HttpServletRequest request, HttpServletResponse response) {
		String partId =request.getParameter("id");
		String deleteNum =request.getParameter("deleteNum");
		AnswerPart answerPart = answerPartService.findById(partId);
		String msg = "É¾³ýÊ§°Ü£¡";
		if(answerPart != null){
			try {
				String partRule = answerPart.getPartRule();
				if(StringUtils.isNotBlank(partRule)){
					JSONArray arr = new JSONArray(partRule);
					arr.remove(Integer.parseInt(deleteNum));
					answerPart.setPartRule(arr.toString());
					answerPartService.update(answerPart);
					msg = "É¾³ý³É¹¦£¡";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		WebUtil.outputHtml(response, msg);
		return null;
	}
	
	@RequestMapping("/addPartRule.do")
	public ModelAndView addPartRule(RobotDef robotDef , HttpServletRequest request, HttpServletResponse response) {
		String partId =request.getParameter("id");
		String addPartRule =request.getParameter("addPartRule");
		AnswerPart answerPart = answerPartService.findById(partId);
		String msg = "Ìí¼ÓÊ§°Ü£¡";
		if(answerPart != null){
			try {
				String partRule = answerPart.getPartRule();
				if(StringUtils.isNotBlank(partRule)){
					JSONArray arr = new JSONArray(partRule);
					if(StringUtils.isNotBlank(addPartRule)){
						JSONArray AddArr = new JSONArray(addPartRule);
						for(int i=0;i<AddArr.length();i++){
							arr.put(AddArr.get(i));
						}
					}
					answerPart.setPartRule(arr.toString());
					answerPartService.update(answerPart);
					msg = "Ìí¼Ó³É¹¦£¡";
				}else{
					if(StringUtils.isNotBlank(addPartRule)){
						JSONArray AddArr = new JSONArray(addPartRule);
						answerPart.setPartRule(AddArr.toString());
						answerPartService.update(answerPart);
						msg = "Ìí¼Ó³É¹¦£¡";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		WebUtil.outputHtml(response, msg);
		return null;
	}
}
