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
 * ��Դ��������serviceImpl
 * @author huangjinyuan
 *
 */
@Service
public class ResourceWorkItemServiceImpl implements ResourceWorkItemService {
	
	@Autowired
	private ResourceWorkItemDao resourceWorkItemDao;
	
	/**
	 * ����
	 */
	public void create(ResourceWorkItem resourceWorkItem) {
		if(StringUtils.isEmpty(resourceWorkItem.getWorkItemId())) {
			resourceWorkItem.setWorkItemId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		resourceWorkItemDao.insert(resourceWorkItem);
	}
	
	/**
	 * ͨ��id����
	 */
	public ResourceWorkItem findById(String resWorkItemId) {
		return resourceWorkItemDao.findById(resWorkItemId);
	}
	
	/**
	 * ����
	 */
	public void update(ResourceWorkItem resourceWorkItem) {
		resourceWorkItemDao.update(resourceWorkItem);
	}
	
	/**
	 * ɾ��
	 */
	public void delete(ResourceWorkItem resourceWorkItem) {
		resourceWorkItem.setWorkStatus(EStatus.delete.getIndex());
		resourceWorkItemDao.update(resourceWorkItem);
	}
	
	/**
	 * ����ɾ��
	 */
	public void psyDelete(String resWorkItemId) {
		resourceWorkItemDao.delete(resWorkItemId);
	}
	
	/**
	 * ͨ��������ѯ
	 */
	@SuppressWarnings("rawtypes")
	public List<ResourceWorkItem> getByCondition(Map paramMap) {
		return resourceWorkItemDao.getByCondition(paramMap);
	}
	
	/**
	 * ��ҳ
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
