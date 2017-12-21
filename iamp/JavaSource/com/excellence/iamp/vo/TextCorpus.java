package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文本预料
 * @author huangjinyuan
 *
 */
public class TextCorpus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -108743322702587071L;

	/**
	 * 唯一标识
	 */
	private String corpusId;
	
	/**
	 * 语料标题
	 */
	private String corpusTitle;
	
	/**
	 * 归属领域
	 */
	private String belongDomain;
	
	/**
	 * 创建人名字
	 */
	private String creatorName;
	
	/**
	 * 创建人id
	 */
	private String creatorId;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 更新日期
	 */
	private Date updateDate;
	/**
	 * 主题词
	 */
	private String keyWord;
	/**
	 * 文件路径
	 */
	private String accessPath;
	/**
	 * 状态 -1删除 1正常
	 */
	private int status;
	public String getCorpusId() {
		return corpusId;
	}
	public void setCorpusId(String corpusId) {
		this.corpusId = corpusId;
	}
	public String getCorpusTitle() {
		return corpusTitle;
	}
	public void setCorpusTitle(String corpusTitle) {
		this.corpusTitle = corpusTitle;
	}
	public String getBelongDomain() {
		return belongDomain;
	}
	public void setBelongDomain(String belongDomain) {
		this.belongDomain = belongDomain;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
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
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getAccessPath() {
		return accessPath;
	}
	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
