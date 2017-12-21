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
 * ���ܣ���ʱ����������  ����  ��ͣ  ɾ��  ֹͣ��
 * @author wangjg
 * **/
public class QuartzManager {


	private static StdScheduler scheduler = (StdScheduler)SpringFactoryUtil.getObject("quartzScheduler");
	//��¼��ʱ������־����
	//private static TaskLogUtil taskLogUtil = InitializeTaskServlet.taskLogUtil;
	
    /** 
     * @Description: ���һ����ʱ���� 
     *  
     * @param task �ɼ�����vo
     */  
    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
    public static void addAndStartJob(Task task) {
    	JobDetail jobDetail1 = null;
        try {
        	//������ȳأ�Ψһ���������������
        	//scheduler = schedulerFactory.getScheduler();
        	// �������������飬����ִ����
        	JobDetail jobDetail = scheduler.getJobDetail(task.getTaskName(), task.getTaskGroup());
        	jobDetail1 = jobDetail;
        	//�ж��������� 1-���ض�ʱ����  2-python��ʱ���� 3-Զ�̶�ʱ����
        	if(task.getTaskType() == 1){
        		Class entityClass = Class.forName(task.getAccessPath());
        		if(entityClass != null){
        			if(jobDetail == null){
        				jobDetail = new JobDetail(task.getTaskName(), task.getTaskGroup(), entityClass);
        			}
                }else{
                	throw new Exception("δ�ҵ���ö�ʱ�����������!");
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
        	
            // ���ݲ���
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
            // ������  
            CronTrigger cornTrigger = (CronTrigger) scheduler.getTrigger(task.getTriggerName(), task.getTriggerGroup());
            if(cornTrigger == null){
            	cornTrigger=new CronTrigger(task.getTriggerName(), task.getTriggerGroup());
            	cornTrigger.setStartTime(sdf.parse(task.getStartTime()));
            	// ������ʱ���趨 
                cornTrigger.setCronExpression(task.getTaskTime());
            }
            /*SimpleTrigger simpleTrigger=new SimpleTrigger(task.getTriggerName(),task.getTriggerGroup());
     		   // 		 ��������
     		   //simpleTrigger.setStartTime(new Date());
     		   // 		 ���ʱ��
     		   simpleTrigger.setRepeatInterval(5000);
     		   // 		 ���д���
     		   simpleTrigger.setRepeatCount(200);*/
     		   
            if(jobDetail1 != null){
            	Boolean historyTask = false;//�Ƿ���ʷ����,��ʷ��������
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
            	// ������������JobDetail��Trigger
                scheduler.scheduleJob(jobDetail, cornTrigger);
            }
           // ����  
            if (!scheduler.isShutdown() && !scheduler.isStarted()) {
            	scheduler.start();
            	System.out.println("��ʱ������ȳ�������====================");
            }
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    

    /** 
     * @Description: �Ƴ�һ������ 
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
            			// ֹͣ������
                    	scheduler1.pauseTrigger(task.getTriggerName(), task.getTriggerGroup());
                        // �Ƴ�������
                    	scheduler1.unscheduleJob(task.getTriggerName(), task.getTriggerGroup());
                        // ɾ������
                    	scheduler1.deleteJob(task.getTaskName(), task.getTaskGroup());
            		}
            	}else{
            		// ֹͣ������
                	scheduler1.pauseTrigger(task.getTriggerName(), task.getTriggerGroup());
                    // �Ƴ�������
                	scheduler1.unscheduleJob(task.getTriggerName(), task.getTriggerGroup());
                    // ɾ������
                	scheduler1.deleteJob(task.getTaskName(), task.getTaskGroup());
            	}
            } 
            
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }

    
    /** 
     * @Description:����ĳ����ʱ����ʱ���� 
     */  
    public static void startTask(Task task) {  
        try { 
        	Scheduler scheduler1 = scheduler;
        	
        	if (scheduler1 != null && !scheduler1.isShutdown()) {
        		Boolean historyTask = false;//�Ƿ���ʷ����,��ʷ��������
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
     * @Description:ֹͣĳ����ʱ���� 
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
     * @Description:��ͣĳ����ʱ���� 
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
     * ��ȡʱ����
     * @author lishuowen
     * @param cron cron���ʽ
     * @return once-һ�Σ�day-�գ�week-�ܣ�month-��
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