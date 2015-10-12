package com.example.omii026.testing.Fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.UserData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Omii026 on 9/10/2015.
 */
public class FriendsListAdapter extends BaseAdapter{

    private ArrayList<UserData> data = new ArrayList<>();
    private Context context;
    private int resource;
    private int call;
    private String grp_nam;
    private LayoutInflater inflater;
    public static ArrayList<String> memList = new ArrayList<>();
    private CheckBox checkBox;


    public FriendsListAdapter(Context context,ArrayList<UserData> data,int resource,int call){
        this.context = context;
        this.data = data;
        this.call = call;
        this.resource = resource;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(resource,viewGroup,false);
        }
        ((TextView) view.findViewById(R.id.name)).setText(data.get(i).getUserId());

        if(call == 1){
             checkBox = (CheckBox) view.findViewById(R.id.check);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()){

                        memList.add(data.get(i).getUserId());
                        Log.d("list",""+memList);

                    }else{
                        memList.remove(data.get(i).getUserId());
                        Log.d("list", "" + memList);

                    }

                }
            });

        }

        return view;
    }
}