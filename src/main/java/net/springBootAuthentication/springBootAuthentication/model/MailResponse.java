package net.springBootAuthentication.springBootAuthentication.model;

public class MailResponse {
    private String message;
    private Boolean status;

    public MailResponse() {
    }

    public MailResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
