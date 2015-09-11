package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;

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


        userChatAdapter = new UserChatAdapter(getContext(),textMessage);
        listView.setAdapter(userChatAdapter);
        userChatAdapter.notifyDataSetChanged();

        chat_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(chat_text.getText().toString().equals(""))){
//                    textMessage.add(chat_text.getText().toString());
                    String msg = chat_text.getText().toString();
                    String key = MeApp.getAppUser().getUserId();
                    HashMap<String,Object> map = new HashMap<String, Object>();
                    map.put("message",msg);
                    map.put("uid",key);
                    FireBaseHandler.getInstance().getUserChatRef()
                            .child(MeApp.getAppUser().getUserId())
                            .child(mParam2).push().setValue(map, new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                            chat_text.setText("");
                        }
                    });

                    FireBaseHandler.getInstance().getUserChatRef().child(MeApp.getAppUser().getUserId()).child(mParam2)
                            .addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    HashMap<String,Object> chatMap = (HashMap<String, Object>) dataSnapshot.getValue();
                                if(chatMap != null){

                                    String msg = chatMap.get("message").toString();
                                    String senderId = chatMap.get("uid").toString();
                                    ChatData chatData = new ChatData(senderId,msg);
                                    if(!(textMessage.contains(chatData))) {
                                        textMessage.add(chatData);
                                        userChatAdapter.notifyDataSetChanged();
                                    }
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


                userChatAdapter.notifyDataSetChanged();
                }
            }
        });


        return view;
    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
////
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
