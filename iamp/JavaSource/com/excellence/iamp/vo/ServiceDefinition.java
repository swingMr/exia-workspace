package com.excellence.iamp.vo;

import java.util.Date;

public class ServiceDefinition {
	/**
	 * @author liuzehang 服务定义
	 */
	//服务Id;
	private String serviceId;
	//服务名称
	private String serviceName;
	//服务中文名称
	private String serviceCname;
	//服务类型
	private String serviceType;
	//所属分类名称
	private String typeName;
	//所属分类ID
	private String typeId;
	//创建时间
	private Date createDate;
	//状态；
	private int status;
	//访问路径
	private String accessPath;
	//python脚本路径
	private String pythonPath;
	//输入
	private String serviceInput;
	//备注
	private String remark;
	//累积访问次数
	private int accessCount;
	//最近访问时间
	private Date recentAccessDate;
	//过期时间
	private Date expireDate;
	//创建人ID；
	private String creatorId;
	//创建人名字；
	private String CreatorName;
	//更新
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
