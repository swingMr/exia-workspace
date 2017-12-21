package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * ʵ������ʽ���ǩ�͸���ȫ������
 * @author chenghq
 *
 */
public class ResourceTask implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2541599345623945791L;

	/**
	 * ����id
	 */
	private String taskId;
	
	/**
	 * ��Դ����
	 */
	private String libNum;
	
	/**
	 * ��Դid
	 */
	private String resId;
	
	/**
	 * ��Դ����
	 */
	private String resTitle;
	
	/**
	 * ��Ϊ����
	 */
	private String actCode;
	
	/**
	 * ����ʱ��
	 */
	private Date createDate;
	
	/**
	 * ������id
	 */
	private String creatorId;
	
	/**
	 * ����������
	 */
	private String creatorName;
	
	/**
	 * ����ʱ��
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
