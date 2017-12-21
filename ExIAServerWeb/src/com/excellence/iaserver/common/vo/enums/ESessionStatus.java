package com.excellence.iaserver.common.vo.enums;
/**
 * �Ի�״̬ö����
 * 
 * @author wangjg
 * 
 */
public enum ESessionStatus {
	enable("δ����",0),normal("����",1),stop("�ѹر�",99);
	
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
