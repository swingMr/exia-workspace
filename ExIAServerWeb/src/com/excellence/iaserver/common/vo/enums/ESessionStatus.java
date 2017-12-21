package com.excellence.iaserver.common.vo.enums;
/**
 * 对话状态枚举类
 * 
 * @author wangjg
 * 
 */
public enum ESessionStatus {
	enable("未启用",0),normal("正常",1),stop("已关闭",99);
	
	private String name;
	private int index;
	
	private ESessionStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getName(int index) {
		for(ESessionStatus status:ESessionStatus.values()) {
			if(status.getIndex() == index) {
				return status.getName();
			}
		}
		return null;
	}
}
