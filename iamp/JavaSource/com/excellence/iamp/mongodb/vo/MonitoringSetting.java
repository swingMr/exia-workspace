package com.excellence.iamp.mongodb.vo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ia_monitoring_setting")
public class MonitoringSetting {
	/**���������
	 * @author Liuzh
	 */
	
	@Id
	private String id;
	//�������������
	private SettingAttribute subjectSetting;
	//��ؿ���������
	private SettingAttribute objectSetting;
	//�����Ϊ�������
	private SettingAttribute actionSetting;
	//���ʱ��������
	private SettingAttribute timeSetting;
	//��ؿռ�������
	private SettingAttribute spaceSetting;
	//�������������
	private SettingAttribute problemSetting;
	//��عؼ��ʼ������
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
