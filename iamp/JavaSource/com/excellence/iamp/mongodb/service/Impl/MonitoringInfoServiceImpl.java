package com.excellence.iamp.mongodb.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.excellence.iamp.mongodb.dao.MonitoringInfoDao;
import com.excellence.iamp.mongodb.service.MonitoringInfoService;
import com.excellence.iamp.mongodb.vo.MonitoringInfo;
import com.excellence.iamp.util.Page;
import com.mongodb.WriteResult;

@Service("monitoringInfoService")
public class MonitoringInfoServiceImpl implements MonitoringInfoService{
	@Autowired
	private MonitoringInfoDao monitoringInfoDao;
	
	//通过状态获取指定的list数据。
	public List<MonitoringInfo> getListByMonitoredStatus(){
		return null;
	};
	
	//列表
	public Page<MonitoringInfo> getList(Map<String, Object> map,int currentPage, int pageSize){
		return monitoringInfoDao.getList(map,currentPage, pageSize);
	}
	
	public MonitoringInfo save(MonitoringInfo monitoringInfo){
		return monitoringInfoDao.save(monitoringInfo);
	}
	public WriteResult update(MonitoringInfo monitoringInfo){
		return monitoringInfoDao.update(monitoringInfo);
	}
	
	public MonitoringInfo getOneById(String id){
		return monitoringInfoDao.findById(id);
	}
}
