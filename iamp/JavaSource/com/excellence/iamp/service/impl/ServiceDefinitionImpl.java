package com.excellence.iamp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.ServiceDefinitionDao;
import com.excellence.iamp.service.ServiceDefinitionService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.ServiceDefinition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class ServiceDefinitionImpl implements ServiceDefinitionService{
	@Autowired
	private ServiceDefinitionDao serviceDefinitionDao;
	@Override
	public PageInfo getList(int num,int pageSize,Map paramMap){
		 int count = serviceDefinitionDao.countByCondition(paramMap);
		 PageHelper.startPage(num, pageSize, false);
		 PageInfo pageInfo = new PageInfo<ServiceDefinition>(serviceDefinitionDao.getList(paramMap));
		 int pageSizes = Page.getPageCount(count, pageSize);
		 pageInfo.setPages(pageSizes);
		 pageInfo.setTotal(count);
		 return pageInfo;
	};
	
	public List<ServiceDefinition> getList(){
		return serviceDefinitionDao.getList();
	};

	public List<ServiceDefinition> getListByConditions(Map paramMap){
		return serviceDefinitionDao.getList();
	};
	
	public int insert(ServiceDefinition serviceDefinition){
		serviceDefinition.setServiceId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		return serviceDefinitionDao.insert(serviceDefinition);
	};
	
	public void delById(String serviceId){
		serviceDefinitionDao.delById(serviceId);
	};
	
	public int update(ServiceDefinition serviceDefinition){
		return serviceDefinitionDao.update(serviceDefinition);
	};
	
	public ServiceDefinition getServiceDefinitionById(String serviceId){
		return serviceDefinitionDao.getServiceDefinitionById(serviceId);
	}
}
