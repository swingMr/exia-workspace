package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.ServiceTypeDao;
import com.excellence.iamp.service.ServiceTypeService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.ServiceType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ServiceTypeServiceImpl implements ServiceTypeService{
	@Autowired
	private ServiceTypeDao serviceTypeDao;
	public PageInfo getList(int num,int targetPageSize,Map paramMap){
		 int count = serviceTypeDao.countByCondition(paramMap);
		 PageHelper.startPage(num, targetPageSize,false);
		 PageInfo pageInfo = new PageInfo<ServiceType>(serviceTypeDao.getList(paramMap));
		 int pageSizes = Page.getPageCount(count, targetPageSize);
		 pageInfo.setPages(pageSizes);
		 pageInfo.setTotal(count);
		 return pageInfo;
	}
	
	public List<ServiceType> getList(){
		return serviceTypeDao.getList();
	}
	
	public ServiceType findById(String id){
		return serviceTypeDao.findById(id);
	}
	
	public void update(ServiceType serviceType){
		serviceTypeDao.update(serviceType);
	} 
	
	public int insert(ServiceType serviceType){
		serviceType.setTypeId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		return serviceTypeDao.insert(serviceType);
	}
	
	public void del(String id){
	 serviceTypeDao.delete(id);
	}
}
