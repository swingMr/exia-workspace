package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

public class PythonType  {
	
	
	/**
	 * ����
	 */
	private String typeId;
	
	/**
	 * ��������
	 */
	private String typeName;
	
	/**
	 * ״̬ -1 ɾ�� 1����
	 */
	private int status;
	
	/**
	 * ��������
	 */
	private Date createDate;
	
	/**
	 * ������id
	 */
	private String creatorId;
	
	public Date getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}

	private Date UpdateDate;
	/**
	 * ����������
	 */
	private String creatorName;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
	
	
	
}
