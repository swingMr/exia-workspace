package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件格式转换配置
 * @author huangjinyuan
 *
 */
public class FileExtConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4219410370337518649L;
	/**
	 * 配置主键
	 */
	private String configId;
	/**
	 * 文件数量
	 */
	private int fileCount;
	/**
	 * 过期时间，单位毫秒
	 */
	private long expireTime;
	/**
	 * 空间大小
	 */
	private long spaceSize;
	
	/**
	 * 支持转换为txt的格式
	 */
	private String txtExt;
	
	/**
	 * 支持转换为pdf的格式
	 */
	private String pdfExt;
	
	/**
	 * 支持转换为html的格式
	 */
	private String htmlExt;
	
	/**
	 * 支持转换为jpg的格式
	 */
	private String jpgExt;
	
	/**
	 * 支持转换为word的格式
	 */
	private String wordExt;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 创建人id
	 */
	private String creatorId;
	
	/**
	 * 创建人名字
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
