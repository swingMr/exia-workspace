package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.ResourceWorkItemDao;
import com.excellence.iamp.service.ResourceWorkItemService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.ResourceWorkItem;
import com.excellence.iamp.vo.enums.EStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 资源处理工作项serviceImpl
 * @author huangjinyuan
 *
 */
@Service
public class ResourceWorkItemServiceImpl implements ResourceWorkItemService {
	
	@Autowired
	private ResourceWorkItemDao resourceWorkItemDao;
	
	/**
	 * 创建
	 */
	public void create(ResourceWorkItem resourceWorkItem) {
		if(StringUtils.isEmpty(resourceWorkItem.getWorkItemId())) {
			resourceWorkItem.setWorkItemId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		resourceWorkItemDao.insert(resourceWorkItem);
	}
	
	/**
	 * 通过id查找
	 */
	public ResourceWorkItem findById(String resWorkItemId) {
		return resourceWorkItemDao.findById(resWorkItemId);
	}
	
	/**
	 * 更新
	 */
	public void update(ResourceWorkItem resourceWorkItem) {
		resourceWorkItemDao.update(resourceWorkItem);
	}
	
	/**
	 * 删除
	 */
	public void delete(ResourceWorkItem resourceWorkItem) {
		resourceWorkItem.setWorkStatus(EStatus.delete.getIndex());
		resourceWorkItemDao.update(resourceWorkItem);
	}
	
	/**
	 * 物理删除
	 */
	public void psyDelete(String resWorkItemId) {
		resourceWorkItemDao.delete(resWorkItemId);
	}
	
	/**
	 * 通过条件查询
	 */
	@SuppressWarnings("rawtypes")
	public List<ResourceWorkItem> getByCondition(Map paramMap) {
		return resourceWorkItemDao.getByCondition(paramMap);
	}
	
	/**
	 * 分页
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageInfo<ResourceWorkItem> page(Map paramMap, int pageNo, int pageSize) {
		int count = resourceWorkItemDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<ResourceWorkItem> list = resourceWorkItemDao.getByCondition(paramMap);
		PageInfo<ResourceWorkItem> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}

}
