package com.excellence.iamp.mongodb.vo;

import java.util.List;

public class SettingAttribute {
	//�޶�����   // no ���޶�  clazz �޶���   concrete�޶�ʵ��
	String type;
	//��������µ�������ԣ�
	public List<String> getParents() {
		return parents;
	}
	public void setParents(List<String> parents) {
		this.parents = parents;
	}
	public List<String> getConcepts() {
		return concepts;
	}
	public void setConcepts(List<String> concepts) {
		this.concepts = concepts;
	}
	List<String> parents;
	List<String> concepts;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
