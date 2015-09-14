package com.example.omii026.testing.Fragments;


import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.MeApp;
import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.UserData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    private ListView listView;
    private ArrayList<UserData> frndList = new ArrayList<>();
    private FriendsListAdapter friendsListAdapter;

    private OnFragmentInteractionListener mListener;
    public static ArrayList<ChatRef> chatRefDataList = new ArrayList<>();


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
        listView = (ListView) view.findViewById(R.id.frndChatList);

        friendsListAdapter = new FriendsListAdapter(getActivity().getApplicationContext(),frndList);
        listView.setAdapter(friendsListAdapter);

        FireBaseHandler.getInstance().getFriendshipRef().child(MeApp.getAppUser().getUserId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String userId = dataSnapshot.getKey();
                Map<String, Object> userData = (Map<String, Object>) dataSnapshot.getValue();
                String userImage = userData.get("profile-image").toString();
                UserData data = new UserData(userId, userImage);
                frndList.add(data);
                friendsListAdapter.notifyDataSetChanged();
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

        FireBaseHandler.getInstance().getUserChatRef().child(MeApp.getAppUser().getUserId())
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String chatKey = dataSnapshot.getKey();
                        HashMap<String,Object> ChatRefvalue = (HashMap<String, Object>) dataSnapshot.getValue();

                        String chatKeydata = (String) ChatRefvalue.get(chatKey);
                        ChatRef chatref = new ChatRef(chatKey,chatKeydata);
                        chatRefDataList.add(chatref);
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
        friendsListAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserData data = (UserData) adapterView.getItemAtPosition(i);
                Toast.makeText(getActivity().getApplicationContext(),""+data.getUserId(),Toast.LENGTH_SHORT).show();

                mListener = (OnFragmentInteractionListener) getActivity();
                mListener.OpenChatFragment(data.getUserId());
            }
        });
//         friendsListAdapter = new FriendsListAdapter(getActivity().getApplicationContext(),frndList);
//        listView.setAdapter(friendsListAdapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    return view;
    }
    public interface OnFragmentInteractionListener{
        void OpenChatFragment(String data);
    }
class ChatRef{
    private String ref;
    private String key;

    public ChatRef(String key,String ref){
        this.key = key;
        this.ref = ref;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}

}
