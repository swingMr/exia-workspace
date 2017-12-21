package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.Task;
import com.github.pagehelper.PageInfo;

/**
 * 定时任务管理管理service
 * @author wangjg
 *
 */
public interface TaskService {
	
	public Integer create(Task task);
	
	public Task findById(String taskId);
	
	public void update(Task task);
	
	public Integer delete(String taskId);
	
	@SuppressWarnings("rawtypes")
	public List<Task> getByCondition(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<Task> page(Map paramMap, int pageNo, int pageSize);

	
}
