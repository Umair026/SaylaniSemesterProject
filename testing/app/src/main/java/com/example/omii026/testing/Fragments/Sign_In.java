package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omii026.testing.Activities.HomeActivity;
import com.example.omii026.testing.Activities.MainActivity;
import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.MeApp;
import com.example.omii026.testing.R;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class Sign_In extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText loginEmail, loginPassword;

    private OnFragmentInteractionListener mListener;
    private View view;
    private ProgressDialog progressDialog1;
    private String l_Email,l_Password;
    private User user;

    // TODO: Rename and change types and number of parameters
    public static Sign_In newInstance(String param1, String param2) {
        Sign_In fragment = new Sign_In();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Sign_In() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign__in, container, false);

        mListener = (OnFragmentInteractionListener) getActivity();
        mListener.changeImage1();


//        Bitmap bmp =  BitmapFactory.decodeResource(getResources(), R.drawable.one_football_app_icon);//your image
//        Log.d("befoer conversion:", "" + R.drawable.one_football_app_icon);
//        Log.d("convert to bitmap:",""+bmp);
//        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
//        bmp.recycle();
//        byte[] byteArray = bYtE.toByteArray();
//        String imageFile = Base64.encodeToString(byteArray, Base64.URL_SAFE);
//        Log.d("convert to base64:",""+imageFile);


        loginEmail = (EditText) view.findViewById(R.id.loginEmail);
        loginPassword = (EditText) view.findViewById(R.id.LoginPassword);

        loginEmail.setText("uumair99@hotmail.com");
        loginPassword.setText("123");

        ((Button) view.findViewById(R.id.signin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog1 = ProgressDialog.show(getActivity(), "Loging in", "Loading..", true, false);

                l_Email = loginEmail.getText().toString();
                l_Password = loginPassword.getText().toString();
                Toast.makeText(getActivity(), "l_Email:" + l_Email, Toast.LENGTH_SHORT).show();

                FireBaseHandler.getInstance().getRootFirebaseRef()
                        .authWithPassword(loginEmail.getText().toString(),
                                loginPassword.getText().toString(), new Firebase.AuthResultHandler() {
                                    @Override
                                    public void onAuthenticated(AuthData authData) {
                                        Toast.makeText(getActivity(), "auth id-> " + authData.getUid(), Toast.LENGTH_SHORT).show();
//
                                        FireBaseHandler.getInstance()
                                                .getUserRef().addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
                                                String mail = data.get("email").toString();
                                                String pass = data.get("password").toString();
                                                if (mail.equals(l_Email) && pass.equals(l_Password)) {
                                                    String fname = data.get("first-name").toString();
                                                    String lname = data.get("last-name").toString();
                                                    user = new User(fname, lname, mail, dataSnapshot.getKey().toString());
                                                    MeApp.setUser(user);
                                                    Toast.makeText(getActivity(),"User Set",Toast.LENGTH_SHORT).show();
                                                    progressDialog1.dismiss();
                                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                    startActivity(intent);
                                                    getActivity().finish();
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

//                                        Intent intent = new Intent(getActivity(), HomeActivity.class);
//                                        startActivity(intent);
//
                                    }

                                    @Override
                                    public void onAuthenticationError(FirebaseError firebaseError) {
                                        Toast.makeText(getActivity(), "Autherror->" + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog1.dismiss();
                                    }
                                });

            }
        });
//        Intent intent = new Intent(getActivity(), HomeActivity.class);
//        startActivity(intent);

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        void changeImage1();
    }

}
