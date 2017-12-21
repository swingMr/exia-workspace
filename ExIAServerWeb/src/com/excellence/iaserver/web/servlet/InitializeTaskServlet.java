package com.excellence.iaserver.web.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excellence.iamp.service.TaskService;
import com.excellence.iamp.vo.Task;
import com.excellence.iamp.util.TaskLogUtil;

/**
 * 
 * @author wangjg
 * @date 2017-09-06
 */

public class InitializeTaskServlet extends HttpServlet {
	
	private static final long serialVersionUID = 9139752603665660685L;
	
	public static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	
	public static TaskLogUtil taskLogUtil = null;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskLogUtil taskLogUtil1;
	
	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext()); 
		taskLogUtil = taskLogUtil1;
		try {
			Map paramMap = new HashMap();
			paramMap.put("status", "1");
			List<Task> taskList = taskService.getByCondition(paramMap);
			if(taskList != null && taskList.size() > 0){
				Scheduler scheduler = schedulerFactory.getScheduler();
				for(Task task : taskList){
				    //1.运行 0 停止 -1禁用 2暂停
					int status = task.getStatus();
					// 任务名，任务组，任务执行类
		        	JobDetail jobDetail = null;
		        	if(status == 1){
		        		//判断任务类型 1-本地定时任务  2-python定时任务 3-远程定时任务
			        	if(task.getTaskType() == 1){
			        		Class entityClass = Class.forName(task.getAccessPath());
			        		if(entityClass != null){
			                    jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), entityClass);
			                }else{
			                	throw new Exception("未找到与该定时任务关联的类!");
			                }
			        	}else if(task.getTaskType() == 2){
			        		jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), com.excellence.iamp.task.PythonServiceTask.class);
			        		jobDetail.getJobDataMap().put("url", task.getAccessPath().trim());
			        	}else if(task.getTaskType() == 3){
			        		jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), com.excellence.iamp.task.OtherApplyTask.class);
			        		jobDetail.getJobDataMap().put("url", task.getAccessPath().trim());
			        	}
			        	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        	 // 传递参数
			            jobDetail.getJobDataMap().put("taskVo", task);
			            jobDetail.getJobDataMap().put("taskLogUtil", taskLogUtil);
			            Map<String,Object> paramMaps = new HashMap<String,Object>();
			            if(StringUtils.isNotBlank(task.getTaskParams())){
			            	JSONArray arr = new JSONArray(task.getTaskParams());
			            	for(int i=0;i<arr.length();i++){
			            		JSONObject obj = (JSONObject)arr.get(i); 
			            		paramMaps.put(obj.getString("name"), obj.getString("value"));
			            	}
			            }
			            jobDetail.getJobDataMap().put("paramMap", paramMaps);
			            
			            // 触发器  
			            CronTrigger cornTrigger=new CronTrigger(task.getTriggerName(), task.getTriggerGroup());
			            // 触发器时间设定 
			            cornTrigger.setCronExpression(task.getTaskTime());
			            
			            cornTrigger.setStartTime(sdf.parse(task.getStartTime()));
			     		   
			            /*SimpleTrigger simpleTrigger=new SimpleTrigger(task.getTriggerName(),task.getTriggerGroup());
			     		   // 		 马上启动
			     		   //simpleTrigger.setStartTime(new Date());
			     		   // 		 间隔时间
			     		   simpleTrigger.setRepeatInterval(5000);
			     		   // 		 运行次数
			     		   simpleTrigger.setRepeatCount(200);*/
			     		   
			            // 调度容器设置JobDetail和Trigger
			            scheduler.scheduleJob(jobDetail, cornTrigger);
		        	}
		        	// 启动  
		            if (!scheduler.isShutdown()) {
		            	scheduler.start();
		            }
		            
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
     }
 
     protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
     }
}
