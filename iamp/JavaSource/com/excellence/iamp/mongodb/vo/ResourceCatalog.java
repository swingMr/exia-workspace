package com.excellence.iamp.mongodb.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ia_resource_catalog")
public class ResourceCatalog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5087746710403905044L;
	
	/**
	 * ����
	 */
	@Id
	private String id;
	
	/**
	 * Ŀ¼���
	 */
	private String catelogNum;
	
	/**
	 * Ŀ¼����
	 */
	private String catelogName;
	
	/**
	 * ��Դ����
	 */
	private String libNum;
	
	/**
	 * ��Ŀ¼id
	 */
	private String parentId;
	
	/**
	 * ��Ŀ¼���
	 */
	private String parentNum;
	
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
	
	/**
	 * ״̬ -1ɾ�� 1����
	 */
	private int status;
	
	/**
	 * ��ע
	 */
	private String remark;
	
	/**
	 * ��ʾ˳��Ĭ������
	 */
	private int displayOrder;
	
	/**
	 * ��ʾģʽ 0�б� 1��Ƭ
	 */
	private int displayMode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCatelogNum() {
		return catelogNum;
	}

	public void setCatelogNum(String catelogNum) {
		this.catelogNum = catelogNum;
	}

	public String getCatelogName() {
		return catelogName;
	}

	public void setCatelogName(String catelogName) {
		this.catelogName = catelogName;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public int getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(int displayMode) {
		this.displayMode = displayMode;
	}

	public String getLibNum() {
		return libNum;
	}

	public void setLibNum(String libNum) {
		this.libNum = libNum;
	}

	public String getParentNum() {
		return parentNum;
	}

	public void setParentNum(String parentNum) {
		this.parentNum = parentNum;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
}
