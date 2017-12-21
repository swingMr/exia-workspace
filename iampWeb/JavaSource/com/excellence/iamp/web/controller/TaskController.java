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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.excellence.common.UserInfo;
import com.excellence.common.util.GUIDGenerator;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.HttpClientUtil;
import com.excellence.iacommon.common.util.WebUtil;
import com.excellence.iamp.service.TaskLogService;
import com.excellence.iamp.service.TaskService;
import com.excellence.iamp.util.TaskLogUtil;
import com.excellence.iamp.vo.Task;
import com.excellence.iamp.vo.TaskLog;
import com.excellence.iamp.vo.enums.TaskStatus;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskLogService taskLogService;
	
	@Autowired
	private TaskLogUtil taskLogUtil;
	
	private static String url = "http://"+Constant.ExIAServer_HOST+":"+Constant.ExIAServer_PORT+ "/ExIAServer/services/base/task/ctrl";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list.do")
	public String getTaskList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		String taskName = MapUtils.getString(map, "taskName");
		String taskType = MapUtils.getString(map, "taskType");
		Map paramMap = new HashMap();
		if(StringUtils.isNotBlank(taskName)){
			paramMap.put("taskName", taskName);
		}
		if(StringUtils.isNotBlank(taskType) && !StringUtils.equals(taskType, "all")){
			paramMap.put("taskType", taskType);
		}
		PageInfo<Task> page = taskService.page(paramMap, pageNo, pageSize);
		
		request.setAttribute("page", page);
		request.setAttribute("taskType", taskType);
		request.setAttribute("taskName", taskName);
		return "/baseManage/task/taskList";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/newTask.do")
	public String newTask(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		String taskId = MapUtils.getString(map, "taskId","");
		Task task = new Task();
		if(StringUtils.isNotBlank(taskId)){
			task = taskService.findById(taskId);
		}
		request.setAttribute("task", task);
		return "/baseManage/task/newTask";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/create.do")
	public void create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		String taskId = MapUtils.getString(map,"taskId");
		String taskName = MapUtils.getString(map,"taskName","");
		String taskGroup = MapUtils.getString(map,"taskGroup");
		int taskType = MapUtils.getIntValue(map,"taskType");//1-本地定时任务，2-python定时任务，3-远程定时任务
		String pythonServiceId = MapUtils.getString(map,"pythonServiceId","");//python定时任务id
		String pythonServicePath = MapUtils.getString(map,"pythonServicePath","");//python定时任务path
		//String triggerName = MapUtils.getString(map,"triggerName","");
		String accessPath = MapUtils.getString(map,"accessPath","");
		//String triggerGroup = MapUtils.getString(map,"triggerGroup");
		String incompleteTimeRegex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
		String startTime = MapUtils.getString(map, "startTime");
		String endTime = MapUtils.getString(map, "endTime");
		String timeSet = MapUtils.getString(map, "timeSet");
		String paramArr = MapUtils.getString(map, "paramArr","");
		String taskTime ="";
		String remark = "";
		String msg = "保存成功！";
		if(StringUtils.isBlank(taskGroup)){
			taskGroup = "task";
		}
		
		if(StringUtils.isNotBlank(pythonServicePath)){
			accessPath = pythonServicePath;
		}
		Task task  = new Task();
		//1表示配置的时间规则，2表示手动输入的时间规则，不存在时间规则默认保存失败
		if(StringUtils.equals(timeSet, "1")){
			taskTime = MapUtils.getString(map, "timeSetHidden");
			remark = MapUtils.getString(map, "remarkOne");
		}else if(StringUtils.equals(timeSet, "2")){
			taskTime = MapUtils.getString(map, "timeSetHidden");
			remark = MapUtils.getString(map, "remarkTwo");
		}else{
			msg = "保存失败！";
		}
		if(StringUtils.isNotEmpty(taskId)){
			task.setTaskId(taskId);
		}else{
			task.setTaskId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		task.setTaskName(taskName);
		task.setTaskGroup(taskGroup);
		task.setAccessPath(accessPath);
		task.setTriggerName(task.getTaskId());//触发器名字为任务id
		task.setTriggerGroup(taskGroup);//触发器组名为任务组名
		task.setStatus(TaskStatus.stop.getIndex());
		task.setRemark(remark);
		task.setTaskTime(taskTime);
		task.setUpdateDate(new Date());
		task.setTaskType(taskType);
		task.setTaskParams(paramArr);
		if(StringUtils.isNotEmpty(startTime)){
			task.setStartTime(startTime);
		}
		if(StringUtils.isNotEmpty(endTime)){
			task.setEndTime(endTime);
		}
		try{
			if(taskType == 1){
				Class entityClass = Class.forName(accessPath);
			}
			if(StringUtils.isNotEmpty(taskId)){
				Task task1 = taskService.findById(taskId);
				if(StringUtils.equals(task1.getTaskTime(), taskTime)){
					//如果时间规则改变了,就移除该定时任务，重新添加
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("taskId", task1.getTaskId());
					params.put("status", -1);
					HttpClientUtil.post(url, "GBK", params);
				}
				taskService.update(task);
			}else{
				task.setCreatorId("admin");
				task.setCreatorName("管理员");
				task.setCreateDate(new Date());
				taskService.create(task);
			}
		}catch (Exception e) {
			msg = "未找到与该定时任务关联的类!";
			if(StringUtils.isNotBlank(task.getTaskId())){
				if(StringUtils.isBlank(taskId)){
					taskService.delete(task.getTaskId());
				}
			}
			e.printStackTrace();
		}
		
		
		WebUtil.outputHtml(response, msg);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		String taskIds = MapUtils.getString(map, "taskIds","");
		int num = 0;
		if(StringUtils.isNotBlank(taskIds)){
			String[] taskIdArr = StringUtils.split(taskIds, ";");
			for(int i=0;i<taskIdArr.length;i++){
				Task task = taskService.findById(taskIdArr[i]);
				try {
					//从任务调度池中移除该任务
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("taskId", task.getTaskId());
					params.put("status", -1);
					HttpClientUtil.post(url, "GBK", params);
					num++;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//taskService.delete(taskIdArr[i]);
				
			}
		}
		WebUtil.outputHtml(response, String.valueOf(num));
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/runTask.do")
	public void runTask(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		String taskIds = MapUtils.getString(map, "taskIds","");
		int num = 0;
		if(StringUtils.isNotBlank(taskIds)){
			String[] taskIdArr = StringUtils.split(taskIds, ";");
			for(int i=0;i<taskIdArr.length;i++){
				Task task = taskService.findById(taskIdArr[i]);
				try {
					//添加定时任务
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("taskId", task.getTaskId());
					params.put("status", 3);
					HttpClientUtil.post(url, "GBK", params);
					num++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String msg = "启动成功个数为"+num+"个！";
		WebUtil.outputHtml(response, msg);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/startTask.do")
	public void startTask(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		String taskIds = MapUtils.getString(map, "taskIds","");
		int num = 0;
		if(StringUtils.isNotBlank(taskIds)){
			String[] taskIdArr = StringUtils.split(taskIds, ";");
			for(int i=0;i<taskIdArr.length;i++){
				Task task = taskService.findById(taskIdArr[i]);
				try {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("taskId", task.getTaskId());
					params.put("status", 1);
					HttpClientUtil.post(url, "GBK", params);
					num++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String msg = "继续运行成功个数为"+num+"个！";
		WebUtil.outputHtml(response, msg);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/stopTask.do")
	public void stopTask(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		String taskIds = MapUtils.getString(map, "taskIds","");
		int num = 0;
		if(StringUtils.isNotBlank(taskIds)){
			String[] taskIdArr = StringUtils.split(taskIds, ";");
			for(int i=0;i<taskIdArr.length;i++){
				Task task = taskService.findById(taskIdArr[i]);
				try {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("taskId", task.getTaskId());
					params.put("status", 2);
					HttpClientUtil.post(url, "GBK", params);
					num++;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		String msg = "暂停成功个数为"+num+"个！";
		WebUtil.outputHtml(response, msg);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/shutdownTask.do")
	public void shutdownTask(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		String taskIds = MapUtils.getString(map, "taskIds","");
		int num = 0;
		if(StringUtils.isNotBlank(taskIds)){
			String[] taskIdArr = StringUtils.split(taskIds, ";");
			for(int i=0;i<taskIdArr.length;i++){
				Task task = taskService.findById(taskIdArr[i]);
				try {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("taskId", task.getTaskId());
					params.put("status", 0);
					HttpClientUtil.post(url, "GBK", params);
					num++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String msg = "停止成功个数为"+num+"个！";
		WebUtil.outputHtml(response, msg);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/viewTaskLog.do")
	public String viewTaskLog(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map map  = WebUtil.requestToMap(request);
		String taskId = MapUtils.getString(map, "taskId","");
		String taskName = MapUtils.getString(map, "taskName","");
		int logType = MapUtils.getIntValue(map, "logType",0);
		int pageNo = MapUtils.getIntValue(map, "pageNo",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",10);
		PageInfo<TaskLog> page = new PageInfo<TaskLog>();
		Map paramMap = new HashMap();
		if(StringUtils.isNotBlank(taskName)){
			paramMap.put("taskName", taskName);
		}
		if(logType != 0){
			paramMap.put("logType", logType);
		}
		if(StringUtils.isNotBlank(taskId)){
			paramMap.put("taskId", taskId);
			page = taskLogService.page(paramMap, pageNo, pageSize);
			
		}
		request.setAttribute("page", page);
		request.setAttribute("taskName", taskName);
		request.setAttribute("logType", logType);
		request.setAttribute("taskId", taskId);
		return "/baseManage/task/taskLogList";
	}
	
}
