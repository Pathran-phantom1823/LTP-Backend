package net.springBootAuthentication.springBootAuthentication.model;

public class MessageModel {
    private String message;
    private Long sentbyId;
    private Long toId;

    public MessageModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSentbyId() {
        return sentbyId;
    }

    public void setSentbyId(Long sentbyId) {
        this.sentbyId = sentbyId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    @Override
    public String toString() {
        return "MessageModel [message=" + message + ", sentbyId=" + sentbyId + ", toId=" + toId + "]";
    }
}
