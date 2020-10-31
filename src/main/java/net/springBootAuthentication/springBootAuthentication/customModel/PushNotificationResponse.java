package net.springBootAuthentication.springBootAuthentication.customModel;

public class PushNotificationResponse {
    private Integer status;
    private String message;

    public PushNotificationResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public PushNotificationResponse() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}   
