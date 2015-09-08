package com.example.omii026.testing.Firebase;

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
            ourReference = new FireBaseHandler();
        }
        return ourReference;
    }

    public FireBaseHandler(){
//        mFirebaseRef = new Firebase(Configuration.FIREBASE_URL);
        mFirebaseRef = new Firebase("https://tvv.firebaseio.com/");
    }

    public Firebase getFirebaseRef(String child){
        return mFirebaseRef.child(child);
    }
    public Firebase getRootFirebaseRef(){
        return mFirebaseRef;
    }

    ///

    public Firebase getUserRef(){
        return userRef = mFirebaseRef.child("Users");
    }


}
