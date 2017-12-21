package com.excellence.iamp.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 智慧化应用
 * @author huangjinyuan
 *
 */
public class App implements Serializable {
	
	private static final long serialVersionUID = -4292223495525501405L;
	
	/**
	 * 主键
	 */
	private String appId;
	
	/**
	 * 应用名称
	 */
	private String appName;
	
	/**
	 * 应用代码，唯一
	 */
	private String appCode;
	
	/**
	 * 应用密码
	 */
	private String appPwd;
	
	/**
	 * 过期时间
	 */
	private Date expireDate;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 限制ip，以;分割
	 */
	private String restrictIp;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建者Id
	 */
	private String creatorId;
	
	/**
	 * 创建者名字
	 */
	private String creatorName;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 更新日期
	 */
	private Date updateDate;
	
	/**
	 * 资源所属单位
	 */
	private String appUnit;
	
	
	/**
	 * token
	 */
	private String token;
	
	public String getAppUnit() {
		return appUnit;
	}

	public void setAppUnit(String appUnit) {
		this.appUnit = appUnit;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppPwd() {
		return appPwd;
	}

	public void setAppPwd(String appPwd) {
		this.appPwd = appPwd;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRestrictIp() {
		return restrictIp;
	}

	public void setRestrictIp(String restrictIp) {
		this.restrictIp = restrictIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getExpireDateStr() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sd.format(this.expireDate);
		if(dateStr.equals("2038-01-01")) {
			dateStr = "永久";
		} else {
			sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateStr = sd.format(this.expireDate);
		}
		return dateStr;
	}

}
