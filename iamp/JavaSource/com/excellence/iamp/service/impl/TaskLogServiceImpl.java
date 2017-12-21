package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.dao.TaskLogDao;
import com.excellence.iamp.service.TaskLogService;
import com.excellence.iamp.vo.TaskLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TaskLogServiceImpl implements TaskLogService {
	
	@Autowired
	private TaskLogDao taskLogDao;
	
	/**
	 * ����
	 */
	public Integer create(TaskLog taskLog) {
		if(StringUtils.isEmpty(taskLog.getId())) {
			taskLog.setId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		Integer num = taskLogDao.insert(taskLog);
		return num;
	}
	
	/**
	 * ͨ��id����
	 */
	public TaskLog findById(String taskLogId) {
		return taskLogDao.findById(taskLogId);
	}
	
	/**
	 * ����
	 */
	public void update(TaskLog taskLog) {
		taskLogDao.update(taskLog);
	}
	
	/**
	 * ɾ��
	 */
	public Integer delete(String taskLogId) {
		Integer num = taskLogDao.delete(taskLogId);
		return num;
	}
	
	/**
	 * ͨ��������ѯ
	 */
	@SuppressWarnings("rawtypes")
	public List<TaskLog> getByCondition(Map paramMap) {
		return taskLogDao.getByCondition(paramMap);
	}
	
	/**
	 * ��ҳ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<TaskLog> page(Map paramMap, int pageNo, int pageSize) {
		int count = taskLogDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<TaskLog> list = taskLogDao.getByCondition(paramMap);
		PageInfo<TaskLog> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}
	
}
