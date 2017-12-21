package com.excellence.iamp.task;


import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.excellence.iamp.mongodb.service.MonitoringInfoService;
import com.excellence.iamp.util.SpringFactory;
import com.excellence.iamp.vo.Task;

public class CrawlJob extends ExBaseTask {
	@Override
	public void executeExJob(JobExecutionContext context, Task task, Map<String,Object> paramMap)
			throws JobExecutionException {
		System.out.println("123456789=======================");
	}


}
