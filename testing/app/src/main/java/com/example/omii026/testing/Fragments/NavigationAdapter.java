package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.omii026.testing.SupportClasses.DrawerItem;
import com.example.omii026.testing.R;

import java.util.ArrayList;

/**
 * Created by Omii026 on 9/2/2015.
 */
public class NavigationAdapter extends BaseAdapter {
    public static ArrayList<String> list = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public NavigationAdapter (Context context){
    this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();

    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = inflater.inflate(R.layout.nav_item, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.navItemText)).setText(list.get(i));

            return view;
    }

    public static void add(DrawerItem Item){
        list.add(Item.getItem());
    }


}
