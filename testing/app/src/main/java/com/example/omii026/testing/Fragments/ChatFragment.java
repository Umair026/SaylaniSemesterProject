package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.example.omii026.testing.Services.ServiceError;
import com.example.omii026.testing.Services.ServiceListener;
import com.example.omii026.testing.Services.UserService;
import com.example.omii026.testing.SupportClasses.ChatData;
import com.example.omii026.testing.SupportClasses.UserData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private ImageView chat_sent;
    private TextView chat_text;
    private ListView listView;
    private UserChatAdapter userChatAdapter;
    private ArrayList<ChatData> textMessage = new ArrayList<>();
    private static final String TAG = "ChatFragment";


    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChatFragment() {
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
        view =  inflater.inflate(R.layout.fragment_chat, container, false);
        ((TextView)view.findViewById(R.id.title_chat)).setText(mParam2);

        chat_sent = (ImageView) view.findViewById(R.id.chat_sent);
        chat_text = (TextView) view.findViewById(R.id.chat_Text);
        listView = (ListView) view.findViewById(R.id.ChatList);

        //method 3



        //end method 3


            //for method 2
//        chat_sent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!(chat_text.getText().toString().equals(""))){
//                    String message = chat_text.getText().toString();
//                    String from = MeApp.getAppUser().getUserId();
//
//                    UserService.createChat(message,from,mParam2);

//                    String time = ""+System.currentTimeMillis();
//                    HashMap<String,Object> mesgMap = new HashMap<>();
//                    mesgMap.put("from",from);
//                    mesgMap.put("message",message);
//                    mesgMap.put("timestamp",time);
//
//
//                        Firebase ref = FireBaseHandler.getInstance().getUserChatRef().child(from).push();
//
//                    FireBaseHandler.getInstance().getConversationRef().
//                        HashMap<String, String> otherRef = new HashMap<String, String>();
//                        otherRef.put(mParam2, ref.toString());
//
//                        HashMap<String, String> userRef = new HashMap<String, String>();
//                        userRef.put(from, ref.toString());

//                        FireBaseHandler.getInstance().getUserChatRef().child(from).setValue(otherRef);
//                        FireBaseHandler.getInstance().getUserChatRef().child(mParam2).setValue(userRef);

//                    ChatData chatData = new ChatData(message,from,time);
//                }
//            }
//        });
        //*** method 2 end *******


                //for method 1
        chat_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(chat_text.getText().toString().equals(""))){

                    String msg = chat_text.getText().toString();
                    String from = MeApp.getAppUser().getUserId();
                    final HashMap<String,Object> textMap = new HashMap<String, Object>();
                    textMap.put("message", msg);
                    textMap.put("senderId", from);
                    textMap.put("timestamp", System.currentTimeMillis());

                    FireBaseHandler.getInstance().getUserChatRef().child(MeApp.getAppUser().getUserId())
                            .child(mParam2).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null){
                                Log.d("no null",""+dataSnapshot.getValue());
                                    HashMap<String,Object> dataSnap = (HashMap<String, Object>) dataSnapshot.getValue();
                                            String ref = (String) dataSnap.get(mParam2);
                                Firebase mRef = FireBaseHandler.getInstance().getConversationRef()
                                        .child(ref).push();
                                textMap.put("key", mRef.getKey());
                                Log.d("sent msg Key:",""+mRef.getKey());
                                mRef.setValue(textMap);
                                chat_text.setText("");
                              }else {
                                Log.d("null",""+dataSnapshot.getValue());

                                Firebase newRef = FireBaseHandler.getInstance().getConversationRef().push();

                                Firebase mRef = newRef.push();
                                textMap.put("key",mRef.getKey());
                                mRef.setValue(textMap);
//                                String Ref = newRef.toString();
//                                Ref = Ref.substring(Ref.length() - 20);
//
                                HashMap<String,String> chatOtherRef = new HashMap<String, String>();
                                chatOtherRef.put(mParam2, newRef.getKey());
                                FireBaseHandler.getInstance().getUserChatRef().child(MeApp.getAppUser().getUserId())
                                        .child(mParam2).setValue(chatOtherRef);

                                HashMap<String,String> chatUserRef = new HashMap<String, String>();
                                chatUserRef.put(MeApp.getAppUser().getUserId(), newRef.getKey());
                                FireBaseHandler.getInstance().getUserChatRef().child(mParam2)
                                        .child(MeApp.getAppUser().getUserId()).setValue(chatUserRef);
                                chat_text.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                }
            }
        });



        //data reteriving
        FireBaseHandler.getInstance().getUserChatRef().child(MeApp.getAppUser().getUserId()).child(mParam2)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null){

                            Log.d("ChatData","not Null");
                            HashMap<String, String> refData = (HashMap<String, String>) dataSnapshot.getValue();
                        String ref = refData.get(mParam2);
//                            ref = ref.substring(ref.length() - 20);
                        FireBaseHandler.getInstance().getConversationRef().child(ref)
                                .addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        HashMap<String, Object> chatData = (HashMap<String, Object>) dataSnapshot.getValue();
                                        String senderId = (String) chatData.get("senderId");
                                        Long timestamp = (Long) chatData.get("timestamp");
                                        String message = (String) chatData.get("message");
                                        String mkey = (String) chatData.get("key");
                                        ChatData chatDataObj = new ChatData(message, senderId, timestamp,mkey);
                                        textMessage.add(chatDataObj);
                                        userChatAdapter.notifyDataSetChanged();
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
                    }else{
                            Log.d("ChatData"," Null");

                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                    //** end of method 1 **
        userChatAdapter = new UserChatAdapter(getContext(),textMessage);
        listView.setAdapter(userChatAdapter);
        userChatAdapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               ChatData a =textMessage.remove(i);
                userChatAdapter.notifyDataSetChanged();
//                FireBaseHandler.getInstance().getConversationRef().child(a.getKey()).removeValue();


                return false;
            }
        });


        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



}
