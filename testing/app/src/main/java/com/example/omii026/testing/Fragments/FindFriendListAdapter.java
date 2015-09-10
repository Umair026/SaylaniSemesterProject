package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.omii026.testing.R;

import java.util.ArrayList;

/**
 * Created by Omii026 on 9/2/2015.
 */
public class FindFriendListAdapter extends BaseAdapter {
    private static ArrayList<UserData> list2 = new ArrayList<>();
    private  ArrayList<UserData> dataList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public FindFriendListAdapter (Context context,ArrayList<UserData> dataList){
        this.context = context;
        this.dataList = dataList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return dataList.size();

    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = inflater.inflate(R.layout.user_list_item, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.name)).setText(dataList.get(i).getUserId());


        return view;
    }

    public static void add(UserData s){
        list2.add(s);
    }


}
