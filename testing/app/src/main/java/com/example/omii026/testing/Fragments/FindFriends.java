package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.UserData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.omii026.testing.Fragments.FindFriends.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.example.omii026.testing.Fragments.FindFriends#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindFriends extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String Item;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private static final String TAG = "FindFriend";
    private ArrayList<UserData> dataList = new ArrayList<>();
    private ListView listView;
    private ImageView imageView;
    private SearchView searchView;
    private FindFriendListAdapter findFriendListAdapter;

    // TODO: Rename and change types and number of parameters
    public static FindFriends newInstance(String param1) {
        FindFriends fragment = new FindFriends();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FindFriends() {
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
          view =  inflater.inflate(R.layout.fragment_find_friends, container, false);
         listView = (ListView) view.findViewById(R.id.Umair);
        searchView = (SearchView) view.findViewById(R.id.searchFriends);

        FireBaseHandler.getInstance().getUserRef().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Map<String,Object> userData = (Map<String, Object>) dataSnapshot.getValue();
                String userId = userData.get("profile-image").toString();
                UserData data = new UserData(key,userId);
//                dataList.add(data);
               findFriendListAdapter.add(data);
                findFriendListAdapter.notifyDataSetChanged();
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
 findFriendListAdapter =new FindFriendListAdapter(getActivity().getApplicationContext(),new ArrayList<UserData>());
        listView.setAdapter(findFriendListAdapter);
        findFriendListAdapter.notifyDataSetChanged();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Toast.makeText(getActivity(),"change",Toast.LENGTH_SHORT).show();

                findFriendListAdapter.filterFriends(newText.toString().toLowerCase());

                return false;
            }
        });


    return view;
    }

//    private void findFriend() {
//        FireBaseHandler.getInstance().getUserRef().addChildEventListener( new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.d(TAG, ""+dataSnapshot.getKey());
//                String key = dataSnapshot.getKey();
//                HashMap<String,Object> userData = (HashMap<String, Object>) dataSnapshot.getValue();
//                    String userImage = userData.get("profile-image").toString();
//                UserData data = new UserData(key,userImage);
//                    list.add(data);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
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
