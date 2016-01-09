package com.example.omii026.testing.Class;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.omii026.testing.MeApp;

/**
 * Created by Omii026 on 9/3/2015.
 */
public class User {

    private String f_name;
    private String userId;
    private String l_name;
    private String email;
    private Bitmap profileImage;
    private String password;
    private String key;
    private boolean online_status;

    public User(String f_name, String l_name,String userId, String email, String image){
        this.f_name = f_name;
        this.l_name = l_name;
        this.userId = userId;
        this.email = email;
        byte[] imageEncode = Base64.decode(image,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageEncode,0,imageEncode.length);

        this.profileImage = bitmap;
    }
    public User(String f_name, String l_name, String email, Bitmap image, String password){
        this.f_name = f_name;
        this.l_name = l_name;
        this.userId = userId;
        this.profileImage = image;
        this.email = email;
//        this.profileImage = profileImage;
        this.password = password;
    }

    public User(String f_name, String l_name, String email,String userId){
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }




    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public static void setProfileImage(Bitmap profileImage) {
        profileImage = profileImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline_status() {
        return online_status;
    }

    public void setOnline_status(boolean online_status) {
        this.online_status = online_status;
    }
}
