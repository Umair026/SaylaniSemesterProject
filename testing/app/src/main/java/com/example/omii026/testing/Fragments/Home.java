package com.example.omii026.testing.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.omii026.testing.Activities.HomeActivity;
import com.example.omii026.testing.Activities.NavigationDrawerFragment;
import com.example.omii026.testing.R;


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
            Item = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_fragment_home, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.ic_nav);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationDrawerFragment.mDrawerLayout.openDrawer(NavigationDrawerFragment.mDrawerListView);
            }
        });

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
    }

}
