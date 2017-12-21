package com.excellence.iamp.mongodb.vo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ia_resource_section")
public class ResourceSection {
	//唯一标识
	@Id
	private String id;
	//资源库编号
	private String libNum;
	//资源标识
	private String resourceSign;
	//资源类型
	private String resourceType;
	//原文标题
	private String resTile;
	//段落主题
	private String sectionTheme;
	//段落内容
	private String sectionContent;
	//标注时间
	private Date createDate;
	//会员Id
	private String creatorId;
	//会员姓名
	private String creatorName;
	//主题词
	private String subWords;
	//显示顺序
	private int displayOrder;
	//状态
	private int status;
	//html内容
	private String sectionHtml;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLibNum() {
		return libNum;
	}
	public void setLibNum(String libNum) {
		this.libNum = libNum;
	}
	public String getResourceSign() {
		return resourceSign;
	}
	public void setResourceSign(String resourceSign) {
		this.resourceSign = resourceSign;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getResTile() {
		return resTile;
	}
	public void setResTile(String resTile) {
		this.resTile = resTile;
	}
	public String getSectionTheme() {
		return sectionTheme;
	}
	public void setSectionTheme(String sectionTheme) {
		this.sectionTheme = sectionTheme;
	}
	public String getSectionContent() {
		return sectionContent;
	}
	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
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
	public String getSubWords() {
		return subWords;
	}
	public void setSubWords(String subWords) {
		this.subWords = subWords;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSectionHtml() {
		return sectionHtml;
	}
	public void setSectionHtml(String sectionHtml) {
		this.sectionHtml = sectionHtml;
	}

	
}
