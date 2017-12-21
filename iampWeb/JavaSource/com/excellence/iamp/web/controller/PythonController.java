package com.excellence.iamp.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

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
import com.excellence.iacommon.common.util.JavaHandlePython;
import com.excellence.iacommon.common.util.T;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.service.PythonProgramService;
import com.excellence.iamp.service.PythonTypeService;
import com.excellence.iamp.vo.PythonProgram;
import com.excellence.iamp.vo.PythonType;
import com.excellence.iamp.vo.ServiceDefinition;
import com.excellence.platform.um.exception.CommonAppException;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/python")
public class PythonController {
	@Autowired
	private PythonProgramService pythonProgramService;
	@Autowired
	private PythonTypeService pythonTypeService;
	
	private static String oaPath =System.getProperty("oapath");
	private static String pythonPath ="exiaserver"+File.separator+"data_dig"+File.separator+"expy"+File.separator+"custom";
	
	/** 显示python列表
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list.do")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Map map  = WebUtil.requestToMap(request);
		String num = request.getParameter("num");
		String typeName = request.getParameter("typeName");
		String pageSize = request.getParameter("pageSize");
		int targetNum = 1;
		int targetPageSize = 10;
		if(StringUtils.isNotBlank(num)){
			targetNum = Integer.parseInt(num);
		}
		if(StringUtils.isNotBlank(pageSize)){
			targetPageSize = Integer.parseInt(pageSize);
		}
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("typeName", typeName);
		PageInfo<PythonProgram> pageInfo = pythonProgramService.getList(targetNum, targetPageSize,paramMap); 
		ModelAndView mv = new ModelAndView("datadig/python/list");
		List<PythonType> typeList = pythonTypeService.getList();
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("pages", pageInfo.getPages());
		mv.addObject("currentPage", pageInfo.getPageNum());
		mv.addObject("typeList", typeList);
		mv.addObject("typeName", typeName);
		return mv;
	}
	
	/**保存或更新
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/save.do")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		String programId = request.getParameter("programId");
		String programName = request.getParameter("programName");
		String programCName = request.getParameter("programCName");
		String typeName = request.getParameter("typeName");
		String keyword = request.getParameter("keyword");
		String pythonScipt = request.getParameter("pythonScipt");
		String pythonInput = request.getParameter("arr");
		String remark = request.getParameter("remark");
		JSONObject json = new JSONObject();
		PythonProgram pythonProgram = new PythonProgram();
		int result =0;
		try {
			UserInfo user= UserUtil.getCurrentUser(request);
			if(StringUtils.isNotBlank(programId)){
				//更新
				pythonProgram = pythonProgramService.findById(programId);
				pythonProgram.setProgramName(programName);
				pythonProgram.setProgramCName(programCName);
				pythonProgram.setKeyWord(keyword);
				saveFile(pythonScipt, pythonProgram.getPythonPath());
				pythonProgram.setProgramInput(pythonInput);
				pythonProgram.setRemark(remark);
				pythonProgram.setCreatorId(user.getId());
				pythonProgram.setCreatorName(user.getUsername());
				pythonProgram.setUpdateDate(new Date());
				pythonProgram.setCreateDate(new Date());
				pythonProgram.setTypeName(typeName);
				result = pythonProgramService.update(pythonProgram);
			}else{
				//新增
				pythonProgram.setProgramName(programName);
				pythonProgram.setProgramCName(programCName);
				pythonProgram.setKeyWord(keyword);
				String targetPath ="";
				if(StringUtils.isNotBlank(pythonScipt)){
					targetPath = saveFile(pythonScipt,targetPath);
				}
				pythonProgram.setPythonPath(targetPath);
				pythonProgram.setProgramInput(pythonInput);
				pythonProgram.setRemark(remark);
				pythonProgram.setCreatorId(user.getId());
				pythonProgram.setCreatorName(user.getUsername());
				pythonProgram.setUpdateDate(new Date());
				pythonProgram.setCreateDate(new Date());
				pythonProgram.setTypeName(typeName);
				result = pythonProgramService.insert(pythonProgram);
			}
			if(result == 1){
				json.put("result", "success");
			}else{
				json.put("result", "false");
			}
		WebUtil.outputHtml(response, json.toString());
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return null;
	}
	
	/**删除
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/del.do")
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		JSONObject json = new JSONObject();
		if(StringUtils.isNotBlank(ids)){
			JSONArray arr = new JSONArray(ids);
			for(int i=0; arr.length()>i; i++){
				String programId = (String) arr.get(i);
				pythonProgramService.delById(programId);
			}
			json.put("result", "success");
		}
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**跳转到新建页面
	 * @author LiuZehang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/update.do")
	public ModelAndView showUpdate(HttpServletRequest request, HttpServletResponse response) {
		String programId = request.getParameter("programId");
		PythonProgram obj  = new PythonProgram ();
		String content = "";
		if(StringUtils.isNotBlank(programId)){
			try {
				obj= pythonProgramService.findById(programId);
				 if(obj != null &&StringUtils.isNotBlank(obj.getPythonPath())){
		                File f = new File(obj.getPythonPath());   
		                InputStream in;
						in = new FileInputStream(f);
		                String a = IOUtils.toString(in, "UTF-8");
		                content = a;
				 }
			}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}  
		}
		ModelAndView mv = new ModelAndView("datadig/python/detail");
		List<PythonType> list = pythonTypeService.getList();
		mv.addObject("list", list);
		mv.addObject("obj", obj);
		mv.addObject("content", content);
		return mv;
	}
	
	
	//跳转到python运行界面
	@RequestMapping("/execute.do")
	public ModelAndView showTestView(HttpServletRequest request, HttpServletResponse response) {
		String programId = request.getParameter("programId");
		ModelAndView mv = new ModelAndView("datadig/python/execute");
		PythonProgram obj  = new PythonProgram ();
		if(StringUtils.isNotEmpty(programId)){
			obj =  pythonProgramService.findById(programId);
		}
		mv.addObject("obj", obj);
		return mv;
	}
	
	//运行python程序并返回结果
	@RequestMapping("/executePython.do")
	public ModelAndView executePython(HttpServletRequest request, HttpServletResponse response) {
		String val = request.getParameter("val");
		String pythonPath = request.getParameter("pythonPath");
		JSONArray arr = new JSONArray(val);
		JSONObject obj = new JSONObject();
		List<String> strList = new ArrayList<String>();
		for(int i=0; arr.length()>i; i++){
			strList.add((String) arr.get(i));
		}
		String content = JavaHandlePython.HandlePython(pythonPath, strList);
		obj.put("content", content);
		WebUtil.outputHtml(response, obj.toString());
		return null;
	}
	
	/**
	 * 上传或保存文件
	 * @author LiuZeHang
	 * @param pythonScipt
	 * @param targetPath
	 * @return
	 */
	private String saveFile(String pythonScipt,String targetPath){
		if(StringUtils.isNotBlank(pythonScipt)){
			try {
				if(StringUtils.isBlank(targetPath)){
					String fileName = T.format(T.getNow(), "yyyyMMddHHmmss");
		            targetPath = oaPath+File.separator+pythonPath+File.separator+fileName+".py";	
				}
				File file = new File(targetPath);
				if(!file.exists()) {
					file.createNewFile();
				}
	            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(targetPath),"UTF-8");
	           out.write(pythonScipt);
	           out.flush();
	           out.close();	
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return targetPath;
	}
}
