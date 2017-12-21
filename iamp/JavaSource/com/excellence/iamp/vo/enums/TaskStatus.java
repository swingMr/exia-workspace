package com.excellence.iamp.vo.enums;
/**
 * ״̬ö����
 * 
 * @author lih
 * 
 */
public enum TaskStatus {
	stop("ֹͣ",0),delete("��ֹ",-1),run("����",1),pause("��ͣ",2);
	
	private String name;
	private int index;
	
	private TaskStatus(String name, int index) {
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
		for(TaskStatus status:TaskStatus.values()) {
			if(status.getIndex() == index) {
				return status.getName();
			}
		}
		return null;
	}
}
