package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.R;
import com.example.omii026.testing.Services.ServiceError;
import com.example.omii026.testing.Services.ServiceListener;
import com.example.omii026.testing.Services.UserService;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Sign_up.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Sign_up#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sign_up extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText f_Name, l_Name, signupUid, signupEmail, signupPassword;
    private String fName,lName,S_Password, S_Uid, S_Email;
    private User user;
    private ProgressDialog progressDialog;


    private OnFragmentInteractionListener mListener;
    private View view;


    // TODO: Rename and change types and number of parameters
    public static Sign_up newInstance(String param1, String param2) {
        Sign_up fragment = new Sign_up();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Sign_up() {
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
        view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        f_Name = (EditText) view.findViewById(R.id.f_name);
        l_Name = (EditText) view.findViewById(R.id.l_name);
        signupUid= (EditText) view.findViewById(R.id.signupUid);
        signupEmail = (EditText) view.findViewById(R.id.signupEmail);
        signupPassword = (EditText) view.findViewById(R.id.signupPassword);

        ((Button) view.findViewById(R.id.create)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName = f_Name.getText().toString();
                lName = l_Name.getText().toString();
                S_Uid = signupUid.getText().toString();
                S_Email = signupEmail.getText().toString();
                S_Password = signupPassword.getText().toString();

                progressDialog =  ProgressDialog.show(getActivity(),"Creating User","Loading..",true,false);

                //creating user using firebase authentication services    "createUser"
                FireBaseHandler.getInstance().getRootFirebaseRef()
                        .createUser(signupEmail.getText().toString(),
                                signupPassword.getText().toString(),
                                new Firebase.ValueResultHandler<Map<String, Object>>() {
                                    @Override
                                    public void onSuccess(Map<String, Object> stringObjectMap) {
                                        Toast.makeText(getActivity(), "userCreated:", Toast.LENGTH_SHORT).show();
//                                        progressDialog.dismiss();
                                        User user = new User(fName, lName,S_Uid, S_Email, S_Password);


                                        // creating user data to firebase node
                                        UserService.addUser(user, new ServiceListener() {
                                            @Override
                                            public void success(Object object) {
                                                Log.d("ServiceListenerObject:", "" + object);
                                                Toast.makeText(getActivity(), "created in firebase:", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
//                                                signInLayout.setVisibility(View.VISIBLE);
//                                                signupLayout.setVisibility(View.GONE);
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
                                        Toast.makeText(getActivity(), "creation Error->" + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                });

            }
        });


        return inflater.inflate(R.layout.fragment_sign_up, container, false);
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
    }

}
