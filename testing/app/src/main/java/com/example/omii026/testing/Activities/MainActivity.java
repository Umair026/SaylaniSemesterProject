package com.example.omii026.testing.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.R;
import com.example.omii026.testing.Services.*;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;


public class MainActivity extends ActionBarActivity {

    String pushKey;
    Firebase pRef;
    Firebase nPRef;
    int a;
    private ProgressDialog progressDialog;
    private EditText f_Name, l_Name, loginEmail, loginPassword, signupUid, signupEmail, signupPassword;
    Button signin,signup,create;
    private String fName,lName,S_Password, l_Email, l_Password, S_Uid, S_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);    // context defining


//        final Firebase ref = new Firebase("https://tvv.firebaseio.com/");
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.LoginPassword);
        f_Name = (EditText) findViewById(R.id.f_name);
        l_Name = (EditText) findViewById(R.id.l_name);
        signupUid= (EditText) findViewById(R.id.signupUid);
        signupEmail = (EditText) findViewById(R.id.signupEmail);
        signupPassword = (EditText) findViewById(R.id.signupPassword);

        loginEmail.setText("uumair99@hotmail.com");
        loginPassword.setText("123");

        final RelativeLayout signInLayout = (RelativeLayout) findViewById(R.id.layout_login);
        final RelativeLayout signupLayout = (RelativeLayout) findViewById(R.id.layout_signup);

        ((Button) findViewById(R.id.create)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName = f_Name.getText().toString();
                lName = l_Name.getText().toString();
                S_Uid = signupUid.getText().toString();
                S_Email = signupEmail.getText().toString();
                S_Password = signupPassword.getText().toString();

                progressDialog =  ProgressDialog.show(MainActivity.this,"Creating User","Loading..",true,false);

         //creating user using firebase authentication services    "createUser"
                FireBaseHandler.getInstance().getRootFirebaseRef()
                        .createUser(signupEmail.getText().toString(),
                                signupPassword.getText().toString(),
                                new Firebase.ValueResultHandler<Map<String, Object>>() {
                                    @Override
                                    public void onSuccess(Map<String, Object> stringObjectMap) {
                                        Toast.makeText(MainActivity.this, "userCreated:", Toast.LENGTH_SHORT).show();
//                                        progressDialog.dismiss();
                                        User user = new User(fName, lName,S_Uid, S_Email, S_Password);


          // creating user data to firebase node
                                        UserService.addUser(user, new ServiceListener() {
                                            @Override
                                            public void success(Object object) {
                                                Log.d("ServiceListenerObject:", "" + object);
                                                Toast.makeText(MainActivity.this, "created in firebase:", Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();
                                                signInLayout.setVisibility(View.VISIBLE);
                                                signupLayout.setVisibility(View.GONE);
                                            }

                                            @Override
                                            public void error(ServiceError serviceError) {
//                                                Log.d("ServiceListenerObject:", "" + serviceError.getMessage());
                                                progressDialog.dismiss();
                                            }
                                        });
                                    }
                                    @Override
                                    public void onError(FirebaseError firebaseError) {
                                        Toast.makeText(MainActivity.this, "creation Error->" + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                          progressDialog.dismiss();
                                    }
                                });

            }
        });
        ((Button) findViewById(R.id.signin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog =  ProgressDialog.show(MainActivity.this,"Loging in","Loading..",true,false);

                l_Email = loginEmail.getText().toString();
                l_Password = loginPassword.getText().toString();
                Toast.makeText(MainActivity.this,"l_Email:"+l_Email+"l_Password:"+l_Password,Toast.LENGTH_SHORT).show();

                FireBaseHandler.getInstance().getRootFirebaseRef()
                        .authWithPassword(loginEmail.getText().toString(),
                                loginPassword.getText().toString(), new Firebase.AuthResultHandler() {

                                    @Override
                                    public void onAuthenticated(AuthData authData) {
                                        Toast.makeText(MainActivity.this, "auth id-> " + authData.getUid(), Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                        startActivity(intent);
//
                                    }

                                    @Override
                                    public void onAuthenticationError(FirebaseError firebaseError) {
                                        Toast.makeText(MainActivity.this, "Autherror->" + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    }
                                });

            }
        });
        ((Button) findViewById(R.id.signup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signInLayout.setVisibility(View.GONE);
                signupLayout.setVisibility(View.VISIBLE);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
