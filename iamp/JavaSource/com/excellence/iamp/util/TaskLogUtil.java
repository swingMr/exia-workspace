package com.excellence.iamp.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.excellence.iamp.service.TaskLogService;
import com.excellence.iamp.vo.Task;
import com.excellence.iamp.vo.TaskLog;

/**
 * 定时任务日志工具类
 * @author wangjg
 * @date 2017-9-7
 * @2014-1015 Excellence
 */
@Component
public class TaskLogUtil {
	
	@Autowired
	private TaskLogService taskLogService;
	/**
	 * 功能	记录定时任务日志
	 * @param task			定时任务对象
	 * @param costTime		任务执行耗时(单位：毫秒)
	 * **/
	@Async
	public void recordTaskLog(Task task,Long costTime){
		try {
			TaskLog taskLog = new TaskLog();
			taskLog.setTaskId(task.getTaskId());
			taskLog.setTaskName(task.getTaskName());
			taskLog.setLogType(task.getTaskType());
			taskLog.setTaskStatus(task.getStatus());
			taskLog.setCreateDate(new Date());
			taskLog.setCostTime(costTime);
			taskLogService.create(taskLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
