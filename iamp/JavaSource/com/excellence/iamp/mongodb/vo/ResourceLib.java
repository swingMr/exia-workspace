package com.excellence.iamp.mongodb.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "ia_resource_lib")
public class ResourceLib {
	/*��Դ��id*/
	@Id
	private String id;
	/*��Դ����*/
	private String libNum;
	/*��Դ������*/
	private String libName;
/*	1 ���� 2 ��չ 3 �ⲿ����*/
	private int type;
	/*������*/
	private String adapter;
/*	1 �ĵ� 2 ͼƬ 3 ��Ƶ 4 ��Ƶ 5 ��ͼ 6 ���� 7 ����*/
	private int resType;
	/*��������*/
	private String collectionName;
/*	Ĭ����������*/
	private int displayOrder;
	/*��ע*/
	private String remark;
	/*������id*/
	private String creatorId;
	/*����������*/
	private String creatorName;
	private Date createDate;
	private Date updateDate;
	private long count;
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

/*	json���飬����ÿ�������
	attrName ������
	attrCName ����������
	require �Ƿ���� true/false
	type ���ͣ�������int/string/longText/date/array
	value ֵ*/
	private List<Attribute> extendAttrs;
	/*	1 ���� -1 ɾ��*/
	private int status;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
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
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	public String getAdapter() {
		return adapter;
	}
	public void setAdapter(String adapter) {
		this.adapter = adapter;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public List<Attribute> getExtendAttrs() {
		return extendAttrs;
	}
	public void setExtendAttrs(List<Attribute> extendAttrs) {
		this.extendAttrs = extendAttrs;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getResType() {
		return resType;
	}

	public void setResType(int resType) {
		this.resType = resType;
	}

	public void addAttribute(Attribute attribute) {
		if(extendAttrs!=null && extendAttrs.size() > 0) {
			extendAttrs.add(attribute);
		} else {
			extendAttrs = new ArrayList<Attribute>();
			extendAttrs.add(attribute);
		}
	}
	
	public void delAttribute(String attrName) {
		if(extendAttrs!=null && extendAttrs.size() > 0) {
			for (Iterator<Attribute> it = extendAttrs.iterator(); it.hasNext();) {
				Attribute attr = it.next();
				if(attr.getAttrName().equals(attrName)) {
					it.remove(); 
				}
			}
		} 
	}
}
