package com.example.omii026.testing.Fragments;

import android.content.Context;
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
 * Created by Omii026 on 9/2/2015.
 */
public class FindFriendListAdapter extends ArrayAdapter<UserData> {
    private static ArrayList<UserData> list2 = new ArrayList<>();
    private  ArrayList<UserData> dataList;
    private  ArrayList<UserData> dataListBackUp;
    private LayoutInflater inflater;
    private Context context;
    private int mCurrentFilterLength;

    public FindFriendListAdapter (Context context,ArrayList<UserData> dataList){
        super(context,R.layout.user_list_item,dataList);

        this.context = context;
        this.dataList = dataList;
        this.dataListBackUp = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return dataList.size();

    }

    @Override
    public UserData getItem(int i) {
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

    public void add(UserData s){
        super.add(s);
        dataListBackUp.add(s);
    }

    public void filterFriends(String s){

        int filterLength = s.length();

        if(filterLength == 0 || filterLength < mCurrentFilterLength) {
            mCurrentFilterLength = filterLength;
            dataList.clear();
            dataList.addAll(dataListBackUp);
            if (filterLength == 0) {
                notifyDataSetChanged();
                return;
            }
        }

            int i=0;
            while (i < dataList.size()){
                if(!dataList.get(i).getUserId().toLowerCase().contains(s)){
                    dataList.remove(i);
                }else{
                    i++;
                }
            }
            mCurrentFilterLength = filterLength;
            notifyDataSetChanged();
    }


}
