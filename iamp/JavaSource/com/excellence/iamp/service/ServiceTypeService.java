package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.ServiceType;
import com.github.pagehelper.PageInfo;

public interface ServiceTypeService {

	PageInfo getList(int num,int targetPageSize,Map paramMap);
	
	List<ServiceType> getList();
	
	ServiceType findById(String id);
	
	void update(ServiceType serviceType);
	
	int insert(ServiceType serviceType);

	void del(String id);
}
