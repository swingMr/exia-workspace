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
	 * ����
	 * @param serviceDefinition
	 */
    int insert(ServiceDefinition serviceDefinition);
	
	List<ServiceDefinition> getList();
	/**
	 * ɾ��
	 * @param serviceId
	 */
	void delById(String serviceId);
	/**
	 * ����
	 * @param serviceDefinition
	 */
	int update(ServiceDefinition serviceDefinition);
	
	ServiceDefinition getServiceDefinitionById(String serviceId);
	
	/**
	 * ͳ������
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
}
