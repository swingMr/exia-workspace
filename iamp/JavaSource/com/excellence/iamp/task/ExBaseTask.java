package com.excellence.iamp.task;

import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.beans.factory.annotation.Autowired;

import com.excellence.iamp.dao.AppDao;
import com.excellence.iamp.mongodb.service.MonitoringInfoService;
import com.excellence.iamp.service.TaskService;
import com.excellence.iamp.util.SpringFactory;
import com.excellence.iamp.util.TaskLogUtil;
import com.excellence.iamp.vo.Task;

public abstract class ExBaseTask implements StatefulJob
{

	public void execute(JobExecutionContext context)
			throws JobExecutionException
	{
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		Task task = (Task) dataMap.get("taskVo");
		TaskLogUtil taskLogUtil = (TaskLogUtil) SpringFactory.getObject("taskLogUtil");
		Map<String,Object> paramMap = (Map<String,Object>)dataMap.get("paramMap");
		try
		{
			long startTime = System.currentTimeMillis();
			this.executeExJob(context,task,paramMap);
			long endTime = System.currentTimeMillis();
			task.setStatus(1);
			taskLogUtil.recordTaskLog(task, endTime - startTime);//记录定时任务日志
		}
		catch(JobExecutionException e)
		{
			e.printStackTrace();
		}
	}
	public abstract void executeExJob(JobExecutionContext context,Task task,Map<String,Object> paramMap) throws JobExecutionException;
}
