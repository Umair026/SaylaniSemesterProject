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
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ImageButton btn1,btn2,btnFab;
    TextView tv1,tv2;
    private onFragmentInteractionListener mListener;
    private Animation menuAnimation;


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
            Log.d("onCreate","Home");
            Item = getArguments().getString(ARG_PARAM1);
         }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("onCreateView","Home");
        rootView =  inflater.inflate(R.layout.fragment_fragment_home, container, false);


//        Bitmap bmp =  BitmapFactory.decodeResource(getResources(), R.drawable.listimage1);//your image
//        String btmapString = ""+bmp;
//        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
//        bmp.recycle();
//        byte[] byteArray = bYtE.toByteArray();
//        String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);
//        Log.d("base64",""+imageFile);
//               try {
//                   FireBaseHandler.getInstance().getUserRef().child(MeApp.getAppUser().getUserId()).child("profile-image").setValue(imageFile);
//
//               }catch (Exception e){
//                   e.printStackTrace();
//                            }

        btn1 = (ImageButton) rootView.findViewById(R.id.dropbox);
        btn2 = (ImageButton) rootView.findViewById(R.id.facebook);

        btnFab = (ImageButton) rootView.findViewById(R.id.btnFab);

        tv1 = (TextView) rootView.findViewById(R.id.text1);
        tv2 = (TextView) rootView.findViewById(R.id.text2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "btn1 click", Toast.LENGTH_SHORT).show();
                animations();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "btn2 click", Toast.LENGTH_SHORT).show();
                animations();
            }
        });
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "feb click", Toast.LENGTH_SHORT).show();
                animations();
            }
        });
        ((ImageButton) rootView.findViewById(R.id.dropbox)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener = (onFragmentInteractionListener) getActivity();
                mListener.OpenDropox();
            }
        });

        ((ImageButton) rootView.findViewById(R.id.facebook)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"TODO",Toast.LENGTH_SHORT).show();
            }
        });

//        imageView = (ImageView) rootView.findViewById(R.id.ic_nav);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavigationDrawerFragment.mDrawerLayout.openDrawer(NavigationDrawerFragment.rootView);
//            }
//        });

//        groupIcon = (ImageView) rootView.findViewById(R.id.ic_user);
//        groupIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener = (onFragmentInteractionListener) getActivity();
//                mListener.UserFragment();
//            }
//        });

        return rootView;
    }
    public void animations() {
        if (btn1.getVisibility() == View.VISIBLE) {
            AnimationSet animationSet = new AnimationSet(false);
            Animation slide_out = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_out_bottom);
            menuAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_out);
            slide_out.setDuration(500);
            animationSet.addAnimation(menuAnimation);
            animationSet.addAnimation(slide_out);

            btn1.startAnimation(animationSet);
            btn2.startAnimation(animationSet);
            tv1.startAnimation(animationSet);
            tv2.startAnimation(animationSet);

            animationSet.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {
            AnimationSet animationSet = new AnimationSet(false);
            menuAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_scale_up);
            Animation slide_in = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_in_bottom);
            slide_in.setDuration(200);
            Animation fade_in = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in);

            animationSet.addAnimation(menuAnimation);
            animationSet.addAnimation(slide_in);
            animationSet.addAnimation(fade_in);

            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);

            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);

            btn1.startAnimation(animationSet);
            btn2.startAnimation(animationSet);

            tv1.startAnimation(animationSet);
            tv2.startAnimation(animationSet);
        }
    }
    public interface onFragmentInteractionListener{
        void UserFragment();
        void OpenDropox();
    }



}
