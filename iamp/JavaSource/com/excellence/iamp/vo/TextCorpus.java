package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * �ı�Ԥ��
 * @author huangjinyuan
 *
 */
public class TextCorpus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -108743322702587071L;

	/**
	 * Ψһ��ʶ
	 */
	private String corpusId;
	
	/**
	 * ���ϱ���
	 */
	private String corpusTitle;
	
	/**
	 * ��������
	 */
	private String belongDomain;
	
	/**
	 * ����������
	 */
	private String creatorName;
	
	/**
	 * ������id
	 */
	private String creatorId;
	
	/**
	 * ��������
	 */
	private Date createDate;
	
	/**
	 * ��������
	 */
	private Date updateDate;
	/**
	 * �����
	 */
	private String keyWord;
	/**
	 * �ļ�·��
	 */
	private String accessPath;
	/**
	 * ״̬ -1ɾ�� 1����
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
