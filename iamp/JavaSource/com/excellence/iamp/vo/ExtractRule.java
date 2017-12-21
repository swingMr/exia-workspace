package com.excellence.iamp.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * ��ȡ����
 * @author liup
 *
 */
public class ExtractRule implements Serializable {
	
	private static final long serialVersionUID = -4292223495525501405L;
	
	/**
	 * ����
	 */
	private String ruleId;
	
	/**
	 * ���
	 */
	private String genre;
	
	/**
	 * ��������
	 */
	private String ruleContent;
	
	/**
	 * �ļ���ʽ
	 */
	private String fileType;
	
	/**
	 * ״̬
	 */
	private int status;
	
	/**
	 * ������Id
	 */
	private String creatorId;
	
	/**
	 * ����������
	 */
	private String creatorName;
	
	/**
	 * ��������
	 */
	private Date createDate;
	
	/**
	 * ��������
	 */
	private Date updateDate;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRuleContent() {
		return ruleContent;
	}

	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
