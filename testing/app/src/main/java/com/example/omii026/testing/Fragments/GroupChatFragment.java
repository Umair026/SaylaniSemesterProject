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

import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.R;

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

    public GroupChatFragment(){

    }

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        args.putString("ARG_PARAM2", param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                    GroupChatAdapter.groupItemList.add(chat_text.getText().toString());
                    groupChatAdapter.notifyDataSetChanged();
                      chat_text.setText("");
                    listView.smoothScrollToPosition(GroupChatAdapter.groupItemList.size());
                } else {

                }
            }
        });


        groupChatAdapter = new GroupChatAdapter(getActivity().getApplicationContext());
        listView.setAdapter(groupChatAdapter);
        groupChatAdapter.notifyDataSetChanged();




        return view;
    }
}
