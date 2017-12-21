package com.excellence.iamp.mongodb.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.mongodb.vo.MonitoringInfo;
import com.excellence.iamp.util.Page;
import com.mongodb.WriteResult;

public interface MonitoringInfoService {
	
	List<MonitoringInfo> getListByMonitoredStatus();
	
	public Page<MonitoringInfo> getList(Map<String, Object> map,int currentPage, int pageSize);
	
	MonitoringInfo save(MonitoringInfo monitoringInfo);
	
	MonitoringInfo getOneById(String id);
	
	WriteResult update(MonitoringInfo monitoringInfo);
}
