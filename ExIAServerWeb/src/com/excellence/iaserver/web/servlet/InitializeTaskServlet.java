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
				    //1.���� 0 ֹͣ -1���� 2��ͣ
					int status = task.getStatus();
					// �������������飬����ִ����
		        	JobDetail jobDetail = null;
		        	if(status == 1){
		        		//�ж��������� 1-���ض�ʱ����  2-python��ʱ���� 3-Զ�̶�ʱ����
			        	if(task.getTaskType() == 1){
			        		Class entityClass = Class.forName(task.getAccessPath());
			        		if(entityClass != null){
			                    jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), entityClass);
			                }else{
			                	throw new Exception("δ�ҵ���ö�ʱ�����������!");
			                }
			        	}else if(task.getTaskType() == 2){
			        		jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), com.excellence.iamp.task.PythonServiceTask.class);
			        		jobDetail.getJobDataMap().put("url", task.getAccessPath().trim());
			        	}else if(task.getTaskType() == 3){
			        		jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), com.excellence.iamp.task.OtherApplyTask.class);
			        		jobDetail.getJobDataMap().put("url", task.getAccessPath().trim());
			        	}
			        	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        	 // ���ݲ���
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
			            
			            // ������  
			            CronTrigger cornTrigger=new CronTrigger(task.getTriggerName(), task.getTriggerGroup());
			            // ������ʱ���趨 
			            cornTrigger.setCronExpression(task.getTaskTime());
			            
			            cornTrigger.setStartTime(sdf.parse(task.getStartTime()));
			     		   
			            /*SimpleTrigger simpleTrigger=new SimpleTrigger(task.getTriggerName(),task.getTriggerGroup());
			     		   // 		 ��������
			     		   //simpleTrigger.setStartTime(new Date());
			     		   // 		 ���ʱ��
			     		   simpleTrigger.setRepeatInterval(5000);
			     		   // 		 ���д���
			     		   simpleTrigger.setRepeatCount(200);*/
			     		   
			            // ������������JobDetail��Trigger
			            scheduler.scheduleJob(jobDetail, cornTrigger);
		        	}
		        	// ����  
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
