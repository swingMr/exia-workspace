package com.excellence.iaserver.task;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.excellence.iamp.util.TaskLogUtil;
import com.excellence.iamp.vo.Task;
import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.SpringFactoryUtil;
import com.excellence.iaserver.common.util.T;
import com.excellence.iaserver.web.servlet.InitializeTaskServlet;
/**
 * 功能：定时任务管理（添加  启动  暂停  删除  停止）
 * @author wangjg
 * **/
public class QuartzManager {


	private static StdScheduler scheduler = (StdScheduler)SpringFactoryUtil.getObject("quartzScheduler");
	//记录定时任务日志工具
	//private static TaskLogUtil taskLogUtil = InitializeTaskServlet.taskLogUtil;
	
    /** 
     * @Description: 添加一个定时任务 
     *  
     * @param task 采集任务vo
     */  
    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
    public static void addAndStartJob(Task task) {
    	JobDetail jobDetail1 = null;
        try {
        	//任务调度池，唯一的且所有任务共享的
        	//scheduler = schedulerFactory.getScheduler();
        	// 任务名，任务组，任务执行类
        	JobDetail jobDetail = scheduler.getJobDetail(task.getTaskName(), task.getTaskGroup());
        	jobDetail1 = jobDetail;
        	//判断任务类型 1-本地定时任务  2-python定时任务 3-远程定时任务
        	if(task.getTaskType() == 1){
        		Class entityClass = Class.forName(task.getAccessPath());
        		if(entityClass != null){
        			if(jobDetail == null){
        				jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), entityClass);
        			}
                }else{
                	throw new Exception("未找到与该定时任务关联的类!");
                }
        	}else if(task.getTaskType() == 2){
        		if(jobDetail == null){
        			jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), com.excellence.iamp.task.PythonServiceTask.class);
    			}
        		jobDetail.getJobDataMap().put("url", task.getAccessPath().trim());
        	}else if(task.getTaskType() == 3){
        		if(jobDetail == null){
        			jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), com.excellence.iamp.task.OtherApplyTask.class);
    			}
        		jobDetail.getJobDataMap().put("url", task.getAccessPath().trim());
        	}
        	
            // 传递参数
            jobDetail.getJobDataMap().put("taskVo", task);
            Map<String,Object> paramMap = new HashMap<String,Object>();
            if(StringUtils.isNotBlank(task.getTaskParams())){
            	JSONArray arr = new JSONArray(task.getTaskParams());
            	for(int i=0;i<arr.length();i++){
            		JSONObject obj = (JSONObject)arr.get(i);
            		if(obj.get("name") != null){
            			paramMap.put(obj.getString("name"), obj.getString("value"));
            		}
            	}
            }
            jobDetail.getJobDataMap().put("paramMap", paramMap);
            SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            // 触发器  
            CronTrigger cornTrigger = (CronTrigger) scheduler.getTrigger(task.getTriggerName(), task.getTriggerGroup());
            if(cornTrigger == null){
            	cornTrigger=new CronTrigger(task.getTriggerName(), task.getTriggerGroup());
            	cornTrigger.setStartTime(sdf.parse(task.getStartTime()));
            	// 触发器时间设定 
                cornTrigger.setCronExpression(task.getTaskTime());
            }
            /*SimpleTrigger simpleTrigger=new SimpleTrigger(task.getTriggerName(),task.getTriggerGroup());
     		   // 		 马上启动
     		   //simpleTrigger.setStartTime(new Date());
     		   // 		 间隔时间
     		   simpleTrigger.setRepeatInterval(5000);
     		   // 		 运行次数
     		   simpleTrigger.setRepeatCount(200);*/
     		   
            if(jobDetail1 != null){
            	Boolean historyTask = false;//是否历史任务,历史任务不启动
        		String endTime = task.getEndTime();
    			if (StringUtils.isNotBlank(endTime)) {
    				if (T.parse(endTime, "yyyy-MM-dd hh:mm:ss").before(T.getNow())) {
    					historyTask = true;
    				}
    			}
    			if(!historyTask){
    				scheduler.resumeJob(task.getTaskName(), task.getTaskGroup());
                	scheduler.resumeTrigger(task.getTriggerName(), task.getTriggerGroup());
    			}
            }else{
            	// 调度容器设置JobDetail和Trigger
                scheduler.scheduleJob(jobDetail, cornTrigger);
            }
           // 启动  
            if (!scheduler.isShutdown() && !scheduler.isStarted()) {
            	scheduler.start();
            	System.out.println("定时任务调度池已启动====================");
            }
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    

    /** 
     * @Description: 移除一个任务 
     *  
     * @param jobName 
     * @param jobGroupName 
     * @param triggerName 
     * @param triggerGroupName 
     */ 
    public static void removeJob(Task task) {  
        try {
        	Scheduler scheduler1 = scheduler;
        	if (scheduler1 != null ) {
        		if(task.getTaskType() == 2){
            		JobDetail job = scheduler1.getJobDetail(task.getTaskName(), task.getTaskGroup());
            		Trigger trigger = scheduler1.getTrigger(task.getTriggerName(), task.getTriggerGroup());
            		if(job != null){
            			// 停止触发器
                    	scheduler1.pauseTrigger(task.getTriggerName(), task.getTriggerGroup());
                        // 移除触发器
                    	scheduler1.unscheduleJob(task.getTriggerName(), task.getTriggerGroup());
                        // 删除任务
                    	scheduler1.deleteJob(task.getTaskName(), task.getTaskGroup());
            		}
            	}else{
            		// 停止触发器
                	scheduler1.pauseTrigger(task.getTriggerName(), task.getTriggerGroup());
                    // 移除触发器
                	scheduler1.unscheduleJob(task.getTriggerName(), task.getTriggerGroup());
                    // 删除任务
                	scheduler1.deleteJob(task.getTaskName(), task.getTaskGroup());
            	}
            } 
            
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }

    
    /** 
     * @Description:启动某个定时任务定时任务 
     */  
    public static void startTask(Task task) {  
        try { 
        	Scheduler scheduler1 = scheduler;
        	
        	if (scheduler1 != null && !scheduler1.isShutdown()) {
        		Boolean historyTask = false;//是否历史任务,历史任务不启动
        		String endTime = task.getEndTime();
    			if (StringUtils.isNotBlank(endTime)) {
    				if (T.parse(endTime, "yyyy-MM-dd hh:mm:ss").before(T.getNow())) {
    					historyTask = true;
    				}
    			}
    			if(!historyTask){
    				if(task.getStatus() == 2){
                		JobDetail job = scheduler1.getJobDetail(task.getTaskName(), task.getTaskGroup());
                		if(job == null){
                			addAndStartJob(task);
                		}else{
                			scheduler1.resumeJob(task.getTaskName(), task.getTaskGroup());
                        	scheduler1.resumeTrigger(task.getTriggerName(), task.getTriggerGroup());
                		}
                	}else if(task.getStatus() == 0){
                		JobDetail job = scheduler1.getJobDetail(task.getTaskName(), task.getTaskGroup());
                		if(job == null){
                			addAndStartJob(task);
                		}
                	}else{
                		scheduler1.resumeJob(task.getTaskName(), task.getTaskGroup());
                    	scheduler1.resumeTrigger(task.getTriggerName(), task.getTriggerGroup());
                	}
    			}
            } 
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    
    /** 
     * @Description:停止某个定时任务 
     */  
    public static void shutdownTask(Task task) {  
        try { 
        	Scheduler scheduler1 = scheduler;
            if (scheduler1 != null && !scheduler1.isShutdown()) { 
            	QuartzManager.removeJob(task);
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    } 
    
    /** 
     * @Description:暂停某个定时任务 
     */  
    public static void stopTask(Task task) {  
        try { 
        	Scheduler scheduler1 = scheduler;
            if (scheduler1 != null && !scheduler1.isShutdown()) {  
            	scheduler1.pauseJob(task.getTaskName(), task.getTaskGroup());
            	//scheduler1.pauseTrigger(task.getTriggerName(), task.getTriggerGroup());
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    } 
    
    /**
     * 获取时间间隔
     * @author lishuowen
     * @param cron cron表达式
     * @return once-一次，day-日，week-周，month-月
     */
    public static String getTimeInterval(String cron) {
    	if (cron.matches("\\d{1,2} \\d{1,2} \\d{1,2} \\* \\* \\? \\*")) {
    		return "day";
    	} else if (cron.matches("\\d{1,2} \\d{1,2} \\d{1,2} \\? \\* \\d{1} \\*")) {
    		return "week";
    	} else if (cron.matches("\\d{1,2} \\d{1,2} \\d{1,2} \\? \\* \\d{1,2}#\\d{1,2} \\*")) {
    		return "month";
    	} else {
    		return "once";
    	}
    }

}