package com.excellence.iamp.vo.enums;
/**
 * 状态枚举类
 * 
 * @author huangjinyuan
 * @created 2017-2-20
 * @copyright Excellence co.
 */
public enum EStatus {
	delete("删除",-1),draft("草稿",0),normal("正常",1),closed("关闭", 99);
	
	private String name;
	private int index;
	
	private EStatus(String name, int index) {
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
		for(EStatus status:EStatus.values()) {
			if(status.getIndex() == index) {
				return status.getName();
			}
		}
		return null;
	}
}
