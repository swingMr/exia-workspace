package com.excellence.iamp.mongodb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.excellence.iamp.mongodb.dao.MonitoringSettingDao;
import com.excellence.iamp.mongodb.service.MonitoringSettingService;
import com.excellence.iamp.mongodb.vo.MonitoringSetting;
import com.mongodb.WriteResult;

@Service("monitoringSettingService")
public class MonitoringSettingServiceImpl implements MonitoringSettingService{
	@Autowired
	private MonitoringSettingDao monitoringSettingDao;
	
	public MonitoringSetting save(MonitoringSetting monitoringSetting){
		return monitoringSettingDao.save(monitoringSetting);
	};
	
	public void update(MonitoringSetting monitoringSetting){
		monitoringSettingDao.update(monitoringSetting);
	};
	
	
	public MonitoringSetting findOne(String id){
		return monitoringSettingDao.findById(id);
	}
	
	public List<MonitoringSetting> getList(){
		return monitoringSettingDao.findAll();
	};
}
