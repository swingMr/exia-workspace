package com.excellence.iaserver.web.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.excellence.iamp.service.TaskService;
import com.excellence.iamp.vo.Task;
import com.excellence.iamp.util.TaskLogUtil;
import com.excellence.iamp.vo.enums.TaskStatus;
import com.excellence.iaserver.common.util.Jwt;
import com.excellence.iaserver.common.util.WebUtil;
import com.excellence.iaserver.task.QuartzManager;
import com.github.pagehelper.PageInfo;

/**
 * ��ʱ�������
 * @author wangjg
 *
 */
@Controller
@RequestMapping("/services/base/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskLogUtil taskLogUtil;
	
	/**
	 * ��ȡ��ǰ�Ѿ�ע��Ķ�ʱ�����嵥
	 * @param extoken		���ʷ����Ӧ������
	 * @param taskType		�������͹���������1--���ض�ʱ����2--Python��ʱ����3--Զ�̶�ʱ����
	 * @param taskName		��ʱ�������ƹ���������
	 * @param page			��ǰҳ�룬Ĭ��Ϊ1��
	 * @param pageSize		��ҳ��С��Ĭ��Ϊ50��
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/list")
	public ModelAndView queryTaskList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(map,"extoken","");
		String taskType = MapUtils.getString(map,"taskType","");
		String taskName = MapUtils.getString(map,"taskName","");
		int page = MapUtils.getIntValue(map, "page",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",50);
		JSONObject obj = new JSONObject();
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess){
			Map paramMap = new HashMap();
			if(StringUtils.isNotBlank(taskName)){
				paramMap.put("taskName", taskName);
			}
			if(StringUtils.isNotBlank(taskType)){
				paramMap.put("taskType", taskType);
			}
			PageInfo<Task> pageList = taskService.page(paramMap, page, pageSize);
			if(pageList.getList() != null && pageList.getList().size() > 0){
				obj.put("status", 1);
				obj.put("msg", "�з������ݣ�");
				JSONObject obj1 = new JSONObject();
				obj1.put("num", pageList.getTotal());
				obj1.put("page", pageList.getPageNum());
				obj1.put("pageSize", pageList.getPageSize());
				JSONArray arr = new JSONArray();
				for(Task task : pageList.getList()){
					JSONObject obj2 = new JSONObject();
					obj2.put("taskId", task.getTaskId());
					obj2.put("taskName", task.getTaskName());
					obj2.put("taskType", task.getTaskType());
					obj2.put("status", task.getStatus());
					arr.put(obj2);
				}
				obj1.put("informations", arr);
				obj.put("data", obj1);
			}else{
				obj.put("status", -1);
				obj.put("msg", "�޷������ݣ�");
			}
			
		}
        obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
	
	/**
	 * ������������
	 * @param taskId		����id
	 * @param status		�����������״̬
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/ctrl")
	public ModelAndView controllerTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		String extoken = MapUtils.getString(map,"extoken","");
		String taskId = MapUtils.getString(map,"taskId","");
		int status = MapUtils.getIntValue(map, "status",0);
		Task task = taskService.findById(taskId);
		JSONObject obj = new JSONObject();
		// -1-ɾ��    0-ֹͣ  1-����   2-��ͣ   3-����  
		if(task != null){
			try {
				if(status == 1 && status != task.getStatus()){
					QuartzManager.startTask(task);
					task.setStatus(TaskStatus.run.getIndex());
					taskService.update(task);
				}else if(status == 0 && status != task.getStatus()){
					QuartzManager.shutdownTask(task);
					task.setStatus(TaskStatus.stop.getIndex());
					taskService.update(task);
					taskLogUtil.recordTaskLog(task, 0L);
				}else if(status == 2 && status != task.getStatus()){
					QuartzManager.stopTask(task);
					task.setStatus(TaskStatus.pause.getIndex());
					taskService.update(task);
					taskLogUtil.recordTaskLog(task, 0L);
				}else if(status == -1 && status != task.getStatus()){
					QuartzManager.removeJob(task);
					taskService.delete(taskId);
					taskLogUtil.recordTaskLog(task, 0L);
				}
				else if(status == 3 && status != task.getStatus()){
					QuartzManager.addAndStartJob(task);
					task.setStatus(TaskStatus.run.getIndex());
					taskService.update(task);
				}
				obj.put("status", status);
				obj.put("msg", "�����ɹ���");
			} catch (Exception e) {
				obj.put("status", status);
				obj.put("msg", "�쳣���ݣ�");
				e.printStackTrace();
			}
		}else{
			obj.put("status", "999");
			obj.put("msg", "���񲻴��ڣ�");
		}
        obj.put("timeCost", System.currentTimeMillis()-startTime+"����");
        WebUtil.outputHtml(response, obj.toString());
        return null; 
	}
      
    
}
