package com.excellence.iamp.web.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excellence.common.UserInfo;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.T;
import com.excellence.iacommon.common.util.UserUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.service.ServiceDefinitionService;
import com.excellence.iamp.service.ServiceTypeService;
import com.excellence.iamp.vo.ServiceDefinition;
import com.excellence.iamp.vo.ServiceType;
import com.excellence.iamp.vo.enums.EStatus;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/service")
public class ServiceController {
	@Autowired
	private ServiceDefinitionService serviceDefinitionService;
	@Autowired
	private ServiceTypeService serviceTypeService;
	
	private static String oaPath =System.getProperty("oapath");
	private static String pythonPath ="exiaserver"+File.separator+"data_dig"+File.separator+"expy"+File.separator+"custom";
	private static String exIAServerBasePath="http://" + Constant.ExIAServer_HOST+ ":" + Constant.ExIAServer_PORT+"/ExIAServer/";
	
	/**查询所有服务定义；
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/list.do")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Map map  = WebUtil.requestToMap(request);
		String num = request.getParameter("num");
		String searchCname = MapUtils.getString(map, "searchCname");
		String searchName = MapUtils.getString(map, "searchName");
		String serviceType = MapUtils.getString(map, "serviceType");
		if(serviceType !=null && serviceType.equals("all")){
			serviceType = "";
		}
		String pageSize = request.getParameter("pageSize");
		int targetNum = 1;
		int targetPageSize = 10;
		if(StringUtils.isNotEmpty(num)){
			targetNum = Integer.parseInt(num);
		}
		if(StringUtils.isNotEmpty(pageSize)){
			targetPageSize = Integer.parseInt(pageSize);
		}
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("serviceCname", searchCname);
		paramMap.put("serviceName", searchName);
		paramMap.put("typeId", serviceType);
		PageInfo<ServiceDefinition> pageInfo = serviceDefinitionService.getList(targetNum,targetPageSize,paramMap);
		List<ServiceType> typeList  =serviceTypeService.getList();
		ModelAndView mv = new ModelAndView("serviceConfig/service/list");
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("pages", pageInfo.getPages());
		mv.addObject("currentPage", pageInfo.getPageNum());
		mv.addObject("searchCname", searchCname);
		mv.addObject("searchName", searchName);
		mv.addObject("typeId", serviceType);
		mv.addObject("typeList", typeList);
		return mv;
	}
	
	
	/**只查询python服务定义；
	 * @author wangjg
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/pythonServiceList.do")
	public ModelAndView getPythonServiceList(HttpServletRequest request, HttpServletResponse response) {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("serviceType", "2");
		PageInfo<ServiceDefinition> pageInfo = serviceDefinitionService.getList(pageNo,pageSize,paramMap);
		ModelAndView mv = new ModelAndView("serviceConfig/service/pythonServiceList");
		request.setAttribute("pageInfo", pageInfo);
		return mv;
	}
	
	/**新增或更新服务定义
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/save.do")
	public ModelAndView insertServiceDefinitions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfo user= UserUtil.getCurrentUser(request);
		int result =0;
		String id = request.getParameter("serviceId");
		String serviceName = request.getParameter("serviceName");
		String serviceCname = request.getParameter("serviceCname");
		String serviceType = request.getParameter("serviceType");
		String typeId = request.getParameter("classify");
		String accessPath = request.getParameter("accessPath");
		String remark = request.getParameter("remark");
		String pythonScipt = request.getParameter("pythonScipt");
		String serviceInput = request.getParameter("arr");
		ServiceDefinition serviceDefinition = new ServiceDefinition();
		JSONObject json = new JSONObject();
		if(StringUtils.isNotEmpty(id)){
			//保存
			serviceDefinition = serviceDefinitionService.getServiceDefinitionById(id);
			serviceDefinition.setServiceName(serviceName);
			serviceDefinition.setServiceCname(serviceCname);
			serviceDefinition.setServiceType(serviceType);
			serviceDefinition.setServiceInput(serviceInput);
			saveFile(pythonScipt,serviceDefinition.getPythonPath());
			if(StringUtils.isNotEmpty(typeId)){
				ServiceType serType = serviceTypeService.findById(typeId);
				serviceDefinition.setTypeName(serType.getTypeName());
				serviceDefinition.setTypeId(typeId);	
			}
			serviceDefinition.setStatus(EStatus.normal.getIndex());
			serviceDefinition.setAccessPath(accessPath);
			serviceDefinition.setRemark(remark);
			serviceDefinition.setRecentAccessDate(new Date());
			serviceDefinition.setExpireDate(new Date());
			serviceDefinition.setUpdateDate(new Date());
			serviceDefinition.setCreatorId(user.getId());
			serviceDefinition.setCreatorName(user.getUsername());
			result =  serviceDefinitionService.update(serviceDefinition);
		}else{
			//新增
			serviceDefinition.setServiceName(serviceName);
			serviceDefinition.setServiceCname(serviceCname);
			serviceDefinition.setServiceType(serviceType);;
			serviceDefinition.setServiceInput(serviceInput);
			serviceDefinition.setCreatorId(user.getId());
			serviceDefinition.setCreatorName(user.getUsername());
			if(StringUtils.isNotEmpty(typeId)){
				ServiceType serType = serviceTypeService.findById(typeId);
				serviceDefinition.setTypeName(serType.getTypeName());
				serviceDefinition.setTypeId(typeId);	
			}
			//1:http服务	2.python服务
			String targetPath ="";
			if(StringUtils.isNotEmpty(serviceType) && serviceType.equals("2")){
				if(StringUtils.isNotEmpty(pythonScipt)){
					targetPath = saveFile(pythonScipt,targetPath);	
				}
			}
			serviceDefinition.setPythonPath(targetPath);
			serviceDefinition.setStatus(EStatus.normal.getIndex());
			serviceDefinition.setAccessPath(accessPath);
			serviceDefinition.setRemark(remark);
			serviceDefinition.setRecentAccessDate(new Date());
			serviceDefinition.setExpireDate(new Date());
			result = serviceDefinitionService.insert(serviceDefinition);
		}
		if(result == 1){
			json.put("result", "success");
		}else{
			json.put("result", "false");
		}
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**删除服务定义
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/del")
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(ids);
		for(int i=0; arr.length()>i; i++){
			String serviceId = (String) arr.get(i);
			serviceDefinitionService.delById(serviceId);
		}
		json.put("result", "success");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**跳转到服务修改页面
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/update.do")
	public ModelAndView showUpdate(HttpServletRequest request, HttpServletResponse response) {
		String serviceId = request.getParameter("serviceId");
		ModelAndView mv = new ModelAndView("serviceConfig/service/detail");
		ServiceDefinition serviceDefinition = new ServiceDefinition();
		String content = "";
		if(StringUtils.isNotEmpty(serviceId)){
			serviceDefinition = serviceDefinitionService.getServiceDefinitionById(serviceId);
			try {
				if(serviceDefinition != null &&StringUtils.isNotBlank(serviceDefinition.getPythonPath())){
		                File f = new File(serviceDefinition.getPythonPath());   
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
		List<ServiceType> typeList= serviceTypeService.getList();
		mv.addObject("obj", serviceDefinition);
		mv.addObject("typeList", typeList);
		mv.addObject("content",content);
		return mv;
	}
    
	/**测试服务 获取数据
	 * @author LiuZeHang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getDataByTest")
	public ModelAndView getDataByTest(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		String result="";
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		String servicePath = request.getParameter("servicePath");
		String serviceName = request.getParameter("serviceName");
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(exIAServerBasePath+servicePath);
        HttpClientContext context = HttpClientContext.create();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if(StringUtils.isNotEmpty(key)) {
            JSONArray arr = new JSONArray(key);
            JSONArray valArr = new JSONArray(val);
            String obj = "";
            String objVal="";
            if(arr != null && arr.length() > 0 ) {
                for(int i=0;i<arr.length();i++) {
                   params.add(new BasicNameValuePair(arr.getString(i), valArr.getString(i)));
                }
            }        
        }
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(params,"GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        httpPost.setEntity(entity);
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                System.out.println("请求和响应成功");
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);
                json.put("data", result);
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WebUtil.outputHtml(response, json.toString());
        return null;
    }
	
	//跳转到测试服务界面；
	@RequestMapping("/test.do")
	public ModelAndView showTestView(HttpServletRequest request, HttpServletResponse response) {
		String serviceId = request.getParameter("serviceId");
		ModelAndView mv = new ModelAndView("serviceConfig/service/testService");
		ServiceDefinition serviceDefinition = new ServiceDefinition();
		if(StringUtils.isNotEmpty(serviceId)){
			serviceDefinition = serviceDefinitionService.getServiceDefinitionById(serviceId);
		}
		mv.addObject("obj", serviceDefinition);
		return mv;
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
