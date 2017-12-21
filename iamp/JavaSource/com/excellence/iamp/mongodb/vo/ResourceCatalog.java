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
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 目录编号
	 */
	private String catelogNum;
	
	/**
	 * 目录名称
	 */
	private String catelogName;
	
	/**
	 * 资源库编号
	 */
	private String libNum;
	
	/**
	 * 父目录id
	 */
	private String parentId;
	
	/**
	 * 父目录编号
	 */
	private String parentNum;
	
	/**
	 * 创建者id
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
	
	/**
	 * 状态 -1删除 1正常
	 */
	private int status;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 显示顺序，默认升序
	 */
	private int displayOrder;
	
	/**
	 * 显示模式 0列表 1卡片
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
