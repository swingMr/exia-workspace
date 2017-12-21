package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 实现增量式打标签和更新全文索引
 * @author chenghq
 *
 */
public class ResourceTask implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2541599345623945791L;

	/**
	 * 任务id
	 */
	private String taskId;
	
	/**
	 * 资源库编号
	 */
	private String libNum;
	
	/**
	 * 资源id
	 */
	private String resId;
	
	/**
	 * 资源标题
	 */
	private String resTitle;
	
	/**
	 * 行为代码
	 */
	private String actCode;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 创建人id
	 */
	private String creatorId;
	
	/**
	 * 创建人名字
	 */
	private String creatorName;
	
	/**
	 * 过期时间
	 */
	private Date expireDate;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getLibNum() {
		return libNum;
	}

	public void setLibNum(String libNum) {
		this.libNum = libNum;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResTitle() {
		return resTitle;
	}

	public void setResTitle(String resTitle) {
		this.resTitle = resTitle;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
}
