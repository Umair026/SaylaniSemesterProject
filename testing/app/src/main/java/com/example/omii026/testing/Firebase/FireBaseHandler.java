package com.example.omii026.testing.Firebase;

import android.util.Log;

import com.firebase.client.Firebase;

/**
 * Created by Omii026 on 9/3/2015.
 */
public class FireBaseHandler {
    private static Firebase mFirebaseRef;
    private static FireBaseHandler ourReference;

    private Firebase userRef;


    public static FireBaseHandler getInstance(){
        if(ourReference == null){
            Log.d("test","getInstance");
            ourReference = new FireBaseHandler();
        }
        return ourReference;
    }

    public FireBaseHandler(){
        Log.d("test","ref_get");
//        mFirebaseRef = new Firebase(Configuration.FIREBASE_URL);
        mFirebaseRef = new Firebase("https://tvv.firebaseio.com/");
    }

    public Firebase getFirebaseRef(String child){
        return mFirebaseRef.child(child);
    }
    public Firebase getRootFirebaseRef(){
        Log.d("test","getRootFirebaseRef");
        return mFirebaseRef;
    }

    ///

    public Firebase getUserRef(){
        return userRef = mFirebaseRef.child("Users");
    }


}
