package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * �ļ���ʽת������
 * @author huangjinyuan
 *
 */
public class FileExtConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4219410370337518649L;
	/**
	 * ��������
	 */
	private String configId;
	/**
	 * �ļ�����
	 */
	private int fileCount;
	/**
	 * ����ʱ�䣬��λ����
	 */
	private long expireTime;
	/**
	 * �ռ��С
	 */
	private long spaceSize;
	
	/**
	 * ֧��ת��Ϊtxt�ĸ�ʽ
	 */
	private String txtExt;
	
	/**
	 * ֧��ת��Ϊpdf�ĸ�ʽ
	 */
	private String pdfExt;
	
	/**
	 * ֧��ת��Ϊhtml�ĸ�ʽ
	 */
	private String htmlExt;
	
	/**
	 * ֧��ת��Ϊjpg�ĸ�ʽ
	 */
	private String jpgExt;
	
	/**
	 * ֧��ת��Ϊword�ĸ�ʽ
	 */
	private String wordExt;
	
	/**
	 * ״̬
	 */
	private int status;
	
	/**
	 * ������id
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

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public long getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(long spaceSize) {
		this.spaceSize = spaceSize;
	}


	public String getTxtExt() {
		return txtExt;
	}

	public void setTxtExt(String txtExt) {
		this.txtExt = txtExt;
	}

	public String getPdfExt() {
		return pdfExt;
	}

	public void setPdfExt(String pdfExt) {
		this.pdfExt = pdfExt;
	}

	public String getHtmlExt() {
		return htmlExt;
	}

	public void setHtmlExt(String htmlExt) {
		this.htmlExt = htmlExt;
	}

	public String getJpgExt() {
		return jpgExt;
	}

	public void setJpgExt(String jpgExt) {
		this.jpgExt = jpgExt;
	}

	public String getWordExt() {
		return wordExt;
	}

	public void setWordExt(String wordExt) {
		this.wordExt = wordExt;
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
	
	public int getExpireType() {
		int type = 0;
		if(expireTime == 3600000*6) type=0;
		if(expireTime == 3600000*24) type=1;
		if(expireTime == 3600000*24*3) type=2;
		if(expireTime == 3600000*24*7) type=3;
		if(expireTime == 3600000*24*30) type=4;
		return type;
		
	}
	
}
