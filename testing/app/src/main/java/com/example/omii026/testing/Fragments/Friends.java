package com.example.omii026.testing.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omii026.testing.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link com.example.omii026.testing.Fragments.Friends#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Friends extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String Item;
    private String mParam2;
    private View view;
    private ImageView imageView;


    // TODO: Rename and change types and number of parameters
    public static Friends newInstance(String param1) {
        Friends fragment = new Friends();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    public Friends() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Item = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_friends, container, false);
        ( (TextView)view.findViewById(R.id.friendText)).setText(Item);
        imageView = (ImageView) view.findViewById(R.id.ic_back_friends);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    return view;}


}
