package com.excellence.iamp.mongodb.service;

import java.util.List;

import com.excellence.iamp.mongodb.vo.MonitoringSetting;

public interface MonitoringSettingService {
	MonitoringSetting save(MonitoringSetting monitoringSetting);
	void update(MonitoringSetting monitoringSetting);
	MonitoringSetting findOne(String id);
	List<MonitoringSetting> getList();
}
