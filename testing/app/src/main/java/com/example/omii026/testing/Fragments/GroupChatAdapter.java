package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.ChatData;
import com.example.omii026.testing.SupportClasses.GroupChatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omii026 on 9/7/2015.
 */
public class GroupChatAdapter extends BaseAdapter {

    private Context context;
    public static ArrayList<String> groupItemList = new ArrayList<>();
    private LayoutInflater inflater;
    private EditText chat_text;
    private ImageView chat_sent;
    private ArrayList<ChatData> listData;

    public GroupChatAdapter(Context context,ArrayList<ChatData> listData){
        this.context = context;
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public ChatData getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.group_chat_item,viewGroup,false);
        }

        ((TextView) view.findViewById(R.id.groupChatText)).setText(getItem(i).getMsg());
        ((TextView) view.findViewById(R.id.senderName)).setText("owner:"+getItem(i).getSenderId());
        ((TextView) view.findViewById(R.id.groupChatTime)).setText(getItem(i).getTimestamp()+"");

        return view;
    }
}
