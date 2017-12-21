package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.ResourceTaskDao;
import com.excellence.iamp.dao.ResourceWorkItemDao;
import com.excellence.iamp.service.ResourceTaskService;
import com.excellence.iamp.service.ResourceWorkItemService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.ResourceTask;
import com.excellence.iamp.vo.enums.EStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 资源处理任务项serviceImpl
 * @author chenghq
 *
 */
@Service
public class ResourceTaskServiceImpl implements ResourceTaskService {
	
	@Autowired
	private ResourceTaskDao resourceTaskDao;
	
	/**
	 * 创建
	 */
	public void create(ResourceTask resourceTask) {
		if(StringUtils.isEmpty(resourceTask.getTaskId())) {
			resourceTask.setTaskId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		resourceTaskDao.insert(resourceTask);
	}
	
	/**
	 * 通过id查找
	 */
	public ResourceTask findById(String resTaskId) {
		return resourceTaskDao.findById(resTaskId);
	}
	
	/**
	 * 更新
	 */
	public void update(ResourceTask resourceTask) {
		resourceTaskDao.update(resourceTask);
	}
	
	/**
	 * 删除
	 */
	public void delete(String resTaskId) {
		resourceTaskDao.delete(resTaskId);
	}
	
	/**
	 * 通过条件查询
	 */
	@SuppressWarnings("rawtypes")
	public List<ResourceTask> getByCondition(Map paramMap) {
		return resourceTaskDao.getByCondition(paramMap);
	}
	
	/**
	 * 分页
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageInfo<ResourceTask> page(Map paramMap, int pageNo, int pageSize) {
		int count = resourceTaskDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<ResourceTask> list = resourceTaskDao.getByCondition(paramMap);
		PageInfo<ResourceTask> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}

	@Override
	public int getCount(Map paramMap) {
		return resourceTaskDao.countByCondition(paramMap);
	}

}
