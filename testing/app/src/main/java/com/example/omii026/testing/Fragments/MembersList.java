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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MembersList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembersList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembersList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private FriendsListAdapter friendsListAdapter;
    private ArrayList<UserData> frndList = new ArrayList<>();
    private ListView listView;
    private Button done;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MembersList.
     */
    // TODO: Rename and change types and number of parameters
    public static MembersList newInstance(String param1, String param2) {
        MembersList fragment = new MembersList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MembersList() {
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
        view =  inflater.inflate(R.layout.fragment_members_list, container, false);
//        ( (TextView)view.findViewById(R.id.friendText)).setText(Item);
//        imageView = (ImageView) view.findViewById(R.id.ic_back_friends);
        listView = (ListView) view.findViewById(R.id.memberList);
        done = (Button) view.findViewById(R.id.done);

        friendsListAdapter = new FriendsListAdapter(getActivity().getApplicationContext(),frndList,R.layout.add_mem_item,1);
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

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"wait..","",true,false);
                HashMap<String,Object> memData = new HashMap<String, Object>();
                        memData.put("mem-type",2);
                        memData.put("timestamp",System.currentTimeMillis());

                for(String a:FriendsListAdapter.memList){
                    Log.d("memList::",""+a);
                    FireBaseHandler.getInstance().getUserGroupRef().child(a).child(mParam1)
                            .setValue(memData);
                }
//                progressDialog.dismiss();
                memData.clear();

            }
        });

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
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
