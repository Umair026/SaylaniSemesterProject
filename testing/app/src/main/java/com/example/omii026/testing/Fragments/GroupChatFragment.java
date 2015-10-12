package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.MeApp;
import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.ChatData;
import com.example.omii026.testing.SupportClasses.GroupChatData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Omii026 on 9/8/2015.
 */
public class GroupChatFragment extends Fragment {

    private View view;
    private ListView listView;
    private GroupChatAdapter groupChatAdapter;
    public static final String TAG = "GroupChatFragment";
    private EditText chat_text;
    private ImageView chat_sent;
    private String group_name;
    private Firebase newRef;
    private ArrayList<ChatData> listData = new ArrayList<>();

    public GroupChatFragment(){

    }

    public static GroupChatFragment newInstance(String param1) {
        GroupChatFragment fragment = new GroupChatFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
//        args.putString("ARG_PARAM2", param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            group_name = getArguments().getString("ARG_PARAM1");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_group_chat,container,false);
        Log.d(TAG, "onCreateView");

        listView = (ListView) view.findViewById(R.id.groupChatList);
        chat_text =(EditText) view.findViewById(R.id.chat_Text);
        chat_sent = (ImageView) view.findViewById(R.id.chat_sent);

        chat_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chat_text.getText().toString().equals("")) {

                    String msg = chat_text.getText().toString();
                    String from = MeApp.getAppUser().getUserId();
                    final HashMap<String, Object> msgData = new HashMap<String, Object>();
                    msgData.put("message", msg);
                    msgData.put("senderId", from);
                    msgData.put("timestamp", System.currentTimeMillis());

                    FireBaseHandler.getInstance().getGroupChatRef().child(group_name).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {

                                HashMap<String, Object> groupChat = (HashMap<String, Object>) dataSnapshot.getValue();
                                String chatKey = groupChat.get(group_name).toString();

                                Firebase ref = FireBaseHandler.getInstance().getConversationRef().child(chatKey).push();
                                msgData.put("key", ref.getKey());

                                ref.setValue(msgData);
                                chat_text.setText(" ");
                                listView.smoothScrollToPosition(groupChatAdapter.getCount());


                            } else {
                                newRef = FireBaseHandler.getInstance().getConversationRef().push();

                                Firebase mRef = newRef.push();
                                msgData.put("key", mRef.getKey());
                                mRef.setValue(msgData);
                                HashMap<String, Object> groupChatRef = new HashMap<String, Object>();
                                groupChatRef.put(group_name, newRef.getKey());

                                FireBaseHandler.getInstance().getGroupChatRef().child(group_name)
                                        .setValue(groupChatRef);
                                chat_text.setText(" ");
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });


                } else {
                }
            }
        });

        FireBaseHandler.getInstance().getGroupChatRef().child(group_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
//                    String chatKey = dataSnapshot.getKey().toString();
                    HashMap<String,Object> chatKey = (HashMap<String, Object>) dataSnapshot.getValue();
                    String key = chatKey.get(group_name).toString();
                    Log.d("listData:",""+key);

                    FireBaseHandler.getInstance().getConversationRef().child(key).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            if(dataSnapshot != null){

                                HashMap<String,Object> chatData = (HashMap<String, Object>) dataSnapshot.getValue();
                                String key = chatData.get("key").toString();
                                String msg = chatData.get("message").toString();
                                String from = chatData.get("senderId").toString();
                                Long timestamp = (Long) chatData.get("timestamp");

                                ChatData chatMsg = new ChatData(msg,from,timestamp,key);
                                listData.add(chatMsg);
                                listView.smoothScrollToPosition(groupChatAdapter.getCount());
                                groupChatAdapter.notifyDataSetChanged();
                                Log.d("listData:",""+chatMsg);
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

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        groupChatAdapter = new GroupChatAdapter(getActivity().getApplicationContext(),listData);
        listView.setAdapter(groupChatAdapter);
        groupChatAdapter.notifyDataSetChanged();




        return view;
    }
}
