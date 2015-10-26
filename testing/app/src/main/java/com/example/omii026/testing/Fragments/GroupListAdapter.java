package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.MeApp;
import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.GroupChatData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Omii026 on 10/10/2015.
 */
public class GroupListAdapter extends BaseAdapter {


    private final Activity activity;
    private ArrayList<GroupChatData> list;
    private LayoutInflater inflater;

    private AdapterInteface mListener;

    public GroupListAdapter(Activity activity, ArrayList<GroupChatData> list) {
        this.activity = activity;
        this.list = list;
        inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public GroupChatData getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.group_item,parent,false);
        }
        ((TextView) convertView.findViewById(R.id.groupName)).setText(getItem(position).getGroupName());
//        ((TextView) convertView.findViewById(R.id.groupDesc)).setText(getItem(position).getGroupDesc());
        ((TextView) convertView.findViewById(R.id.groupOwner)).setText("owner:"+getItem(position).getGroupOwner());

          final ImageView popup = (ImageView) convertView.findViewById(R.id.selector);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(activity.getApplicationContext(), popup);
                popupMenu.getMenuInflater().inflate(R.menu.group_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.addMem1:
                                Toast.makeText(activity.getApplicationContext(), "addMem", Toast.LENGTH_SHORT).show();

                                mListener = (AdapterInteface) activity;
                                mListener.addGroupMember(getItem(position).getGroupName());
                                break;

                            case R.id.remove:
                                Toast.makeText(activity.getApplicationContext(), "remove", Toast.LENGTH_SHORT).show();


                                FireBaseHandler.getInstance().getUserGroupRef().addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        if (dataSnapshot != null) {
                                            Log.d("UserGroupRef():", dataSnapshot + "");
                                            Log.d("UserGroupRef()/key:", dataSnapshot.getKey() + "");
                                            Log.d("UserGroupRef()/childern:", dataSnapshot.getChildren() + "");
                                            Log.d("UserGroupRef()/Value:", dataSnapshot.getValue() + "");
//                                            HashMap<String, Object> groupData = (HashMap<String, Object>) dataSnapshot.getValue();
//
//                                            if (groupData.equals(getItem(position).getGroupName())) {
//
//                                                FireBaseHandler.getInstance().getUserGroupRef().child(dataSnapshot.toString())
//                                                        .child(getItem(position).getGroupName()).removeValue();
//                                            }
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
//                                FireBaseHandler.getInstance().getGroupRef().child(getItem(position).getGroupName())
//                                        .removeValue();

                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();

            }
        });

        return convertView;
    }
    public interface AdapterInteface{
        void addGroupMember(String nam);
    }
}
