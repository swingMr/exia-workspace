package com.excellence.iamp.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.excellence.iamp.service.TaskLogService;
import com.excellence.iamp.vo.Task;
import com.excellence.iamp.vo.TaskLog;

/**
 * ��ʱ������־������
 * @author wangjg
 * @date 2017-9-7
 * @2014-1015 Excellence
 */
@Component
public class TaskLogUtil {
	
	@Autowired
	private TaskLogService taskLogService;
	/**
	 * ����	��¼��ʱ������־
	 * @param task			��ʱ�������
	 * @param costTime		����ִ�к�ʱ(��λ������)
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
