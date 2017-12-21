package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * �ļ�
 * @author huangjinyuan
 *
 */
public class IFile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1516640262166180949L;
	
	/**
	 * �ļ�id
	 */
	private String fileId;
	
	/**
	 * �ļ���׺
	 */
	private String fileExt;
	
	/**
	 * �ļ���
	 */
	private String fileName;
	
	/**
	 * �ļ�·��
	 */
	private String accessPath;
	
	/**
	 * �Ƿ���ʱ�ļ�
	 * 1 ��ʱ�ļ�   0 ����ʱ�ļ�
	 */
	private int isTemp;
	
	/**
	 * ״̬
	 */
	private int status;
	
	/**
	 * ����ʱ��
	 */
	private Date expireDate;
	
	/**
	 * ����ʱ��
	 */
	private Date createDate;
	
	/**
	 * ����ʱ��
	 */
	private Date updateDate;
	
	/**
	 * ������Id
	 */
	private String creatorId;
	
	/**
	 * ����������
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
