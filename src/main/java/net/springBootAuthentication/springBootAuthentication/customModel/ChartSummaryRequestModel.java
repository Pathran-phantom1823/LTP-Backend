package net.springBootAuthentication.springBootAuthentication.customModel;

public class ChartSummaryRequestModel {
	private int month;
	private int year;
	
	public ChartSummaryRequestModel(int month, int year) {
		super();
		this.month = month;
		this.year = year;
	}
	
	public ChartSummaryRequestModel() {
		super();
	}
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
