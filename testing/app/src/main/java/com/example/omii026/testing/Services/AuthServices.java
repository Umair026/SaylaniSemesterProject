package com.example.omii026.testing.Services;

import android.widget.Toast;

import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.MeApp;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by Omii026 on 9/11/2015.
 */
public class AuthServices {

    public static void Login(final String email, final String password, final ServiceListener listener){

        FireBaseHandler.getInstance()
                .getUserRef().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
                String mail = data.get("email").toString();
                String pass = data.get("password").toString();
                if (mail.equals(email) && pass.equals(password)) {
                    String fname = data.get("first-name").toString();
                    String lname = data.get("last-name").toString();
                    User user = new User(fname, lname, mail, dataSnapshot.getKey().toString());
                    MeApp.setUser(user);
//                    Toast.makeText(, "User Set", Toast.LENGTH_SHORT).show();
                } else {

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        FireBaseHandler.getInstance().getRootFirebaseRef()
                .authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        listener.success(authData);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        listener.error(new ServiceError(firebaseError.getMessage(), firebaseError.toException()));
                    }

                });

    }
}
