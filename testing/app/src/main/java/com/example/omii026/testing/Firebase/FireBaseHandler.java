package com.example.omii026.testing.Firebase;

import com.firebase.client.Firebase;

/**
 * Created by Omii026 on 9/3/2015.
 */
public class FireBaseHandler {
    private static Firebase mFirebaseRef;
    private static FireBaseHandler ourReference;

    private Firebase userRef;
    private Firebase userChatRef;
    private Firebase conversationRef;
    private Firebase friendshipRef;
    private Firebase groupRef;
    private Firebase userGroupRef;
    private Firebase groupChatRef;



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

    public Firebase getUserRef()
    {
        return userRef = mFirebaseRef.child("Users");
    }
    public Firebase getUserChatRef()
    {
        return userChatRef = mFirebaseRef.child("Users-chat");
    }
    public Firebase getConversationRef() {
        return conversationRef = mFirebaseRef.child("user-conversation");
    }
    public Firebase getFriendshipRef() {
        return friendshipRef = mFirebaseRef.child("friendship");
    }

    public Firebase getGroupRef(){
        return groupRef = mFirebaseRef.child("groups");
    }
    public Firebase getUserGroupRef() {
        return userGroupRef = mFirebaseRef.child("user-groups");
    }
    public Firebase getGroupChatRef(){
        return groupChatRef = mFirebaseRef.child("group-chat");
    }
}
