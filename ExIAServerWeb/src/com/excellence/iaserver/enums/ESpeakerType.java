package com.excellence.iaserver.enums;

public enum ESpeakerType {

	robot(1),user(2);
	
	private int index;
	
	private ESpeakerType(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
}
