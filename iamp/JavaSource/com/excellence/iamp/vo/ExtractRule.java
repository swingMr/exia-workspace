package com.excellence.iamp.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 抽取规则
 * @author liup
 *
 */
public class ExtractRule implements Serializable {
	
	private static final long serialVersionUID = -4292223495525501405L;
	
	/**
	 * 主键
	 */
	private String ruleId;
	
	/**
	 * 体裁
	 */
	private String genre;
	
	/**
	 * 规则内容
	 */
	private String ruleContent;
	
	/**
	 * 文件格式
	 */
	private String fileType;
	
	/**
	 * 状态
	 */
	private int status;
	
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
