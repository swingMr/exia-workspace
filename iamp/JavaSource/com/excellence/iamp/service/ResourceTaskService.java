package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.ResourceTask;
import com.github.pagehelper.PageInfo;

/**
 * ��Դ��������service
 * @author chenghq
 *
 */
public interface ResourceTaskService {
	
	public void create(ResourceTask resourceTask);
	
	public ResourceTask findById(String resTaskId);
	
	public void update(ResourceTask resourceTask);
	
	public void delete(String resTaskId);
	
	@SuppressWarnings("rawtypes")
	public List<ResourceTask> getByCondition(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<ResourceTask> page(Map paramMap, int pageNo, int pageSize);
	
	/**
	 * ��ȡ���������ļ�¼��
	 * @param paramMap
	 * @return
	 */
	public int getCount(Map paramMap);
	
	
}
