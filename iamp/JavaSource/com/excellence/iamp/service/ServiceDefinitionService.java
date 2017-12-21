package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.ServiceDefinition;
import com.github.pagehelper.PageInfo;


public interface ServiceDefinitionService {
	PageInfo getList(int num,int targetPageSize,Map paramMap);
	
	List<ServiceDefinition> getList();
	
	List<ServiceDefinition> getListByConditions(Map paramMap);
	
	int insert(ServiceDefinition serviceDefinition);
	
	void delById(String serviceId);
	
	int update(ServiceDefinition serviceDefinition);
	
	ServiceDefinition getServiceDefinitionById(String serviceId);
}
