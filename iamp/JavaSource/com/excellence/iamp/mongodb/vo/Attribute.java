package com.excellence.iamp.mongodb.vo;

import java.io.Serializable;

/**
 * ��Դ����
 * @author huangjinyuan
 *
 */
public class Attribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8565135787040994456L;
	
	/**
	 * ������
	 */
	String attrName;
	
	/**
	 * �������� int,string,longtext,date,array
	 */
	String type;
	
	/**
	 * ����ֵ
	 */
	Object value;
	
	/**
	 * ����������
	 */
	String attrCName;
	
	/**
	 * �Ƿ����
	 */
	Boolean require;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getAttrCName() {
		return attrCName;
	}

	public void setAttrCName(String attrCName) {
		this.attrCName = attrCName;
	}

	public Boolean getRequire() {
		return require;
	}

	public void setRequire(Boolean require) {
		this.require = require;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
	public String getAttrName() {
		return attrName;
	}
}