package com.chatapp.chatappbackend.entity;

public class ChatMessage {

    private Integer senderId;
    private Integer receiverId;
    private String sender;
    private String content;

    public ChatMessage() {
    }

    public ChatMessage(Integer senderId, Integer receiverId, String sender, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.sender = sender;
        this.content = content;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}