package com.excellence.iamp.vo.enums;
/**
 * ״̬ö����
 * 
 * @author huangjinyuan
 * @created 2017-2-20
 * @copyright Excellence co.
 */
public enum EStatus {
	delete("ɾ��",-1),draft("�ݸ�",0),normal("����",1),closed("�ر�", 99);
	
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
