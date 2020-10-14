package net.springBootAuthentication.springBootAuthentication.model;

import java.util.ArrayList;

public class Response {
	private int status;
	private String message;
	private ArrayList<?> data;
	
	public Response(int status, String message, ArrayList<?> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public Response(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<?> getData() {
		return data;
	}
	public void setData(ArrayList<?> data) {
		this.data = data;
	}
}
