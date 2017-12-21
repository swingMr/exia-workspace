package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.dao.TaskDao;
import com.excellence.iamp.service.TaskService;
import com.excellence.iamp.vo.Task;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	/**
	 * 创建
	 */
	public Integer create(Task task) {
		if(StringUtils.isEmpty(task.getTaskId())) {
			task.setTaskId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		Integer num = taskDao.insert(task);
		return num;
	}
	
	/**
	 * 通过id查找
	 */
	public Task findById(String taskId) {
		return taskDao.findById(taskId);
	}
	
	/**
	 * 更新
	 */
	public void update(Task task) {
		taskDao.update(task);
	}
	
	/**
	 * 删除
	 */
	public Integer delete(String taskId) {
		Integer num = taskDao.delete(taskId);
		return num;
	}
	
	/**
	 * 通过条件查询
	 */
	@SuppressWarnings("rawtypes")
	public List<Task> getByCondition(Map paramMap) {
		return taskDao.getByCondition(paramMap);
	}
	
	/**
	 * 分页
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<Task> page(Map paramMap, int pageNo, int pageSize) {
		int count = taskDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<Task> list = taskDao.getByCondition(paramMap);
		PageInfo<Task> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}
	
}
