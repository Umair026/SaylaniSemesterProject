package com.example.omii026.testing.SupportClasses;

/**
 * Created by Omii026 on 9/11/2015.
 */
public class ChatData {
   private String msg;
   private String senderId;
   public ChatData(String senderId,String msg){
        this.msg = msg;
        this.senderId = senderId;
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
}
