package net.springBootAuthentication.springBootAuthentication.customModel;

public class PaymentDetailsRequestModel {
	private int translator;
	private int job;
	
	public PaymentDetailsRequestModel(int translator, int job) {
		super();
		this.translator = translator;
		this.job = job;
	}
	
	public PaymentDetailsRequestModel() {
		super();
	}
	
	public int getTranslator() {
		return translator;
	}
	public void setTranslator(int translator) {
		this.translator = translator;
	}
	public int getJob() {
		return job;
	}
	public void setJob(int job) {
		this.job = job;
	}
	
}
