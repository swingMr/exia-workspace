package com.excellence.iamp.mongodb.vo;

import java.util.List;

public class SettingAttribute {
	//限定类型   // no 不限定  clazz 限定类   concrete限定实例
	String type;
	//监控设置下的相关属性；
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
