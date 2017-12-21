package com.excellence.iamp.mongodb.vo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ia_monitoring_setting")
public class MonitoringSetting {
	/**监控设置类
	 * @author Liuzh
	 */
	
	@Id
	private String id;
	//相关主体监控属性
	private SettingAttribute subjectSetting;
	//相关客体监控属性
	private SettingAttribute objectSetting;
	//相关行为监控属性
	private SettingAttribute actionSetting;
	//相关时间监控属性
	private SettingAttribute timeSetting;
	//相关空间监控属性
	private SettingAttribute spaceSetting;
	//相关问题监控属性
	private SettingAttribute problemSetting;
	//相关关键词监控属性
	private SettingAttribute keywordSetting; 
	
	public String getId() {
		return id;
	}

	public SettingAttribute getKeywordSetting() {
		return keywordSetting;
	}

	public void setKeywordSetting(SettingAttribute keywordSetting) {
		this.keywordSetting = keywordSetting;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public SettingAttribute getSubjectSetting() {
		return subjectSetting;
	}

	public void setSubjectSetting(SettingAttribute subjectSetting) {
		this.subjectSetting = subjectSetting;
	}

	public SettingAttribute getObjectSetting() {
		return objectSetting;
	}

	public void setObjectSetting(SettingAttribute objectSetting) {
		this.objectSetting = objectSetting;
	}

	public SettingAttribute getActionSetting() {
		return actionSetting;
	}

	public void setActionSetting(SettingAttribute actionSetting) {
		this.actionSetting = actionSetting;
	}

	public SettingAttribute getTimeSetting() {
		return timeSetting;
	}

	public void setTimeSetting(SettingAttribute timeSetting) {
		this.timeSetting = timeSetting;
	}

	public SettingAttribute getSpaceSetting() {
		return spaceSetting;
	}

	public void setSpaceSetting(SettingAttribute spaceSetting) {
		this.spaceSetting = spaceSetting;
	}

	public SettingAttribute getProblemSetting() {
		return problemSetting;
	}

	public void setProblemSetting(SettingAttribute problemSetting) {
		this.problemSetting = problemSetting;
	}
	
	
}
