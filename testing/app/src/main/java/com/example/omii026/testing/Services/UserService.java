package com.example.omii026.testing.Services;

import android.util.Log;

import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

/**
 * Created by Omii026 on 9/4/2015.
 */
public class UserService {

    public static final String TAG = "UserServices";

    public static void addUser(User user, final ServiceListener listener){
        HashMap<String,Object> userObject = new HashMap<>();
        userObject.put("first-name",user.getF_name());
        userObject.put("last-name",user.getL_name());
        userObject.put("profile-image","");
        userObject.put("online-status","");
        userObject.put("email",user.getEmail());
        userObject.put("password",user.getPassword());
        userObject.put("timestamp", System.currentTimeMillis());

        FireBaseHandler.getInstance()
                .getUserRef().child(user.getUserId())
                .setValue(userObject, getCompletionListener(listener, new Callback() {
                    @Override
                    public void callback() {
                        Log.d(TAG, "calback");
                        listener.success(null);
                    }
                }));

    }

    public static Firebase.CompletionListener getCompletionListener(final ServiceListener listener, final Callback callback){
        return new Firebase.CompletionListener(){

            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Log.d(TAG, "onComplete");

            if(firebaseError != null){
                Log.d(TAG, "firebaseError");
                listener.error(new ServiceError(firebaseError.getMessage(),firebaseError.toException()));
            }else{
                callback.callback();
            }
            }
        };

        }

    public static void createChat(String message,String from,String mPrams){

        HashMap<String,Object> chatMap = new HashMap<>();
        chatMap.put("message",message);
        chatMap.put("from",from);
        chatMap.put("time",System.currentTimeMillis());
//
//        Firebase ref = FireBaseHandler.getInstance()
//                      .getConversationRef().push();
//
//                       ref.push().setValue(chatMap);
////                .child("messages").push().setValue(chatMap);


    }


    }


