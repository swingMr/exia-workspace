package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件
 * @author huangjinyuan
 *
 */
public class IFile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1516640262166180949L;
	
	/**
	 * 文件id
	 */
	private String fileId;
	
	/**
	 * 文件后缀
	 */
	private String fileExt;
	
	/**
	 * 文件名
	 */
	private String fileName;
	
	/**
	 * 文件路径
	 */
	private String accessPath;
	
	/**
	 * 是否临时文件
	 * 1 临时文件   0 非临时文件
	 */
	private int isTemp;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 过期时间
	 */
	private Date expireDate;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 创建人Id
	 */
	private String creatorId;
	
	/**
	 * 创建人名字
	 */
	private String creatorName;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAccessPath() {
		return accessPath;
	}

	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}

	public int getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(int isTemp) {
		this.isTemp = isTemp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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
	
	
}
