package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.Task;
import com.excellence.iamp.vo.TaskLog;
import com.github.pagehelper.PageInfo;

/**
 * 定时任务日志管理service
 * @author wangjg
 *
 */
public interface TaskLogService {
	
	public Integer create(TaskLog taskLog);
	
	public TaskLog findById(String taskLogId);
	
	public void update(TaskLog taskLog);
	
	public Integer delete(String taskLogId);
	
	@SuppressWarnings("rawtypes")
	public List<TaskLog> getByCondition(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<TaskLog> page(Map paramMap, int pageNo, int pageSize);

	
}
