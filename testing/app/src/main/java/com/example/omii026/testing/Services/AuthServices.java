package com.example.omii026.testing.Services;

import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Omii026 on 9/11/2015.
 */
public class AuthServices {

    public static void Login(String email,String password){

        FireBaseHandler.getInstance().getRootFirebaseRef()
                .authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                    }

                });

    }
}
