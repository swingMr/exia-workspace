package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.ServiceDefinition;
import com.excellence.iamp.vo.ServiceType;


@Repository
public interface ServiceDefinitionDao {	
	List<ServiceDefinition> getList(Map paramMap);
	
	/**
	 * 新增
	 * @param serviceDefinition
	 */
    int insert(ServiceDefinition serviceDefinition);
	
	List<ServiceDefinition> getList();
	/**
	 * 删除
	 * @param serviceId
	 */
	void delById(String serviceId);
	/**
	 * 更新
	 * @param serviceDefinition
	 */
	int update(ServiceDefinition serviceDefinition);
	
	ServiceDefinition getServiceDefinitionById(String serviceId);
	
	/**
	 * 统计总数
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
}
