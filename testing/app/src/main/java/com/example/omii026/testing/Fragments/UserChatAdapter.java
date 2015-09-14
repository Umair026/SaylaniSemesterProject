package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.ChatData;

import java.util.ArrayList;

/**
 * Created by Omii026 on 9/7/2015.
 */
public class UserChatAdapter extends BaseAdapter {

    private ArrayList<ChatData> obj;
    private Context context;
    private LayoutInflater inflater;
    public UserChatAdapter(Context context,ArrayList<ChatData> obj){
        this.context = context;
        this.obj = obj;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return obj.size();
    }

    @Override
    public String getItem(int i)
    {
        return obj.get(i).getMsg();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.group_chat_item,viewGroup,false);
        }
        ((TextView)view.findViewById(R.id.groupChatText)).setText(getItem(i));

        return view;
    }
}
