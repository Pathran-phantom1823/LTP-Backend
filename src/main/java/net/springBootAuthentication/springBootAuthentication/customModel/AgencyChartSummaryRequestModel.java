package net.springBootAuthentication.springBootAuthentication.customModel;

public class AgencyChartSummaryRequestModel {
	private int month;
	private int year;
	private int id;
	
	public AgencyChartSummaryRequestModel() {}

	public AgencyChartSummaryRequestModel(int month, int year, int id) {
		super();
		this.month = month;
		this.year = year;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
