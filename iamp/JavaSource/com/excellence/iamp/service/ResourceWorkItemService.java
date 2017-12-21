package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.ResourceWorkItem;
import com.github.pagehelper.PageInfo;

/**
 * 资源处理工作项service
 * @author huangjinyuan
 *
 */
public interface ResourceWorkItemService {
	
	public void create(ResourceWorkItem resourceWorkItem);
	
	public ResourceWorkItem findById(String resWorkItemId);
	
	public void update(ResourceWorkItem resourceWorkItem);
	
	public void delete(ResourceWorkItem resourceWorkItem);
	
	public void psyDelete(String resWorkItemId);
	
	@SuppressWarnings("rawtypes")
	public List<ResourceWorkItem> getByCondition(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<ResourceWorkItem> page(Map paramMap, int pageNo, int pageSize);
	
	
}
