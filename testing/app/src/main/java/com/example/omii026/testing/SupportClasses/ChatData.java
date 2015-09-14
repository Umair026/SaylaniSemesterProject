package com.example.omii026.testing.SupportClasses;

/**
 * Created by Omii026 on 9/11/2015.
 */
public class ChatData {

    //for method 1
   private String msg;
   private String senderId;
   private Long timestamp;
    private String key;

   public ChatData(String senderId,String msg){
        this.msg = msg;
        this.senderId = senderId;
    }

    public ChatData(String message,String senderId,Long timestamp,String key){
        this.msg = message;
        this.senderId = senderId;
        this.key = key;
        this.timestamp = timestamp;
    }

    public ChatData(String message,String senderId,Long timestamp){
        this.msg = message;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    //for method 2
//    private String message;
//    private String from;
//    private String to;
//    private String timestamp;
//
//    public ChatData(String message,String from,String timestamp){
//        this.message = message;
//        this.from = from;
//        this.timestamp = timestamp;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getFrom() {
//        return from;
//    }
//
//    public void setFrom(String from) {
//        this.from = from;
//    }
//
//    public String getTo() {
//        return to;
//    }
//
//    public void setTo(String to) {
//        this.to = to;
//    }
//
//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
}
