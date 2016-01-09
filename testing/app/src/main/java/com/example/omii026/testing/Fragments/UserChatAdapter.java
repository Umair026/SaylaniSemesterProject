package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.omii026.testing.MeApp;
import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.ChatData;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        int thisUser  = R.layout.group_chat_item;
        int otherUser  = R.layout.group_chat_item2;

        Bitmap bitmap1 = MeApp.getAppUser().getProfileImage();
        Bitmap bitmap2;

        if(view == null){
            view = inflater.inflate(ChatData.isUser(obj.get(i).getSenderId()) ? thisUser : otherUser,viewGroup,false);
        }
        ((TextView)view.findViewById(R.id.groupChatText)).setText(getItem(i));
//        if(ChatData.isUser(obj.get(i).getSenderId()))
//          ((CircleImageView) view.findViewById(R.id.userImage)).setImageBitmap(bitmap1);


//        ((CircleImageView) view.findViewById(R.id.userImage)).setImageBitmap(ChatData.isUser(obj.get(i).getSenderId()) ? bitmap1 : );
        return view;
    }
}
