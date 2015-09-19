package com.example.omii026.testing.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.omii026.testing.Activities.HomeActivity;
import com.example.omii026.testing.Activities.NavigationDrawerFragment;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.MeApp;
import com.example.omii026.testing.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link com.example.omii026.testing.Fragments.Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String Item;
    private View rootView;
    private TextView UserName;

    private ImageView imageView;
    private DrawerLayout mDrawerLayout;
    private ListView listView;
    private ImageView groupIcon;
    private onFragmentInteractionListener mListener;


    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Home() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d("umair onCreate","Umair G");
            Item = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//            Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.image1);
//            ByteArrayOutputStream bYte = new ByteArrayOutputStream();
//            bmp.compress(Bitmap.CompressFormat.PNG,100,bYte);
//            byte[] byteArray = bYte.toByteArray();
//            final String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);
//            FireBaseHandler.getInstance().getUserRef().child(MeApp.getAppUser().getUserId()).child("profile-image").setValue(imageFile);
         }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("umair","Umair G");
        rootView =  inflater.inflate(R.layout.fragment_fragment_home, container, false);
        ((Button) rootView.findViewById(R.id.dropbox)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener = (onFragmentInteractionListener) getActivity();
                mListener.OpenDropox();
            }
        });

//        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.ic_gallery);
//        ByteArrayOutputStream bYte = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG,100,bYte);
//        byte[] byteArray = bYte.toByteArray();
//        final String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);
//        Log.d("umair",""+imageFile);
//        FireBaseHandler.getInstance().getUserRef().child(MeApp.getAppUser().getUserId()).child("profile-image").setValue(imageFile);

        imageView = (ImageView) rootView.findViewById(R.id.ic_nav);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationDrawerFragment.mDrawerLayout.openDrawer(NavigationDrawerFragment.rootView);
            }
        });
//        ((Button) rootView.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FireBaseHandler.getInstance().getUserRef().child(MeApp.getAppUser().getUserId()).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//
//                    }
//                });
//            }
//        });

        groupIcon = (ImageView) rootView.findViewById(R.id.ic_user);
        groupIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener = (onFragmentInteractionListener) getActivity();
                mListener.UserFragment();
            }
        });

        return rootView;
    }

    public interface onFragmentInteractionListener{
        void UserFragment();
        void OpenDropox();
    }

}
