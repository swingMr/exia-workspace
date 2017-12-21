package com.excellence.iamp.vo;

import java.util.Date;

public class ServiceDefinition {
	/**
	 * @author liuzehang ������
	 */
	//����Id;
	private String serviceId;
	//��������
	private String serviceName;
	//������������
	private String serviceCname;
	//��������
	private String serviceType;
	//������������
	private String typeName;
	//��������ID
	private String typeId;
	//����ʱ��
	private Date createDate;
	//״̬��
	private int status;
	//����·��
	private String accessPath;
	//python�ű�·��
	private String pythonPath;
	//����
	private String serviceInput;
	//��ע
	private String remark;
	//�ۻ����ʴ���
	private int accessCount;
	//�������ʱ��
	private Date recentAccessDate;
	//����ʱ��
	private Date expireDate;
	//������ID��
	private String creatorId;
	//���������֣�
	private String CreatorName;
	//����
	private Date updateDate;
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getRecentAccessDate() {
		return recentAccessDate;
	}
	public void setRecentAccessDate(Date recentAccessDate) {
		this.recentAccessDate = recentAccessDate;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceCname() {
		return serviceCname;
	}
	public void setServiceCname(String serviceCname) {
		this.serviceCname = serviceCname;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPythonPath() {
		return pythonPath;
	}
	public void setPythonPath(String pythonPath) {
		this.pythonPath = pythonPath;
	}
	public String getAccessPath() {
		return accessPath;
	}
	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}
	public String getServiceInput() {
		return serviceInput;
	}
	public void setServiceInput(String serviceInput) {
		this.serviceInput = serviceInput;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return CreatorName;
	}
	public void setCreatorName(String creatorName) {
		CreatorName = creatorName;
	}
}
