package com.example.omii026.testing.SupportClasses;

/**
 * Created by Omii026 on 9/10/2015.
 */
public class UserData{
    String image;
    String userId;

    public UserData(String userId,String image){
        this.image = image;
        this.userId = userId;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
