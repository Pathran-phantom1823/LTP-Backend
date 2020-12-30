package net.springBootAuthentication.springBootAuthentication.customModel;

public class IntegerRequestModel {
	private int num;
	
	public IntegerRequestModel() {}
	
	public IntegerRequestModel(int num) {
		this.num = num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return this.num;
	}
}
