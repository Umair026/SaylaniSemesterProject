package com.example.omii026.testing.Fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.UserData;

import java.util.ArrayList;

/**
 * Created by Omii026 on 9/10/2015.
 */
public class FriendsListAdapter extends BaseAdapter{

    private ArrayList<UserData> data = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    public FriendsListAdapter(Context context,ArrayList<UserData> data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.user_list_item,viewGroup,false);
        }
        ((TextView) view.findViewById(R.id.name)).setText(data.get(i).getUserId());

        return view;
    }
}