package com.example.omii026.testing.Services;

/**
 * Created by Omii026 on 9/4/2015.
 */
public class ServiceError {
    public Throwable erroeObject;
    public String message;

    public ServiceError(){
        message = "";
    }

    public ServiceError(String message){
        this.message = message;
    }
    public ServiceError(String message,Throwable erroeObject){
        this.message = message;
        this.erroeObject = erroeObject;
    }

    public String getMessage(){
        return message;
    }

    public Throwable getErroeObject(){
        return erroeObject;
    }




}
